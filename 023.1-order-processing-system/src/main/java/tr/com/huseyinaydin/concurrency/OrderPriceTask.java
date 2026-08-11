package tr.com.huseyinaydin.concurrency;

import java.util.concurrent.Callable;

/*
Callable<T>, Runnable gibi bir işi ayrı bir thread'de çalıştırmak için kullanılır ancak temel farkı bir
sonuç döndürebilmesi ve Exception fırlatabilmesidir. 🎯 Burada call() tamamlandığında siparişin
hesaplanan fiyatı Integer olarak elde edilir ve bu sonuç daha sonra Future üzerinden alınabilir.
*/

public class OrderPriceTask implements Callable<Integer> {

    private final int orderId;

    public OrderPriceTask(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public Integer call() throws Exception {

        Thread.sleep(1000);

        return orderId * 100;
    }
}