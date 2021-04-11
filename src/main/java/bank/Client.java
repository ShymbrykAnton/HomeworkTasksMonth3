package bank;

public class Client implements Runnable {

    private final int moneyBalance;
    private final int depositBalance;
    private final int creditBalance;

    public Client(int moneyBalance, int depositBalance, int creditBalance) {
        this.moneyBalance = moneyBalance;
        this.depositBalance = depositBalance;
        this.creditBalance = creditBalance;
    }

    @Override
    public void run() {

    }
}
