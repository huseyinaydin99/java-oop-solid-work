package tr.com.huseyinaydin.app;

import tr.com.huseyinaydin.concurrency.OrderPriceTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
Future.cancel(true) görevin henüz başlamamış olması durumunda çalışmasını engelleyebilir veya görev
çalışıyorsa thread'e interrupt sinyali göndermeyi deneyebilir. 🛑 Ancak cancel(true) thread'i
zorla kill etmez; görevin interrupt sinyalini doğru şekilde ele alması gerekir.
*/

public class ApplicationCallableCancelDemo {

    public static void main(String[] args) throws Exception {

        ExecutorService executor =
                Executors.newFixedThreadPool(2);

        Future<Integer> future =
                executor.submit(new OrderPriceTask(10));

        boolean cancelled =
                future.cancel(true); // kill etmez, thread'a durdurma sinyali gönderir.

        System.out.println(
                "İptal edilip edilememe durumu: " + (cancelled ? "İptal edildi" : "İptal edilemedi")
        );

        executor.shutdown();
    }
}