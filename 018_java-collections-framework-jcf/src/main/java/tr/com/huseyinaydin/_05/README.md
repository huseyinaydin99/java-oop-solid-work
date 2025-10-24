#### 🛠️ Java Set Koleksiyonları: HashSet, TreeSet ve LinkedHashSet’in Derinlemesine İncelenmesi

##### HashSet Veri Ambarı: 👜

HashSet, veri ambarı veya büyük veri koleksiyonlarında benzersiz elemanları hızlıca tutmak için ideal bir yapıdır. 🎯 Eleman ekleme, silme ve arama işlemleri çoğunlukla O(1) zaman karmaşıklığıyla gerçekleşir, bu sayede milyonlarca kayıt olsa bile performans kaybı minimumdur. İçeride veriler hash tabanlı tutulduğu için sıralama veya ekleme sırası hiçbir şekilde garanti edilmez; yani veri ambarındaki kayıtları rastgele bir torbaya attığını düşünebilirsin 🎲. Null değeri ekleyebilirsin, ancak yalnızca bir kez; tekrar eden elemanlar da eklenmez ve böylece veri bütünlüğü korunur. HashSet, büyük veri analizi veya benzersiz kayıt listeleri oluştururken hafızayı verimli kullanır ve yüksek performans sağlar. 💡

Diğerlerinden farkı: TreeSet ve LinkedHashSet’in aksine, HashSet ekleme sırasını veya sıralamayı umursamaz, sadece hız ve benzersizlik üzerine odaklanır. ⚡

##### Özellikleri:

- 🎲 Rastgele sıralama: HashSet’te elemanlar kafasına göre takılır, ekleme sırası hiçbir şekilde garanti edilmez. Hani tombala torbası gibi düşün 🎟️, ne çıkacağını asla bilemezsin.

- ⚪ Null eklenebilir: Tek bir null ekleyebilirsin; bir kez eklenmişse tekrar denesen de eklenmez. Yani null da tıpkı diğer elemanlar gibi benzersiz davranır.

- ⚡ Hızlıdır: Eleman ekleme, silme ve arama işlemleri çok hızlıdır, çoğu zaman O(1) ⏱️. Büyük veri setlerinde bile performans kaybı neredeyse hissedilmez.

- 🏆 Avantaj: Performans canavarı 🦾! Veri miktarı artsa bile ekleme ve sorgulama işlemleri süper seri gerçekleşir.

- ⚠️ Dezavantaj: Sıra tamamen kaotik 😅. Ekleme sırasını veya alfabetik düzeni görmek istiyorsan HashSet tek başına yetersiz kalır.

> Ne zaman kullanılır: Elemanların sırasının önemli olmadığı, yalnızca benzersiz değerlerin gerektiği durumlarda.

---

##### TreeSet Veri Ambarı: 🌳

TreeSet, Java’da Set arayüzünü implement eden ve elemanları otomatik olarak sıralayan bir koleksiyon sınıfıdır. 🧩 Ekleme sırasında elemanlar doğal sıralama düzenine göre yerleştirilir; String ise alfabetik, sayılar ise küçükten büyüğe doğru sıralanır. 📈 TreeSet null eklemeye izin vermez, çünkü sıralama sırasında null ile karşılaştırma yapılamaz ve hata fırlatır. ⚡ Performansı HashSet’e göre biraz daha yavaştır, çünkü eleman ekleme, silme ve arama işlemleri O(log n) karmaşıklığındadır. TreeSet, verilerin hem benzersiz hem de sıralı olmasını istediğin durumlarda mükemmel bir tercihtir. 🏆

##### Özellikleri:

- TreeSet’e her eleman eklediğinde veya sildiğinde ağaç yapısını (Red-Black Tree) korumak için içsel olarak gerekli karşılaştırmaları ve dengelemeleri yapar. Bu yüzden her işlem sırasında ekleme/silme mantığını yeniden organize eder, sırayı bozmamak için sürekli kontrol eder. 🌳⚡ Performans, donanım, elektrik ve zaman maaliyeti.

