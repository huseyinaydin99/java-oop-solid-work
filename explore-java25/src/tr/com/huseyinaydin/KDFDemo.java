package tr.com.huseyinaydin;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java
 *
 */

//Gizli Anahtar/Key/Hash Üretme Fonksiyonu API'si:

public class KDFDemo {
    // psvm'de public eklenmiş veya eklenmemiş fark etmez varsayılan olarak zaten public'dir.
    public static void main(String[] args) throws Exception {
        // 1️⃣ Kullanıcı tarafından sağlanan şifre
        char[] password = "Hüseyin AYDIN".toCharArray();

        // 2️⃣ Ekstra++ güvenlik için rastgele bir tuz oluşturmak
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);

        // 3️⃣ PBKDF2 parametreleri: parola, tuz/salt, tekrarlama ile ektra güvenlik, anahtar uzunluğu
        PBEKeySpec spec = new PBEKeySpec(password, salt, 65536, 256);

        // 4️⃣ PBKDF2WithHmacSHA256 algoritması ile anahtar fabrikası nesnesini oluşturur ve gizli anahtar nesnesini üretir
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        SecretKey derivedKey = factory.generateSecret(spec);

        // 5️⃣ Sonuçları göster
        System.out.println("Üretilmiş anahtar (hex): " + bytesToHex(derivedKey.getEncoded()));
        System.out.println("Kullanılan tuz/salt (hex): " + bytesToHex(salt));

        // 6️⃣ Hassas verileri temizle
        spec.clearPassword();
        Arrays.fill(password, '\0');
        Arrays.fill(salt, (byte)0);
        //Temizlik, şifre ve tuz gibi hassas verilerin bellekten silinerek güvenliğini sağlamak amacıyla yapılır.
    }

    // Baytları onaltılık dizeye dönüştürmek için yardımcı metotdur
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}