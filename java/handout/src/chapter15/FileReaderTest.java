package chapter15;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {
    public static void main(String[] args) throws IOException {
        try (FileReader fr = new FileReader("./src/chapter15/FileReaderTest.java")) {
            char[] cbuf = new char[1024];
            int hasRead = 0;
            while ((hasRead = fr.read(cbuf)) > 0) {
                System.out.println(new String(cbuf, 0, hasRead));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
