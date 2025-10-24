#### 🗂️ Java Stack Veri Ambarı - Derinlemesine Anlatım

Java’da Stack, klasik veri yapılarından biri olan yığıt (LIFO – Last In First Out) mantığıyla çalışan bir koleksiyondur. Yani son eklenen ilk çıkar mantığıyla işler. 🌊

##### 🔹 Nedir, Ne Değildir?

>Nedir: Stack, verileri üst üste dizmenizi sağlayan bir yapıdır. push() ile ekler, pop() ile çıkarırsınız. Son giren ilk çıkar! 🏗️

>Ne değildir: Stack, rastgele erişim sağlayan bir yapı değildir (mesela array gibi index ile direkt veri çekemezsiniz). Onun amacı sırayla işlemek, kaotik değil düzenli yönetmektir. ❌

##### 🔹 Neden İhtiyaç Duyulur?

Bazı durumlarda verileri son giren ilk çıkar mantığıyla yönetmek gerekir:

- Undo/Redo işlemleri 📝

- Fonksiyon çağrılarını takip etmek (call stack) 🔄

- Tarayıcı geçmişi 🖥️

- Matematiksel ifadelerin çözümü (Postfix, Prefix) ➗

- Stack, bu tip durumlarda kodu sade ve yönetilebilir kılar.

##### 🔹 Avantajları & Dezavantajları

###### Avantajlar:
✅ Son giren veriye hızlı erişim (peek()): Stack’in en güçlü yönü, en son eklenen öğeye anında ulaşabilmek. peek() ile üstteki öğeyi çıkarılmadan görebilir ve işlem yapabilirsin. ⚡

✅ Kolay ekleme ve çıkarma (push() / pop()): Stack’e veri eklemek (push()) veya en üstteki veriyi çıkarmak (pop()) çok basit ve hızlıdır. Karmaşık indekslerle uğraşmana gerek yok. 🔄

✅ Bellek yönetimi kolay, basit ve hızlı: Stack, verileri sırayla üst üste dizdiği için bellek kullanımı düzenli ve öngörülebilir. Küçük ve orta boy veri işlemlerinde çok hızlı çalışır. 🧠💨

###### Dezavantajlar:
❌ Rastgele erişim yok: Stack’te sadece en üstteki elemana erişebilirsin. Mesela ortadaki bir öğeye direkt ulaşamazsın, önce üsttekileri çıkarman gerekir. Bu da bazı durumlarda zaman kaybı yaratır. 🕒

❌ Büyük veri yüklerinde bellek sorunu: Stack, öğeleri üst üste eklediği için çok fazla veri geldiğinde bellekte yük oluşturabilir. Çok uzun stack’ler performansı düşürebilir. 💾

❌ null değer karmaşası: Java Stack null eklemeyi destekler ama mantıksal olarak dikkat gerekir. null bir eleman var mı yok mu ayırt etmek zorlaşabilir, yanlışlıkla peek() veya pop() sırasında kafa karışıklığı yaratabilir. ⚠️

##### 🔹 Ana Amacı

Stack’in ana amacı, verileri bir düzen içinde yönetmek ve son giren veriye öncelik vermek. Kodun akışını takip etmek, geçici işlemleri yönetmek veya geriye dönük işlemler yapmak stack ile çok kolay. 🎯

- 🔹 Diğer Koleksiyonlardan Farkı

- ArrayList veya LinkedList gibi yapılar rastgele erişim sağlar.

- Stack sadece LIFO mantığı ile çalışır, yani en son eklenen her zaman en önce çıkar.

- Kodun okunabilirliğini ve yönetimini artırır, kaos yerine düzen getirir.

##### 🔹 Hangi Durumlarda Kullanılır?

- Fonksiyon çağrı sıralarını takip etmek (recursion) 🔁

- Undo/Redo mekanizmaları

- Tarayıcı veya uygulama geçmişi yönetimi

- Parantez veya ifade denetimi, matematiksel işlem çözümleri

##### 📌 Kodumuzun Açıklaması

```java
Stack<String> stackList = new Stack<>();
```

Burada bir Stack oluşturduk. Generic ile String tipinde veri saklayacağını belirledik.

```java
stackList.add("Hüseyin");
stackList.push("Selami");
stackList.push("Şakir");
```
add() ve push() ile veri ekledik. add() arka planda aynı push() gibi çalışıyor ama push() kullanımı daha mantıklı ve okunur.

```java
System.out.println(stackList);
```
Stack’in mevcut içeriğini yazdırır. En üstteki öğe en son eklenen.

```java
System.out.println(stackList.peek());
```
peek() ile stack’in en üstündeki elemanı çıkarılmadan görürüz.

```java
System.out.println(stackList.search("Betül"));
```
search() ile stack içinde bir öğe ararız. Eğer yoksa -1 döner.

```java
System.out.println(stackList.empty());
System.out.println(stackList.isEmpty());
```
İki yöntem de stack’in boş olup olmadığını kontrol eder. empty() daha klasik, isEmpty() Collection arayüzünden geliyor.

💡 Özetle: Stack, kodda düzen, takip ve hızlı erişim sağlar. Karmaşadan uzak, basit ama çok güçlü bir yapı. LIFO mantığını bilmek ve gerektiği yerde kullanmak, seni kod yazarken gerçekten profesyonel yapar. 🌟