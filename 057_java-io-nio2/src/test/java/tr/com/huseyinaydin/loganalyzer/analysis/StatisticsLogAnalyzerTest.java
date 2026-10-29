package tr.com.huseyinaydin.loganalyzer.analysis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import tr.com.huseyinaydin.loganalyzer.domain.AnalysisResult;
import tr.com.huseyinaydin.loganalyzer.domain.ErrorSummary;
import tr.com.huseyinaydin.loganalyzer.domain.LogLevel;
import tr.com.huseyinaydin.loganalyzer.parsing.PipeDelimitedLogLineParser;
import tr.com.huseyinaydin.loganalyzer.reading.LogFileReader;
import tr.com.huseyinaydin.loganalyzer.reading.StreamingLogFileReader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatisticsLogAnalyzerTest {

    @TempDir
    Path tempDir;

    private final LogAnalyzer analyzer = new StatisticsLogAnalyzer(new PipeDelimitedLogLineParser());
    private final LogFileReader reader = new StreamingLogFileReader(StandardCharsets.UTF_8);

    @Test
    void countsLevelsMalformedLinesAndSummarizesErrors() throws IOException {
        Path logFile = tempDir.resolve("app.log");
        Files.write(logFile, List.of(
                "2026-10-27 09:00:00 | INFO | OrderService | Başladı",
                "2026-10-27 15:00:00 | ERROR | PaymentService | Ödeme alınamadı",
                "2026-10-27 09:30:00 | WARN | StockService | Stok azaldı",
                "bozuk satır",
                "",
                "2026-10-27 08:15:00 | ERROR | PaymentService | Kart reddedildi",
                "2026-10-27 12:00:00 | ERROR | OrderService | Kargo hatası"), StandardCharsets.UTF_8);

        AnalysisResult result;
        try (Stream<String> lines = reader.lines(logFile)) {
            result = analyzer.analyze(lines);
        }

        assertEquals(1, result.countOf(LogLevel.INFO));
        assertEquals(1, result.countOf(LogLevel.WARN));
        assertEquals(3, result.countOf(LogLevel.ERROR));
        assertEquals(5, result.parsedLineCount());
        assertEquals(1, result.malformedLineCount());

        ErrorSummary summary = result.errorSummary().orElseThrow();
        assertEquals("PaymentService", summary.topService());
        assertEquals(2, summary.topServiceErrorCount());
        // Satır sırası değil zaman sırası esas alınmalı.
        assertEquals(LocalDateTime.of(2026, 10, 27, 8, 15), summary.firstErrorAt());
        assertEquals(LocalDateTime.of(2026, 10, 27, 15, 0), summary.lastErrorAt());
    }
}
