package bank;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client implements Runnable {

    private  int moneyBalance;
    private  int depositBalance;
    private  int creditBalance;
    private final Bank bank;
    private final Lock lock = new ReentrantLock();

    public Client(Bank bank) {
        this.bank = bank;
        this.moneyBalance =  (int) (Math.random()*30_000) +1;;
        this.depositBalance = 0;
        this.creditBalance = 0;
    }

    @Override
    public void run() {
        Thread thread = new Thread(this);
        thread.setName(this.toString());
        thread.start();
        try {
            if(lock.tryLock(10,TimeUnit.SECONDS)){
            this.takeCredit();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
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
    public void takeCredit(){
        int creditValue  = (int)(Math.random()*19_499)+500;
        bank.giveCreditIfPossible(creditValue,this);
        int timeCredit = (int)(Math.random()*360)+120;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
