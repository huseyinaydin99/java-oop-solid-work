#### 🐱 Java’da Class ve Obje Derin Mantığı 💻

Java’da class 🏗️, bir nesnenin tüm özelliklerini (fields) ve davranışlarını (methods) tanımlayan mavi baskıdır; örneğin Cat class’ı bir kedinin yaşını, cinsini ve rengini saklar. Objeler (objects) 🐾, bu blueprint’ten yaratılan somut örneklerdir; Cat kediNesne = new Cat(); ifadesi bellekte yeni bir kedi nesnesi oluşturur ve constructor 🔧 aracılığıyla ilk değerleri atar. Nesnelerin özellikleri doğrudan (kediNesne.yas = 2) veya encapsulation ile (setYas(2)) değiştirilebilir ve getYas() gibi metodlarla okunabilir. Farklı constructor’lar sayesinde nesneler farklı başlangıç durumlarıyla 🚀 yaratılabilir, her obje bağımsızdır ve kendi veri kopyasını taşır. Böylece class, veriyi ve davranışı organize eden mantıksal çatı 🧠; objeler ise bu çatının somut, işleyen örnekleri 💡dir.

"Mavi baskı (blueprint) 📝, bir class’ın Java’da nesnelerin nasıl oluşturulacağını ve hangi özelliklere sahip olacağını tanımlayan planıdır."

⚡, bir nesne oluşturulurken önce static bloklar (class yüklenirken) ve ardından instance bloklar (constructor çağrılmadan hemen önce) çalışır, sonra constructor devreye girer.

✅, Java her class’a parametresiz varsayılan constructor ekler; ancak biz parametreli bir constructor eklersek varsayılan otomatik constructor artık oluşmaz, yalnızca tanımladığımız constructor geçerli olur.

🔹, eğer kendimiz parametreli constructor tanımlarsak, Java’nın otomatik verdiği varsayılan parametresiz constructor artık yok olur.

Java’da yıkıcı metot (destructor) yoktur ❌; bellek yönetimi çöp toplayıcı (Garbage Collector) tarafından otomatik yapılır.

finalize() 🗑️, bir nesne çöp toplama sırasında temizlenmeden hemen önce JVM tarafından çağrılabilen, kaynak serbest bırakma amaçlı özel metottur, ancak kullanımı artık önerilmez.

┌──────────────────────────────────────────────────────────┐
│    💾 JAVA MEMORY MODEL - JAVA BELLEK MODELİ 💾          │
├──────────────────────────────────────────────────────────┤
│                          STACK 🧩                        │
│  (Metot çağrıları, yerel değişkenler, referanslar)       │
│                                                          │
│  ┌────────────────────────────────────────────────────┐  │
│  │ main()                                            │   │
│  │   ┌───────────────┐                               │   │
│  │   │ Cat kedi1 ────┼───▶ 🏷️ 0x1A2F  (adres)        │   │
│  │   └───────────────┘                               │   │
│  │   int sayi = 40;                                  │   │
│  │   String isim = "Mehmet"; ───▶ 🏷️ 0x9B7D          │   │
│  └────────────────────────────────────────────────────┘  │
│                                                          │
├──────────────────────────────────────────────────────────┤
│                           HEAP 🧱                        │
│  (Nesneler, diziler, String literal’ler depolanır)       │
│                                                          │
│  📦 0x1A2F → new Cat()                                   │
│     ├── yas: 2                                           │
│     ├── cinsi: "Van" ───▶ 🏷️ 0x7CC1                      │
│     └── rengi: "Sarı" ─▶ 🏷️ 0x8AA9                       │
│                                                          │
│  📦 0x9B7D → "Mehmet" (String pool)                      │
│  📦 0x7CC1 → "Van" (String pool)                         │
│  📦 0x8AA9 → "Beyaz" (String pool)                       │
│                                                          │
├──────────────────────────────────────────────────────────┤
│                   🧹 Garbage Collector 🧹                │
│     Stack’ten erişilemeyen heap objelerini temizler.     │
│     (referans koparsa nesne toplanır)                    │
└──────────────────────────────────────────────────────────┘
