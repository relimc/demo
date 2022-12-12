import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> System.out.println("a new thread"));
    }
}

class Count {
    public static int count = 0;

    public static int getCount() {
        return Count.count;
    }
}

class AddThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Count.count += 1;
        }
    }
}

class DecreaseThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Count.count -= 1;
        }
    }
}


