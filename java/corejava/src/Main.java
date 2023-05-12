import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Paths.get("E:\\Code\\mine\\demo\\java\\corejava"), new SimpleFileVisitor<>() {
            // �����ļ�ʱ�����������
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("���ڷ��� " + file + " �ļ�");
                if (file.endsWith("Main.java")) {
                    System.out.println("�Ѿ��ҵ�Ŀ���ļ�");
                    return FileVisitResult.TERMINATE;
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("���ڷ��ʣ�" + dir + " ·��");
                return FileVisitResult.CONTINUE;
            }
        });
    }
}







