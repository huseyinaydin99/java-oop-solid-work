package tr.com.huseyinaydin.demo;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Konsolda gösterilecek tek bir demo adımı. Yeni bir konu eklemek için Main'e
 * yeni bir DemoStep vermek yeterli; mevcut adımlara dokunulmaz.
 */
public interface DemoStep {

    String title();

    // Demo kodu öğretici olsun diye IOException'ı doğrudan gösterir; Main tek noktada ele alır.
    void run(Path workDirectory) throws IOException;
}
