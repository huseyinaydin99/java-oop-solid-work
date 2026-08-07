package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.config.ThreadPoolConfig;
import tr.com.huseyinaydin.model.Order;

import java.util.concurrent.CompletableFuture;

public class OrderService {

    private final StockService stockService = new StockService();
    private final PaymentService paymentService = new PaymentService();
    private final ShippingService shippingService = new ShippingService();
    private final NotificationService notificationService = new NotificationService();

    public void process(Order order) {

        CompletableFuture<Boolean> stockFuture =
                CompletableFuture.supplyAsync(
                        () -> stockService.checkStock(order),
                        ThreadPoolConfig.executor()
                );

        CompletableFuture<Boolean> paymentFuture =
                CompletableFuture.supplyAsync(
                        () -> paymentService.processPayment(order),
                        ThreadPoolConfig.executor()
                );

        CompletableFuture<String> shippingFuture =
                CompletableFuture.supplyAsync(
                        () -> shippingService.prepareShipment(order),
                        ThreadPoolConfig.executor()
                );

        CompletableFuture<Void> notificationFuture =
                CompletableFuture.runAsync(
                        () -> notificationService.sendNotification(order),
                        ThreadPoolConfig.executor()
                );

        CompletableFuture.allOf(
                stockFuture,
                paymentFuture,
                shippingFuture,
                notificationFuture
        ).join();
        /*
            Her servis CompletableFuture ile aynı anda çalıştırılır ve allOf().join()
            tüm görevlerin tamamlanmasını bekler. Böylece birbirinden bağımsız işlemler
            paralel yürütülerek toplam işlem süresi önemli ölçüde azaltılır.
         */

        System.out.printf("[%s] Sipariş #%d tamamlandı.\n",
                Thread.currentThread().getName(),
                order.getId());

    }

}