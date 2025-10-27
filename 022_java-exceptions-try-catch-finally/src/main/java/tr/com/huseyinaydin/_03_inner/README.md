#### 🧩 Java'da İç İçe try-catch-finally Kullanımı – “Hata Yönetiminde Katmanlı Düşünmek”

Bu kodu yazarken amacım, **hata yönetimini derinlemesine anlamak** ve “bir hata sadece bir yerde değil, farklı katmanlarda da nasıl ele alınabilir” sorusuna yanıt bulmaktı. 💭  
Gerçek hayatta sistemler tek parçadan oluşmaz; işlemler **katmanlı**dır. Aynı şekilde programda da hatalar sadece yüzeyde değil, iç fonksiyonlarda da patlayabilir. 💣  
Bu yüzden burada **iç içe (nested) try-catch-finally** yapısını kullandım: biri `main()` içinde **dış try-catch**, diğeri `hesapYap()` metodunda **iç try-catch**. Her biri kendi görev bölgesinde hatayı yakalayıp yönetiyor, ama asıl fark şu — **iç blok kendi hatasını çözerse**, hata dışa taşmaz; çözemediği durumda **dış blok devreye girer**. 🎯

---

##### 🔹 Kodun Hikayesi

```java
package tr.com.huseyinaydin._03_inner;

import java.util.Date;

public class AppMain {

    static int sayi1 = 10;
    static int sayi2 = 0;

    static int toplam = 0;
    static int bolme = 0;

    public static void main(String[] args) {
        try {
            // içteki try-catch
            hesapYap();

            toplam = sayi1 + sayi2;
            System.out.println("DIŞ Toplama: " + toplam);

            bolme = sayi1 / sayi2; // ZeroDivideException
            System.out.println("DIŞ Bolme: " + bolme);
        } catch (Exception e) {
            System.out.println("DIŞ Hata: " + e);
        } finally {
            System.out.println("DIŞ Teşekkür ederiz. : " + new Date());
        }
    }

    private static void hesapYap() {
        try {
            toplam = sayi1 + sayi2;
            System.out.println("İÇ Toplama: " + toplam);

            bolme = sayi1 / sayi2;
            System.out.println("İÇ Bolme: " + bolme);
        } catch (Exception e) {
            System.out.println("İÇ Hata: " + e);
        } finally {
            System.out.println("İÇ Teşekkür ederiz. : " + new Date());
        }
    }
}
```