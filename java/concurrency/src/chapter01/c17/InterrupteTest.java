package chapter01.c17;

public class InterrupteTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread() + " hello");
            }
        });
        thread.start();
        Thread.sleep(1000);
        System.out.println("main thread interrupt thread");
        thread.interrupt();  // 当发送中断信号时，线程的 while 循环会收到终端信号，导致循环中止
        thread.join();
        System.out.println("main is over");
    }
}
