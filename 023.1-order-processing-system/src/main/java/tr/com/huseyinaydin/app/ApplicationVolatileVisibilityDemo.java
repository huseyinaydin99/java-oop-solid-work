package tr.com.huseyinaydin.app;

public class ApplicationVolatileVisibilityDemo {
    private static volatile boolean running = true;

    /*
    volatile, running değişkeninin bir thread tarafından değiştirilmesinin diğer thread'ler
    tarafından görülmesini (visibility) garanti eder. 👀 main thread'i running = false
    yaptığında worker güncel değeri görebilir ve while döngüsünden çıkar; volatile olmasaydı
    thread'in eski değeri görmeye devam etmesi mümkün olabilirdi.
     */
    public static void main(String[] args) throws InterruptedException {
        // Thread'i var say ki = işçi

        Thread worker = new Thread(() -> {
            int i = 0;
            while (running) {
                System.out.println(i++);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // İşçi iş yapıyor
            }

            System.out.println("İşçi durdu!");
        });

        worker.start();

        Thread.sleep(1000);

        System.out.println("Ana/main thread işçiyi durduruyor...");

        running = false;

        worker.join();

        System.out.println("Uygulama durduruldu!");
    }
}
