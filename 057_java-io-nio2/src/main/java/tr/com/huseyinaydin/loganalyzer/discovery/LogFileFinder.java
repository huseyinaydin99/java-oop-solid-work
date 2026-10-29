package tr.com.huseyinaydin.loganalyzer.discovery;

import java.nio.file.Path;
import java.util.List;

/**
 * İşlenecek log dosyalarını bulur.
 */
public interface LogFileFinder {

    List<Path> find(Path directory);
}
