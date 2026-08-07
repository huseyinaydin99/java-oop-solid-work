package tr.com.huseyinaydin.util;

public final class SleepUtil {

    private SleepUtil() {
    }

    public static void sleep(long millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

}