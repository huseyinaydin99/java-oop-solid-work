package tr.com.huseyinaydin.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ThreadPoolConfig {

    private ThreadPoolConfig() {
    }

    private static final ExecutorService EXECUTOR =
            Executors.newFixedThreadPool(4);

    public static ExecutorService executor() {
        return EXECUTOR;
    }

    public static void shutdown() {
        EXECUTOR.shutdown();
    }

}