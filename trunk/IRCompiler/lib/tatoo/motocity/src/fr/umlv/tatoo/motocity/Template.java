package fr.umlv.tatoo.motocity;

import java.io.PrintWriter;
import java.util.Map;

public interface Template {
   void call(PrintWriter out, Map<String, ?> params);
}
