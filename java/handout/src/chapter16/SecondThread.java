package chapter16;

public class SecondThread implements Runnable{ //  声明一个实现了 Runnable 接口的类
    private int i;

    @Override
    public void run() {  // 实现类需要实现 Runnable 接口的 run 方法，方法体就是线程要做的事情
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                SecondThread st = new SecondThread();  // 实例化一个 Runnable 接口实现类的实例，此实例并不是线程对象，而是作为参数
                new Thread(st, "子线程1").start();  // 此实例作为 Thread(Runnable var1, String var2) 构造方法的 var1 参数，第二个参数可以给线程取名字
                new Thread(st, "子线程2").start(); // 两个线程对象使用同一个 Runnable 实现类时，线程间可以共享实现类的实例字段 i
            }
        }
    }
}
