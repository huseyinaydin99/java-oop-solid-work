package tr.com.huseyinaydin.app;

import tr.com.huseyinaydin.concurrency.Inventory;
import tr.com.huseyinaydin.config.ThreadPoolConfig;

import java.util.concurrent.ExecutorService;

public class ApplicationRaceConditionDemo {

    public static void main(String[] args) {
        /*
        Thread.sleep(100) iki thread'in aynı anda aynı stok değerini okuma ihtimalini artırarak
        Race Condition'ın daha belirgin şekilde oluşmasını sağlar. Bu gecikme yalnızca eğitim
        amacıyla eklenmiştir ve gerçek projelerde bu amaçla kullanılmaz.
         */

        Inventory inventory = new Inventory();

        ExecutorService executor = ThreadPoolConfig.executor();

        for (int i = 1; i <= 20; i++) {
            executor.submit(inventory::decreaseStock);
        }

        ThreadPoolConfig.shutdown();

    }

}