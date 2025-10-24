#### 💫 Java’da List ve ArrayList — Esnekliğin ve Düzenin Dansı

Java’da veri saklamanın iki temel yolu vardır: diziler (array) ve koleksiyonlar (collections). Dizi, sabit boyutlu bir kutu gibidir; kaç eleman alacağını baştan söylersin. Ama ArrayList? O daha çok akıllı bir depo gibidir. 📦 Yeni eleman eklendikçe kendini büyütür, gerekirse küçülür, elemanları kolayca arar, siler, sıralar. Yani modern Java’nın dinamik yüzüdür. ⚙️

##### 🧠 ArrayList list = new ArrayList() vs List list = new ArrayList()

İlk tanımlama (ArrayList list = new ArrayList()) doğrudan sınıfa bağımlıdır. Yani referans türü de, nesne türü de ArrayList.
İkincisinde (List list = new ArrayList()) ise referans türü List arayüzüdür, ama arkasında çalışan gerçek nesne ArrayList’tir.

Bu fark küçük görünse de derindir. Çünkü bağımlılığı azaltır.
Referansı arayüze göre tanımlarsan, yarın LinkedList, Vector ya da CopyOnWriteArrayList’e geçmek istersen kodunun geri kalanına dokunmadan sadece “new” kısmını değiştirmen yeterlidir. 🔄
Yani birinde sınıfa bağımlısın, diğerinde soyutlamaya. İşte bu, nesne yönelimli tasarımın özüdür. 🧩

##### ⚙️ ArrayList Nedir, Ne Değildir?

- ArrayList, List arayüzünü uygulayan, dinamik boyutlu bir dizidir.
- Arka planda hâlâ bir array (dizi) vardır ama bu dizi doldukça otomatik olarak genişler.
- Sen sadece .add(), .remove(), .get() diyerek çalışırsın, kapasiteyle uğraşmazsın.

- Normal dizide length sabittir.
- ArrayList’te size() dinamik olarak değişir.
- Bu fark, Java’nın “esneklik ve kolaylık” felsefesinin ta kendisidir. 🚀

##### 📏 Kullanmazsak Ne Olur?

Klasik dizilerle çalışırsın ama şu sıkıntılarla karşılaşırsın:

- Yeni eleman ekleyemezsin, çünkü boyut sabittir.

- Ortadan eleman silersen, gerisini sen kaydırmak zorundasın.

- Arama, sıralama, içerik kontrolü (contains, indexOf gibi) işlemlerini manuel yazman gerekir.

>ArrayList bunların hepsini senin yerine yapar.
>Yani kodu hem kısa, hem bakımı kolay, hem de daha güvenli hale getirir. 🧰

##### 🧮 hashCode() Neden Var?

hashCode(), bir listenin (ya da genel olarak bir nesnenin) bellekteki durumunu sayısal olarak temsil eden bir kimliktir.
Liste içeriği değiştiğinde hashCode() da değişir.
Böylece Java, iki listenin içerik olarak aynı mı yoksa sadece aynı adreste mi bulunduğunu anlamakta bu değeri kullanabilir.

>Bu değer, özellikle HashSet, HashMap gibi yapılarda verimli arama ve karşılaştırma için hayati öneme sahiptir.
>Bir bakıma “nesnelerin parmak izi” gibidir. 🧬

##### 🧩 Referans ve Değer Karşılaştırması

- == operatörü, iki değişkenin aynı bellek adresini (referansı) tutup tutmadığını kontrol eder.
- .equals() metodu ise içerik karşılaştırması yapar.

Yani iki liste farklı adreslerde olabilir ama içindeki elemanlar aynıysa .equals() onları eşit sayar, == ise farklı der.
Bu ayrım, Java’da en çok karıştırılan ama en temel farklardan biridir. 🧠

##### 💡 ArrayList’in Sağladığı İmkanlar

