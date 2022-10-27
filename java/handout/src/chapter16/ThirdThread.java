package chapter16;

import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThirdThread implements Callable<Integer> {  // 声明一个实现了 Callable 接口的类

    @Override
    public Integer call() throws Exception {  // 实现类需要重写 Callable 接口的 call 方法，call 方法体就是线程要做的是，与 run 方法不同的的事，call 可以抛出异常，可以有返回值
        int i = 0;
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " 的循环变量 i 的值：" + i);
        }
        return i;
    }

    public static void main(String[] args) {
        ThirdThread tt = new ThirdThread();  // 实例化一个 Callable 实现类对象，该对象由于没有实现 Runnable 接口，不能作为 Thread(Runnable var1, String var2) 构造方法的 var1 参数
        FutureTask<Integer> task = new FutureTask<>(tt);  // FutureTask 类实现了 Runnable 接口，其构造器接收一个 Callable 实现类的对象，可以将 tt 包装成一个 FutureTask 对象
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " 的循环变量 i 的值：" + i);
            if (i == 20) {
                new Thread(task, "有返回值的线程").start();  // task 对象实现了 Runnable 接口，可以作为 Thread 构造器的参数。
            }
        }
        try {
            System.out.println("子线程的返回值：" + task.get());  // get 方法返回 call 方法的返回值
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
