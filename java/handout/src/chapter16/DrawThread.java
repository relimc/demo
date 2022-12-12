package chapter16;

public class DrawThread extends Thread{
    private LockAccount account;
    private double drawAccount;

    public DrawThread(String name, LockAccount account, double drawAccount) {
        super(name);
        this.account = account;
        this.drawAccount = drawAccount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            account.draw(drawAccount);
        }
    }
}
