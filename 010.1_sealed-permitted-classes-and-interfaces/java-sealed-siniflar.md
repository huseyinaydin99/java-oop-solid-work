# Java'da Sealed (Mühürlü) Sınıflar ve Arayüzler

Java 17 ile standart hale gelen **sealed** (mühürlü) sınıflar ve arayüzler, nesne yönelimli programlamadaki (OOP) **"kalıtım kontrolü"** problemini kökten çözen harika bir özelliktir.

Kafanı hiç karıştırmadan, en temelden en ileri seviyeye doğru 5 adımda bu işi bitirelim.

---

## Adım 1: Neden `sealed` diye bir şeye ihtiyaç duyuldu? (Problem)

Java'da `sealed` gelmeden önce kalıtım (miras alma) konusunda elimizde sadece iki uç nokta vardı:

- **Ya tamamen açık:** Bir sınıfı normal tanımlarsın (`public class Odeme`), projedeki herhangi biri (hatta kütüphaneni kullanan başka bir geliştirici) kafasına göre `extends Odeme` diyerek senin sınıfından miras alabilir.
- **Ya tamamen kapalı:** Sınıfın başına `final` koyarsın (`public final class Odeme`), kimse miras alamaz.

Peki ya şunu istiyorsan?

> "Ben `Odeme` diye bir sınıf/arayüz yapayım. Bundan **SADECE** `KrediKarti`, `Havale` ve `Kripto` sınıfları miras alabilsin. Başka hiç kimse kafasına göre `SenetleOdeme` diye bir sınıf uydurup benim yapımdan miras alamasın!"

İşte `sealed`, **"ya hep ya hiç"** kuralını yıkarak **kontrollü (izne tabi) kalıtım** yapmanı sağlar.

---

## Adım 2: Temel Söz Dizimi (`sealed` ve `permits`)

Bir sınıfı veya arayüzü mühürlemek için `sealed` kelimesini, kimlere izin verdiğimizi belirtmek için ise `permits` (izin verir) kelimesini kullanırız.

```java
// Bu arayüz mühürlüdür! Sadece KrediKarti ve Havale onu implemente edebilir.
public sealed interface OdemeYontemi permits KrediKarti, Havale {
    void odemeYap(double tutar);
}
```

Eğer başka bir yazılımcı gelip `public class Nakit implements OdemeYontemi` yazmaya kalkarsa, kod daha derlenirken (**Compile Time**) hata verir:

> "Sen izin verilenler (`permits`) listesinde yoksun!"

---

## Adım 3: Alt Sınıfların Mecburi Kararı (3 Altın Kural)

Burası en kritik kısım. Mühürlü bir sınıftan (`sealed`) miras alan çocuk sınıflar, kendi sınırlarını da açıkça belli etmek **ZORUNDADIR**. Java, çocuk sınıfları şu 3 kelimeden birini seçmeye mecbur bırakır:

1. **`final` (Mühür burada biter):** "Ben miras aldım ama benden kimse miras alamaz. Zincirin son halkası benim."
2. **`sealed` (Mühürü kontrollü devam ettir):** "Ben miras aldım, benden de sadece şu belirttiğim sınıflar miras alabilir."
3. **`non-sealed` (Mühürü kır / Herkese aç):** "Babam mühürlüydü ama ben kapıları ardına kadar açıyorum, benden isteyen herkes miras alabilir."

Hemen kod üzerinde görelim:

```java
// 1. Ana Mühürlü Sınıfımız
public abstract sealed class Bildirim permits SmsBildirim, EmailBildirim, PushBildirim {
    public abstract void gonder(String mesaj);
}

// KURAL 1: 'final' -> Zincir bitti. Kimse SmsBildirim'den extends edemez.
public final class SmsBildirim extends Bildirim {
    @Override
    public void gonder(String mesaj) {
        System.out.println("SMS gönderildi: " + mesaj);
    }
}

// KURAL 2: 'sealed' -> Kontrollü devam. Sadece KurumsalEmail bundan miras alabilir.
public sealed class EmailBildirim extends Bildirim permits KurumsalEmail {
    @Override
    public void gonder(String mesaj) {
        System.out.println("Email gönderildi: " + mesaj);
    }
}

// EmailBildirim'in izin verdiği alt sınıf (O da kararını 'final' olarak verdi)
public final class KurumsalEmail extends EmailBildirim {
    // ...
}

// KURAL 3: 'non-sealed' -> Mühür kırıldı! Artık isteyen her sınıf PushBildirim'den miras alabilir.
public non-sealed class PushBildirim extends Bildirim {
    @Override
    public void gonder(String mesaj) {
        System.out.println("Push bildirim gönderildi: " + mesaj);
    }
}
```

---

## Adım 4: Modern Java Kombinasyonu (`sealed interface` + `record`)

