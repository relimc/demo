package chapter16;

public class SyncAccount {
    private String accountNo;
    private double balance;
    private boolean flag = false;

    private SyncAccount() {};

    public SyncAccount(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public synchronized void draw(double drawAmount) {
        try {
            if (!flag) {  // 当没有存款时，线程等待
                wait();
            } else {  // 有存款时，取钱
                System.out.println(Thread.currentThread().getName() + "取钱" + drawAmount);
                balance -= drawAmount;
                System.out.println("账户余额为：" + balance);
                flag = false;  // 取完钱后，将标识位改为 false，不在执行取钱操作
                notifyAll();  // 通知 wait 线程运行
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void deposit(double depositAmount) {
        try {
            if (flag) {
                wait();
            } else {
                System.out.println(Thread.currentThread().getName() + "存款：" + depositAmount);
                balance += depositAmount;
                System.out.println("账户余额：" + balance);
                flag = true;  // 存钱后，标识位为 true
                notifyAll();  // 通知被 wait 的线程运行
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
