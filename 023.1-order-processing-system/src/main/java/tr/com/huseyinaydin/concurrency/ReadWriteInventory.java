package tr.com.huseyinaydin.concurrency;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteInventory {

    private int stock = 10;

    private final ReadWriteLock lock =
            new ReentrantReadWriteLock();

    public int getStock() {

        lock.readLock().lock();

        try {
            System.out.printf(
                    "[%s] Okunan stok: %d\n",
                    Thread.currentThread().getName(),
                    stock
            );

            Thread.sleep(1000);

            return stock;

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
            return stock;

        } finally {

            lock.readLock().unlock();
        }
    }

    public void increaseStock(int amount) {

        lock.writeLock().lock();

        try {
            System.out.printf(
                    "[%s] Artan stok: %d -> %d\n",
                    Thread.currentThread().getName(),
                    stock,
                    stock + amount
            );

            Thread.sleep(2000);

            stock += amount;

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

        } finally {

            lock.writeLock().unlock();
        }
    }
}