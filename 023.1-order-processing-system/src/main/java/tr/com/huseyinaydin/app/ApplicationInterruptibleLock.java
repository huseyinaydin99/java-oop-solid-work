package tr.com.huseyinaydin.app;

import tr.com.huseyinaydin.concurrency.InterruptibleLockDemo;

public class ApplicationInterruptibleLock {

    public static void main(String[] args) throws InterruptedException {

        InterruptibleLockDemo demo = new InterruptibleLockDemo();

        Thread firstThread = new Thread(() -> {
            try {
                demo.execute();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread secondThread = new Thread(() -> {
            try {
                demo.execute();
            } catch (InterruptedException e) {
                System.out.printf(
                        "[%s] Thread interrupt edildi.\n",
                        Thread.currentThread().getName()
                );

                Thread.currentThread().interrupt();
            }
        });

        firstThread.start();

        Thread.sleep(500);

        secondThread.start();

        Thread.sleep(1000);

        secondThread.interrupt();

        firstThread.join();
        secondThread.join();
    }
}