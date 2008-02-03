package fr.umlv.tatoo.cc.common.main;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import fr.umlv.tatoo.cc.Tatoo;
import fr.umlv.tatoo.cc.common.extension.ExtensionBus;
import fr.umlv.tatoo.cc.common.log.Level;
import fr.umlv.tatoo.cc.common.main.GeneratorBean.GenerateOption;


public class GeneratorOption {
  private GeneratorOption() {
    // don't instantiable
  }
  
  public static Command<GeneratorBean> packaze(final String... tools) {
    return new Command<GeneratorBean>() {
      public void execute(String option,GeneratorBean bean, List<? extends String> args) {
        String packaze=args.get(0);
        int index=option.indexOf(':');
        if (index!=-1)
          bean.setPackage(Unit.parse(option.substring(index+1)),packaze);
        else
          bean.setAllPackages(packaze);
      }
      public String usage(String option, int numberOfArgument) {
        int index=option.indexOf(':');
        if (index!=-1)
          return "package name for "+option.substring(index+1)+" generated classes";    
        return "package name for all generated classes";
      }
      public String usageArgumentName(String option, int numberOfArgument, int index) {
        return "name of the package";
      }
      public void register(OptionRegistry< ? extends GeneratorBean> registry) {
        registry.registerOption("package",this,1);
        registry.registerOption("-","p",this,1);
        for(String tool:tools) {
          registry.registerOption("package:"+tool,this,1);
          registry.registerOption("-","p:"+tool,this,1);
        }
      }
    };
  }
    
  public static final AbstractSimpleCommand<GeneratorBean> destination=
    new AbstractSimpleCommand<GeneratorBean>("destination") {
    public void execute(String option,GeneratorBean bean, List<? extends String> args) {
      bean.setDestination(new File(args.get(0)));
    }
    public String usage(String optionName, int numberOfArgument) {
      return "folder where Java files will be generated";
    }
    public String usageArgumentName(String optionName, int numberOfArgument, int index) {
      return "destination folder";
    }
  };
  
  public static final AbstractSimpleCommand<GeneratorBean> validating=
    new AbstractSimpleCommand<GeneratorBean>("validating") {
    public void execute(String option,GeneratorBean bean, List<? extends String> args) {
      bean.setValidating(Boolean.valueOf(args.get(0)));
    }
    public String usage(String optionName, int numberOfArgument) {
      return "tells whenever validating xml or not (true by default)";
    }
    public String usageArgumentName(String optionName, int numberOfArgument, int index) {
      return "true|false";
    }
  };
  
  public static final Command<GeneratorBean> logLevel=
    new Command<GeneratorBean>() {
    public void execute(String option,GeneratorBean bean, List<? extends String> args) {
      bean.setLogLevel(Level.parse(args.get(0)));
    }
    public String usage(String optionName, int numberOfArgument) {
      return "set the logger verbosity level";
    }
    public String usageArgumentName(String optionName, int numberOfArgument, int index) {
      return "[FATAL_ERROR, ERROR, WARNING, INFO, FINE or ALL]";
    }
    public void register(OptionRegistry<? extends GeneratorBean> registry) {
      registry.registerOption("logLevel",this,1);
      registry.registerOption("-","ll",this,1);
    }
  };
  
  public static final Command<GeneratorBean> version= 
    new Command<GeneratorBean>() {
      public void execute(String optionName, GeneratorBean userData, List<? extends String> optionArguments) {
        System.err.println(Tatoo.version());
        System.exit(1);
      }
      public void register(OptionRegistry<? extends GeneratorBean> registry) {
        registry.registerOption("version",this,0);
      }
      public String usage(String optionName, int numberOfArgument) {
        return "prints the current version";
      }
      public String usageArgumentName(String optionName, int numberOfArgument, int index) {
        throw new AssertionError("no argument");
      }
    };
  
  public static final Command<GeneratorBean> check= 
    new Command<GeneratorBean>() {
      public void execute(String optionName, GeneratorBean userData, List<? extends String> optionArguments) {
        try {
          // check velocity class
          Class<?> velocityClass=Class.forName("org.apache.velocity.app.Velocity");
          
          // check 1.4 version
          velocityClass.getMethod("resourceExists",String.class);
          
          System.err.println("velocity dependency ok");
        } catch (Exception e) {
          System.err.println("Tatoo requires Apache Velocity 1.4 Jar (lib/velocity/velocity-dep-1.4.jar)");
          e.printStackTrace();
          System.exit(1);
        }
        
        System.err.println(Tatoo.version());
      }
      public void register(OptionRegistry<? extends GeneratorBean> registry) {
        registry.registerOption("check",this,0);
      }
      public String usage(String optionName, int numberOfArgument) {
        return "checks the installation and prints the current version";
      }
      public String usageArgumentName(String optionName, int numberOfArgument, int index) {
        throw new AssertionError("no argument");
      }
    };
 
