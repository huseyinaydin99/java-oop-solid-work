package tr.com.huseyinaydin.app;

import tr.com.huseyinaydin.concurrency.OrderPriceTask;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ApplicationMultipleCallableDemo {

    public static void main(String[] args) throws Exception {

        ExecutorService executor =
                Executors.newFixedThreadPool(4);

        List<Callable<Integer>> tasks = List.of(
                new OrderPriceTask(1),
                new OrderPriceTask(2),
                new OrderPriceTask(3),
                new OrderPriceTask(4)
        );

        List<Future<Integer>> futures =
                executor.invokeAll(tasks);

        for (Future<Integer> future : futures) {

            System.out.println(
                    "Sipariş fiyatı: " + future.get()
            );
        }

        executor.shutdown();
    }
}