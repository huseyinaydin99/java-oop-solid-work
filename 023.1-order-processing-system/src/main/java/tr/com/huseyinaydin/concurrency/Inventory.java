package tr.com.huseyinaydin.concurrency;

// Bu sınıf ürün stokunu temsil eder ve şu an hiçbir koruma mekanizması kullanmaz.
// Aynı anda birden fazla thread bu metoda girdiğinde stock değişkeni tutarsız sonuçlar üretebilir.
public class Inventory {

    private int stock = 10;

    public void decreaseStock() {

        if (stock > 0) {

            System.out.printf("[%s] Okunan stok : %d%n",
                    Thread.currentThread().getName(),
                    stock);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            stock--;

            System.out.printf("[%s] Son hali : %d%n",
                    Thread.currentThread().getName(),
                    stock);
        }

    }

    public int getStock() {
        return stock;
    }

}