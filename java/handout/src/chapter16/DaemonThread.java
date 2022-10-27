package chapter16;

public class DaemonThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        DaemonThread dt = new DaemonThread();
        dt.setDaemon(true);  // setDaemon 设置为 true，可将一个普通线程设置为守护线程
        System.out.println(dt.isDaemon());  // isDaemon 判断某个线程是否是守护线程
        dt.start();  // setDaemon 必须在 start 方法之前调用，线程就绪后，不能再将线程设置为后台线程
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        // 程序执行到此处时，main 进程结束，在这里子线程被设置为后台线程（守护线程），所有前台线程结束时，守护线程（没执行完）也会结束。
    }
}


