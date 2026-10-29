package tr.com.huseyinaydin.loganalyzer.report;

import tr.com.huseyinaydin.loganalyzer.domain.AnalysisResult;
import tr.com.huseyinaydin.loganalyzer.domain.ErrorSummary;
import tr.com.huseyinaydin.loganalyzer.domain.LogLevel;

import java.util.ArrayList;
import java.util.List;

import static tr.com.huseyinaydin.loganalyzer.domain.LogLineFormat.TIMESTAMP_FORMATTER;

/**
 * Başka araçlara (Excel, script) aktarılacak "metrik,deger" biçiminde CSV rapor.
 */
public final class CsvReportFormatter implements ReportFormatter {

    private static final String EXTENSION = "csv";
    private static final String DELIMITER = ",";
    private static final String QUOTE = "\"";
    private static final String ESCAPED_QUOTE = "\"\"";
    private static final String HEADER = "metrik" + DELIMITER + "deger";
    private static final String LEVEL_KEY_PREFIX = "seviye_";

    @Override
    public String fileExtension() {
        return EXTENSION;
    }

    @Override
    public List<String> format(AnalysisResult result) {
        List<String> rows = new ArrayList<>();
        rows.add(HEADER);
        rows.add(row("ayristirilan_satir", result.parsedLineCount()));
        rows.add(row("hatali_satir", result.malformedLineCount()));
        for (LogLevel level : LogLevel.values()) {
            rows.add(row(LEVEL_KEY_PREFIX + level.name(), result.countOf(level)));
        }
        result.errorSummary().ifPresent(summary -> rows.addAll(errorRows(summary)));
        return rows;
    }

    private List<String> errorRows(ErrorSummary summary) {
        return List.of(
                row("en_cok_hata_veren_servis", summary.topService()),
                row("en_cok_hata_sayisi", summary.topServiceErrorCount()),
                row("ilk_hata_zamani", summary.firstErrorAt().format(TIMESTAMP_FORMATTER)),
                row("son_hata_zamani", summary.lastErrorAt().format(TIMESTAMP_FORMATTER)));
    }

    private String row(String key, Object value) {
        return escape(key) + DELIMITER + escape(String.valueOf(value));
    }

    // Servis adında virgül ya da tırnak varsa sütunlar kaymasın diye RFC 4180'e göre kaçırılır.
    private String escape(String value) {
        boolean needsQuoting = value.contains(DELIMITER) || value.contains(QUOTE)
                || value.contains("\n") || value.contains("\r");
        if (!needsQuoting) {
            return value;
        }
        return QUOTE + value.replace(QUOTE, ESCAPED_QUOTE) + QUOTE;
    }
}
