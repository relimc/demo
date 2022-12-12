package chapter16;

public class DepositThread extends Thread{
    private LockAccount account;
    private double depositAmount;

    public DepositThread(String name, LockAccount account, double depositAmount) {
        super(name);
        this.account = account;
        this.depositAmount = depositAmount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            account.deposit(depositAmount);
        }
    }
}
