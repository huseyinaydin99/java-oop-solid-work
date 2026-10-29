package tr.com.huseyinaydin.demo;

import tr.com.huseyinaydin.resource.TrackedResource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * try-with-resources: çoklu kaynak, kapanma sırası, kendi AutoCloseable sınıfımız ve suppressed exception.
 */
public final class TryWithResourcesDemo implements DemoStep {

    private static final String FOLDER_NAME = "demo-try-with-resources";
    private static final String INDENT = "  ";
    private static final List<String> SOURCE_LINES = List.of(
            "2026-10-27 09:00:01 | INFO | OrderService | Sipariş kuyruğu dinleniyor",
            "2026-10-27 12:05:09 | ERROR | OrderService | Kargo firmasına ulaşılamadı");

    private final PrintStream out;

    public TryWithResourcesDemo(PrintStream out) {
        this.out = Objects.requireNonNull(out, "out");
    }

    @Override
    public String title() {
        return "try-with-resources: çoklu kaynak, kapanma sırası, suppressed exception";
    }

    @Override
    public void run(Path workDirectory) throws IOException {
        showClosingOrder();
        copyWithTwoResources(workDirectory);
        showSuppressedException();
    }

    private void showClosingOrder() {
        out.println("1) Kapanma sırası (açılışın tersi):");
        Consumer<String> sink = event -> out.println(INDENT + event);
        // Sonra açılan kaynak öncekine bağımlı olabilir (yazıcı -> bağlantı); bu yüzden önce o kapanır.
        try (TrackedResource connection = TrackedResource.open("veritabanı bağlantısı", sink);
             TrackedResource writer = TrackedResource.open("rapor yazıcı", sink)) {
            connection.use("sorgu");
            writer.use("satır yaz");
        }
    }

    private void copyWithTwoResources(Path workDirectory) throws IOException {
        out.println("2) Aynı anda okuyucu ve yazıcı açmak (yalnızca ERROR satırlarını kopyala):");
        Path folder = Files.createDirectories(workDirectory.resolve(FOLDER_NAME));
        Path source = folder.resolve("source.log");
        Path target = folder.resolve("errors-only.log");
        Files.write(source, SOURCE_LINES, StandardCharsets.UTF_8);

        try (BufferedReader reader = Files.newBufferedReader(source, StandardCharsets.UTF_8);
             BufferedWriter writer = Files.newBufferedWriter(target, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("| ERROR |")) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
        // Yazıcı kapanmadan tampon diske boşaltılmaz; okuma bilerek try bloğunun dışında yapıldı.
        out.println(INDENT + "Hedef dosya: " + Files.readAllLines(target, StandardCharsets.UTF_8));
    }

    private void showSuppressedException() {
        out.println("3) Suppressed exception:");
        Consumer<String> sink = event -> out.println(INDENT + event);
        try (TrackedResource resource = TrackedResource.openFailingOnClose("bozuk bağlantı", sink)) {
            resource.use("ödeme kaydı");
            throw new IllegalStateException("İş mantığı hatası: tutar negatif");
        } catch (IllegalStateException e) {
            // Asıl hata kaybolmaz; close() sırasındaki hata ona "suppressed" olarak eklenir.
            // Klasik try-finally'de close hatası asıl hatayı ezerdi.
            out.println(INDENT + "Yakalanan asıl hata : " + e.getMessage());
            for (Throwable suppressed : e.getSuppressed()) {
                out.println(INDENT + "Bastırılmış hata    : " + suppressed.getMessage());
            }
        }
    }
}
