package tr.com.huseyinaydin.customer.domain;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailTest {

    @Test
    void turkceYerelAyardaBileDogruKucukHarfeCevrilir() {
        Locale original = Locale.getDefault();
        try {
            Locale.setDefault(Locale.forLanguageTag("tr-TR"));

            assertEquals("irem@example.com", new Email("IREM@EXAMPLE.COM").value());
        } finally {
            Locale.setDefault(original);
        }
    }
}
