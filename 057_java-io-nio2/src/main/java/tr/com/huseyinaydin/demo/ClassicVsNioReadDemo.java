package tr.com.huseyinaydin.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Aynı dosyayı klasik java.io ve NIO.2 ile okuyup farkı gösterir.
 */
public final class ClassicVsNioReadDemo implements DemoStep {

    private static final String FOLDER_NAME = "demo-classic-vs-nio";
    private static final List<String> SAMPLE_LINES = List.of(
            "2026-10-27 10:02:44 | INFO | PaymentService | İade işlemi tamamlandı",
            "2026-10-27 11:30:10 | ERROR | PaymentService | Kart doğrulaması başarısız (müşteri: Şükrü Öztürk)");

    private final PrintStream out;

    public ClassicVsNioReadDemo(PrintStream out) {
        this.out = Objects.requireNonNull(out, "out");
    }

    @Override
    public String title() {
        return "Klasik I/O (FileReader) ile NIO.2 (Files.newBufferedReader) karşılaştırması";
    }

    @Override
    public void run(Path workDirectory) throws IOException {
        Path file = Files.createDirectories(workDirectory.resolve(FOLDER_NAME)).resolve("compare.log");
        Files.write(file, SAMPLE_LINES, StandardCharsets.UTF_8);

        List<String> classic = readClassic(file);
        List<String> nio = readNio(file);

        out.println("Klasik okuma : " + classic.size() + " satır, son satır -> " + classic.getLast());
        out.println("NIO.2 okuma  : " + nio.size() + " satır, son satır -> " + nio.getLast());
        out.println("Sonuçlar aynı mı? " + classic.equals(nio));
        out.println("Fark: java.io File nesnesi ve iki sarmalayıcı ister, hata mesajları belirsizdir.");
        out.println("      NIO.2 Path ile çalışır, tek satırda tamponlu okuyucu verir, hatalar daha açıklayıcıdır");
        out.println("      (ör. NoSuchFileException dosya yolunu söyler).");
    }

    private List<String> readClassic(Path file) throws IOException {
        // FileReader'ın kodlama alan yapıcısı Java 11'de geldi; öncesinde platform kodlaması kullanılırdı
        // ve Windows'ta Türkçe karakterler bozulurdu.
        try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile(), StandardCharsets.UTF_8))) {
            return readAll(reader);
        }
    }

    private List<String> readNio(Path file) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            return readAll(reader);
        }
    }

    private List<String> readAll(BufferedReader reader) throws IOException {
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }
}
