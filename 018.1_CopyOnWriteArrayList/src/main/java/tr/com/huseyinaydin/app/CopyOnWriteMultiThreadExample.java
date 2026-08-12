package tr.com.huseyinaydin.app;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteMultiThreadExample {

    /*
    🧠 Buradaki çok önemli nokta: reader çalışırken writer "Ayşe" elemanını eklese bile,
    reader kendi iterasyonunu başlattığı andaki array üzerinde devam eder ve "Ayşe"
    bu iterasyonda görünmez. 🛡️ Yazma işlemi yeni bir array oluşturduğu için
    okuyucunun elindeki snapshot değişmez; ancak iterasyon bittikten sonra
    listenin güncel hali "Ayşe" elemanını içerir.
     */

    public static void main(String[] args) throws InterruptedException {

        CopyOnWriteArrayList<String> users = new CopyOnWriteArrayList<>();

        users.add("Hüseyin");
        users.add("Ahmet");
        users.add("Mehmet");

        Thread reader = new Thread(() -> {

            for (String user : users) {

                System.out.println(
                        "Okunan değer: " + user + " Thread" + Thread.currentThread().getName()
                );

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread writer = new Thread(() -> {

            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            users.add("Ayşe");

            System.out.println(
                    "Yazıcı thread: Ayşe eklendi." + "Thread" + Thread.currentThread().getName()
            );
        });

        reader.start();
        writer.start();

        reader.join();
        writer.join();

        System.out.println(
                "Son liste: " + users
        );
    }
}
