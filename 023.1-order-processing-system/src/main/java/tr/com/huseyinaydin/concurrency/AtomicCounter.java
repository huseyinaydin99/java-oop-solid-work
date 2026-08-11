package tr.com.huseyinaydin.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {

    private final AtomicInteger counter =
            new AtomicInteger(0);

    /*
    AtomicInteger, counter++ gibi atomik olması gereken işlemleri thread-safe şekilde
    gerçekleştirmek için kullanılır. ⚛️ Burada yalnızca değişikliğin görünür olması değil,
    artırma işleminin bölünemez (atomic) şekilde gerçekleşmesi garanti edildiği için
    eşzamanlı thread'lerin yaptığı artışlar kaybolmaz.
     */

    public void increment() {
        counter.incrementAndGet();
    }

    public int getCounter() {
        return counter.get();
    }
}