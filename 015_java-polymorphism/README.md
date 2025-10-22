#### 🐾 Java Polymorphism - Çok Biçimlilik Derinlemesine 🚀🎯

Bu projede 🐱🐶🐑🐦 çok biçimliliği (polymorphism), hayvanlar dünyası üzerinden somut olarak gösterdim. Hayvan üst sınıfı tüm temel davranışları (sesVer, hareketeGec) ve ortak özellikleri barındırıyor, Kedi, Kopek, Kus, Koyun ve Van alt sınıfları ise bu davranışları kendi özelliklerine göre özelleştiriyor (override) 🎨. Örneğin Kedi kendi sesVer() metodunu “miyav miyav” diye tanımlarken, Kopek “hav hav” diyor; Van ise Kedi’nin alt sınıfı olarak daha özel bir ses veriyor 🐈‍⬛.

Buradaki polymorphism runtime polymorphism (geç çalışma zamanı çok biçimliliği) ile ortaya çıkıyor, çünkü bir Hayvan referansına farklı alt sınıf nesnelerini atayabiliyorum ve çağırdığım metodun davranışı nesne tipine göre değişiyor 🔄. Örneğin:

```java
Hayvan hayvan0bj = new Kedi();
hayvan0bj.sesVer(); // Van Kedisi veya Kedi’nin sesini verir. Hangisi enjekte edilirse onun sesini verir. Ne verirsen eliynen o gelir seniynen hesaaabı.
```

Bu örnek, öncekilerden (Personel projesi) farkını gösteriyor: Önceki projede hem compile-time polymorphism (overload) hem de runtime polymorphism (override) vardı; burada ise tamamen runtime polymorphism vurgusu var, yani metodlar alt sınıflarda override edilerek gerçek nesne tipine göre davranış değişiyor ⏱️.

##### Amaç:
Aynı üst sınıf tipini kullanarak, farklı alt sınıf nesnelerinde uygun davranışı çağırabilmek 🎯. Bu, kodu esnek, bakımı kolay ve genişletilebilir kılıyor 🌱.

##### Avantajları:
```
Kod tekrarını önler ✅

Yeni alt sınıflar eklemek kolaydır 🔧

Çalıştırma zamanında davranışı belirleyebilirsin 🧩
```

##### Dezavantajları:
```
Alt sınıfların metodlarını yanlış override etmek hataya yol açabilir ⚠️

Çok karmaşık hiyerarşilerde takip zorlaşır 🌀
```

Özetle, burada yaptığım şey Hayvan sınıfının davranışlarını alt sınıflarda özelleştirerek gerçek runtime polymorphism’i göstermek, böylece tek bir üst sınıf referansıyla farklı türlerde nesnelere özgü davranışları tetikleyebilmek 🐾💡.

```scss
                   ┌───────────────────────────┐
                   │          Hayvan           │
                   ├───────────────────────────┤
                   │ + Hayvan()                │
                   │ + sesVer()                │
                   │ + hareketeGec()           │
                   └─────────────┬─────────────┘
                                 │
        ┌───────────────┬───────────────┬───────────────┐
        │               │               │               │
   ┌─────────────┐  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐
   │    Kedi     │  │    Kopek    │  │    Kus      │  │   Koyun     │
   ├─────────────┤  ├─────────────┤  ├─────────────┤  ├─────────────┤
   │ + Kedi()    │  │ + Kopek()   │  │ + Kus()     │  │ + Koyun()   │
   │ + sesVer()  │  │ + sesVer()  │  │ + sesVer()  │  │             │
   │ + hareketeGec()│             │  |             │  │             │
   └─────┬───────┘  └─────────────┘  │ + yemYe()   │  └─────────────┘
         │                             │
         │                             │
   ┌─────────────┐
   │    Van      │
   ├─────────────┤
   │ + sesVer()  │
   │ - beslen()  │
   └─────────────┘
```

#### ⚡ Runtime vs Compile-Time Polymorphism — Çok Biçimlilik Farkları 🧩

Compile-time polymorphism (derleme zamanı çok biçimlilik) metod overloading ile olur; yani aynı metod adı farklı parametrelerle bir sınıfta tanımlanır ve hangi metodun çalışacağı derleme anında belli olur 🏗️.
Runtime polymorphism (çalışma zamanı çok biçimlilik) metod overriding ile sağlanır; üst sınıf referansı, alt sınıf nesnesini tutar ve hangi metodun çalışacağı nesnenin gerçek tipine göre çalışma anında belirlenir ⏱️.
Örnek: Hayvan hayvan = new Kedi(); hayvan.sesVer(); → sesVer() çağrısı runtime’da Kedi’nin davranışını tetikler, oysa hesapla(int yil) gibi overloading örnekleri derleme sırasında hangi metod çağrılacağını kesinleştirir 🐾.

🧩 Generic Polymorphism — Tip Bazlı Çok Biçimlilik

Ben burada generic polymorphism ile tek bir metodun veya sınıfın, farklı tip parametreleriyle farklı davranış gösterebilmesini sağladım 🏗️. Örneğin Hesapla<T> sınıfında yaz() metodunu hem Integer hem String ile çağırabiliyorum ve her seferinde doğru şekilde çalışıyor ✅. Bu sayede kod tekrarını önlüyorum ve tip güvenliğini koruyorum 🛡️. Avantajı, tek metodla farklı veri tiplerini yönetmek, dezavantajı ise bazen karmaşık tip tanımlamalarıyla kafa karıştırması 💡.
```java
package tr.com.huseyinaydin.hesap;

public class Hesapla<T> { // T tipi parametre
    public void yaz(T veri) {
        System.out.println("Değer: " + veri);
    }

    public static void main(String[] args) {
        Hesapla<Integer> intHesapla = new Hesapla<>();
        intHesapla.yaz(123);  // Integer tipi ile çalışır

        Hesapla<String> stringHesapla = new Hesapla<>();
        stringHesapla.yaz("Merhaba"); // String tipi ile çalışır
    }
}
```