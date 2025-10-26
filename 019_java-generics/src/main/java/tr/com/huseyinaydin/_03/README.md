#### 💫 Java'da Generic Mantığı - Tür Esnekliğinin Sanatı 🎨

Java’da Generic kavramı, yazılım geliştiricinin elindeki **en zarif araçlardan biridir**. Amacı, **farklı veri türleriyle çalışabilen ama aynı işlevi sürdürebilen** bir yapı kurmaktır. Generic kullanmadığımızda, aynı metodu veya sınıfı farklı türler için defalarca yazmamız gerekir — bu hem kod tekrarı yaratır hem de bakım maliyetini artırır. Generic’ler sayesinde kodumuzu **tür bağımsız**, **tip güvenli (type-safe)** ve **yeniden kullanılabilir** hale getiririz. 🚀  
Aslında generic, “türden bağımsız ama güvenli yazılım” felsefesinin somut hâlidir. Eğer kullanılmazsa, `Object` tipiyle çalışmak zorunda kalırız ve **derleme zamanında değil, çalışma zamanında hatalarla karşılaşırız**. Bu da hem performansı hem güvenilirliği düşürür. Generic’in ana amacı, **farklı veri tipleriyle çalışan ama tek kural setine uyan bir yapı kurmaktır**. 💡  
Bir programcı için generic demek, **düşünce yapısının olgunlaşması** demektir. Çünkü bu yaklaşım, yazılıma **esneklik**, **sürdürülebilirlik** ve **temiz mimari** kazandırır. Avantajları arasında kod tekrarsızlığı, tip güvenliği, yeniden kullanılabilirlik ve okunabilirlik vardır. Dezavantajı ise bazen karmaşık generic tanımlarının okunması güçleşebilir ama bu, güçlü bir yapının doğal bedelidir. ⚙️  
Kısacası, Generic kullanmak; **geleceği düşünerek kod yazmak**, **tür bağımlılığından kurtulmak** ve **soyutlama gücünü zirveye taşımaktır.** 🌍

---

##### 💻 Kod Örneği
```java
package tr.com.huseyinaydin._03;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//                    Number ,                  String, Boolean...
public class Personel<GelenTip1 extends Number, GelenTip2> {

    private GelenTip2 adi;
    private GelenTip2 soyadi;
    private boolean medeniDurum;
    private GelenTip1 sira;

    public void cizgiCek() {
        System.out.println("==================");
    }

    public void ekranaYaz() {
        System.out.println("EKRANA YAZ");
    }

    // Number türüyle sınırlanmış generic yapı
    public void ekranaYaz(Number sira, GelenTip2 adi) {
        System.out.println("Sira: " + sira + " --  " + sira.getClass());
        System.out.println("Adi: " + adi + "  --  " + adi.getClass());
    }

    // Tam anlamıyla generic yapı — türü fark etmiyor
    public <T> void ekranaYaz(T deger, GelenTip2 adi) {
        System.out.println("Sira: " + deger + " --  " + deger.getClass());
        System.out.println("Adi: " + adi + "  --  " + adi.getClass());
    }
}
```

```java
package tr.com.huseyinaydin._03;

public class AppMain {

    public static void main(String[] args) {
        Personel<Integer, String> personel = new Personel();

        personel.ekranaYaz();
        personel.cizgiCek();

        personel.ekranaYaz(1, "Bilal");
        personel.cizgiCek();

        personel.ekranaYaz(2.0, "Bilal");
        personel.cizgiCek();

        personel.ekranaYaz(3.0f, "Bilal");
        personel.cizgiCek();

        personel.ekranaYaz(4L, "Bilal");
        personel.cizgiCek();

        personel.ekranaYaz((short) 5, "Bilal");
        personel.cizgiCek();
    }
}
```
##### ⚙️ Derin Anlam

Bu örnekte, Personel<GelenTip1, GelenTip2> sınıfı aslında Java Generic’lerin özünü yansıtıyor. GelenTip1 sadece Number türlerinden birini alabilirken, GelenTip2 herhangi bir tür olabilir. Yani sınıfın neyle çalışacağını ben belirliyorum, Java buna göre derleme zamanında kontrol sağlıyor. 💪
Eğer Generic kullanmasaydım, Integer, Double, Float, Long gibi her tür için ayrı metotlar yazmak zorunda kalırdım. Ama şimdi tek metotla her şeyi yönetiyorum. Bu, kodun hem soyut gücünü hem de esnekliğini artırıyor. 🎯

