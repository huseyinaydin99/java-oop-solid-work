package tr.com.huseyinaydin.nativememory;

/*
    IntelliJ'de VM options kısmına:
    -XX:NativeMemoryTracking=summary

    Program çalışırken terminalden:
    jcmd -l

    PID'yi bulup:
    jcmd <PID> VM.native_memory summary

    Burada özellikle Thread, Class, Code, GC, Internal gibi Native Memory
    kategorilerini gözlemleyeceksin; users dizisi ise esas olarak Heap'i büyütecek.
*/

public class Main {

    static class User {
        private String name;

        public User(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) throws Exception {

        // Heap kullanımı
        User[] users = new User[1_000_000]; // kocaman bir Heap alanı

        for (int i = 0; i < users.length; i++) {
            users[i] = new User("User-" + i); // üff her eleman new'leniyor goca goca dömbekleri war goca ğoca (:
        }

        // Thread altyapısı + Thread Stack kullanımı
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(300_000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            thread.start();
        }

        // JVM'nin çalışmaya devam etmesini sağla
        Thread.sleep(300_000);
    }
}