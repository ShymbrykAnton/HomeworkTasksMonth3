package bank;

public class Cashier implements Runnable{
private final Bank bank;
    public Cashier(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        System.out.println("WORKKKKK!");
        Thread.currentThread().setName("Kasir");
    }
}