- Otomatik sıralama: TreeSet, eklenen elemanları her zaman doğal sıralama veya sağladığın Comparator’a göre tutar. String ise alfabetik, sayılar küçükten büyüğe sıralanır. Bu sayede ayrıca sıralama yapmak zorunda kalmazsın. 📈

- Benzersiz elemanlar: TreeSet, Set arayüzünden geldiği için tekrarlayan elemanlara izin vermez. Aynı elemanı birden fazla eklemeye çalışırsan sadece ilk eklenen kalır, diğerleri eklenmez. ✅

- Null değeri yok: TreeSet, sıralama yaparken karşılaştırma kullandığı için null eklenemez. Eğer null eklemeye çalışırsan NullPointerException fırlatır. ⚠️

- Dengeli ağaç yapısı: İçeride elemanları Red-Black Tree ile saklar. Bu nedenle her ekleme ve silme işleminde ağacı dengede tutar ve elemanları sıralı gösterir. 🌳⚡

- Performans: Eleman ekleme, silme ve arama O(log n) karmaşıklığına sahiptir. Yani HashSet kadar hızlı olmasa da, sıralı ve benzersiz veri gerektiğinde mantıklı bir tercihtir. ⏱️

- Koleksiyon uyumluluğu: TreeSet, Set ve NavigableSet arayüzlerini implement eder, bu sayede alt küme alma, üst sınır bulma gibi gelişmiş işlemleri kolayca yapabilirsin. 🛠️

---

##### LinkedHashSet Veri Ambarı 🔗

LinkedHashSet, Java’da HashSet’in bir çeşididir ama ekleme sırasını koruyan bir settir. 🎯 Elemanlar benzersizdir, yani tekrar eden bir eleman eklemeye çalışırsan eklenmez. Null eklenebilir ve tıpkı diğer elemanlar gibi tek seferlik kabul edilir. 💡 İçeride HashMap tabanlı bir yapı kullanır ve ekleme sırasını linked list ile takip eder, bu sayede çıktılar ekleme sırasına göre gelir. ⚡ HashSet’e yakın hız sunar, ancak biraz daha fazla hafıza kullanır, çünkü her eleman için bağlantı bilgisi de saklanır.

LinkedHashSet, Java’da HashSet’in özel bir versiyonudur ama en büyük farkı ekleme sırasını korumasıdır. 🎯 İçeride elemanları bir HashMap tabanlı yapı ile saklar ve aynı zamanda her elemanı birbirine bağlayan doubly linked list kullanır, bu sayede ekleme sırası her zaman hatırlanır. 🔗 Yani evet, referans olarak birbirine bağlıdırlar; her eleman hem HashMap ile hızlı erişime sahiptir hem de linked list sayesinde sıralı bir zincir oluşturur. Elemanlar benzersizdir; tekrar eden değerler eklenmez ve null değeri bir kez ekleyebilirsin. ⚪ Performansı HashSet’e çok yakındır, çünkü temel erişim HashMap üzerinden O(1) gerçekleşir, ama linked list bağlantıları nedeniyle biraz daha fazla hafıza kullanır. 💡 Bu yapı, hem hızlı erişim hem de ekleme sırasını korumak istediğin durumlar için ideal bir seçimdir.

##### Özellikleri:

- Ekleme sırasını korur: Elemanlar, ekleme sırasına göre saklanır ve çıktıda da aynı sırayla görünür. Bu sayede sıralamayı manuel yapmana gerek kalmaz. 📋

- Benzersiz elemanlar: Set olduğu için tekrar eden elemanları eklemez; aynı eleman ikinci kez eklenirse ihmal edilir. ✅

- Null değeri eklenebilir: Tek bir null eklenebilir, tekrar denense de eklenmez. ⚪

