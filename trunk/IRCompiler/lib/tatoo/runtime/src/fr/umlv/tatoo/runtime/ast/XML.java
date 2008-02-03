package fr.umlv.tatoo.runtime.ast;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Result;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

import fr.umlv.tatoo.runtime.util.DefaultStringConverter;
import fr.umlv.tatoo.runtime.util.StringConverter;

/** This class contains static methods usefull for serialize/deserialize
 *  an AST.
 *  
 * @author Remi
 */
public class XML {
  private XML() {
    // can't be instantiated
  }
  
  /** Writes the node and all it's subnode in XML on the writer.
   * @param writer the writer used to write the XML tree.
   * @param node the root of the AST.
   * @throws IOException raised on a error of the underlying writer.
   * 
   * @see #createSAXSource(Node)
   * @see #transform(Node,Result)
   */
  public static void serialize(Writer writer,Node node) throws IOException {
    try {
      transform(node,new StreamResult(writer));
    } catch (TransformerConfigurationException e) {
      throw (IOException)new IOException().initCause(e);
    } catch (TransformerException e) {
      Throwable cause=e.getCause();
      if (cause instanceof IOException)
        throw (IOException)cause;
      
      if (cause==null)
        cause=e;
      throw (IOException)new IOException().initCause(cause);
    }
  }
  
  /** Returns a SAXSource that can be using as an input of
   *  a transform operation on an XML Transformer.
   * @param node root of the AST.
   * @return a SAXSource.
   * 
   * @see #transform(Node, Result)
   * @see javax.xml.transform.Transformer#transform(javax.xml.transform.Source, Result)
   */
  public static SAXSource createSAXSource(final Node node) {  
    XMLReader reader=new FakeXMLReader() {
      @Override protected void parse() throws SAXException {
        new XMLGenerator().generateDocument(getContentHandler(),node);
      }
    };
    return new SAXSource(reader,new InputSource()); 
  }
  
  //FIXME Remi need to be namespace aware
  static abstract class FakeXMLReader implements XMLReader {
    public ContentHandler getContentHandler() {
      return contentHandler;
    }
    public void setContentHandler(ContentHandler handler) {
      this.contentHandler=handler;
    }
    public DTDHandler getDTDHandler() {
      return dtdHandler;
    }
    public void setDTDHandler(DTDHandler handler) {
      this.dtdHandler=handler;
    }
    public EntityResolver getEntityResolver() {
      return entityResolver;
    }
    public void setEntityResolver(EntityResolver resolver) {
      this.entityResolver=resolver;
    }
    public ErrorHandler getErrorHandler() {
      return errorHandler;
    }
    public void setErrorHandler(ErrorHandler handler) {
      this.errorHandler=handler;
    }
    public boolean getFeature(String name) {
      return features.contains(name);
    }
    public void setFeature(String name, boolean value) {
      if (value)
        features.add(name);
      else
        features.remove(name);
    }
    public Object getProperty(String name) {
      return properties.get(name);
    }
    public void setProperty(String name, Object value) {
      properties.put(name,value);
    }
    public void parse(InputSource input) throws SAXException {
      parse();
    }
    public void parse(String systemId) throws SAXException {
      parse();
    }
    
    /** Abstract how the document is obtained.
     * @throws SAXException if the XML document is ill formed or
     *  if an exception occured when data of the document are read.
     */
    protected abstract void parse() throws SAXException;
    
    private ContentHandler contentHandler;
    private DTDHandler dtdHandler;
    private EntityResolver entityResolver;
    private ErrorHandler errorHandler;
    private final HashSet<String> features=
      new HashSet<String>();
    private final HashMap<String,Object> properties=
      new HashMap<String,Object>();
  }
  
  /** Apply an identity transformation on the AST views as an XML tree.
   *  This method permits by example to transform an AST to a DOM :
   *  <pre>
   *   fr.umlv.tatoo.runtime.ast.Node astToot=...
   *   DOMResult result=new DOMResult();
   *   XML.transform(root,result);
   *   org.w3c.dom.Node domRoot=result.getNode();
   *  </pre>
   *  
   * @param node the root of the AST.
   * @param result a transformation result.
   * @throws TransformerConfigurationException if no transformer available.
   * @throws TransformerException if the transformer issue an error.
   * 
   * @see #createSAXSource(Node)
   */
  public static void transform(Node node,Result result) throws TransformerConfigurationException, TransformerException {
    if (result instanceof SAXResult) {
      try {
        new XMLGenerator().generateDocument(((SAXResult)result).getHandler(),node);
      } catch(SAXException e) {
        throw new TransformerException(e);
      }
    } else {
      TransformerFactory factory = TransformerFactory.newInstance();
      factory.newTransformer().transform(createSAXSource(node),result);
    }
  }

