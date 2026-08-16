package tr.com.huseyinaydin.outofmemory;

import java.util.ArrayList;
import java.util.List;

public class HeapOutOfMemory {
    public static void main(String[] args) {

        List<byte[]> memory = new ArrayList<>();

        while (true) {
            memory.add(new byte[10 * 1024 * 1024]); // 10 MB her seferinde - sonsuza kadar - heap taşar max sınır aşılır - outofmemory fırlatılır

            System.out.println("10 MB bellek tahsis edildi.");
        }
    }
}