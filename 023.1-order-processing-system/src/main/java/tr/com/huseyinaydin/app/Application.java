package tr.com.huseyinaydin.app;

import tr.com.huseyinaydin.config.ThreadPoolConfig;
import tr.com.huseyinaydin.model.Order;
import tr.com.huseyinaydin.service.OrderService;

import java.util.concurrent.ExecutorService;

public class Application {

    public static void main(String[] args) {

        ExecutorService executor = ThreadPoolConfig.executor();
        OrderService orderService = new OrderService();

        for (byte i = 1; i <= 10; i++) {

            Order order = new Order(
                    i,
                    "Müşteri-" + i,
                    "Laptop"
            );

            executor.submit(() -> orderService.process(order));
            /*
            Soru; burada 10 tane sipariş oluşturuluyor ama en fazla 4 thread var. bu durumda diğerlerini sıraya mı koyar?
            Cevap; evet ilk 4 görev mevcut thread'lere atanır, kalan 6 görev ise task queue (görev kuyruğu) içinde bekler ve bir thread boşaldıkça sırayla çalıştırılır.
            */
        }

        ThreadPoolConfig.shutdown();
    }
}