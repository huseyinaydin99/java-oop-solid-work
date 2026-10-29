package tr.com.huseyinaydin.loganalyzer.report;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import tr.com.huseyinaydin.loganalyzer.domain.AnalysisResult;
import tr.com.huseyinaydin.loganalyzer.domain.ErrorSummary;
import tr.com.huseyinaydin.loganalyzer.domain.LogLevel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileReportWriterTest {

    private static final String REPORT_NAME = "log-report";

    @TempDir
    Path tempDir;

    private final AnalysisResult result = new AnalysisResult(
            Map.of(LogLevel.INFO, 4L, LogLevel.WARN, 1L, LogLevel.ERROR, 3L),
            2,
            Optional.of(new ErrorSummary("Ödeme, Servisi", 2,
                    LocalDateTime.of(2026, 10, 27, 9, 16, 5),
                    LocalDateTime.of(2026, 10, 27, 14, 32, 10))));

    @Test
    void writesTextReportWithTurkishCharacters() throws IOException {
        ReportWriter writer = new FileReportWriter(new TextReportFormatter(), REPORT_NAME, StandardCharsets.UTF_8);

        Path report = writer.write(result, tempDir.resolve("reports"));

        assertEquals("log-report.txt", report.getFileName().toString());
        List<String> lines = Files.readAllLines(report, StandardCharsets.UTF_8);
        assertEquals("=== Log Analiz Raporu ===", lines.getFirst());
        assertTrue(lines.contains("Ayrıştırılan satır    : 8"));
        assertTrue(lines.contains("Hatalı formatlı satır : 2"));
        assertTrue(lines.contains("  ERROR : 3"));
        assertTrue(lines.contains("  En çok hata veren servis : Ödeme, Servisi (2 hata)"));
        assertTrue(lines.contains("  İlk hata zamanı          : 2026-10-27 09:16:05"));
    }

    @Test
    void writesCsvReportAndOverwritesPreviousContent() throws IOException {
        ReportWriter writer = new FileReportWriter(new CsvReportFormatter(), REPORT_NAME, StandardCharsets.UTF_8);
        Path existing = tempDir.resolve("log-report.csv");
        Files.write(existing, List.of("eski", "rapor", "satırları", "kalmamalı", "1", "2", "3", "4", "5", "6", "7", "8"));

        Path report = writer.write(result, tempDir);

        assertEquals(List.of(
                "metrik,deger",
                "ayristirilan_satir,8",
                "hatali_satir,2",
                "seviye_INFO,4",
                "seviye_WARN,1",
                "seviye_ERROR,3",
                "en_cok_hata_veren_servis,\"Ödeme, Servisi\"",
                "en_cok_hata_sayisi,2",
                "ilk_hata_zamani,2026-10-27 09:16:05",
                "son_hata_zamani,2026-10-27 14:32:10"), Files.readAllLines(report, StandardCharsets.UTF_8));
    }
}
