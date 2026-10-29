package tr.com.huseyinaydin.demo;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 * Türkçe karakterli bir satırı yanlış kodlamayla okuyunca ne olduğunu gösterir.
 */
public final class EncodingDemo implements DemoStep {

    private static final String FOLDER_NAME = "demo-encoding";
    private static final String TURKISH_LINE =
            "2026-10-27 14:32:10 | ERROR | PaymentService | Ödeme alınamadı, müşteri: Çağlar Şişli";

    private final PrintStream out;

    public EncodingDemo(PrintStream out) {
        this.out = Objects.requireNonNull(out, "out");
    }

    @Override
    public String title() {
        return "Karakter kodlaması: neden StandardCharsets.UTF_8 açıkça yazılır";
    }

    @Override
    public void run(Path workDirectory) throws IOException {
        Path file = Files.createDirectories(workDirectory.resolve(FOLDER_NAME)).resolve("turkish.log");
        Files.writeString(file, TURKISH_LINE, StandardCharsets.UTF_8);

        out.println("Bu JVM'in varsayılan kodlaması: " + Charset.defaultCharset());
        out.println("Karakter sayısı: " + TURKISH_LINE.length() + ", dosya boyutu (byte): " + Files.size(file));
        out.println("  (Fark, Türkçe harflerin UTF-8'de 2 byte tutmasından geliyor.)");

        out.println("UTF-8 ile okundu     : " + readFirstLine(file, StandardCharsets.UTF_8));
        out.println("ISO-8859-1 ile okundu: " + readFirstLine(file, StandardCharsets.ISO_8859_1));
        out.println("Java 18'den beri varsayılan UTF-8, ama dosya başka sistemden gelebilir, kod eski JVM'de");
        out.println("çalışabilir. Kodlamayı yazmak, varsayımı koddan okunur hale getirir.");
    }

    private String readFirstLine(Path file, Charset charset) throws IOException {
        List<String> lines = Files.readAllLines(file, charset);
        return lines.getFirst();
    }
}
