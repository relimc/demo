import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Properties out = new Properties();
        out.setProperty("a", "1");
        out.setProperty("b", "2");
        out.store(new FileOutputStream("./src/out.ini"), "test");

        Properties in = new Properties();
        in.load(new FileInputStream("./src/out.ini"));
        System.out.println(in);
    }
}





