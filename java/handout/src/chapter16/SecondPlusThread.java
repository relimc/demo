package chapter16;

public class SecondPlusThread {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                Thread t = new Thread(() -> {
                    for (int j = 0; j < 100; j++) {
                        System.out.println(Thread.currentThread().getName() + " " + j);
                    }
                }, "子线程1");
                t.start();
            }
        }
    }
}