✅ Dinamik boyut yönetimi
✅ Null değer desteği
✅ Kolay erişim (get(index))
✅ İçerik arama (contains, indexOf)
✅ Silme ve ekleme işlemlerinde kolaylık
✅ Otomatik hashCode güncellemesi
✅ Koleksiyonlar arası kolay karşılaştırma

##### ⚡️ Sonuç — Esnekliğin Estetiği

Benim gözümde ArrayList, Java’da sadece bir veri yapısı değil, esnekliğin sembolüdür.
Normal dizilerin kısıtlayıcı doğasını aşar, kodu özgürleştirir.
Bugün bir listeyi yönetmek, yarın veri tabanından gelen kayıtları tutmak, öbür gün bir API’den dönen sonuçları işlemek... Hepsi aynı yapı üzerinden akar gider. 🌊

>Yani ArrayList öyle sessiz bir sınıftır ki, kodun içinde fark edilmeden çalışır ama sistemin akışını düzen ve hızla sürdürür.
>Soyutlamanın ve pratiğin mükemmel dengesidir. ⚖️💫


---

#### 💫 Java’da LinkedList - Arka Plandaki Zincirli Kahraman!

Java’da LinkedList, aslında sahnenin arkasında ip gibi birbirine bağlı düğümler (nodes) dizisidir. Her düğüm kendi verisini ve bir sonrakine (next), hatta çift yönlü yapıda bir öncekine (previous) referans tutar. 🎯

##### 🚀 Nedir Ne Değildir?

LinkedList, klasik ArrayList gibi bir koleksiyon sınıfıdır ama veri yapısı bambaşkadır. ArrayList verileri bellekte bitişik olarak tutar, LinkedList ise dağınık ama bağlantılı tutar. Yani biri apartman dairesi gibiyken diğeri zincirle bağlı müstakil evler gibidir. 🏠➡️🏠➡️🏠

##### ⚙️ Neden Var?

ArrayList’te ortadan eleman eklemek/silmek maliyetlidir çünkü arkadaki elemanları kaydırmak gerekir. LinkedList bu noktada parlar! 💡
Eleman ekleme/silme işlemleri referans güncellemeyle olur, o yüzden çok daha hızlıdır.
Ama rastgele erişim (örneğin get(4)) yavaştır çünkü zinciri baştan başlayarak takip etmesi gerekir. 🐢

##### 🧭 Indexleme Var mı?

Evet, LinkedList de List arayüzünü uygular, dolayısıyla get(index) veya set(index, value) gibi işlemleri destekler ama bu arka planda tek tek düğümleri gezerek yapılır. Yani vardır ama hızlı değildir. ⚠️

##### 🔗 Next / Previous Olayı

Her düğümün next ve previous referansı vardır. Bu sayede hem ileri hem geri gezilebilir.
Yani LinkedList aslında doubly linked listtir. ↔️ “Doubly linked list”, her elemanın hem kendinden sonraki hem de önceki elemana bağlantı (referans) tuttuğu, yani çift yönlü gezilebilen bir listedir. ↔️

##### 🧩 Kodumuzda:

- Integer ve String türlerinde iki ayrı LinkedList oluşturduk.

- add(), addFirst(), addLast(), set(), get() gibi temel metotları kullandık.

- Hem for döngüsü hem foreach ile elemanları dolaştık.

- null değer ekledik, LinkedList bunu da sorunsuz kabul etti. 💪

- Listenin ilk ve son elemanlarına erişimi gösterdik (getFirst(), getLast()).

- Kısacası örneğimiz, LinkedList’in dinamik, esnek ve bağlantılı yapısını birebir sergiliyor. 🔥

##### ⚡ Özetle:

> ArrayList = Hızlı erişim/okuma ama ekleme/silmede//güncellemede yavaş 🚀

> LinkedList = Yavaş erişim/okuma ama ekleme/silmede/güncellemede hızlı 🔗

> next, previous bağlantılarıyla zincir gibi birbirine bağlıdır tren gibi ⛓️

> Hafıza yönetimi farklıdır; adres tabanlı çalışır 🧠
