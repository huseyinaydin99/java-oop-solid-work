package tr.com.huseyinaydin.loganalyzer.archive;

import tr.com.huseyinaydin.loganalyzer.exception.ArchiveException;

import java.io.IOException;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Dosyayı, yanındaki arşiv klasörüne tarih damgalı yeni bir adla taşır.
 * Örnek: logs/payment.log -> logs/archive/payment_20261027-143210.log
 */
public final class TimestampedLogArchiver implements LogArchiver {

    private static final DateTimeFormatter STAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
    private static final String STAMP_SEPARATOR = "_";
    private static final char EXTENSION_DOT = '.';
    private static final int NO_EXTENSION = -1;

    private final String archiveFolderName;
    private final Clock clock;

    // Clock dışarıdan gelir: testte sabit zaman verip dosya adını kesin olarak doğrulayabilmek için.
    public TimestampedLogArchiver(String archiveFolderName, Clock clock) {
        this.archiveFolderName = Objects.requireNonNull(archiveFolderName, "archiveFolderName");
        this.clock = Objects.requireNonNull(clock, "clock");
    }

    @Override
    public Path archive(Path file) {
        Path archiveDirectory = file.resolveSibling(archiveFolderName);
        Path target = archiveDirectory.resolve(timestampedName(file));
        try {
            Files.createDirectories(archiveDirectory);
            return moveAtomicallyIfPossible(file, target);
        } catch (IOException e) {
            throw new ArchiveException(file, target, e);
        }
    }

    private Path moveAtomicallyIfPossible(Path source, Path target) throws IOException {
        try {
            // Atomik taşımada dosya ya tamamen eski yerdedir ya tamamen yeni yerde; yarım kopya kalmaz.
            return Files.move(source, target, ATOMIC_MOVE);
        } catch (AtomicMoveNotSupportedException e) {
            // Farklı disk/bölüm arasında atomik taşıma yapılamaz; o zaman kopyala-sil yoluna düşülür.
            // Aynı saniyede aynı adlı dosya arşivlenirse eskisinin üzerine yazılması bilinçli bir tercihtir.
            return Files.move(source, target, REPLACE_EXISTING);
        }
    }

    private String timestampedName(Path file) {
        String fileName = file.getFileName().toString();
        String stamp = LocalDateTime.now(clock).format(STAMP_FORMATTER);
        int dotIndex = fileName.lastIndexOf(EXTENSION_DOT);
        if (dotIndex == NO_EXTENSION) {
            return fileName + STAMP_SEPARATOR + stamp;
        }
        return fileName.substring(0, dotIndex) + STAMP_SEPARATOR + stamp + fileName.substring(dotIndex);
    }
}
