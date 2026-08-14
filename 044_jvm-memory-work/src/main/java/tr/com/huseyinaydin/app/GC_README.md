### Serial GC:
Tek GC thread’iyle çalışıp özellikle küçük heap’lerde ve düşük kaynaklı uygulamalarda sadeliğiyle öne çıkar; günümüzde belirli küçük uygulamalar dışında genellikle tercih edilmez ve -XX:+UseSerialGC ile seçilebilir. 🧹

---

### Parallel GC:
GC işini birden fazla thread’e paralel dağıtarak yüksek throughput(işlem hacmi) hedefler, uzun duraklamaların kabul edilebildiği batch/CPU-ağırlıklı uygulamalarda anlamlıdır; -XX:+UseParallelGC ile seçilebilir. ⚙️

---

### G1 GC:
Heap’i bölgelere ayırıp pause-time hedeflerini gözeterek çöp toplamayı daha kontrollü gerçekleştirdiğinden genel amaçlı modern Java uygulamalarında güçlü varsayılan tercihtir; -XX:+UseG1GC ile açıkça seçilebilir. 🎯

---

### ZGC:
Çok büyük heap’lerde bile GC duraklamalarını çok düşük seviyede tutmayı hedefleyen concurrent bir collector’dır; düşük latency gerektiren modern uygulamalarda tercih edilir ve -XX:+UseZGC ile seçilebilir. 🚀 Shenandoah GC Red Hat tarafından geliştirilen bir Garbage Collector’dır; G1’e benzer şekilde düşük pause time hedefler, ancak temel farkı Shenandoah’ın GC sırasında nesneleri concurrent olarak taşıyıp (evacuate/compact) uygulamanın duraklamasını daha da azaltmaya odaklanmasıdır. 🔴🧠 G1, Heap’i region’lara bölüp “hangi region’ları temizlersem hedeflediğim pause süresine ulaşırım?” diye seçim yaparak çalışırken, Shenandoah’ın ayırt edici özelliği nesneleri taşıma ve referanslarını güncelleme gibi pahalı işleri de büyük ölçüde uygulama çalışırken concurrent yapmasıdır; yani ikisi de düşük pause hedefler ama Shenandoah’ın odağı GC’nin “taşıma” aşamasını bile mümkün olduğunca duraksamasız gerçekleştirmektir. 🔴🧠 Taşıma (evacuation), GC’nin canlı nesneleri eski Heap bölgelerinden daha düzenli/birleşik bölgelere kopyalayıp eski alanları serbest bırakması işlemidir; bu sayede Heap’te parçalanma azaltılır ve kullanılabilir alan yeniden düzenlenir. 🧹➡️📦

---

### Shenandoah GC: 
Uygulamanın çalışmasını mümkün olduğunca kesmeden concurrent olarak collection yaparak düşük pause time hedefler; özellikle latency hassas sistemlerde kullanılabilir ve -XX:+UseShenandoahGC ile seçilebilir. 🔄

---

### Epsilon GC:
Hiç gerçek çöp toplama yapmayan, yalnızca allocation gerçekleştirip Heap dolduğunda başarısız olan özel amaçlı bir collector’dır; performans testi veya GC davranışını izole etmek gibi deneysel senaryolarda kullanılır ve -XX:+UseEpsilonGC ile seçilebilir. 🧪

---

### CMS (Concurrent Mark Sweep):
Düşük pause time amacıyla concurrent collection yapıyordu ancak JDK 14'te kaldırıldığı için artık geçerli bir seçenek değildir; günümüzde G1, ZGC veya Shenandoah gibi modern collector'lar tercih edilir. 🪦

---
### 🧩 G1 GC ile Shenandoah GC arasındaki temel fark nedir; hangi yaklaşımda GC pause süreleri daha düşük tutulur? 

G1 GC temizleme ve taşıma işlerinin önemli bir kısmını belirli GC pause’larında yaparken, 🧹 Shenandoah GC nesnelerin taşınması ve referansların güncellenmesi gibi işlemleri büyük ölçüde uygulama çalışırken 🔄 concurrent yaparak daha düşük pause sürelerini hedefler. 🛰️

### 🔬 GC pause ve concurrent kavramları ne anlama gelir; aralarındaki temel fark nedir? 

GC pause, Garbage Collector’ın belirli işlemleri yapabilmek için uygulamanın Java thread’lerini ⏸️ kısa süreliğine durdurduğu zamandır; concurrent ise GC’nin bu işlemleri uygulama thread’leri çalışmaya devam ederken ⚙️ eşzamanlı olarak yürütmesi demektir. ⏳
Yani temel fark şu: pause = “uygulamayı durdur, 🛑 GC işini yap”, concurrent = “uygulama çalışırken 🔄 GC işini de yap” yaklaşımıdır.

### 🧵 Thread’lerin GC sırasında durdurulması veya çalışmaya devam etmesi uygulama açısından ne fark yaratır? 

Fark, GC’nin Heap’i temizlemesinden çok uygulamanın ne kadar süre boyunca yanıt veremez hâle geldiğidir: thread’ler durursa GC sırasında ⏸️ uygulama ilerleyemez (pause), çalışmaya devam ederse GC arka planda ⚙️ ilerler ve gecikme çok daha düşük olur (concurrent). ⚡

### 🏗️ Shenandoah GC, özellikle düşük latency ve büyük Heap hedeflerinde G1 GC’ye göre daha üstün müdür; üstünse bu fark hangi koşullarda ortaya çıkar? 

Düşük latency ve büyük Heap hedeflerinde Shenandoah kağıt üzerinde G1’den 🔍 bir adım önde görünebilir, ancak bunun karşılığında daha fazla CPU ve çalışma zamanı overhead’i getirebildiği için ⚖️ her uygulamada G1’den daha iyi değildir. 🎯