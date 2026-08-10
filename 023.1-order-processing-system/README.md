### 🚀 Thread Pool Nedir? Neden Kullanılır?

Thread Pool, uygulamanın her iş için yeni bir thread oluşturması yerine, önceden oluşturulmuş belirli sayıdaki thread'i ortak bir havuzda tutarak görevleri bu thread'ler üzerinde çalıştırmasını sağlayan bir yapıdır. 🎯 Böylece thread oluşturma ve yok etme maliyeti ortadan kalkar, bellek kullanımı daha verimli hale gelir 💡 ve uygulama aynı anda çok sayıda isteği daha kontrollü şekilde işleyebilir. Örneğin 100 görev geldiğinde ve havuzda yalnızca 4 thread varsa, ilk 4 görev hemen çalışır ⚙️, kalan görevler ise kuyrukta 🗂️ bekler; bir thread işini bitirdiğinde sıradaki görevi alarak çalışmaya devam eder. Bu sayede sistem hem daha kararlı 🚦 hem de daha ölçeklenebilir 📈 olur.

### ❓ Neden new Thread() yerine ExecutorService kullanılır?

new Thread() her görev için yeni bir thread oluşturduğu için 🧵 zamanla ciddi performans kaybına ve gereksiz bellek tüketimine neden olabilir. ExecutorService ise thread'leri bir havuzda ♻️ yeniden kullanarak kaynakları daha verimli yönetir, uygulamanın daha hızlı ⚡, kararlı 🛡️ ve ölçeklenebilir 📈 çalışmasını sağlar.

### ❓ Thread Pool dolarsa ne olur?

Thread Pool'daki tüm thread'ler meşgulse 🚦 yeni gelen görevler hemen çalıştırılmaz, Task Queue (Görev Kuyruğu) içerisine alınarak sırasını bekler. 🗂️ Bir thread işini tamamladığında kuyruktaki ilk görevi devralır ve böylece görevler kontrollü, güvenli ve düzenli şekilde işlenmeye devam eder. ✅

### ❓ Görevler nasıl sıraya alınır?

ExecutorService, çalışacak boş bir thread bulunmadığında gelen görevleri dahili Task Queue içerisinde bekletir. 📥 Thread'lerden biri işini bitirdiğinde ⏳ kuyruktaki ilk görevi otomatik olarak alır ve çalıştırır; böylece aynı anda yalnızca havuzdaki thread sayısı kadar görev çalışırken sistem düzenini korur. ⚙️

### ❓ Fixed Thread Pool ile Cached Thread Pool arasındaki fark nedir?

FixedThreadPool belirlenen sayıda thread ile çalışır 🧵 ve fazla gelen görevleri kuyruğa alarak kaynak kullanımını kontrol altında tutar. CachedThreadPool ise ihtiyaç duydukça yeni thread oluşturur 🚀 ve boşta kalanları daha sonra kapatır; bu nedenle yoğun iş yüklerinde hızlı olabilir ancak kontrolsüz kullanılırsa gereğinden fazla thread oluşturarak sistem kaynaklarını tüketebilir. ⚠️

### ❓ Thread Pool boyutu neye göre belirlenir?

Thread Pool boyutu yapılan işin türüne göre belirlenir; CPU ağırlıklı işlemlerde genellikle işlemci çekirdek sayısı 🖥️ esas alınırken, I/O ağırlıklı işlemlerde bekleme süreleri fazla olduğu için daha yüksek thread sayıları tercih edilebilir. 🎯 Amaç, işlemciyi verimli kullanırken gereksiz thread oluşturmadan ⚡ maksimum performans ve kararlılık sağlamaktır.

### 🚀 CompletableFuture Nedir?

CompletableFuture, uzun süren işlemleri ana akışı bekletmeden 🧵 arka planda asenkron olarak çalıştırmaya ve sonuçlarını daha sonra yönetmeye yarayan güçlü bir Java API'sidir. ⚡ Birden fazla görevi aynı anda başlatabilir, sonuçlarını birleştirebilir 🤝, hata yönetimi yapabilir 🛡️ ve karmaşık iş akışlarını okunabilir, esnek ve performanslı şekilde oluşturmayı sağlar.

### 🎯 CompletableFuture Neden Kullanılır?

