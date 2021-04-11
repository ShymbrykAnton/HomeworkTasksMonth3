package bank;

import org.apache.logging.log4j.core.util.ExecutorServices;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        Client client = null;
        Lock lock = new ReentrantLock();
        int count =0;
        Cashier cashier = new Cashier(bank);

        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ExecutorService executorService = Executors.newFixedThreadPool(10_000, threadFactory);

       ScheduledExecutorService kasirExecutor = Executors.newScheduledThreadPool(1);
       kasirExecutor.scheduleWithFixedDelay(cashier,1,5,TimeUnit.SECONDS);
        while (count<=10_000) {
            try {
                lock.lock();
                client = new Client(bank);
                Condition condition = lock.newCondition();
                executorService.execute(threadFactory.newThread(client));
                condition.await(10, TimeUnit.MILLISECONDS);
                count++;
            } finally {
                lock.unlock();
            }
        }

    }
}