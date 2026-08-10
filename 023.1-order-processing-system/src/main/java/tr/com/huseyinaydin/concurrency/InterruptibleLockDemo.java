package tr.com.huseyinaydin.concurrency;

import java.util.concurrent.locks.ReentrantLock;

public class InterruptibleLockDemo {

    private final ReentrantLock lock = new ReentrantLock();

    /*
    İlk thread lock'ı aldığı için ikinci thread lock'ı bekler ve daha sonra interrupt()
    çağrısıyla bekleme durumundan çıkarılır. 🧵 Bu örnek, özellikle uygulamanın kapanması
    veya bir görevin iptal edilmesi gerektiğinde interruptible locking
    mekanizmasının neden önemli olduğunu gösterir.
     */

    public void execute() throws InterruptedException {

        lock.lockInterruptibly();

        try {
            System.out.printf(
                    "[%s] Lock alındı.%n",
                    Thread.currentThread().getName()
            );

            Thread.sleep(5000);

        } finally {
            lock.unlock();

            System.out.printf(
                    "[%s] Lock bırakıldı.%n",
                    Thread.currentThread().getName()
            );
        }
    }
}