package tr.com.huseyinaydin.minmaxheap;

public class MemoryMonitor {

    public static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime();

        printHeap(runtime);

        byte[] data = new byte[200 * 1024 * 1024]; // 200 MB lık Heap alanı maşaAllah!

        printHeap(runtime);
    }

    private static void printHeap(Runtime runtime) {

        long total = runtime.totalMemory() / (1024 * 1024); // MB formülü var
        long max = runtime.maxMemory() / (1024 * 1024);

        System.out.println("Kullanılabilir Heap: " + total + " MB");
        System.out.println("Maksimum Heap: " + max + " MB");
        System.out.println("--------------------");
    }
}