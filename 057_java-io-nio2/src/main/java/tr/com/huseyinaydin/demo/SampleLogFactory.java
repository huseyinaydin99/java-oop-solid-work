package tr.com.huseyinaydin.demo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 * Demo için gerçekçi örnek log dosyaları üretir. Bilerek hatalı satırlar ve
 * analiz dışı kalması gereken bir .txt dosyası da içerir.
 */
public final class SampleLogFactory {

    private static final List<String> PAYMENT_SERVICE_LOG = List.of(
            "2026-10-27 09:00:01 | INFO | PaymentService | Servis başlatıldı",
            "2026-10-27 09:15:22 | WARN | PaymentService | Banka yanıtı yavaş: 2300 ms",
            "2026-10-27 09:16:05 | ERROR | PaymentService | Ödeme alınamadı, sipariş: 1042",
            "2026-10-27 10:02:44 | INFO | PaymentService | İade işlemi tamamlandı",
            "bozuk satır: zaman damgası ve ayraç yok",
            "2026-10-27 11:30:10 | ERROR | PaymentService | Kart doğrulaması başarısız (müşteri: Şükrü Öztürk)",
            "",
            "2026-10-27 14:32:10 | ERROR | PaymentService | Ödeme alınamadı");

    private static final List<String> ORDER_SERVICE_LOG = List.of(
            "2026-10-27 08:55:00 | INFO | OrderService | Sipariş kuyruğu dinleniyor",
            "2026-10-27 09:20:13 | INFO | OrderService | Sipariş oluşturuldu: 1042",
            "2026-10-27 09:21:40 | WARN | StockService | Stok azaldı: ürün 77, kalan 3",
            "2026-10-27 12:05:09 | ERROR | OrderService | Kargo firmasına ulaşılamadı",
            "2026-13-45 99:99:99 | ERROR | OrderService | Geçersiz tarih",
            "2026-10-27 13:10:00 | FATAL | OrderService | Tanımsız seviye",
            "2026-10-27 16:45:30 | ERROR | StockService | Stok senkronizasyonu çöktü",
            "2026-10-27 17:00:00 | INFO | OrderService | Gün sonu: başarılı | başarısız oranı 9/1");

    private static final List<String> NOT_A_LOG_FILE = List.of("Bu dosya .log uzantılı değil, analiz edilmemeli.");

    private final Charset charset;

    public SampleLogFactory(Charset charset) {
        this.charset = Objects.requireNonNull(charset, "charset");
    }

    public List<Path> createSampleLogs(Path logDirectory) throws IOException {
        Files.createDirectories(logDirectory);
        Path payment = Files.write(logDirectory.resolve("payment-service.log"), PAYMENT_SERVICE_LOG, charset);
        Path order = Files.write(logDirectory.resolve("order-service.log"), ORDER_SERVICE_LOG, charset);
        Files.write(logDirectory.resolve("notes.txt"), NOT_A_LOG_FILE, charset);
        return List.of(payment, order);
    }
}
