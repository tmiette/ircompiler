package fr.umlv.IRCompiler;

import fr.umlv.IRCompiler.tatoo.tools.Import_Statement;
import fr.umlv.IRCompiler.tatoo.tools.Import_Statement_Star;
import fr.umlv.IRCompiler.tatoo.tools.Multiple_Package_Name;
import fr.umlv.IRCompiler.tatoo.tools.Single_Package_Name;
import fr.umlv.IRCompiler.tatoo.tools.Start;
import fr.umlv.IRCompiler.tatoo.tools.Visitor;

public class SemanticVisitor extends Visitor<String, Void, Void, Throwable> {

  private final SymbolTable<String> symbolsTable = new IRCompilerSymbolTable<String>();

  @Override
  public String visit(Start start, Void param) throws Throwable {
    start.getImport_statement_star().accept(this, param);
    start.getInstruction_star().accept(this, param);
    return null;
  }

  @Override
  public String visit(Import_Statement_Star import_statement_star, Void param)
      throws Throwable {
    for (Import_Statement statement : import_statement_star.nodeList()) {
      statement.accept(this, param);
    }
    return null;
  }

  @Override
  public String visit(Import_Statement import_statement, Void param)
      throws Throwable {
    import_statement.getPackage_name().accept(this, param);
    return null;
  }

  @Override
  public String visit(Single_Package_Name single_package_name, Void param)
      throws Throwable {
    return single_package_name.getIdentifier_() + ".";
  }

  @Override
  public String visit(Multiple_Package_Name multiple_package_name, Void param)
      throws Throwable {
    String s = multiple_package_name.getPackage_name().accept(this, param);
    System.err.println(s);
    validateImport(s + multiple_package_name.getIdentifier_());
    return null;
  }

  public void validateImport(String packageName)
      throws UnresolvedClassException {
    try {
      Class.forName(packageName);
    } catch (ClassNotFoundException e) {
      throw new UnresolvedClassException("Class " + packageName
          + " cannot be resolved.", e.getCause());
    }
  }

}
