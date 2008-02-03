package fr.umlv.tatoo.samples.httpserver;

import fr.umlv.tatoo.samples.httpserver.HTTPServer.ReadHandler;
import fr.umlv.tatoo.samples.httpserver.tools.GrammarEvaluator;

public class HTTPGrammarEvaluator implements GrammarEvaluator {
  public void inject(ReadHandler handler) {
    this.handler=handler;
  }
  
  public void acceptStart() {
    // do nothing
  }
  
  public void start() {
    // do nothing
  }

  public void firstline(Service service,String url,int version,int version2) {
    if (service==Service.GET)
      handler.service(url);
    //System.out.println(service+" "+url+" HTTP/"+version+'.'+version2);
  }
  
  public Service service_get(Service get) {
    return get;
  }

  public Service service_post(Service post) {
    return post;
  }

  public void header() {
    // do nothing
  }
  
  public void header_part(String header_key,String header_value) {
    //System.out.println("header "+header_key+": "+header_value);
  }
  private ReadHandler handler;
}
