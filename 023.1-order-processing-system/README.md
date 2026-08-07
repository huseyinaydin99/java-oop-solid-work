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