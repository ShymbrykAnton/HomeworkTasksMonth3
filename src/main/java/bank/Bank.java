package bank;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private AtomicInteger capital = new AtomicInteger(50_000);
    private AtomicInteger balance = new AtomicInteger(54_000);
    private AtomicInteger depositCount = new AtomicInteger();
    private AtomicInteger creditCount = new AtomicInteger();
    private AtomicInteger creditSum = new AtomicInteger();
    private AtomicInteger depositSum = new AtomicInteger();
    private Lock fairnessLock = new ReentrantLock(true);

    public void setCapital(AtomicInteger capital) {
        try {
            fairnessLock.lock();
            this.capital = capital;
        } finally {
            fairnessLock.unlock();
        }
    }

    public void setBalance(AtomicInteger balance) {
        try {
            fairnessLock.lock();
            this.balance = balance;
        } finally {
            fairnessLock.unlock();
        }
    }

    public void setDepositCount(AtomicInteger depositCount) {
        try {
            fairnessLock.lock();
            this.depositCount = depositCount;
        } finally {
            fairnessLock.unlock();
        }
    }

    public void setCreditCount(AtomicInteger creditCount) {
        try {
            fairnessLock.lock();
            this.creditCount = creditCount;
        } finally {
            fairnessLock.unlock();
        }
    }

    public void setCreditSum(AtomicInteger creditSum) {
        try {
            fairnessLock.lock();
            this.creditSum = creditSum;
        } finally {
            fairnessLock.unlock();
        }
    }


    public void setDepositSum(AtomicInteger depositSum) {
        try {
            fairnessLock.lock();
            this.depositSum = depositSum;
        } finally {
            fairnessLock.unlock();
        }
    }

    public AtomicInteger getCapital() {
        return capital;
    }

    public AtomicInteger getBalance() {
        return balance;
    }

    public AtomicInteger getDepositCount() {
        return depositCount;
    }

    public AtomicInteger getCreditCount() {
        return creditCount;
    }

    public AtomicInteger getCreditSum() {
        return creditSum;
    }

    public AtomicInteger getDepositSum() {
        return depositSum;
    }
}
