package tr.com.huseyinaydin.app;

import tr.com.huseyinaydin.concurrency.OrderPriceTask;

import java.util.concurrent.*;

/*
future.get(timeout, unit) sonucu sonsuza kadar beklemek yerine belirlenen süre içinde almaya
çalışır ve süre aşılırsa TimeoutException üretir. ⏱️ Böylece dış sistemlerden gelen yavaş
işlemlerde thread'in sınırsız şekilde bloklanması önlenebilir ve gerekirse görev iptal
edilerek kaynak kontrolü sağlanabilir.
*/

public class ApplicationCallableTimeoutDemo {

    public static void main(String[] args) {

        ExecutorService executor =
                Executors.newFixedThreadPool(2);

        Future<Integer> future =
                executor.submit(new OrderPriceTask(10));

        try {

            Integer result =
                    future.get(500, TimeUnit.MILLISECONDS);

            System.out.println(
                    "Sipariş fiyatı: " + result
            );

        } catch (TimeoutException e) {

            System.out.println(
                    "Thread'ın görev süresi çok uzun sürdü!"
            );

            future.cancel(true);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.out.println(
                    "Çalıştırma hatası: " + e.getCause()
            );
        } finally {
            executor.shutdown();
        }
    }
}