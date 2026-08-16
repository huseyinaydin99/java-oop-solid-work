package tr.com.huseyinaydin.minmaxheap;

public class MinMaxHeap {
    public static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime();
                                                    // MB cinsine dönüştürme formülü
        long totalMemory = runtime.totalMemory() / (1024 * 1024); // şu an ki total
        long maxMemory = runtime.maxMemory() / (1024 * 1024); // ulaşılabilecek en fazla heap alanı

        System.out.println("Heap başlangıç: " + totalMemory + " MB");
        System.out.println("Heap maksimum: " + maxMemory + " MB");
    }
}