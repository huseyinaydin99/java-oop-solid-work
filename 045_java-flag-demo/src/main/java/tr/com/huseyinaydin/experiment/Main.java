package tr.com.huseyinaydin.experiment;

public class Main {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        long result = 0;

        for (int i = 0; i < 100_000_000; i++) {
            result += calculate(i);
        }

        long end = System.currentTimeMillis();

        System.out.println("Sonuç: " + result);
        System.out.println("Süre: " + (end - start) + " ms");
    }

    static long calculate(int value) {
        return (long) value * 2 + 10;
    }
}