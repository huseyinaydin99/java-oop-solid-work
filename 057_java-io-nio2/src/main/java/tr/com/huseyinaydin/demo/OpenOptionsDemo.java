package tr.com.huseyinaydin.demo;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;

/**
 * StandardOpenOption seçeneklerinin aynı dosya üzerindeki etkisi.
 * Senaryo: her analiz çalıştırmasını bir geçmiş dosyasına not etmek.
 */
public final class OpenOptionsDemo implements DemoStep {

    private static final String FOLDER_NAME = "demo-open-options";

    private final PrintStream out;

    public OpenOptionsDemo(PrintStream out) {
        this.out = Objects.requireNonNull(out, "out");
    }

    @Override
    public String title() {
        return "StandardOpenOption: CREATE, APPEND, TRUNCATE_EXISTING, CREATE_NEW";
    }

    @Override
    public void run(Path workDirectory) throws IOException {
        Path history = Files.createDirectories(workDirectory.resolve(FOLDER_NAME)).resolve("analysis-history.log");

        // CREATE + APPEND: dosya yoksa açar, varsa sonuna ekler. Geçmiş kaydı için doğru seçim.
        writeAndShow(history, "1. çalıştırma tamamlandı", "CREATE, APPEND", CREATE, APPEND);
        writeAndShow(history, "2. çalıştırma tamamlandı", "CREATE, APPEND", CREATE, APPEND);

        // TRUNCATE_EXISTING: içeriği sıfırlar. Rapor gibi her seferinde baştan üretilen dosyalar için.
        writeAndShow(history, "Geçmiş temizlendi", "CREATE, TRUNCATE_EXISTING, WRITE", CREATE, TRUNCATE_EXISTING, WRITE);

        out.println("Seçenek verilmezse Files.write varsayılan olarak CREATE, TRUNCATE_EXISTING, WRITE kullanır.");
        demonstrateCreateNew(history);
    }

    private void writeAndShow(Path file, String line, String label, OpenOption... options) throws IOException {
        Files.write(file, List.of(line), StandardCharsets.UTF_8, options);
        out.println("[" + label + "] -> " + Files.readAllLines(file, StandardCharsets.UTF_8));
    }

    private void demonstrateCreateNew(Path existingFile) throws IOException {
        try {
            // CREATE_NEW: dosya varsa hata verir. Başka bir sürecin dosyasını ezmemek için kullanılır.
            Files.write(existingFile, List.of("ezilmemeli"), StandardCharsets.UTF_8, CREATE_NEW, WRITE);
        } catch (FileAlreadyExistsException e) {
            // Bu hata demonun beklenen sonucu; mesajı gösterip devam ediyoruz.
            out.println("[CREATE_NEW] -> FileAlreadyExistsException: " + Path.of(e.getFile()).getFileName());
        }
    }
}
