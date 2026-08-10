package tr.com.huseyinaydin.concurrency;

import java.util.concurrent.locks.ReentrantLock;

// Bu sınıf ürün stokunu temsil eder ve şu an hiçbir koruma mekanizması kullanmaz.
// Aynı anda birden fazla thread bu metoda girdiğinde stock değişkeni tutarsız sonuçlar üretebilir.
public class Inventory {

    private int stock = 10;

    private final ReentrantLock lock = new ReentrantLock();

    public void decreaseStock() {

        lock.lock();

        try {
            if (stock > 0) {
                System.out.printf("[%s] Öncesi : %d%n",
                        Thread.currentThread().getName(),
                        stock);

                Thread.sleep(100);

                stock--;

                System.out.printf("[%s] Sonrası  : %d%n",
                        Thread.currentThread().getName(),
                        stock);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public int getStock() {
        return stock;
    }

    /*
    ReentrantLock, aynı anda yalnızca bir thread'in kritik bölgeye girmesine izin vererek ortak
    veriyi güvenli şekilde korur. unlock() işleminin finally bloğunda yapılması, hata oluşsa bile
    kilidin mutlaka serbest bırakılmasını garanti eder.
     */
}