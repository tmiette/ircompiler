package fr.umlv.tatoo.samples.httpserver;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.Set;

import fr.umlv.tatoo.runtime.buffer.impl.LocationTracker;
import fr.umlv.tatoo.runtime.buffer.impl.ReadableByteChannelWrapper;
import fr.umlv.tatoo.runtime.lexer.Lexer;
import fr.umlv.tatoo.samples.httpserver.tools.AnalyzerBuilder;
import fr.umlv.tatoo.samples.httpserver.tools.TerminalEvaluator;

public class HTTPServer {
  public HTTPServer(int capacity,Selector writeSelector) {
    handlers=initReadHandlers(capacity);
    writeHandlers=initWriteHandlers(capacity);
    this.writeSelector=writeSelector;
  }
  
  //FIXME use ArrayDeque here (1.6)
  private LinkedList<ReadHandler> initReadHandlers(int capacity) {
    LinkedList<ReadHandler> queue=new LinkedList<ReadHandler>();
    for(int i=0;i<capacity;i++)
      queue.add(new ReadHandler());
    return queue;
  }
  private LinkedList<WriteHandler> initWriteHandlers(int capacity) {
    LinkedList<WriteHandler> queue=new LinkedList<WriteHandler>();
    for(int i=0;i<capacity;i++)
      queue.add(new WriteHandler());
    return queue;
  }
  
  static abstract class Handler {
    abstract void performs() throws IOException;
  }
  
  public void addAcceptHandler(Selector selector,InetSocketAddress address) throws IOException {
    ServerSocketChannel serverChannel=ServerSocketChannel.open();
    serverChannel.configureBlocking(false);
    serverChannel.socket().bind(address);
    
    AcceptHandler handler=new AcceptHandler(selector,serverChannel);
    serverChannel.register(selector,SelectionKey.OP_ACCEPT,handler);
  }
  
  class AcceptHandler extends Handler {
    public AcceptHandler(Selector selector,ServerSocketChannel serverChannel) {
      this.selector=selector;
      this.serverChannel=serverChannel;
    }
    @Override
    void performs() throws IOException {
      SocketChannel socketChannel=serverChannel.accept();
      socketChannel.configureBlocking(false);
      ReadHandler handler=handlers.poll();
      if (handler==null) {
        // schedule for an internal error
        socketChannel.close();
        return;
      }
      SelectionKey key=socketChannel.register(selector,SelectionKey.OP_READ,handler);
      handler.inject(key,socketChannel);
    }
    private final Selector selector; 
    private final ServerSocketChannel serverChannel;
  }
  
  class ReadHandler extends Handler {
    public ReadHandler() {
      grammarEvaluator=new HTTPGrammarEvaluator();
      TerminalEvaluator<ByteBuffer> attributeEvaluator=new HTTPTerminalEvaluator();
        //Debug.createTraceProxy(TerminalAttributeEvaluator.class,new HTTPTerminalEvaluator());
      
      //LocationTracker tracker=new LocationTracker();
      
      ReadableByteChannelWrapper wrapper=new ReadableByteChannelWrapper(true,8192,2048,2048,null,new LocationTracker());
      
      AnalyzerBuilder<ReadableByteChannelWrapper> analyzerBuilder=
        AnalyzerBuilder.createTokenBufferAnalyzer(wrapper,attributeEvaluator,grammarEvaluator);
      
      //analyzerBuilder.setReporter(new LocationErrorReporter<NewByteBufferWrapper>());
      
      lexer=analyzerBuilder.createLexer();
      this.wrapper=wrapper;
    }
    
    void inject(SelectionKey key,SocketChannel socketChannel) {
      this.key=key;
      this.socketChannel=socketChannel;
      wrapper.restart(socketChannel);
      grammarEvaluator.inject(this);
    }
    
