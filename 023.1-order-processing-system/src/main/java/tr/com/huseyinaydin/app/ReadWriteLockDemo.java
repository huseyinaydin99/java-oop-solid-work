package tr.com.huseyinaydin.app;

import tr.com.huseyinaydin.concurrency.ReadWriteInventory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReadWriteLockDemo {

    public static void main(String[] args) {

        ReadWriteInventory inventory =
                new ReadWriteInventory();

        ExecutorService executor =
                Executors.newFixedThreadPool(6);

        /*
        4 defa okuma thread'ı devreye girer.
        ardından yazma devreye girer ama okumalar bitmeden devreye giremez onların bitmesini bekler onlar bitince devreye girer yazma biter.
        ardından son okuma thread i devreye girer okuma bitince tamamlanır.
         */

        for (int i = 0; i < 4; i++) {

            executor.submit(inventory::getStock);
        }

        executor.submit(
                () -> inventory.increaseStock(10)
        );

        executor.submit(inventory::getStock);

        executor.shutdown();
    }
}