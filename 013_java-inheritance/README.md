#### 🌳 Java Inheritance (Kalıtım)

Inheritance (Kalıtım), bir sınıfın başka bir sınıfın özelliklerini (fields) ve davranışlarını (methods) devralmasını sağlayan Java’nın temel nesne yönelimli programlama konseptidir 🧩. Örneğimizde Mudur, Muhasebe ve Yazilimci sınıfları, Personel sınıfını extends ederek hem ortak alanları (adi, soyadi, telefonNo, departmani, sicilNo vb.) hem de davranışları miras alıyor; böylece tekrar tekrar aynı kodu yazmıza gerek kalmıyor 🔑.

Inheritance’ın amacı sadece kod tekrarını önlemek değil, aynı zamanda hiyerarşik ve mantıksal bir sınıf yapısı kurarak daha okunabilir ve bakımı kolay bir sistem oluşturmaktır 📚. Örneğin bütün personel tiplerinin ortak özellikleri Personel sınıfında, özel farklılıkları (uzmanlık alanı, makam aracı, yemin durumu vb.) ise kendi sınıflarında tutuluyor, böylece hem esnek hem de anlaşılır bir yapı ortaya çıkıyor 🏗️.

##### Avantajları:
```
Kod tekrarını önler ✅

Merkezi kontrol ile bakımı kolaylaştırır 🛠️

Polimorfizm ve method overriding ile esnek davranış değişikliği sağlar 🔄

Dezavantajları:

Çok derin kalıtım hiyerarşileri karmaşıklaşabilir ⚠️

Yanlış tasarlanırsa, bağımlılık ve sıkı bağlılık (tight coupling) artar 🔗
```

Kısaca, inheritance sayesinde Personel sınıfında tanımladığım ortak özellikleri bir kez yazar, tüm alt sınıfların kullanmasını sağlarken, her bir alt sınıf kendi özel alanını ve davranışını ekleyerek temiz, anlaşılır ve sürdürülebilir bir nesne hiyerarşisi oluşturuyor 🚀💡.
