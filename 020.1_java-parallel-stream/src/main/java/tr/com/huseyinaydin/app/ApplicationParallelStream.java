package tr.com.huseyinaydin.app;

import java.util.List;
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
    }

}