package chapter15;

import java.io.File;
import java.io.IOException;

public class FileTest {
    public static void main(String[] args) throws IOException {
        // File 对象接收一个目录参数时
        File dir = new File(".");  // . 表示当前目录，这里的当前目录不是指 chapter15 这个目录，而是指项目的根目录 handout
        System.out.println(dir.getName());  // 获取目录名
        System.out.println(dir.getPath());  // 获取目录名
        System.out.println(dir.getParent());  // 获取当前目录的父目录
        System.out.println(dir.getAbsoluteFile());  // 获取当前目录的绝对路径
        System.out.println(dir.getAbsolutePath());  // 获取当前目录的绝对路径
        System.out.println(dir.getAbsoluteFile().getParent());  // 获取当前目录的上一级目录
        String[] fileList = dir.list();
        for (String fileName : fileList) {
            System.out.println(fileName);
        }

        File[] roots = File.listRoots();
        for (File root : roots) {
            System.out.println(root);
        }
    }
}
