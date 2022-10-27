package chapter16;

public class JoinThread extends Thread{
    public JoinThread(String name) {
        super(name);
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) throws Exception{
        new JoinThread("新线程").start();  // start 执行后，子线程处于就绪状态
        for (int i = 0; i < 100; i++) {  // main 线程的循环体开始执行
            if (i == 20) {  // 当 i == 20 时，新建一个线程
                JoinThread jt = new JoinThread("被 join 的线程");
                jt.start();  // 启动线程，线程处于就绪状态
                jt.join(); // 当这个被 join 的子线程开始执行时，jt.join 是在 main 线程中调用，main 线程会挂起（谁调用谁被挂起），直到子线程执行完毕后，才会继续执行 main 进程。
            }
            System.out.println(Thread.currentThread().getName());  // 打印 main 线程名字
        }
    }
}
