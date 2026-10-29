package tr.com.huseyinaydin.demo;

import tr.com.huseyinaydin.loganalyzer.domain.LogEntry;
import tr.com.huseyinaydin.loganalyzer.domain.LogLevel;
import tr.com.huseyinaydin.loganalyzer.domain.LogLineFormat;
import tr.com.huseyinaydin.loganalyzer.parsing.LogLineParser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Büyük bir log dosyasını Files.lines ile belleği şişirmeden işler.
 */
public final class LargeFileStreamingDemo implements DemoStep {

    private static final String FOLDER_NAME = "demo-large-file";
    private static final int LINE_COUNT = 200_000;
    private static final int ERROR_EVERY_NTH_LINE = 50;
    private static final LocalDateTime START_TIME = LocalDateTime.of(2026, 10, 27, 0, 0);
    private static final double BYTES_PER_MEGABYTE = 1024.0 * 1024.0;

    private final LogLineParser parser;
    private final PrintStream out;

    public LargeFileStreamingDemo(LogLineParser parser, PrintStream out) {
        this.parser = Objects.requireNonNull(parser, "parser");
        this.out = Objects.requireNonNull(out, "out");
    }

    @Override
    public String title() {
        return "Büyük dosya: Files.lines ile stream tabanlı okuma";
    }

    @Override
    public void run(Path workDirectory) throws IOException {
        Path file = Files.createDirectories(workDirectory.resolve(FOLDER_NAME)).resolve("big.log");
        generate(file);
        out.printf("Oluşturulan dosya: %,d satır, %.1f MB%n", LINE_COUNT, Files.size(file) / BYTES_PER_MEGABYTE);

        // Files.lines satırları talep edildikçe okur; aynı anda bellekte yalnızca tampon kadar veri olur.
        // Stream arkada açık bir dosya tutar: try-with-resources olmadan dosya tanıtıcısı sızar.
        try (Stream<String> lines = Files.lines(file, StandardCharsets.UTF_8)) {
            long errorCount = lines.map(parser::parse)
                    .flatMap(Optional::stream)
                    .filter(entry -> entry.level() == LogLevel.ERROR)
                    .count();
            out.printf("ERROR sayısı: %,d%n", errorCount);
        }
        out.println("readAllLines aynı işi yapardı ama 200 bin satırı önce listeye doldururdu.");
    }

    private void generate(Path file) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            for (int index = 0; index < LINE_COUNT; index++) {
                writer.write(LogLineFormat.format(entryAt(index)));
                writer.newLine();
            }
        }
    }

    private LogEntry entryAt(int index) {
        boolean isError = index % ERROR_EVERY_NTH_LINE == 0;
        LogLevel level = isError ? LogLevel.ERROR : LogLevel.INFO;
        String message = isError ? "Zaman aşımı oluştu" : "İstek işlendi";
        return new LogEntry(START_TIME.plusSeconds(index), level, "GatewayService", message);
    }
}
