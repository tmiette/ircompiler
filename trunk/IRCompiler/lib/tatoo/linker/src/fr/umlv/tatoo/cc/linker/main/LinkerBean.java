package fr.umlv.tatoo.cc.linker.main;

import java.io.File;

import fr.umlv.tatoo.cc.common.main.GeneratorBean;

public class LinkerBean extends GeneratorBean {
  public LinkerBean() {
    setDestination(new File("."));
  }
}