CompletableFuture, birbirinden bağımsız işlemleri aynı anda çalıştırarak ⏱️ toplam işlem süresini kısaltmak ve sistem kaynaklarını daha verimli kullanmak için tercih edilir. 🚀 Özellikle veritabanı, API, dosya sistemi veya ödeme işlemleri gibi bekleme süresi yüksek operasyonlarda ⚙️ uygulamanın daha hızlı, akıcı ve ölçeklenebilir çalışmasına katkı sağlar.

### 🛠️ CompletableFuture Hangi Amaca Hizmet Eder?

Temel amacı, bağımsız görevleri paralel şekilde çalıştırıp 🧩 sonuçlarını belirli bir sıraya göre yönetmek ve tüm süreci tek bir akış içerisinde kontrol edebilmektir. 📈 Böylece hem kod tekrarını azaltır ✨ hem de karmaşık asenkron işlemleri daha okunabilir, sürdürülebilir ve yönetilebilir hale getirir.

### 🚨 CompletableFuture Hangi Soruna Çözüm Getirir?

Uzun süren işlemlerin birbirini gereksiz yere beklemesi ⏳ ve uygulamanın zaman kaybetmesi problemini ortadan kaldırır. ⚡ Görevleri aynı anda çalıştırarak işlem süresini azaltır 🚀, sistem kaynaklarını daha verimli kullanır 💻 ve kullanıcıya daha hızlı yanıt verilmesini sağlar.

### 🔄 Senkron (Synchronous) Nedir?

Senkron çalışma modelinde her işlem, kendisinden önceki işlem tamamlanmadan başlayamaz ⛔ ve tüm görevler belirli bir sırayla ilerler. 📋 Bu yapı veri tutarlılığı açısından avantaj sağlarken, uzun süren işlemler nedeniyle uygulamanın beklemesine ve toplam işlem süresinin uzamasına neden olabilir. ⏱️

### ⚡ Asenkron (Asynchronous) Nedir?

Asenkron çalışma modelinde bir işlem başlatıldıktan sonra 🧵 uygulama onu beklemek zorunda kalmadan diğer işlere devam edebilir. 🚀 Bu yaklaşım özellikle birbirinden bağımsız görevlerin aynı anda yürütülmesini sağlayarak performansı artırır 📈 ve kaynakların daha verimli kullanılmasına yardımcı olur.

### 🧵 Multi Threading Nedir?

Multi Threading, bir uygulamanın birden fazla thread kullanarak aynı anda birden fazla işi yürütebilmesini sağlayan eşzamanlı programlama yaklaşımıdır. ⚙️ İşlemci kaynaklarının daha verimli kullanılmasını sağlar 💪, bağımsız görevlerin paralel çalışmasına olanak tanır 🚀 ve özellikle yüksek performans gerektiren uygulamalarda işlem süresini önemli ölçüde azaltır.

### 🏁 Race Condition Nedir, Ne Değildir?

Race Condition, birden fazla thread'in aynı paylaşılan veriye aynı anda erişip, işlemlerin hangi sırayla gerçekleşeceğine bağlı olarak öngörülemeyen veya hatalı sonuçlar üretmesi durumudur. 🧵 Özellikle bir thread'in veriyi okuması ile güncellemesi arasındaki süreçte başka bir thread'in aynı veriye müdahale etmesi ⚠️ problemi doğurur; yani mesele yalnızca "iki thread'in aynı anda çalışması" değil, sonucun thread'lerin zamanlamasına ve erişim sırasına bağımlı hale gelmesidir. 🎯 Örneğin stok değeri 1 iken iki thread'in de aynı anda stock > 0 kontrolünü geçmesi, her ikisinin de ürünü satılmış kabul etmesine yol açabilir. 🛒 Race Condition bir exception değildir ❌, doğrudan bir thread hatası da değildir; paylaşılan mutable state üzerinde kontrolsüz eşzamanlı erişimden kaynaklanan bir concurrency problemidir. 🔥

### 🔐 ReentrantLock Nedir, Ne Değildir?

