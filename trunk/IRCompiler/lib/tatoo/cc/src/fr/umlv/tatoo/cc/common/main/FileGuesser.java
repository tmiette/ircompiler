package fr.umlv.tatoo.cc.common.main;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//FIXME Remi Unit.ebnf/linker has no sense in this package
public class FileGuesser {
  private FileGuesser() {}
  
  private static class UnitException extends RuntimeException {
    private final Unit unit;

    public UnitException(Unit unit) {
      this.unit = unit;
    }
    
    public Unit getUnit() {
      return unit;
    }
  }
  private static final Unit[] VALUES = Unit.values();
  private static final UnitException[] EXCEPTIONS;
  static {
    UnitException[] exceptions =  new UnitException[VALUES.length+1];
    for(int i=0;i<VALUES.length;i++)
      exceptions[i]=new UnitException(VALUES[i]);
    exceptions[VALUES.length] = new UnitException(null);
    EXCEPTIONS = exceptions;
  }
  
  private static final SAXParserFactory FACTORY;
  static {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setNamespaceAware(true);
    FACTORY=factory;
  }
  
  
  public static Unit guessUnit(File file) throws IOException {
    if (file.getName().endsWith(".ebnf"))
      return Unit.ebnf;
    try {
    SAXParser parser = FACTORY.newSAXParser();
    parser.parse(file, new DefaultHandler() {
    
      @Override
      public void startElement(String uri, String localName, String name,
          Attributes attributes) throws SAXException {
        Unit ans = Unit.parse(localName);
        if (ans==null)
          throw EXCEPTIONS[VALUES.length];
        throw EXCEPTIONS[ans.ordinal()];
      }

      
    });
    }
    catch (UnitException u) {
      return u.getUnit();
    }
    catch (ParserConfigurationException e) {
      throw new AssertionError(e);
    }
    catch (SAXException e) {
      return null;
    }
    return null; /* empty file */
  }
  
  public static void main(String[] args) {
    if (args.length==0) {
      System.err.println("no input files");
      return;
    }
    for (String s : args) {
      if (args.length!=1)
        System.out.print(s+": ");
      try {
        System.out.println(guessUnit(new File(s)));
      }
      catch (IOException e) {
        System.out.println("failed: "+e.getClass().getSimpleName()+": "+e.getMessage());
      }
    }
  }
}
