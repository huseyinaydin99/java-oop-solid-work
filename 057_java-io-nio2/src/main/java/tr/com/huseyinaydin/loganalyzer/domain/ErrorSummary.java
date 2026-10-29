package tr.com.huseyinaydin.loganalyzer.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * ERROR seviyesindeki kayıtların özeti. Yalnızca en az bir hata varsa oluşur.
 */
public record ErrorSummary(String topService,
                           long topServiceErrorCount,
                           LocalDateTime firstErrorAt,
                           LocalDateTime lastErrorAt) {

    public ErrorSummary {
        Objects.requireNonNull(topService, "topService");
        Objects.requireNonNull(firstErrorAt, "firstErrorAt");
        Objects.requireNonNull(lastErrorAt, "lastErrorAt");
        if (topServiceErrorCount <= 0) {
            throw new IllegalArgumentException("topServiceErrorCount pozitif olmalı");
        }
    }
}
