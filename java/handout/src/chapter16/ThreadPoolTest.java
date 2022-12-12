package chapter16;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
    public static void main(String[] args) throws Exception{
        ExecutorService pool = Executors.newFixedThreadPool(6);
        pool.submit(new MyThread1());
        pool.submit(new MyThread1());
        pool.shutdown();
    }
}

class MyThread1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "的 i 值为：" + i);
        }
    }
}
