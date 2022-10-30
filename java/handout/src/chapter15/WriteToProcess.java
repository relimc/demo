package chapter15;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class WriteToProcess {
    public static void main(String[] args) throws IOException {
        Process p = Runtime.getRuntime().exec("java ReadStandard");
        try (PrintStream ps = new PrintStream(p.getOutputStream())) {
            ps.println("普通字符串");
            ps.println(new WriteToProcess());
        }
    }
}

class ReadStandard {
    public static void main(String[] args) {
        String out = "./src/chapter15/out.txt";
        try (Scanner sc = new Scanner(System.in); PrintStream ps = new PrintStream(new FileOutputStream(out))) {
            sc.useDelimiter("\n");
            while (sc.hasNext()) {
                ps.println("键盘输入的内容是：" + sc.next());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
