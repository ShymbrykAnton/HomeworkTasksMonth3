package bank;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(0,10_000,10,
                TimeUnit.MINUTES,new ArrayBlockingQueue<Client>(10_000),)
    }
}