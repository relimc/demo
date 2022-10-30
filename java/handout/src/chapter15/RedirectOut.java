package chapter15;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class RedirectOut {
    public static void main(String[] args) {
        try (PrintStream ps = new PrintStream(new FileOutputStream("./src/chapter15/out.txt"))) {
            System.setOut(ps);  // 将标准输出重定向到 ps 输出流
            System.out.println("普通字符串");
            System.out.println(new RedirectOut());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
