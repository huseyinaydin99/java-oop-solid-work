package tr.com.huseyinaydin.maxheap;

public class MaxHeap {

    public static void main(String[] args) {

        long maxMemory = Runtime.getRuntime().maxMemory();
                                                               // bu kısım MB dönüşümü içindir!
        System.out.println("JVM maksimum Heap: " + maxMemory / (1024 * 1024) + " MB");

    }

}