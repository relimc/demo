package chapter01.c110;

public class DaemonThread {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (;;) {}
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("main thread is over");
    }
}
