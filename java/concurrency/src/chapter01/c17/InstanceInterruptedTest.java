package chapter01.c17;

public class InstanceInterruptedTest {
    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            while (!Thread.interrupted()) {}  // 当前线程处于中断状态时，退出循环，并清除中断标志，中断标志清除后，isInterrupted 返回 false
            System.out.println("threadOne isInterrupted: " + Thread.currentThread().isInterrupted());
        });
        threadOne.start();
        threadOne.interrupt();
        threadOne.join();
        System.out.println("main thread is over");
    }
}
