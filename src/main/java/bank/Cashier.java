package bank;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cashier implements Runnable{
private final Bank bank;
private final Lock lock = new ReentrantLock();
    public Cashier(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        System.out.println("WORKKKKK!!!!!!!!!!!!!!!");
        Thread.currentThread().setName("Kasir");
        bank.giveCreditIfPossible(-1,new Client(bank));

    }
}
