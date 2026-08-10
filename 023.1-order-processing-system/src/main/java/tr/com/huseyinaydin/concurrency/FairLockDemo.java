package tr.com.huseyinaydin.concurrency;

import java.util.concurrent.locks.ReentrantLock;

public class FairLockDemo {

    private final ReentrantLock lock =
            new ReentrantLock(true);

    public void execute() {

        lock.lock();

        try {
            System.out.printf(
                    "[%s] Lock aldı.%n",
                    Thread.currentThread().getName()
            );

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        } finally {
            lock.unlock();
        }
    }
}