##### 🧠 Extends ve Super — Türlerin Hiyerarşisinde Ustaca Denge ⚖️

Java’da **Generic sınırlandırmaları (bounded generics)**, türler arasındaki ilişkileri akıllıca yönetmenin yoludur. `GelenTip1 extends Number` dediğimde aslında şunu söylüyorum: “Bu generic tür yalnızca `Number` sınıfından türemiş veya `Number`' ın kendisi (yani alt sınıfı olan) türlerle çalışabilir.” 🔒 Yani `Integer`, `Double`, `Float`, `Long` gibi sınıflar kabul edilir ama `String` veya `Boolean` olamaz. Bu, üst sınıftan miras alan türleri kapsar ve “**üstten aşağı doğru**” bir sınır koyar — bu yüzden **upper bounded** denir.  
Buna karşılık `GelenTip1 super Number` deseydim, “Bu generic tür `Number` veya onun üst atalarından biri olabilir” demiş olurdum. Yani burada yön tersine döner, “**alttan yukarı doğru**” bir sınır çizilir — bu da **lower bounded** yaklaşımıdır. 🚀  
Kısaca düşünürsek: `extends` “**ben ondan türeyenlerle çalışırım**” derken, `super` “**ben onun atalarıyla çalışırım**” der. 🌳  
Bu fark, Generic yapıyı hem güvenli hem esnek hale getirir. Çünkü `extends` kullanarak veri **tüketebilir**, `super` kullanarak veri **üretebiliriz**. 💡  
Benim için bu kavram, sadece bir sözdizimi farkı değil; **veri akışının yönünü kontrol etme sanatı**dır. Programcı olarak hangi yöne akacağını ben belirlerim — işte asıl ustalık budur. 🎯🔥

##### 🔄 Extends Tüketir, Super Üretir — Veri Akışının Yönü 🎯

`extends` kullanıldığında, generic yapı **üst sınıfın alt türleriyle** çalışır; yani nesneleri sadece **okuyabilirim (consume)** ama içine güvenle yazamam. Çünkü tam olarak hangi alt sınıfın geldiğini derleyici bilemez. 🍃  
`super` kullanıldığında ise yapı **alt sınıfın üst türleriyle** çalışır; bu durumda nesneleri güvenle **üretilen (produce)** tarafta kullanabilirim, çünkü her şey en azından o süper tipin kurallarına uyar. ⚙️  
Kısacası `extends` bana **“benim içimden oku ama yazma”**, `super` ise **“bana yaz ama içimden okuma”** der. 🧭  
Bu yön farkı, generic sistemin kalbidir; veri akışını doğru yönlendirmeyi öğrenen bir programcı, artık tip sistemine hükmetmeye başlar. 💪🔥

##### Kod Örneği;

```java
import java.util.ArrayList;
import java.util.List;

public class GenericOrnek {
    public static void main(String[] args) {
        List<Integer> sayilar = List.of(10, 20, 30);
        List<Number> numaralar = new ArrayList<>();

        // ✅ ? extends Number
        listeYazdir(sayilar); // okuma işlemi yapılır, yazılamaz ❌

        // ✅ ? super Integer
        listeyeEkle(numaralar); // yazma işlemi yapılır, okuma sınırlı ✅
    }

    // "extends" örneği → sadece okuyabiliriz (consume/tüketim)
    public static void listeYazdir(List<? extends Number> liste) {
        for (Number n : liste) {
            System.out.println("Sayı: " + n);
        }
        // liste.add(100); // ❌ HATA: hangi alt tür geleceği bilinmiyor. Number ve onun alt sınıfları olabilir yani Number'ı miras alanları.
    }

    // "super" örneği → sadece yazabiliriz (produce/üretim)
    public static void listeyeEkle(List<? super Integer> liste) {
        liste.add(100); // ✅ Integer veya alt türü eklenebilir
        liste.add(200);
        System.out.println("Listeye eklendi!");
        // Number n = liste.get(0); // ❌ HATA: türü tam bilinmiyor, sadece Object döner. Integer'in üstleri olabilir demek.
    }
}
```