Temiz mimari ve **Domain-Driven Design (DDD)** yaklaşımlarında `sealed` en çok `record` ile birlikte kullanılır. Çünkü `record` sınıfları doğuştan (örtülü olarak) `final` kabul edilir! Yani `record` kullandığında ekstra `final` yazmana bile gerek kalmaz.

Örneğin bir API veya servis katmanında işlemin sonucunu döneceksin. Sonuç ya **Başarılı** olur ya da **Hatalı** olur, üçüncü bir ihtimal yoktur:

```java
// Tek bir dosya içinde bile (aynı pakette) tertemiz tanımlayabilirsin:
public sealed interface ServisSonucu permits Basarili, Hatali {}

// Record'lar otomatik olarak 'final' olduğu için kuralı doğrudan sağlar!
public record Basarili(String veri, int durumKodu) implements ServisSonucu {}

public record Hatali(String hataMesaji, String hataKodu) implements ServisSonucu {}
```

> 💡 **Küçük İpucu:** Eğer alt sınıfları (`Basarili`, `Hatali`) ana `sealed` arayüz ile aynı `.java` dosyasının içine yazarsan, `permits Basarili, Hatali` kısmını yazmana bile gerek kalmaz; Java aynı dosyadakileri otomatik algılar.

---

## Adım 5: Asıl Süper Güç: `switch` İfadelerinde `default` Yazmaktan Kurtulmak!

> "Tamam, miras almayı kısıtladık, güzel. Peki bunun bize kod yazarken en büyük pratik faydası ne?"

Java 21 ile gelen **Switch Pattern Matching** ile birleştiğinde ortaya muazzam bir güvenlik çıkar. Normalde bir `switch` bloğu yazdığında derleyici senden `default` (hiçbiri değilse ne yapayım?) dalını ister. Ama `sealed` kullandığında derleyici dünyada başka hiçbir alt sınıf olamayacağını **%100** bilir!

Adım 4'teki `ServisSonucu` örneğini kullanalım:

```java
public class SonucIsleyici {

    public String yanitiCozumle(ServisSonucu sonuc) {
        // DİKKAT: 'default' bloğu YOK!
        // Çünkü Java, ServisSonucu'nun SADECE Basarili veya Hatali olabileceğini biliyor.
        return switch (sonuc) {
            case Basarili b -> "İşlem harika! Gelen veri: " + b.veri();
            case Hatali h   -> "Eyvah! Hata kodu: " + h.hataKodu() + " - Mesaj: " + h.hataMesaji();
        };
    }
}
```

### Bunun mimari açıdan mükemmelliği

Diyelim ki 6 ay sonra projeye yeni bir durum ekledin: `permits Basarili, Hatali, Beklemede`.

Sen `Beklemede` sınıfını eklediğin saniye, projedeki tüm `switch (sonuc)` blokları **Derleme Zamanında (Compile Time)** kırmızı yanar ve der ki:

> "Hey! `Beklemede` durumunu `switch` içinde ele almayı unuttun!"

Eğer `default` kullansaydın, yeni eklediğin durum sessizce `default` bloğuna düşecek ve belki de canlı ortamda (**Production**) fark etmediğin bir bug oluşturacaktı. `sealed` sayesinde hataları daha kodu çalıştırmadan yakalarsın.

---

## Özet Kontrol Listesi (Aklında Kalması Gerekenler)

| Anahtar Kelime | Kim Kullanır?       | Anlamı Nedir?                                                          |
|----------------|---------------------|------------------------------------------------------------------------|
| `sealed`       | Ana Sınıf / Arayüz  | "Benden sadece izin verdiklerim miras alabilir."                       |
| `permits`      | Ana Sınıf / Arayüz  | İzin verilen alt sınıfların listesini belirtir.                        |
| `final`        | Alt Sınıf           | "Miras aldım ve kapıyı kapattım. Benden kimse miras alamaz."           |
| `sealed`       | Alt Sınıf           | "Miras aldım ve ben de kendi alt sınıflarımı `permits` ile kısıtlıyorum." |
| `non-sealed`   | Alt Sınıf           | "Miras aldım ama mührü kırdım. Benden isteyen herkes miras alabilir."  |

> ⚠️ **Önemli Kısıtlama:** `sealed` sınıf ile onun `permits` verdiği alt sınıflar **aynı pakette** (veya modüler bir projeyse **aynı Java modülünde**) bulunmak zorundadır. Farklı bir paketteki sınıfa izin veremezsin.

---

Aklına yatmayan bir adım varsa veya kendi projendeki bir senaryoya (örneğin bir ödeme sistemi, sipariş durumu veya hata yönetimi) nasıl uyarlayacağımızı görmek istersen hemen üzerinden geçebiliriz!
