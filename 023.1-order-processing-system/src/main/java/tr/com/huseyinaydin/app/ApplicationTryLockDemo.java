package tr.com.huseyinaydin.app;

import tr.com.huseyinaydin.concurrency.TryLockInventory;
import tr.com.huseyinaydin.config.ThreadPoolConfig;

import java.util.concurrent.ExecutorService;

public class ApplicationTryLockDemo {

    public static void main(String[] args) {

        TryLockInventory inventory = new TryLockInventory();

        ExecutorService executor = ThreadPoolConfig.executor();

        for (int i = 0; i < 20; i++) {
            executor.submit(inventory::decreaseStock);
        }

        ThreadPoolConfig.shutdown();
    }
}