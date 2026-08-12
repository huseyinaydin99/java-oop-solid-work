### CopyOnWriteArrayList — Snapshot Tabanlı Thread-Safe Liste 🔄

#### Nedir?

CopyOnWriteArrayList, yazma operasyonlarında arka plandaki dizinin kopyasını oluşturan thread-safe bir List implementasyonudur. 📦 Okuma işlemleri mevcut array üzerinden gerçekleştiği için okuyucular değişikliklerden izole bir snapshot görür.

#### Ne değildir?

CopyOnWriteArrayList, her yazmada array kopyaladığı için yüksek yazma trafiği için optimize edilmiş bir koleksiyon değildir. ⚠️ Özellikle sık add/remove yapılan senaryolarda kopyalama maliyeti performansı düşürür.

#### ArrayList'ten farkı nedir?

ArrayList mevcut backing array'i doğrudan değiştirirken, CopyOnWriteArrayList her mutasyonda yeni bir array oluşturur. 🔄 Bu nedenle mevcut iterator eski array üzerinde çalışmaya devam eder ve eklenen eleman mevcut iterasyona dahil olmaz.

#### Ne işe yarar ve hangi soruna çözüm getirir?

Başka thread'ler listeyi değiştirirken okuyucuların kilitleme olmadan güvenli ve tutarlı şekilde iterasyon yapmasını sağlar. 🧠 Böylece özellikle çok okuma + az yazma senaryolarında concurrent modification problemini snapshot izolasyonu ile çözer.