  static class XMLGenerator {
    void generateDocument(ContentHandler handler,Node node) throws SAXException {
      handler.startDocument();
      generateNodes(handler,node);
      handler.endDocument();
    }
    private void generateNodes(ContentHandler handler,Node node) throws SAXException {
      startElement(handler,node);
      for(Node n:node.nodeList())
        generateNodes(handler,n);
      
      endElement(handler,node);
    }

    private void startElement(ContentHandler handler,Node node) throws SAXException {
      impl.clear();
      
      for(Map.Entry<String,?> attributeEntry:node.attributeMap().entrySet()) {
        String name=attributeEntry.getKey();
        impl.addAttribute(null,name,name,"",attributeEntry.getValue().toString());
      }
      String name=node.getName();
      handler.startElement(null,name,name,impl);
    }
    private void endElement(ContentHandler handler,Node node) throws SAXException {
      String name=node.getName();
      handler.endElement(null,name,name);
    }
    private final AttributesImpl impl=new AttributesImpl();
  }
  
  /** De-serialize an XML reader to an AST.  
   * @param reader the reader containing XML.
   * @param builder the builder used to create AST node.
   * @return the root of the AST.
   * @throws IOException if an error occured on the underlying reader.
   * 
   * @see #unserialize(Reader,XMLNodeFactory,StringConverter)
   */
  public static Node unserialize(Reader reader,final XMLNodeFactory builder) throws IOException {
    return unserialize(reader,builder,new DefaultStringConverter());
  }
  
  /** De-serialize an XML reader to an AST.
   * @param reader the reader containing XML.
   * @param builder the builder used to create AST node.
   * @param converter used to transforme XML attribute avlue to AST attibute object. 
   * 
   * @return the root of the AST.
   * @throws IOException if an error occured on the underlying reader.
   * 
   * @see #unserialize(Reader,XMLNodeFactory,StringConverter)
   */
  public static Node unserialize(Reader reader,final XMLNodeFactory builder,StringConverter converter) throws IOException {
    SAXParserFactory factory=SAXParserFactory.newInstance();
    factory.setNamespaceAware(true);
    try {
      SAXParser parser = factory.newSAXParser();
      NodeHandler handler=new NodeHandler(builder,converter);
      parser.parse(new InputSource(reader), handler);
      return handler.root;
    } catch (ParserConfigurationException e) {
      throw (IOException)new IOException().initCause(e);
    } catch (SAXException e) {
      Throwable cause=e.getCause();
      if (cause instanceof IOException)
        throw (IOException)cause;
      
      if (cause==null)
        cause=e;
      throw (IOException)new IOException().initCause(cause);
    }
  }
  
  private static class NodeHandler extends DefaultHandler {
    NodeHandler(XMLNodeFactory builder,StringConverter converter) {
      this.builder = builder;
      this.converter=converter;
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes xmlAttrs) throws SAXException {
      // cast is needed because XML serialisation need AbstractNode support
      // but AbstractNode is package visible
      AbstractNode<?,?,Object> node=(AbstractNode<?,?,Object>)builder.createEmptyNode(localName);
      if (node==null)
        throw new SAXException("invalid element name "+localName);
      
      for(int i=0;i<xmlAttrs.getLength();i++) {
        String name=xmlAttrs.getLocalName(i);
        int index=node.indexOfAttributeName(name);
        
        node.attributeAt(index,
          converter.convert(xmlAttrs.getValue(i),
            node.attributeTypeAt(index)));
      }
      
      stack.add(StackEntry.create(node));
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
      StackEntry<?> entry=stack.remove(stack.size()-1);
      if (stack.isEmpty()) {
        root=entry.node;
      }
      else {
        StackEntry<?> parent=stack.get(stack.size()-1);
        parent.add(entry);
      }
    }

    AbstractNode<?,?,?> root;
    private final XMLNodeFactory builder;
    private final StringConverter converter;
    private final ArrayList<StackEntry<?>> stack=new ArrayList<StackEntry<?>>();    
  }
  
  private static class StackEntry<N extends Node> {
    private StackEntry(AbstractNode<N,?,?> node) {
      this.node=node;
    }
    
    void add(StackEntry<?> childEntry) {
      N child;
      try {
        child=node.getElementType().cast(childEntry.node);
      } catch(ClassCastException e) {
        throw new ClassCastException(node.getElementType()+" "+childEntry.node);
      }
      node.nodeAt(offset++,child);
    }
    
    private int offset;
    final AbstractNode<N,?,?> node;
    
    // just used to capture
    static <N extends Node> StackEntry<N> create(AbstractNode<N,?,?> node) {
      return new StackEntry<N>(node);
    }
  }
}
