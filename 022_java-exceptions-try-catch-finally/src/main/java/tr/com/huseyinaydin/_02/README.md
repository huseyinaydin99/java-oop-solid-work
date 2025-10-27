#### ⚖️ Java'da Checked ve Unchecked Exception Farklılıkları

Bu kodda asıl amacım, **Java’da hata yönetimi** kavramını derinlemesine kavramak ve **checked** ile **unchecked exception** farkını kendi elimle deneyimlemekti. 💡  
Gerçek bir senaryo gibi düşündüm: elimde belli bir **kilo numarası** var, bu numara belirli bir aralıkta olmalı. Eğer kullanıcı bu sınırları aşıyorsa, sistem bunu **hoş görmemeli**; hatayı fırlatıp beni bilgilendirmeli. ⚠️  
Programın kalbi `try-catch-finally` bloğunda atıyor. `try` kısmı potansiyel hatayı deneyen bölge; `catch` kısmı hatayı yakalayıp yöneten akıl; `finally` kısmı ise “ne olursa olsun” görevini tamamlayan sadık bir bekçi. 🛡️  
Burada iki metot var: biri **Unchecked (kontrolsüz)** diğeri **Checked (kontrollü)** hata mantığını temsil ediyor.

---

##### 💥 Unchecked Exception – “Ani Patlamalar”
Unchecked exception’lar, **derleme zamanında değil, çalışma anında** ortaya çıkar. 💣  
Java bizden bu hataları önceden yakalamamızı zorunlu tutmaz — yani “istersen ilgilen, istemezsen sistem çöker” der gibidir. 😅  
Genelde `RuntimeException` sınıfından türeyen hatalardır (örneğin `ArithmeticException`, `NullPointerException`, `ArrayIndexOutOfBoundsException`).  
Bu hatalar, **mantıksal dikkatsizliklerin veya beklenmedik kullanıcı davranışlarının** sonucudur; sistemin sağlamlığı için yazılımcının öngörülü davranması gerekir. 🚧

---

##### 🧩 Checked Exception – “Planlı Müdahaleler”
Checked exception’lar ise **derleme zamanında Java derleyicisi tarafından denetlenir**. 🧠  
Yani “bu metot hata fırlatabilir, ilgilenmek zorundasın” diyerek yazılımcıya sorumluluk yükler. ✋  
Bu hatalar genelde **girdi/çıktı işlemleri, dosya okuma-yazma, ağ bağlantıları** gibi dış dünyayla etkileşen durumlarda ortaya çıkar. 🌍  
Checked exception sayesinde, programın olası hatalar karşısında **önlem alması** garanti altına alınır, sistem kararlılığı korunur. 💪

---

##### 🧮 Kodun Hikayesi

```java
package tr.com.huseyinaydin._02;

import java.util.Date;

public class AppMain {

    public static void main(String[] args) {
        try {
            //int sonuc = urunlerinKilosunuAlUnchecked(250, 85);
            int sonuc = urunlerinKilosunuAlChecked(250, 85);
            System.out.println("Ürünlerin sayısı: " + sonuc);
        } catch (Exception e) {
            System.out.println("Hata: " + e);
        } finally {
            System.out.println("Teşekkür ederiz. : " + new Date());
        }
    }

    // 🔴 Unchecked Exception (Çalışma Zamanı Hatası)
    private static int urunlerinKilosunuAlUnchecked(int kiloNo, int uretilenUrunlerinKilosu) {
        if (kiloNo < 1 || kiloNo > 50) {
            throw new ArithmeticException("Kilo sayısnı lütfen doğru giriniz!");
        } else {
            return uretilenUrunlerinKilosu;
        }
    }

    // 🟡 Checked Exception (Derleme Zamanı Hatası)
    private static int urunlerinKilosunuAlChecked(int kiloNo, int uretilenUrunlerinKilosu) throws ArithmeticException {
        if (kiloNo < 1 || kiloNo > 50) {
            throw new ArithmeticException("Kilo sayısnı lütfen doğru giriniz!");
        } else {
            return uretilenUrunlerinKilosu;
        }
    }
}
```