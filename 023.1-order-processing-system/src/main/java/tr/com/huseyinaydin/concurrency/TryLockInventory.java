package tr.com.huseyinaydin.concurrency;

import java.util.concurrent.locks.ReentrantLock;

public class TryLockInventory {

    private int stock = 10;

    private final ReentrantLock lock = new ReentrantLock();

    /*
    tryLock() kilidi almak için beklemek yerine hemen sonucu bildirir; kilit başkasındaysa thread
    bloklanmadan false döner. ⚡ Bu yaklaşım, kilidi uzun süre beklemek istemediğimiz veya beklemek
    yerine alternatif bir işlem yapmak istediğimiz senaryolarda kullanışlıdır.
     */

    public boolean decreaseStock() {

        if (!lock.tryLock()) {
            System.out.printf(
                    "[%s] Lock alınamadı, işlem atlandı.%n",
                    Thread.currentThread().getName()
            );

            return false;
        }

        try {
            if (stock <= 0) {
                return false;
            }

            System.out.printf(
                    "[%s] Stock Before: %d%n",
                    Thread.currentThread().getName(),
                    stock
            );

            stock--;

            System.out.printf(
                    "[%s] Stock After: %d%n",
                    Thread.currentThread().getName(),
                    stock
            );

            return true;

        } finally {
            lock.unlock();
        }
    }

    public int getStock() {
        return stock;
    }
}