#### ⚙️ Java’da Vector — Eski Kurt Ama Hâlâ İş Görür! 🧠

Java’da Vector, aslında ArrayList’in atalarından biridir. 90’ların Java dünyasında “dinamik dizi” ihtiyacını karşılamak için vardı. Yani klasik diziler gibi sabit boyutlu değil; kapasitesi doldukça kendini otomatik büyütür. 📈

Java’da Vector, aslında ArrayList’in atalarından biridir. 90’ların Java dünyasında “dinamik dizi” ihtiyacını karşılamak için vardı. Yani klasik diziler gibi sabit boyutlu değil; kapasitesi doldukça kendini otomatik büyütür. 📈

##### 🚀 Nedir Ne Değildir?

Vector, ArrayList’e çok benzer çünkü ikisi de dinamik dizi (dynamic array) mantığıyla çalışır. Ama en büyük fark: Vector’ün thread-safe olmasıdır.
Yani aynı anda birden fazla thread (iş parçacığı) bu listeye erişmeye çalıştığında, Vector veriyi korumak için senkronizasyon (synchronized) kullanır. 🔒

##### 🎯 Ana Amacı ve Neden Var?

Ana amacı, çoklu thread ortamlarında veri bütünlüğünü koruyarak liste işlemlerini güvenli yapmak.
O dönemlerde koleksiyonlar için ayrı thread-safe yapı yoktu, bu yüzden Vector büyük bir ihtiyacı karşılıyordu.

##### 🧰 Hangi Durumlarda Kullanılır?

Aynı anda birden fazla thread’in listeye eriştiği, veri çakışmasının yaşanabileceği durumlarda.

>Ama günümüzde Collections.synchronizedList() veya CopyOnWriteArrayList gibi modern alternatifler varken artık çok tercih edilmez.

>🧩 Yani Vector hâlâ işini yapar ama artık “eski usul” kalmıştır.

##### ❌ Kullanmazsak Ne Olur?

Eğer tek thread’li bir program yazıyorsak, ArrayList çok daha hızlı ve hafiftir.
Vector’ün her ekleme ve okuma işlemi senkronize olduğu için, gereksiz yere yavaş çalışır. ⚠️

##### ⚖️ Avantaj / Dezavantaj:

###### Vector, ArrayList ve LinkedList arasında performans karşılaştırması yaptık:

- Vector → 10 milyon eleman ekledik, senkronizasyon yüzünden biraz daha gecikmeli çalıştı.

- ArrayList → Aynı işlemi daha hızlı tamamladı çünkü senkronizasyon yok ancak ArrayList, LinkedList'e göre okumada hızlı yazmada(ekle, sil, güncelle) yavaştır..

- LinkedList → LinkedList, Vector’e göre ekleme ve silmede daha hızlı, ancak veri erişiminde (get) Vector’den daha yavaştır. ⚖️ Çünkü LinkedList, eleman eklerken veya silerken sadece node bağlantılarını değiştirir, Vector ise bellekteki diziyi yeniden düzenlemek zorundadır. 🔗⚡

##### Kodda ayrıca:

- System.currentTimeMillis() ile çalışma süresi ölçtük. ⏱️

- add() ve add(index, value) metotlarını kullandık.

- Döngüyle elemanları gezdik (ama println’i kapatarak zaman kaybını önledik). 👏

##### 💥 Java’da Vector — Üst Üste Eklemede Ne Oluyor? 🤔

Vector aslında bir koleksiyon (liste) yapısıdır, yani sayıları toplamaz, sadece arka arkaya ekler. 📦
Her add() çağrısında yeni bir eleman, listenin sonuna eklenir; eski elemanlar olduğu gibi kalır. 🔗

Eğer kapasite dolarsa, Vector akıllıca davranıp kendini otomatik büyütür (genelde 2 katına çıkar) ve verileri yeni bir diziye taşır. 🚀
Ama burada matematiksel bir toplama yok — sadece veri biriktirme vardır. Yani add(10) ve sonra add(20) dediğinde sonuç [10, 20] olur, 30 değil! Aman karışmasın yağlı boya. 😄

##### 🧠 Kısaca:

Vector, hesap makinesi değil; depo gibidir. Her “add” çağrısında yeni bir kutu koyar, içindekileri asla karıştırmaz. 📦✨

##### Ölçümler:

Bu satırlar kodumuzdaki performans ölçüm sonuçlarını gösteriyor kardeşim ⚙️⏱️
Yani her biri, farklı koleksiyon tipinin (Vector, ArrayList, LinkedList) ekleme (add) ve okuma (read) işlemlerinde ne kadar zaman harcadığını özetliyor. 🔍

##### 📊 Ayrıntılı Açıklama (aha dayıya sor):

- // add 549 add + read : 740   // 1054   →  Vector
- // add 437 add + read : 470   // 890    →  ArrayList
- // add 3235 add + read : 3247 // 3362   →  LinkedList

##### 🧠 Ne demek bunlar?

- add 549 → 10 milyon veriyi Vector’e eklerken 549 milisaniye sürdü.

- add + read : 740 → Ekleme + okuma birlikte 740 ms sürdü.

- // 1054 → Toplam test süresi.

###### Aynı şekilde diğerleri de:

- ArrayList toplamda 890 ms ile en hızlı. ⚡

- Vector ikinci sırada (1054 ms) çünkü senkronizasyon yüzünden biraz yavaş. 🔒

- LinkedList ise 3362 ms ile açık ara en yavaş çünkü node bağlantılarını kurmak zaman alıyor. 🐢

##### LinkedList ArrayList'e Göre Ekleme Silme Güncellemede Hızlı Ama Okumada Yavaştır. 
##### O Halde ArrayList Nasıl En hızlı Çıktı? Tezat Yok Mu?:

##### ⚡ Önemli Nokta: Test Şartları

Kodumuzda 10 milyon elemanı arka arkaya ekledik ve sonra 100 defa add(index, value) yaptık.

##### Bu durumda:

- ArrayList → ekleme çoğunlukla sona yapıldığı için çok hızlıdır, çünkü sonuna eklemek sadece diziyi genişletmek anlamına gelir ve çoğu zaman zaten kapasite yeterli olduğu için hızlıdır. 🚀

- LinkedList → Eleman ekleme teoride hızlıdır, ama toplam 10 milyon eleman üzerinden her get(i) veya for döngüsüyle gezmek gerekince node’ları tek tek dolaşmak zorunda kaldığı için okuma süresi fırlıyor. 🐢

##### 🔍 Tezatın Kaynağı

- Teoride: LinkedList, ortadan eklemede hızlıdır.

- Pratikte: Testimizde çoğu ekleme sondan yapılıyor ve toplam süreyi ölçerken okuma (for veya get) süresi dahil ediliyor.

> Bu yüzden ArrayList toplamda daha hızlı çıktı, LinkedList’in avantajı tam olarak kullanılamadı. ⚖️

> Çok iyi oldu, çok da güzel oldu hadi kapat kayrı yeter gözün bozulacak :-D

---