package tr.com.huseyinaydin.app;

import tr.com.huseyinaydin.concurrency.SynchronizedInventory;
import tr.com.huseyinaydin.config.ThreadPoolConfig;

public class ApplicationSynchronizedInventoryDemo {
    public static void main(String[] args) {

        SynchronizedInventory inventory =
                new SynchronizedInventory();

        /*
        synchronized metot, aynı SynchronizedInventory nesnesi üzerinde aynı anda yalnızca bir thread'in
        bu metoda girmesine izin verir. 🔒 Böylece stock değerinin okunması ve değiştirilmesi sırasında
        başka bir thread'in araya girerek Race Condition oluşturması engellenir.
         */
        for (int i = 0; i < 20; i++) {

            ThreadPoolConfig.executor()
                    .submit(inventory::decreaseStock);
        }
        /*
        Burada 20 thread aynı stok üzerinde işlem yapmaya çalışsa bile synchronized sayesinde
        kritik bölgeye yalnızca bir thread girebilir. 🧵 Stok 0 olduğunda sonraki thread'ler
        metodu çalıştırabilse bile if (stock > 0) koşulunu geçemeyeceği için stok negatif değere düşmez.
         */

        ThreadPoolConfig.shutdown();

        System.out.println(
                "Final Stock: " + inventory.getStock()
        );
    }

}