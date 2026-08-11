package tr.com.huseyinaydin.app;

import tr.com.huseyinaydin.concurrency.AtomicCounter;
import tr.com.huseyinaydin.concurrency.VolatileCounter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplicationVolatileCounterDemo {

    public static void main(String[] args) throws InterruptedException {

        VolatileCounter counter = new VolatileCounter();

        AtomicCounter counter2 = new AtomicCounter();

        ExecutorService executor =
                Executors.newFixedThreadPool(4);

        for (int i = 0; i < 1000; i++) {
            executor.submit(counter::increment);
        }

        for (int i = 0; i < 1000; i++) {
            executor.submit(counter2::increment);
        }

        executor.shutdown();

        /*
        Thread.yield() thread'i durdurmaz veya işi bırakmaz; sadece işletim sistemine
        "Ben şu anda çalışmaya devam etmek zorunda değilim, CPU'yu bekleyen başka bir
        thread varsa ona çalışma fırsatı verebilirsin" şeklinde bir öneride bulunur.
        🧵 Buradaki while döngüsünde Thread Pool'un işleri bitene kadar sürekli kontrol
        yapıldığı için yield() CPU'yu boş yere tamamen bu kontrol döngüsüne ayırmak
        yerine başka thread'lerin çalışmasına fırsat vermeyi amaçlar; ancak
        bu sadece bir tavsiye olduğu için işletim sistemi bunu uygulamak zorunda değildir. ⚙️
         */
        while (!executor.isTerminated()) {
            Thread.yield();
        }

        System.out.println("Atomik olmayan counter");

        System.out.println(
                "Beklenen : 1000"
        );

        System.out.println(
                "Ama aslında olan   : " + counter.getCounter()
        );

        System.out.printf("------------\n");

        System.out.println("Atomik olan counter");

        System.out.println(
                "Beklenen : 1000"
        );

        System.out.println(
                "Ama aslında olan   : " + counter2.getCounter()
        );
    }
}