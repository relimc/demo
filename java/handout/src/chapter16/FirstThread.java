package chapter16;

public class FirstThread extends Thread {  // 声明一个线程类，该类继承 Thread
    private int i;

    @Override
    public void run() {  // 定义一个线程对象，需要重写父类 Thread 的 run 方法，run 方法内部的代码，也就是这个线程要做的事情
        for (; i < 100; i++) {
            System.out.println(getName() + " " + i);  // getName 是 Thread 类的实例方法，返回调用该方法的线程的名字
        }
    }

    public static void main(String[] args) {  // main 方法是主线程
        for (int i = 0; i < 100; i++) {
            // currentThread 是 Thread 类的静态方法，该方法返回当前正在执行的线程对象，在这里是主线程
            System.out.println(Thread.currentThread().getName() + " " + i);  // 主线程的名字默认是 main，子线程默认名字是 Thread-0,...
            if (i == 20) {  // 当主线程执行到 i == 20 时，会开启两个新的子线程，新的子线程做的事是：打印 100 次子线程的名字
                new FirstThread().start();  // 每个子线程对象，都有一个实例字段 i
                new FirstThread().start();  // 两个子线程对象间的实例字段互不影响，即多个线程之间无法共享线程类的实例变量
            }
        }
    }
}