  public static final AbstractSimpleCommand<GeneratorBean> extension=
    new AbstractSimpleCommand<GeneratorBean>("extension") {
    public void execute(String option,GeneratorBean bean, List<? extends String> args) {
      String[] extensionClassNames=args.get(0).split(",");
      ExtensionBus bus = bean.getExtensionBus();
      for(String extensionClassName:extensionClassNames)
        bus.loadAndRegisterExtension(extensionClassName);
    }
    public String usage(String optionName, int numberOfArgument) {
      return "add extensions using their fully qualified class names";
    }
    public String usageArgumentName(String optionName, int numberOfArgument, int index) {
      return "fr.umlv.tatoo.ExtensionClassName1,fr.umlv.tatoo.ExtensionClassName1 etc.";
    }
  };
  
  /** define the class name of all generated enums.
   * @param aliases aliases that could be changed
   * @return an option that parse alias options
   */
  public static AbstractSimpleCommand<GeneratorBean> generate(final AliasPrototype... aliases) {
    final HashMap<String,AliasPrototype> aliasMap=new HashMap<String,AliasPrototype>();
    for(AliasPrototype alias:aliases)
      aliasMap.put(alias.name().toLowerCase(),alias);
      
    return new AbstractSimpleCommand<GeneratorBean>("generate") {
      public void execute(String option,GeneratorBean bean,List<? extends String> args) {
        bean.setGenerateDefault(false);
        
        String[] aliasNames=args.get(0).split(",");
        for(String aliasName:aliasNames) {
          AliasPrototype alias=aliasMap.get(aliasName.toLowerCase());
          if (alias==null)
            throw new IllegalArgumentException("option generate, unkown alias file "+aliasName);
          
          bean.setGenerate(alias,GenerateOption.TRUE);
        }
      }
      public String usage(String optionName,int numberOfArguments) {
        return "indicates which files must be generated";
      }
      public String usageArgumentName(String option, int numberOfArgument, int index) {
        StringBuilder builder=new StringBuilder().append('[');
        for(AliasPrototype alias:aliases)
          builder.append(alias.name()).append(',');
        builder.setLength(builder.length()-1);
        return builder.append(']').toString();
      }
    };
  }
  
  /** define the class name of all generated files.
   * @param aliases aliases that could be changed
   * @return an option that parse alias options
   */
  public static AbstractSimpleCommand<GeneratorBean> name(final AliasPrototype... aliases) {
    final CommandLineParser<GeneratorBean> clp=
      new CommandLineParser<GeneratorBean>(
        /*(Command<? super GeneratorBean>[])*/
          createCommandsFromAliases(aliases));
    
    return new AbstractSimpleCommand<GeneratorBean>("name") {
      public void execute(String option,GeneratorBean bean, List<? extends String> args) throws IllegalCommandLineArgumentException {
        String[] arguments=args.get(0).split(",|=");
        clp.parse(bean,arguments);
      }
      public String usage(String optionName, int numberOfArguments) {
        return "change the name of generated class of terminals,"+
        " non-terminals, productions etc.";
      }
      public String usageArgumentName(String option, int numberOfArgument, int index) {
        StringBuilder builder=new StringBuilder().append('[');
        for(AliasPrototype alias:aliases)
          builder.append(alias.name()).append('=').append("name").append(',');
        builder.setLength(builder.length()-1);
        return builder.append(']').toString();
      }
    };
  }
  
  private static Command<GeneratorBean>[] createCommandsFromAliases(
    AliasPrototype... aliases) {
    
    @SuppressWarnings("unchecked")
    Command<GeneratorBean>[] commands=
      (Command<GeneratorBean>[])
      new Command<?>[aliases.length];
    
    for(int i=0;i<aliases.length;i++) {
      final AliasPrototype alias=aliases[i];
      commands[i]=new Command<GeneratorBean>() {
        public String usage(String optionName, int numberOfArgument) {
          throw new AssertionError("not implemented");
        }
        public String usageArgumentName(String optionName, int numberOfArgument, int index) {
          throw new AssertionError("not implemented");
        }
        public void register(OptionRegistry<? extends GeneratorBean> registry) {
          registry.registerOption(alias.name(),this,1);
        }
        public void execute(String optionName, GeneratorBean bean, List<? extends String> optionArguments) {
          bean.setTypeName(alias,optionArguments.get(0));
        }
      };
    }
    return commands;
  }
}
