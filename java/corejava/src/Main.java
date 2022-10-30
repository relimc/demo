import java.io.*;
import java.util.Scanner;

public class Main {
    public static void readFile(String string) {

        try (InputStream in = new FileInputStream(string)) {
            int cc = in.read();
            while (cc != -1) {
                System.out.print((char) cc);
                cc = in.read();
            }
        } catch (FileNotFoundException e) {
            System.out.println("您所读取的文件不存在！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        try (Scanner in = new Scanner(new FileInputStream("/etc/apt/source.list"), "UTF-8");
             PrintWriter out = new PrintWriter("out.txt")) {
            while ((in.hasNext())) {
                out.println(in.next().toUpperCase());
            }
        }
    }
}