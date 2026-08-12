### CopyOnWriteArrayList — Snapshot Tabanlı Thread-Safe Liste 🔄

#### Nedir?

CopyOnWriteArrayList, yazma operasyonlarında arka plandaki dizinin kopyasını oluşturan thread-safe bir List implementasyonudur. 📦 Okuma işlemleri mevcut array üzerinden gerçekleştiği için okuyucular değişikliklerden izole bir snapshot görür.

#### Ne değildir?

CopyOnWriteArrayList, her yazmada array kopyaladığı için yüksek yazma trafiği için optimize edilmiş bir koleksiyon değildir. ⚠️ Özellikle sık add/remove yapılan senaryolarda kopyalama maliyeti performansı düşürür.

#### ArrayList'ten farkı nedir?

ArrayList mevcut backing array'i doğrudan değiştirirken, CopyOnWriteArrayList her mutasyonda yeni bir array oluşturur. 🔄 Bu nedenle mevcut iterator eski array üzerinde çalışmaya devam eder ve eklenen eleman mevcut iterasyona dahil olmaz.

#### Ne işe yarar ve hangi soruna çözüm getirir?

Başka thread'ler listeyi değiştirirken okuyucuların kilitleme olmadan güvenli ve tutarlı şekilde iterasyon yapmasını sağlar. 🧠 Böylece özellikle çok okuma + az yazma senaryolarında concurrent modification problemini snapshot izolasyonu ile çözer.

#### 1 soru, önceden ekli 3 eleman olsun sonra başka işlemler yapılsın okuma veya herhangi bir iş, o iş bitsin, sonrasında ise ekleme yapılsın. Bu ekleme işleminde yeni kopyaya mı eklenir yoksa hali hazırda var olan array'e mi eklenir? Okuma anında değil ama. Okuma bitmiş sonlanmış. Sonrasında ekleniyor ama. 

Okuma tamamen bittikten sonra ekleme yapılsa bile CopyOnWriteArrayList mevcut array'i değiştirmez; yine yeni bir array kopyası oluşturup elemanı bu yeni array'e ekler. 🔧

#### Peki bir sonraki okumada hangi array okunur en son hali mi? 

Evet, bir sonraki okuma en son oluşturulan ve eklenen elemanı içeren güncel array'i okur. 🔄

#### Peki öncekile ne olur otomatik silinir mi? 

Evet, eski array'e artık hiçbir referans kalmadığında Garbage Collector tarafından otomatik olarak temizlenebilir. ♻️

#### Peki okuma işlemi yokken yeni kopya oluşturması ve eklemeyi oraya yapması saçma değil mi? Boşuna açmış oluyor en son hangi array varsa git ona yaz değil mi? Zaten okuma yok. 

Hayır; çünkü CopyOnWriteArrayList okuma olup olmadığını kontrol etmez, her mutasyonda yeni array oluşturmak tasarımının temel garantisidir; okuma yokken bile mevcut array'e doğrudan yazmaz. 🔒

#### Peki okuma olup olmadığını kontrol etseydi daha iyi olmaz mıydı? 

Genellikle hayır; çünkü bunu kontrol etmek için ek senkronizasyon ve durum takibi gerekir, bu da basit ve lock-free okuma modelinin maliyetini artırarak tasarımın temel avantajını zayıflatır. ⚙️

#### Peki güncelleme ve silme işlemleri de aynı mantıkta mıdır? 

Evet, set, remove, add gibi tüm yapısal veya içerik değiştiren mutasyonlarda aynı Copy-on-Write mantığı uygulanır; yeni array oluşturulur ve değişiklik bu yeni array üzerinde gerçekleştirilir. 🔁

#### CopyOnWriteArrayList Iterator — Değişmez Snapshot Üzerinden Güvenli İterasyon 🧭

Bu aşamada CopyOnWriteArrayList iterator'ının oluşturulduğu anda mevcut array'i snapshot olarak aldığını ve sonradan yapılan eklemelerin mevcut iterator'a yansımadığını gördük. 🔎 Ancak users.iterator() yeniden çağrıldığında yeni iterator güncel array'i snapshot aldığı için "Ayşe" gibi sonradan eklenen elemanları da görür; ayrıca snapshot değiştirilemediğinden iterator.remove() desteklenmez. 🧩

İkinci iterator = users.iterator() sonrasında çağırdığım remove(), ikinci iterator'ın oluşturulduğu anda gördüğü güncel yani son array üzerinde çalışmaya çalışır; ancak iterator ilgili snapshot'ı(array'i) değiştiremediği için işlem UnsupportedOperationException ile kesilir. 🔒