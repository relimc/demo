package chapter15;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterTest {
    public static void main(String[] args) {
        try (FileWriter fw = new FileWriter("./src/chapter15/poem.txt")) {
            fw.write("111\n");
            fw.write("222\n");
            fw.write("333\n");
            fw.write("444\n");
            fw.write("555\n");
            fw.write("666");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
