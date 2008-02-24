





import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class TestDump implements Opcodes {

  public static void main(String[] args) throws Exception {
    OutputStream o = new FileOutputStream(new File("Test.class"));
    o.write(dump());
    o.close();
  }

  public static byte[] dump() throws Exception {

    ClassWriter cw = new ClassWriter(0);
    FieldVisitor fv;
    MethodVisitor mv;

    cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "Test", null, "java/lang/Object", null);

    {
    fv = cw.visitField(ACC_PUBLIC + ACC_STATIC, "p1", "LPoint;", null, null);
    fv.visitEnd();
    }
    {
    fv = cw.visitField(ACC_PUBLIC + ACC_STATIC, "x", "I", null, null);
    fv.visitEnd();
    }
    {
    fv = cw.visitField(ACC_PUBLIC + ACC_STATIC, "y", "I", null, null);
    fv.visitEnd();
    }
    {
    mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
    mv.visitCode();
    mv.visitVarInsn(ALOAD, 0);
    mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
    mv.visitInsn(RETURN);
    mv.visitMaxs(1, 1);
    mv.visitEnd();
    }
    {
    mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
    mv.visitCode();
    mv.visitTypeInsn(NEW, "Point");
    mv.visitInsn(DUP);
    mv.visitInsn(ICONST_0);
    mv.visitInsn(ICONST_0);
    mv.visitMethodInsn(INVOKESPECIAL, "Point", "<init>", "(II)V");
    mv.visitFieldInsn(PUTSTATIC, "Test", "p1", "LPoint;");
    mv.visitFieldInsn(GETSTATIC, "Test", "p1", "LPoint;");
    mv.visitMethodInsn(INVOKEVIRTUAL, "Point", "getX", "()I");
    mv.visitFieldInsn(PUTSTATIC, "Test", "x", "I");
    mv.visitFieldInsn(GETSTATIC, "Test", "p1", "LPoint;");
    mv.visitInsn(ICONST_0);
    mv.visitInsn(ICONST_0);
    mv.visitInsn(ICONST_0);
    mv.visitMethodInsn(INVOKEVIRTUAL, "Point", "getS", "(III)I");
    mv.visitFieldInsn(PUTSTATIC, "Test", "y", "I");
    mv.visitInsn(RETURN);
    mv.visitMaxs(4, 1);
    mv.visitEnd();
    }
    cw.visitEnd();

    return cw.toByteArray();
  }
}
