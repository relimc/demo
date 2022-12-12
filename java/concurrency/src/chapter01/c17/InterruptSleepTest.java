package chapter01.c17;

public class InterruptSleepTest {
    public static void main(String[] args) throws InterruptedException{
        Thread threadOne = new Thread(() -> {
            try {
                System.out.println("threadOne begin sleep for 2000 seconds");
                Thread.sleep(2000000);
                System.out.println("threadOne awaking");
            } catch (InterruptedException e) {
                System.out.println("threadOne is interrupted while sleeping");
                return;
            }
            System.out.println("threadOne-leaving normally");
        });
        threadOne.start();
        Thread.sleep(1000);  // main 线程阻塞 1 秒，子线程将会启动，子线程启动后会睡眠 2000 秒
        threadOne.interrupt();  // main 线程阻塞 1 秒后，调用了子线程的中断方法，子线程将从睡眠状态抛出中断异常，将子线程唤醒
        threadOne.join();
        System.out.println("main thread is over");
    }
}