    // called by the parser
    void service(String url) {
      try {
        WriteHandler handler=writeHandlers.poll();
        if (handler==null) {
          // schedule for an internal error
          //FIXME Remi need more test
          dispose(true);
          return;
        }

        socketChannel.register(writeSelector,SelectionKey.OP_READ,handler);
        handler.inject(socketChannel,url);
        writeSelector.wakeup();
      } catch(IOException e) {
        // resources are disposed by performs
        throw new RuntimeException(e);
      }
    }
    
    @Override
    void performs() throws IOException {
      if (!wrapper.read()) { 
        System.out.println("close");
        
        try {
          lexer.close();
          dispose(false);
        } catch(Throwable t) {
          t.printStackTrace();
          dispose(true);
        } 
        return;
      }
      
      try {
        lexer.step();
      } catch(Throwable t) {
        t.printStackTrace();
        dispose(true);
      }
    }
    
    private void dispose(boolean onError) throws IOException {
      key.attach(null);
      key.cancel(); 
      key=null;
      
      if (onError)
        socketChannel.close();
      socketChannel=null;
      
      wrapper.restart(null);
      lexer.reset(wrapper);
      
      // recycle instance
      handlers.offer(this);
    }
    
    private SocketChannel socketChannel;
    private SelectionKey key;
    private final HTTPGrammarEvaluator grammarEvaluator;
    private final ReadableByteChannelWrapper wrapper;
    private final Lexer<ReadableByteChannelWrapper> lexer;
  }
  
  class WriteHandler extends Handler {
    void inject(SocketChannel socketChannel,String url) throws IOException {
      File file=new File("doc-root",url);
      FileChannel fileChannel=new RandomAccessFile(file,"r").getChannel();
      buffers[0]=fileChannel.map(FileChannel.MapMode.READ_ONLY,0,file.length());
      
      socketChannel.register(writeSelector,SelectionKey.OP_WRITE,this);
      writeSelector.wakeup();
    }
    
    @Override
    void performs() throws IOException {
      socketChannel.write(buffers);
      if (!buffers[1].hasRemaining()) {
        dispose();
      }
    }
    
    private void dispose() throws IOException {
      key.attach(null);
      key.cancel();
      key=null;
      socketChannel.close();
      socketChannel=null;
      
      buffers[1] = null;
      fileChannel.close();
      fileChannel=null;
      
      writeHandlers.offer(this);
    }
    
    private final ByteBuffer[] buffers;
    private final ByteBuffer response=RESPONSE_BUFFER.duplicate();
    {
      buffers = new ByteBuffer[]{response,null};
    }
    private FileChannel fileChannel;
    private SelectionKey key;
    private SocketChannel socketChannel;
  }
  
  public static void select(Selector selector) throws IOException {
    Set<SelectionKey> selectedSet=selector.selectedKeys();
    for(;;) {
      selector.select();
      for(SelectionKey key:selectedSet) {
        ((Handler)key.attachment()).performs();
      }
      selectedSet.clear();
    }
  }
  
  final Selector writeSelector;
  final LinkedList<ReadHandler> handlers;
  final LinkedList<WriteHandler> writeHandlers;
  
  static final ByteBuffer RESPONSE_BUFFER;
  static {
    byte[] bytes;
    try {
      bytes="HTTP/1.1 OK 200\r\n\r\n".
        getBytes("ISO-8859-1");
    } catch(UnsupportedEncodingException e) {
      throw new AssertionError(e);
    }
    ByteBuffer buffer=ByteBuffer.allocateDirect(bytes.length);
    buffer.put(bytes);
    buffer.position(0);
    RESPONSE_BUFFER=buffer.asReadOnlyBuffer();
  }
  
  public static void main(String[] args) throws IOException {
    Selector acceptReadSelector=Selector.open();
    Selector writeSelector=Selector.open();
    
    HTTPServer server=new HTTPServer(1024,writeSelector);
    server.addAcceptHandler(acceptReadSelector,new InetSocketAddress(8080));
    select(acceptReadSelector);
  }
}
