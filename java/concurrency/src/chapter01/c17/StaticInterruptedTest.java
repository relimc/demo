package chapter01.c17;

public class StaticInterruptedTest {
    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            for(;;) {}
        });
        threadOne.start();
        threadOne.interrupt();
        System.out.println("isInterrupted: " + threadOne.isInterrupted());  // isInterrupted 是实例方法，获取线程对象的中断状态
        System.out.println("isInterrupted: " + threadOne.interrupted());  // interrupted 是类方法，获取当前线程的中断状态，在这里当前线程是 main 线程
        System.out.println("isInterrupted: " + Thread.interrupted());
        System.out.println("isInterrupted: " + threadOne.isInterrupted());
        threadOne.join();
        System.out.println("main thread is over");
    }
}