ReentrantLock, Java'nın java.util.concurrent.locks paketinde bulunan ve birden fazla thread'in paylaşılan bir kaynağa aynı anda erişmesini kontrol etmek için kullanılan açıkça yönetilen bir lock mekanizmasıdır. 🧵 Buradaki lock, kritik bölgeye aynı anda yalnızca bir thread'in girmesini sağlayarak Race Condition ⚠️ ve buna bağlı veri tutarsızlıklarını önler; thread kilidi aldığı sürece diğer thread'ler bekler, işini bitirdiğinde unlock() ile kilidi bırakır. 🔐 Reentrant olması ise aynı thread'in, sahip olduğu kilidi tekrar kilitleyebilmesini ifade eder; bu nedenle basit bir synchronized alternatifi olmakla birlikte tryLock(), adil kilitleme (fairness) ve interruptible locking gibi daha ayrıntılı kontrol seçenekleri sunar. 🎯 Ancak ReentrantLock bir thread oluşturma mekanizması, Thread Pool veya genel bir performans artırıcı değildir; temel amacı paylaşılan mutable state üzerindeki kritik bölgeye erişimi senkronize ederek concurrency kaynaklı veri yarışlarını kontrol etmektir.

### 🛑 lockInterruptibly() Nasıl Çalışır?

Thread-A işi aldığı için lock'ı alır ve çalışırken Thread-B aynı lock'ı almak için beklemek zorunda kalır. 🧵 Thread-B bu bekleme sırasında interrupt() alırsa, lockInterruptibly() sayesinde beklemeyi bırakıp InterruptedException ile akıştan çıkabilir; yani "lock'ı almak için bekle ama artık beklemeye gerek kalmadıysa bu işi bırak" mantığı uygulanır. ⚡ Buradaki interrupt() thread'i zorla kill etmez; thread'e iptal/beklemeyi bırak sinyali gönderir ve thread'in bu sinyale nasıl tepki vereceğini uygulama belirler. 🎯 Bu mekanizma özellikle uzun süre lock bekleyen görevlerin gerektiğinde iptal edilebilmesi ve kontrollü kapanış sırasında thread'lerin gereksiz yere kilit bekleyerek sistemde takılı kalmaması için kullanılır.

### ⚖️ Fair ReentrantLock Nedir?

new ReentrantLock(true) ile oluşturulan fair lock, kilidi bekleyen thread'lerin lock'a erişiminde mümkün olduğunca bekleme sırasını (FIFO) takip etmesini sağlar. 🧵 Örneğin Thread-A lock'ı kullanırken Thread-B, Thread-C ve Thread-D sıraya girdiyse, lock serbest kaldığında bu thread'lerin sırayı bozmadan ilerlemesi hedeflenir. ⚖️ Böylece bir thread'in sürekli lock'ı kapıp diğer thread'lerin uzun süre beklemesi (starvation) riski azaltılır, ancak bu adaletin sağlanması ek koordinasyon maliyeti getirdiği için performans bir miktar düşebilir. 🚦 Kısacası fair lock'ın amacı daha hızlı çalışmak değil, lock'a erişimde thread'ler arasında daha adil bir bekleme düzeni sağlamaktır.

### ⚡ TryLockInventory Nasıl Çalışır?

TryLockInventory, ReentrantLock kullanarak stok üzerinde işlem yaparken kilidi beklemek yerine kilidi o anda alıp alamadığını kontrol eden yapıdır. 🧵 Bir thread lock'ı almışsa diğer thread tryLock() çağrısında beklemeye geçmez, doğrudan false alarak işlemi bırakır; yani "kilit müsaitse gir, değilse bekleme" mantığı uygulanır. ⚡ Bu yaklaşım, özellikle lock'ın uzun süre meşgul olabileceği durumlarda thread'lerin boş yere beklemesini önleyerek sistemin daha esnek davranmasını sağlar. 🎯 Dolayısıyla TryLockInventory'nin amacı Race Condition'ı çözmekten ziyade, kilit alma sürecini bloklamayan ve başarısız kilit denemesinde alternatif karar verebilen bir kontrol mekanizması sağlamaktır.

tryLock() kilidi alamazsa varsayılan olarak tekrar denemez, false döndürür ve senin kodun ne yapıyorsa onu yapar; bizim örnekte doğrudan return false ile işlem sonlanıyor. 🔄 Ama istersen while döngüsüyle tekrar deneyebilir, tryLock(2, TimeUnit.SECONDS) ile belirli bir süre bekleyebilir veya lock alınamazsa başka bir işlem yapabilirsin.

Evet; eğer lock alınamadığında işlem doğrudan bırakılıyorsa, thread üstlenmesi gereken işi gerçekleştirmeden atlamış olur ve bu işin kritik olması durumunda uygulama açısından ciddi bir iş mantığı problemi doğabilir.