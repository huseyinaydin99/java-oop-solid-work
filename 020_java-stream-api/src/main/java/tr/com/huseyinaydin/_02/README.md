#### Stream ile Ürün Yönetimi ve Hesaplama Case Çalışma 🚀🛒
Bu projede, kendime ait bir ürün listesi oluşturdum ve Java Stream API ile ona hayat verdim 🌟.
Önce productsList1 içine hem normal hem anonim Product objeleri ekledim, yani veri kaynağımı kurdum 📦.
Sonra filtreleme yaptım: fiyatı 2 ile 5 arasında olan ürünleri seçtim (filter) ve gerekirse ilk birkaçını (limit) aldım 🎯.
Bunları collect(Collectors.toList()) veya .toList() ile yeni listelere topladım, böylece akışı kontrol edip istediğim sonucu elde ettim 📝.
Ayrıca fiyatları double’a çevirip toplamını aldım (mapToDouble + sum), yani listeden basit bir finansal özet çıkardım 💰.
Kısaca, bu kod veri filtreleme, sınırlama, toplama ve listeleme süreçlerini tek bir akışta işleyerek temiz, fonksiyonel ve okunaklı bir çözüm sundu 🌊💡.

##### Özetle:

- Listeyi hazırladım ve ürünleri ekledim 🏗️

- Stream ile filtreleme ve limitleme yaptım 🔍➡️

- Fiyatları topladım ve sonucu yazdırdım 💵

- Tüm süreçler kısa, okunaklı ve hatasız bir şekilde işlendi ✨

>Bu kod, bana Stream’in gücünü gösterdi; veriyle hızlı ve fonksiyonel şekilde çalışabilmek, hem kodu temiz tutuyor hem de performansı artırıyor ⚡📈.

#### Stream Metotları; 🛠️

##### Filter 🔍
`filter()` ile Stream’deki elemanları kendi kurallarıma göre seçiyorum; öyle rastgele değil, **fiyatı belirli aralıkta olan ürünleri** akışta tutuyorum ⚡, böylece gereksiz veriyi işlemden çıkarıp performansı artırıyorum 💨.

##### Limit ⏱️
`limit()` ile akışı sınırlıyorum; örneğin **ilk 2 veya 10 ürünü** alarak çok büyük listelerde bile **kontrol bende** 😎, fazla veriyle kafam karışmıyor ve işlem daha hızlı ilerliyor 🏎️.

##### Collect(Collectors.toList()) 📜
`.collect(Collectors.toList())` ile akışı **somut bir listeye dönüştürüyorum**, yani Stream’de akan veriyi artık elimde tutabilir ve üstünde başka işlemler yapabilirim 👐; adeta akışı bir kutuya koyup saklamak gibi 🎁.

##### .toList() 🗃️
`.toList()` ise **daha modern ve kısa** bir yöntemle aynı işi yapıyor, Stream’i listeye çevirmek, ama ekstra yazım karmaşası olmadan; kod hem okunaklı hem estetik 😎✨.

##### mapToDouble(Product::getPrice) 💵
`mapToDouble()` ile ürünlerin fiyatlarını **double türüne dönüştürüp sayısal akışa** çeviriyorum, böylece matematiksel işlemler (toplama, ortalama) yapmak çok kolay 🧮, float ile uğraşmak yok 😅.

##### sum() ➕
`sum()` ile artık dönüştürdüğüm sayısal akıştaki tüm fiyatları topluyorum, yani Stream bana **otomatik hesap makinesi** gibi davranıyor 🏦; tek bir satırda toplam değer elimde 💥.


##### Java Stream API ve SQL Arasındaki Bağlantı 🛠️💾

Java Stream API’ye baktığımda aklıma SQL sorguları geliyor çünkü temelde aynı mantığı izliyorlar 🔍. Stream’de filter() ile veri seçmek, SQL’deki WHERE şartına; sorted() ile sıralama yapmak, SQL’deki ORDER BY’e; distinct() ile benzersiz veri almak, SQL’deki DISTINCT kavramına tekabül ediyor 🌟. Hatta map() ile veriyi dönüştürmek, SQL’de SELECT içinde hesaplamalar yapmak gibi düşünülebilir 🧮. Bu benzerlik sayesinde, SQL ile çalışmış bir programcı Stream API’ye hızlı adapte olabiliyor, veri akışını fonksiyonel, okunaklı ve optimize edilmiş bir şekilde yönetebiliyor 🚀. Kısaca, Stream API bana Java içinde SQL benzeri bir veri işleme gücü sunuyor; hem filtreleme, sıralama hem de toplama işlemlerini akış halinde, hatasız ve esnek şekilde yapabiliyorum 💡📊.