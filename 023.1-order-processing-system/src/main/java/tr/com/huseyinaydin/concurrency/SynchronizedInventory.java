package tr.com.huseyinaydin.concurrency;

public class SynchronizedInventory {

    private int stock = 10;


    public synchronized void decreaseStock() {

        if (stock > 0) {

            System.out.printf(
                    "[%s] Stok öncesi: %d\n",
                    Thread.currentThread().getName(),
                    stock
            );

            stock--;

            System.out.printf(
                    "[%s] Stok sonrası: %d\n",
                    Thread.currentThread().getName(),
                    stock
            );
        }
    }

    public synchronized int getStock() {
        return stock;
    }
}