package tr.com.huseyinaydin.app;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

/*
stream() işlemleri varsayılan olarak tek thread üzerinde ve sıralı yürütürken,
parallelStream() elemanları birden fazla thread arasında bölerek ForkJoinPool.commonPool()
üzerinden paralel olarak işler. Bu nedenle ikinci çıktıda main,
ForkJoinPool.commonPool-worker-* gibi farklı thread isimleri görebilir ve elemanların
ekrana geliş sırasının değiştiğini gözlemleyebilirsin.
 */

public class ApplicationParallelStream {

    public static void main(String[] args) {
        List<Integer> numbers = IntStream.rangeClosed(1, 10)
                .boxed()
                .toList();

        numbers.stream()
                .forEach(number ->
                        System.out.println(
                                Thread.currentThread().getName() + " -> " + number
                        )
                );

        System.out.println("---");

        numbers.parallelStream()
                .forEach(number ->
                        System.out.println(
                                Thread.currentThread().getName() + " -> " + number
                        )
                );

        //------

        /*
        .boxed(), primitive türlerle çalışan IntStream gibi özel stream'leri Integer, Long veya Double
        nesneleri taşıyan normal Stream<T> yapısına dönüştürerek List<Integer> gibi nesne
        tabanlı koleksiyonlarla çalışabilmesini sağlar.
         */
        List<Integer> numbersTwo = IntStream.rangeClosed(1, 8)
                .boxed()
                .toList();

        numbersTwo.parallelStream()
                .forEach(number -> {

                    System.out.println(
                            "Başladı  : " + number +
                                    " | " + Thread.currentThread().getName()
                    );

                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    System.out.println(
                            "Bitti     : " + number +
                                    " | " + Thread.currentThread().getName()
                    );
                });

        System.out.println("-------");

        ForkJoinPool commonPool = ForkJoinPool.commonPool();

        System.out.println("Parallelism: " + commonPool.getParallelism());
        System.out.println("Pool Size : " + commonPool.getPoolSize());

        /*
        parallelStream(), varsayılan olarak ForkJoinPool.commonPool()'u kullanır
        ve stream'deki işleri bu pool'un worker(işçi) thread'lerine dağıtır. ⚙️

        getParallelism():
        Pool'un aynı anda kaç worker thread ile çalışmayı hedeflediğini gösterir. 🔄

        getPoolSize():
        Pool'un o anda oluşturmuş olduğu worker thread sayısını gösterir. 🔧

        Kısaca:
        getParallelism() → "Kaç thread ile çalışmayı hedefliyor?"
        getPoolSize()    → "Şu anda kaç thread oluşturulmuş?"

        ForkJoinPool.commonPool() bu değerleri JVM'nin mevcut işlemci çekirdeği sayısını
        (availableProcessors()) temel alarak varsayılan şekilde otomatik belirler. ⚙️
        */

        numbers.parallelStream()
                .forEachOrdered(number ->
                        System.out.println(
                                Thread.currentThread().getName() +
                                        " -> " + number
                        )
                );
        /*
        forEachOrdered(), paralel stream kullanılmasına rağmen stream'in encounter order'ını
        koruyarak sonuçların sırayla tüketilmesini sağlar. 🔒 Ancak bu sıra garantisinin
        bir maliyeti vardır; thread'lerin tamamen bağımsız ilerlemesini kısıtlayabildiği
        için forEach() kadar paralel çalışma avantajı sağlayamayabilir.

        Buradaki kritik ayrım paralel olup olmamaları değil, sonucu tüketirken sıra garantisinin
        bulunup bulunmamasıdır; her iki işlem de parallelStream() üzerinden çalışabilir ancak
        forEachOrdered() encounter order'ı korumak için ek koordinasyon gerektirir. ⚙️ Bu nedenle
        sıralamanın önemli olmadığı durumlarda forEach(), sıralamanın mutlaka korunması
        gerektiğinde ise forEachOrdered() tercih edilmelidir.
         */
    }

}