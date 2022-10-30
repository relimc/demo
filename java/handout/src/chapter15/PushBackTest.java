package chapter15;

import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.io.PushbackReader;

public class PushBackTest {
    public static void main(String[] args) {
        // 将 FileReader 节点流包装成 PushbackReader 处理流，以字符的形式读取文件内容，同时指定缓冲区的长度为 64
        try (PushbackReader pr = new PushbackReader(new FileReader("./src/chapter15/PushBackTest.java"), 64)) {
            char[] buf = new char[32]; // 定义一个能存放 32 个字符元素的数组，用来当做
            String lastContent = "";  // 用于存放上次循环读取到的字符串
            int hasRead = 0;
            while ((hasRead = pr.read(buf)) > 0) {  // 循环读取文件内容，每次最多读取 32 个字符
                String content = new String(buf, 0, hasRead);  // 将读取到的字符数组转换成字符串，该字符串最长为 32 个字符
                int targetIndex = 0;  // 目标字符串在源字符串中的位置
                if ((targetIndex = (lastContent + content).indexOf("new PushBackReader")) > 0) {  // 将 64 个字符推回缓冲区，在这 64 个字符序列中出现指定字符串时
                    pr.unread((lastContent + content).toCharArray());
                    if (targetIndex > 32) {
                        System.out.println(new String((lastContent + content).toCharArray(), 0, targetIndex));
                    } else {
                        pr.read(buf, 0, targetIndex);
                        System.out.println(new String(buf, 0, targetIndex));
                    }
                    System.exit(0);
                } else {
                    System.out.println(lastContent);
                    lastContent = content;
                }
            }
            System.out.println(lastContent);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
