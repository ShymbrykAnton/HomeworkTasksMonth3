package bank;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final AtomicInteger balance = new AtomicInteger(54_000);
    private final AtomicInteger depositCount = new AtomicInteger();
    private final AtomicInteger creditCount = new AtomicInteger();
    private final AtomicInteger creditSum = new AtomicInteger();
    private final AtomicInteger depositSum = new AtomicInteger();
    private final static Lock fairnessLock = new ReentrantLock(true);

    public void giveCreditIfPossible(int creditValue, Client client) {
        int mediumDeposit = creditValue;
        if (depositCount.get() != 0) {
            mediumDeposit = depositSum.get() / depositCount.get();
        }
        if (balance.get() - creditValue > mediumDeposit * 2) {
            doCreditTransaction(creditValue, client);
            System.out.println("ГраБежж " + balance.get());
        } else {
            waiteNewCredit(creditValue, client);
        }
    }

    public void returnCredit(int creditValue, Client client) {
        if (balance.get() + creditValue + (creditValue / 2) < 1_000_000) {
            System.out.println(client.toString() + "Возвращает много денег");
            doReturnCreditTransaction(creditValue, client);
            System.out.println("ПОСЛЕ ВОЗВРАТА " + balance.get());
        } else {
            waiteOldCredit(creditValue, client);
        }
    }

    public void giveDeposit(int depositValue, Client client) {
        if (balance.get() + depositValue < 1_000_000) {
            doNewDepositTransaction(depositValue, client);
            System.out.println("Банк принят депосит на  " + depositValue);
        } else {
            waiteNewDeposit(depositValue, client);
        }
    }

    public void returnDeposit(int depositValue, Client client) {
        int mediumDeposit = depositSum.get() / depositCount.get();
        if (balance.get() - depositValue > mediumDeposit * 2) {
            doOldDepositTransaction(depositValue, client);
            System.out.println(depositValue + " Банк вернул, Наш баланс " + balance.get());
        } else {
            waiteDeposit(depositValue, client);
        }
    }

    private void doCreditTransaction(int creditValue, Client client) {
        int currentBalance = balance.get();
        balance.compareAndSet(currentBalance, currentBalance - creditValue);
        creditCount.compareAndSet(creditCount.get(), creditCount.get() + 1);
        creditSum.compareAndSet(creditSum.get(), creditSum.get() + creditValue);
        System.out.println(client + "Have credit " + creditValue);
        client.setCreditBalance(client.getCreditBalance() + creditValue);
        client.setMoneyBalance(client.getMoneyBalance() + creditValue);
    }

    private void waiteNewCredit(int creditValue, Client client) {
        try {
            fairnessLock.lock();
            Condition condition = fairnessLock.newCondition();
            condition.await(5, TimeUnit.SECONDS);

            giveCreditIfPossible(creditValue, client);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            fairnessLock.unlock();
        }
    }

    private void doReturnCreditTransaction(int creditValue, Client client) {
        balance.compareAndSet(balance.get(), balance.get() + (creditValue + (creditValue / 2)));
        creditCount.compareAndSet(creditCount.get(), creditCount.decrementAndGet());
        creditSum.compareAndSet(creditSum.get(), creditSum.get() - creditValue);
        client.setMoneyBalance(client.getMoneyBalance() - (creditValue + (creditValue / 2)));
        client.setCreditBalance(client.getCreditBalance() - creditValue);
    }

    private void waiteOldCredit(int creditValue, Client client) {
        try {
            fairnessLock.lock();
            Condition condition = fairnessLock.newCondition();
            condition.await(5, TimeUnit.SECONDS);
            returnCredit(creditValue, client);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            fairnessLock.unlock();
        }
    }

    private void doNewDepositTransaction(int depositValue, Client client) {
        balance.compareAndSet(balance.get(), balance.get() + depositValue);
        depositCount.compareAndSet(depositCount.get(), depositCount.incrementAndGet());
        depositSum.compareAndSet(depositSum.get(), depositSum.get() + depositValue);
        client.setMoneyBalance(client.getMoneyBalance() - depositValue);
        client.setDepositBalance(client.getDepositBalance() + depositValue);
    }

    private void waiteNewDeposit(int depositValue, Client client) {
        try {
            fairnessLock.lock();
            Condition condition = fairnessLock.newCondition();
            condition.await(5, TimeUnit.SECONDS);
            giveDeposit(depositValue, client);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            fairnessLock.unlock();
        }
    }

    private void waiteDeposit(int depositValue, Client client) {
        try {
            fairnessLock.lock();
            Condition condition = fairnessLock.newCondition();
            condition.await(5, TimeUnit.SECONDS);
            returnDeposit(depositValue, client);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            fairnessLock.unlock();
        }
    }

    private void doOldDepositTransaction(int depositValue, Client client) {
        balance.compareAndSet(balance.get(), balance.get() - (int) (depositValue + depositValue * 0.03));
        depositSum.compareAndSet(depositSum.get(), depositSum.get() - depositValue);
        depositCount.compareAndSet(depositCount.get(), depositCount.decrementAndGet());
        client.setDepositBalance(client.getDepositBalance() - depositValue);
        client.setMoneyBalance(client.getMoneyBalance() + (int) (depositValue + depositValue * 0.03));
    }
}
