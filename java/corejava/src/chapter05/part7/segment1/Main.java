package chapter05.part7.segment1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // 获取 Class 对象的三种方法
        Class cl1 = "".getClass();
        Class cl2 = String.class;
        Class cl3 = Class.forName("java.lang.String");
        System.out.println(cl1 == cl2);
        System.out.println(cl2 == cl3);

        // 使用 Class 类的 newInstance 方法调用无参构造器创建实例
        Object o = cl2.newInstance();
        String o2s = (String) o;
        System.out.println(o2s.isBlank());
        System.out.println(o2s.isEmpty());

        // 使用 Constructor 类的 newInstance 方法调用无参或有参构造器创建实例
        Constructor[] constructors = Class.forName("java.lang.String").getConstructors();
        Constructor constructor = constructors[0];
        System.out.println(constructor.getName());
    }
}
