package tr.com.huseyinaydin.loganalyzer.reading;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Bir log dosyasının satırlarını tembel (lazy) bir stream olarak verir.
 * Dönen stream bir dosya tanıtıcısı tutar; çağıran taraf mutlaka kapatmalıdır.
 */
public interface LogFileReader {

    Stream<String> lines(Path file);
}
