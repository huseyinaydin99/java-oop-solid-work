package tr.com.huseyinaydin.app;

import tr.com.huseyinaydin.concurrency.OrderPriceTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
executor.submit(callable) görevi Thread Pool'a gönderir ve karşılığında sonucun gelecekte hazır olacağını
temsil eden Future döndürür. ⏳ future.get() çağrıldığında sonuç henüz hazır değilse mevcut thread bekler;
sonuç hazır olduğunda hesaplanan değer alınır.
*/

public class ApplicationCallableDemo {
    public static void main(String[] args) throws Exception {

        ExecutorService executor =
                Executors.newFixedThreadPool(4);

        Callable<Integer> task =
                new OrderPriceTask(10);

        Future<Integer> future =
                executor.submit(task);

        System.out.println("Görev ilgili thread'a verildi.");

        Integer result = future.get();

        System.out.println("Sipariş fiyatı: " + result);

        executor.shutdown();
    }
}
