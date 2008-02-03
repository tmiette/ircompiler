import java.util.Date;

public class Test {

private static final String DATE = new Date().toString();
private static String date() {
  return DATE;
}
/* end of headings */

public static void __input(java.io.PrintWriter out,java.util.Map<String,?> map) {
  String name = (String)map.get("name");
out.print("\r\n\r\n");
for(int i=0;i<10;i++) {
out.print("Bonjour ");
out.print(name+i);
out.print(". Il est ");
out.print(date());
out.print(".\r\n");
}}
}
