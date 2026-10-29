package tr.com.huseyinaydin.loganalyzer.report;

import tr.com.huseyinaydin.loganalyzer.domain.AnalysisResult;
import tr.com.huseyinaydin.loganalyzer.domain.ErrorSummary;
import tr.com.huseyinaydin.loganalyzer.domain.LogLevel;

import java.util.ArrayList;
import java.util.List;

import static tr.com.huseyinaydin.loganalyzer.domain.LogLineFormat.TIMESTAMP_FORMATTER;

/**
 * İnsanın okuyacağı düz metin rapor.
 */
public final class TextReportFormatter implements ReportFormatter {

    private static final String EXTENSION = "txt";
    private static final String TITLE = "=== Log Analiz Raporu ===";
    private static final String NO_ERROR_MESSAGE = "  Hata kaydı bulunamadı.";
    private static final String LEVEL_LINE = "  %-5s : %d";

    @Override
    public String fileExtension() {
        return EXTENSION;
    }

    @Override
    public List<String> format(AnalysisResult result) {
        List<String> lines = new ArrayList<>();
        lines.add(TITLE);
        lines.add("Ayrıştırılan satır    : " + result.parsedLineCount());
        lines.add("Hatalı formatlı satır : " + result.malformedLineCount());
        lines.add("");
        lines.add("Seviyelere göre dağılım");
        for (LogLevel level : LogLevel.values()) {
            lines.add(LEVEL_LINE.formatted(level.name(), result.countOf(level)));
        }
        lines.add("");
        lines.add("Hata özeti");
        lines.addAll(result.errorSummary()
                .map(this::formatErrorSummary)
                .orElse(List.of(NO_ERROR_MESSAGE)));
        return lines;
    }

    private List<String> formatErrorSummary(ErrorSummary summary) {
        return List.of(
                "  En çok hata veren servis : %s (%d hata)".formatted(summary.topService(), summary.topServiceErrorCount()),
                "  İlk hata zamanı          : " + summary.firstErrorAt().format(TIMESTAMP_FORMATTER),
                "  Son hata zamanı          : " + summary.lastErrorAt().format(TIMESTAMP_FORMATTER));
    }
}