- Hash tabanlı yapı + bağlantı: İçeride HashMap tabanlı bir yapı ve linked list kullanılır, bu yüzden HashSet gibi hızlıdır ama ekstra hafıza kullanır. 🧩

- Performans: Eleman ekleme, silme ve arama çoğunlukla O(1)’dir, yani HashSet kadar hızlıdır, ama linked list bağlantıları nedeniyle küçük bir ek yük vardır. ⚡

- Koleksiyon uyumluluğu: TreeSet gibi sıralı değildir, sadece ekleme sırasını korur; Set arayüzü ile uyumludur ve HashSet’in fonksiyonlarının çoğunu kullanabilirsin. 🛠️


---

##### Set’e TreeSet, HashSet veya LinkedHashSet Enjekte Etmenin Mantığı ⚡🌳🛍️

Java’da Set bir interface olduğu için, TreeSet, HashSet veya LinkedHashSet’i Set’e enjekte etmek, kodu bağımsız, esnek ve sürdürülebilir hâle getirir 🌱. Böylece ihtiyacına göre alt yapıyı değiştirebilirsin; örneğin başta HashSet ile hızlı ve benzersiz veri tutarken, sonra sıralı veri gerekirse TreeSet’e geçiş yapabilirsin 🌳. Bu yaklaşım test edilebilirliği artırır, çünkü birim testlerde Set’i mock veya farklı implementasyonlarla kolayca değiştirebilirsin 🧪. Ayrıca kod tekrarı azaltılır, ortak arayüz sayesinde tüm Set işlemleri tek bir metod üzerinden yürütülür, böylece OOP prensipleri olan polymorphism ve abstraction aktif kullanılmış olur ⚡. Sonuç olarak, Set arayüzünü kullanmak hem bakımı kolay hem de gelecekteki değişikliklere hazır bir tasarım sağlar 🛍️.

---

```mathematica
╔═══════════════════╦══════════════════════╦═════════════════════╦════════════════════════╗
║      Özellik      ║      HashSet 🎲      ║     TreeSet 🌳      ║  LinkedHashSet 🛍️    ║
╠═══════════════════╬══════════════════════╬═════════════════════╬════════════════════════╣
║ Sıralama          ║ Rastgele             ║ Doğal / Comparator  ║ Ekleme sırası          ║
║                   ║ (tombala torbası 🟤)║ Alfabetik / Küçükten║ Korunur               ║
║                   ║                      ║ büyüğe              ║                        ║
╠═══════════════════╬══════════════════════╬═════════════════════╬════════════════════════╣
║ Null Değeri       ║ ✔ (tek)              ║ ❌                  ║ ✔ (tek)                ║
╠═══════════════════╬══════════════════════╬═════════════════════╬════════════════════════╣
║ Benzersizlik      ║ ✔                    ║ ✔                   ║ ✔                      ║
╠═══════════════════╬══════════════════════╬═════════════════════╬════════════════════════╣
║ Ekleme / Silme    ║ ⚡ O(1)              ║ ⏱️ O(log n)         ║ ⚡ O(1)                ║
║ Performansı       ║                      ║ (ağaç dengeleme)    ║ (linked list ek yükü)  ║
╠═══════════════════╬══════════════════════╬═════════════════════╬════════════════════════╣
║ Hafıza Kullanımı  ║ Minimal              ║ Orta                ║ Biraz daha fazla       ║
║                   ║                      ║                     ║ (linked list)          ║
╠═══════════════════╬══════════════════════╬═════════════════════╬════════════════════════╣
║ Kullanım Amacı    ║ Benzersiz, hızlı veri║ Benzersiz, sıralı   ║ Benzersiz, ekleme sırası║
║                   ║ setleri              ║ veri setleri        ║ önemli setler          ║
╚═══════════════════╩══════════════════════╩═════════════════════╩════════════════════════╝
```

---