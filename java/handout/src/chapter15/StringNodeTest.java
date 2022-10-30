package chapter15;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class StringNodeTest {
    public static void main(String[] args) {
        String src = "从明天起，做一个幸福的人\n"
        + "喂马、劈柴，周游世界\n"
        + "从明天起，关心粮食和蔬菜\n"
        + "我有一所房子，面朝大海，春暖花开\n"
        + "从明天起，和每一个亲人通信\n"
        + "告诉他们我的幸福";

        char[] buffer = new char[32];
        int hasRead = 0;
        try (StringReader sr = new StringReader(src)) {
            while ((hasRead = sr.read(buffer)) > 0) {
                System.out.println(new String(buffer, 0, hasRead));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try (StringWriter sw = new StringWriter()) {
            sw.write("愿你有一个灿烂的前程\n");
            sw.write("愿你有情人终成眷属\n");
            sw.write("愿你在尘世获得幸福");
            System.out.println(sw);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
