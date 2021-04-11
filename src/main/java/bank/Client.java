package bank;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client implements Runnable {

    private int moneyBalance;
    private int depositBalance;
    private int creditBalance;
    private final Bank bank;
    private final Lock lock = new ReentrantLock();

    public Client(Bank bank) {
        this.bank = bank;
        this.moneyBalance = (int) (Math.random() * 30_000) + 1;
        ;
        this.depositBalance = 0;
        this.creditBalance = 0;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(this.toString());
        try {
            lock.lock();
            Condition condition = lock.newCondition();
            int random = (int) (Math.random() * 100);
            if (random < 50) {
                condition.await(this.takeCredit(), TimeUnit.SECONDS);
                bank.returnCredit(creditBalance, this);
                condition.await(this.takeMoneyOnDeposit(), TimeUnit.SECONDS);
                bank.returnDeposit(depositBalance,this);
            } else {
                condition.await(this.takeMoneyOnDeposit(), TimeUnit.SECONDS);
                bank.returnDeposit(depositBalance,this);
                condition.await(this.takeCredit(), TimeUnit.SECONDS);
                bank.returnCredit(creditBalance, this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    @Override
    public String toString() {
        return "Client{" +
                "moneyBalance=" + moneyBalance +
                ", depositBalance=" + depositBalance +
                ", creditBalance=" + creditBalance +
                '}';
    }

    public int takeCredit() {
        int creditValue = (int) (Math.random() * 19_499) + 500;
        bank.giveCreditIfPossible(creditValue, this);
        return (int) (Math.random() * 36) + 12;
    }

    public int takeMoneyOnDeposit() {
        int depositValue = (int) (Math.random() * 19_499) + 500;
        if (depositValue > moneyBalance) {
            depositValue = moneyBalance;
        }
        bank.giveDeposit(depositValue, this);
        return (int) (Math.random() * 36) + 12;
    }

    public int getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(int moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    public int getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(int depositBalance) {
        this.depositBalance = depositBalance;
    }

    public int getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(int creditBalance) {
        this.creditBalance = creditBalance;
    }

    public Bank getBank() {
        return bank;
    }
}
