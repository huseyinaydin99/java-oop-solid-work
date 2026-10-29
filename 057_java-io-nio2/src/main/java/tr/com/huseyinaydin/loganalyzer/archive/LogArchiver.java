package tr.com.huseyinaydin.loganalyzer.archive;

import java.nio.file.Path;

/**
 * İşlenmiş bir log dosyasını arşive kaldırır ve yeni konumunu döner.
 */
public interface LogArchiver {

    Path archive(Path file);
}
