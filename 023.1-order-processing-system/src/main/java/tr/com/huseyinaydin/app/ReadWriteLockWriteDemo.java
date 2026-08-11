package tr.com.huseyinaydin.app;

import tr.com.huseyinaydin.concurrency.ReadWriteInventory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReadWriteLockWriteDemo {

    public static void main(String[] args) {

        ReadWriteInventory inventory =
                new ReadWriteInventory();

        ExecutorService executor =
                Executors.newFixedThreadPool(4);

        for (int i = 1; i <= 3; i++) {

            executor.submit(
                    () -> inventory.increaseStock(5)
            );
        }

        /*
        Bu kodda her döngüde önce bir okuma görevi, hemen ardından bir yazma görevi Thread Pool'a gönderilir;
        fakat submit() sırası görevlerin aynı sırayla çalışacağını garanti etmez. 🧵
        Örneğin Thread-A getStock() için readLock almışken Thread-B increaseStock(1)
        için writeLock isterse Thread-B bekler; Thread-A okumasını bitirip readLock'ı
        bıraktığında Thread-B yazma işlemini gerçekleştirir. 🔒
         */
        for (int i = 1; i <= 4; i++) {
            int finalI = i;
            executor.submit(inventory::getStock);
            executor.submit(
                    () -> inventory.increaseStock(finalI));
        }

        executor.shutdown();
    }
}