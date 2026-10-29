package tr.com.huseyinaydin.loganalyzer.report;

import tr.com.huseyinaydin.loganalyzer.domain.AnalysisResult;
import tr.com.huseyinaydin.loganalyzer.exception.ReportWriteException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;

/**
 * Formatlayıcının ürettiği satırları dosyaya yazar. Formatı bilmez, yalnızca yazmayı bilir.
 */
public final class FileReportWriter implements ReportWriter {

    // Rapor her çalıştırmada baştan üretilir; eski içeriğin sonuna eklenirse iki rapor birbirine karışır.
    private static final OpenOption[] OVERWRITE_OPTIONS = {CREATE, TRUNCATE_EXISTING, WRITE};
    private static final String EXTENSION_SEPARATOR = ".";

    private final ReportFormatter formatter;
    private final String baseFileName;
    private final Charset charset;

    public FileReportWriter(ReportFormatter formatter, String baseFileName, Charset charset) {
        this.formatter = Objects.requireNonNull(formatter, "formatter");
        this.baseFileName = Objects.requireNonNull(baseFileName, "baseFileName");
        this.charset = Objects.requireNonNull(charset, "charset");
    }

    @Override
    public Path write(AnalysisResult result, Path outputDirectory) {
        Path target = outputDirectory.resolve(baseFileName + EXTENSION_SEPARATOR + formatter.fileExtension());
        try {
            Files.createDirectories(outputDirectory);
            writeLines(target, result);
            return target;
        } catch (IOException e) {
            throw new ReportWriteException(target, e);
        }
    }

    private void writeLines(Path target, AnalysisResult result) throws IOException {
        // BufferedWriter her write çağrısında diske gitmez; tampon dolunca ya da close'da toplu yazar.
        try (BufferedWriter writer = Files.newBufferedWriter(target, charset, OVERWRITE_OPTIONS)) {
            for (String line : formatter.format(result)) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
