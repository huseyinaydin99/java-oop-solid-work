package tr.com.huseyinaydin.concurrency;

public class VolatileCounter {

    private volatile int counter = 0;

    public void increment() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}