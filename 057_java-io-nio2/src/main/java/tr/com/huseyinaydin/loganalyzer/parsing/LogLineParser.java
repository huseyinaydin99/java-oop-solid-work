package tr.com.huseyinaydin.loganalyzer.parsing;

import tr.com.huseyinaydin.loganalyzer.domain.LogEntry;

import java.util.Optional;

/**
 * Ham satırı alan nesnesine çevirir. Format dışı satır için exception yerine boş döner,
 * çünkü bozuk satır bu senaryoda beklenen bir durumdur, istisna değil.
 */
public interface LogLineParser {

    Optional<LogEntry> parse(String rawLine);
}
