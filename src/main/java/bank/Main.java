package bank;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        Client client = null;
        int counter = 0;
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        LinkedBlockingDeque<Runnable> taskList = new LinkedBlockingDeque<>();
        ExecutorService executorService = new ThreadPoolExecutor(0, 10_000, 10, TimeUnit.MINUTES, taskList, threadFactory);
        while (counter <= 10_000) {
            client = new Client(bank);
//            threadFactory.newThread(client);
            counter++;
            System.out.println(taskList.size());
//            System.out.println(counter);
        }
            Runnable task = client;
            executorService.execute(client);

    }
}