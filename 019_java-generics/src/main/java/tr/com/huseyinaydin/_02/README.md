#### 🌐 Java'da Generic Mantığı — Tür Bağımsızlığın Gücü 💎

Java’da Generic kavramı, kodun esnekliğini ve yeniden kullanılabilirliğini artıran bir mekanizmadır. Benim aşağıdaki örneğimde aslında **Generic bir yaklaşımın temelleri** mevcut, ancak klasik anlamda `<T>` kullanımı yok. Burada yaptığım şey, **aynı işlevi farklı türlerle** (Integer, Double, Float, Long) çalışacak şekilde overload etmek. Yani `ekranaYaz()` metodu her bir sayısal tür için ayrı ayrı yazılmış olsa da, hepsinin amacı aynı: türden bağımsız olarak veriyi ekrana bastırmak. Eğer gerçekten generic yazmak isteseydim, bu metotları tek bir `ekranaYaz(T sira, U adi)` şeklinde yazar, parametrelerin türüne göre çalışacak şekilde tanımlardım. Bu haliyle kod, **"generic düşünce tarzının manuel hali"** denebilir — Java'nın tür güvenliği (type safety) mantığını kavramak açısından çok öğretici. 💡 Yani kısaca, burada **farklı türlerle aynı davranışı sağlayan, generic yapıya ön basamak bir kod yapısı** mevcut.

---

##### 💻 Kod Örneği
```java
package tr.com.huseyinaydin._02;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Personel {

    private String adi;
    private String soyadi;
    private boolean medeniDurum;

    public void cizgiCek() {
        System.out.println("==================");
    }

    public void ekranaYaz() {
        System.out.println("EKRANA YAZ");
    }

    public void ekranaYaz(Integer sira, String adi) {
        System.out.println("Sira: " + sira + " --  " + sira.getClass());
        System.out.println("Adi: " + adi + "  --  " + adi.getClass());
    }

    public void ekranaYaz(Double sira, String adi) {
        System.out.println("Sira: " + sira + " --  " + sira.getClass());
        System.out.println("Adi: " + adi + "  --  " + adi.getClass());
    }

    public void ekranaYaz(Float sira, String adi) {
        System.out.println("Sira: " + sira + " --  " + sira.getClass());
        System.out.println("Adi: " + adi + "  --  " + adi.getClass());
    }

    public void ekranaYaz(Long sira, String adi) {
        System.out.println("Sira: " + sira + " --  " + sira.getClass());
        System.out.println("Adi: " + adi + "  --  " + adi.getClass());
    }

    public static void main(String[] args) {
        Personel personel = new Personel();

        personel.ekranaYaz();
        personel.cizgiCek();

        personel.ekranaYaz(1, "Hüseyin");
        personel.cizgiCek();

        personel.ekranaYaz(2.0, "Hüseyin");
        personel.cizgiCek();

        personel.ekranaYaz(3.0f, "Hüseyin");
        personel.cizgiCek();

        personel.ekranaYaz(4L, "Hüseyin");
        personel.cizgiCek();
    }
}
```