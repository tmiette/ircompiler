package fr.umlv.tatoo.samples.httpserver;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import fr.umlv.tatoo.samples.httpserver.tools.TerminalEvaluator;

public class HTTPTerminalEvaluator implements TerminalEvaluator<ByteBuffer>{ 
  public Service get(ByteBuffer data) {
    return Service.GET;
  }
  public Service post(ByteBuffer data) {
    return Service.POST;
  }
  
  public String url(ByteBuffer data) {
    return extractString(data);
  }
  
  public int version(ByteBuffer data) {
    return (data.get()&0xFF)-'0';
  }
  
  public String header_key(ByteBuffer data) {
    return extractString(data);
  }
  public String header_value(ByteBuffer data) {
    return extractString(data);
  }
  
  private static String extractString(ByteBuffer data) {
    byte[] array=new byte[data.remaining()];
    data.get(array);
    // FIXME Remi this constructor was undefined according to eclipse (I guess in 1.5).
    // replaced by new String(array,charset);
    try {
      return new String(array,charset.name());
    } catch(UnsupportedEncodingException e) {
      throw new AssertionError(e);
    }
  }
  
  private static final Charset charset=Charset.forName("ISO-8859-1");
}
