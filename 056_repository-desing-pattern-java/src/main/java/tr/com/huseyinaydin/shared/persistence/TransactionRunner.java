package tr.com.huseyinaydin.shared.persistence;

import java.util.function.Supplier;

/** Bir iş senaryosunu tek bir transaction içinde çalıştırır. */
public interface TransactionRunner {

    <R> R inTransaction(Supplier<R> work);

    default void runInTransaction(Runnable work) {
        inTransaction(() -> {
            work.run();
            return null;
        });
    }
}
