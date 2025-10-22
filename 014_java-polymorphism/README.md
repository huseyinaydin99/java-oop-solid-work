#### 🌟 Java Polymorphism - Çok Biçimlilik (Polymorphism) Derin Diyaliz (: Analiz 🧠🚀💼

Bu projede 💻 polymorphism (çok biçimlilik) kavramını somut olarak gösterdim. Personel sınıfı tüm personel tiplerinin temelini oluşturuyor 🏗️ ve Mudur, Muhasebe, Yazilimci gibi alt sınıflar extends Personel ile miras alıyor; bu sayede hem ortak özellikler (adi, soyadi, telefonNo, departmani, sicilNo, maasi, medeniDurumu vb.) hem de davranışlar (bilgisiniYaz, hesapla) merkezi olarak kontrol edilebiliyor 🔑.

Polymorphism’in temel amacı, aynı metot isminin farklı nesnelerde farklı davranışlar sergileyebilmesini sağlamaktır 🧩. Örneğin bilgisiniYaz() metodunu hem Mudur hem Muhasebe hem de Yazilimci kendi özel alanlarını da göstererek override ettim; böylece ayrı ayrı metod çağrıları yerine aynı isimle farklı davranışlar elde ediliyor 🎭.

Projemizde hem compile-time polymorphism (method overloading) hem de runtime polymorphism (method overriding) kullanıldı:

hesapla() metodunu farklı parametre sayısı ve türleriyle aşırı yükleyerek derleme zamanında farklı versiyonları çalıştırabilme imkanı verdim ⏳.

bilgisiniYaz() metodunu alt sınıflarda override ederek, çalıştırma zamanında nesne tipine uygun davranışı göstermesini sağladım 🔄.

##### Avantajları:
```
Kod tekrarını önler ✅

Esnek ve genişletilebilir bir tasarım sağlar 🌱

Aynı metot ismiyle farklı nesne davranışlarını yönetebilirsin 🎯
```

##### Dezavantajları:
```
Yanlış tasarlanırsa karmaşıklık artar ⚠️

Overriding yapılırken dikkat edilmezse beklenmedik sonuçlar ortaya çıkabilir ⚡

Projede eksik yan olarak, bilgisiniYaz() metodlarını sadece kendi tipleriyle sınırlı yazdım, yani alt sınıf tiplerini Personel tipinde parametre alacak şekilde düzenleyip tam runtime polymorphism’i göstermek daha net olurdu. Ayrıca interface veya abstract class kullanımıyla polymorphism daha esnek ve güvenli hale getirilebilirdi 🛠️.
```

Özetle 📝, burada çok biçimlilik sayesinde hem method overloading ile farklı parametrelerle aynı işlemi yapabilme hem de method overriding ile alt sınıfların kendi özel davranışlarını tanımlayabilme olanağı sağladım. Bu, projeyi bakımı kolay, genişletilebilir ve nesne odaklı bir yapıya kavuşturuyor 🚀💡.

##### Proje UML Diyagramı;
```
┌───────────────────────────────┐
│           Personel            │
├───────────────────────────────┤
│ - adi: String                 │
│ - soyadi: String              │
│ - telefonNo: String           │
│ - departmani: String          │
│ - sicilNo: int                │
│ - tahsilDurumu: String        │
│ - adres: String               │
│ - maasi: float                │
│ - medeniDurumu: boolean       │
├───────────────────────────────┤
│ + bilgisiniYaz(Personel)      │
│ + hesapla()                    │
│ + hesapla(int yil)             │
│ + hesapla(int yil, int ay)     │
│ + hesapla(float ay, int yil)  │
│ + hesapla(int yil, int ay, int gun) │
│ + hesapla(int yil, short ay)  │
│ + hesapla(int yil, short ay, int gun) │
│ + hesapla(int yil, short ay, short gun) │
│ + hesapla(short yil, short ay, int gun) │
└───────────────┬───────────────┘
│
┌─────────┴─────────┐
│                   │
┌───────────────┐   ┌───────────────┐
│     Mudur     │   │   Muhasebe    │
├───────────────┤   ├───────────────┤
│ - makamAraci: boolean │ - yeminDurumu: Boolean │
│                     │ - unvani: String       │
├───────────────┤   ├───────────────┤
│ + bilgisiniYaz(Mudur) │ + bilgisiniYaz(Muhasebe) │
└───────────────┘   └───────────────┘
│
┌───────────────┐
│   Yazilimci   │
├───────────────┤
│ - uzmanlikAlani: String │
├───────────────┤
│ + bilgisiniYaz(Yazilimci) │
└───────────────┘
```