package tr.com.huseyinaydin.demo;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Files sınıfının temel işlemleri: exists, createDirectories, write, size, copy, readAllLines, delete.
 */
public final class FileBasicsDemo implements DemoStep {

    private static final String FOLDER_NAME = "demo-files";
    private static final List<String> SAMPLE_LINES = List.of(
            "2026-10-27 09:00:01 | INFO | PaymentService | Servis başlatıldı",
            "2026-10-27 09:16:05 | ERROR | PaymentService | Ödeme alınamadı");

    private final PrintStream out;

    public FileBasicsDemo(PrintStream out) {
        this.out = Objects.requireNonNull(out, "out");
    }

    @Override
    public String title() {
        return "Files: exists, createDirectories, write, size, copy, readAllLines, delete";
    }

    @Override
    public void run(Path workDirectory) throws IOException {
        // createDirectories ara klasörleri de açar ve klasör zaten varsa hata vermez; createDirectory ikisini de yapmaz.
        Path folder = Files.createDirectories(workDirectory.resolve(FOLDER_NAME).resolve("nested"));
        Path original = folder.resolve("sample.log");
        Path backup = folder.resolve("sample.log.bak");

        out.println("Yazmadan önce exists: " + Files.exists(original));
        Files.write(original, SAMPLE_LINES, StandardCharsets.UTF_8);
        out.println("Yazdıktan sonra exists: " + Files.exists(original));
        out.println("Boyut (byte): " + Files.size(original));

        // REPLACE_EXISTING verilmezse hedef varsa FileAlreadyExistsException alınır.
        Files.copy(original, backup, REPLACE_EXISTING);
        out.println("Kopya boyutu eşit mi? " + (Files.size(original) == Files.size(backup)));

        // readAllLines tüm dosyayı belleğe alır; küçük dosyalar için pratik, büyükler için değil.
        List<String> lines = Files.readAllLines(backup, StandardCharsets.UTF_8);
        out.println("Kopyadan okunan satır sayısı: " + lines.size());

        Files.delete(backup);
        out.println("Sildikten sonra kopya exists: " + Files.exists(backup));
    }
}
