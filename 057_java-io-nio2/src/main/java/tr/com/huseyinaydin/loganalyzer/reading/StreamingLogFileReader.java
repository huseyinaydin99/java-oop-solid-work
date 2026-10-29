package tr.com.huseyinaydin.loganalyzer.reading;

import tr.com.huseyinaydin.loganalyzer.exception.LogReadException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Files.lines ile satır satır okur; dosya ne kadar büyük olursa olsun bellekte tek satır tutulur.
 */
public final class StreamingLogFileReader implements LogFileReader {

    private final Charset charset;

    // Kodlama dışarıdan verilir: log dosyasının hangi kodlamayla yazıldığını okuyucu tahmin etmemeli.
    public StreamingLogFileReader(Charset charset) {
        this.charset = Objects.requireNonNull(charset, "charset");
    }

    @Override
    public Stream<String> lines(Path file) {
        try {
            return Files.lines(file, charset);
        } catch (IOException e) {
            throw new LogReadException(file, e);
        }
    }
}
