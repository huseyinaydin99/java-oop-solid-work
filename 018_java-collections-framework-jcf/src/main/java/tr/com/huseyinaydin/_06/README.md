#### 🎯 JAVA'DA QUEUE VE ARRAYBLOCKINGQUEUE VERI AMBARLARI

Java’da **Queue (Kuyruk)** yapısı, bir verinin **ilk giren ilk çıkar (FIFO - First In First Out)** mantığıyla işlendiği, **veri akışını düzenleyen** çok önemli bir koleksiyon türüdür.  
📬 Gerçek hayatta sıraya giren insanlar gibi düşün: Kim önce geldiyse, önce işlem görür.  
İşte Queue da tam olarak bunu yapar. Aynı Ramazan ayı fırın pide kuyruğu, sona kalan dona kalır.

---

##### 🚦 Queue Nedir, Ne İşe Yarar?

Queue, **verileri belirli bir sırada tutup**, bu sırayla işlem yapılmasını sağlayan bir **koleksiyon arayüzüdür (interface)**.  
Java’daki `java.util.Queue` arayüzü, FIFO mantığına göre çalışır.

Amaç?  
👉 **Veri akışını güvenli, düzenli ve yönetilebilir** hale getirmek.

Örneğin bir mesaj kuyruğu, görev planlayıcı veya üretici-tüketici (producer-consumer) modeli kuruyorsan, Queue tam bu iş için vardır.

Eğer Queue kullanmazsan:
- Verilerin **karışık sırada işlenmesi** riski doğar,
- **Çok iş parçacıklı (multithreading)** ortamlarda **veri tutarsızlığı** yaşanır,
- Kodun yönetimi ve genişletilebilirliği düşer.

---

##### ⚙️ ArrayBlockingQueue Nedir?

`ArrayBlockingQueue`, Queue arayüzünü **thread-safe** bir şekilde uygulayan (implement eden) **bloklayıcı (blocking)** bir kuyruktur.  
Bu yapı **sabit bir kapasiteye** sahiptir. Yani kuyruğun uzunluğu önceden belirlenir.  
Örneğin:
```java
Queue<String> studentList1 = new ArrayBlockingQueue<>(25);
```

📦 Burada kapasite **25** olarak belirlenmiştir.  
Kuyruk dolduğunda yeni eleman eklenemez; boşalana kadar **üretici (producer)** bekletilir.

**Yani:**
- Çok iş parçacıklı (thread’li) yapılarda **veri senkronizasyonu sağlar**,
- **Üretici-tüketici** sistemlerinde mükemmel bir çözümdür,
- Hem güvenli hem de tahmin edilebilir davranış sergiler.

---

##### 🧩 PriorityQueue Nedir?

`PriorityQueue`, Queue’nun **öncelikli versiyonudur**.  
FIFO mantığı yerine **öncelik sırasına** göre elemanları işler.  
Yani elemanlar doğal sıralarına (alfabetik veya sayısal) göre dizilir.

Örnek:
Queue<String> studentList2 = new PriorityQueue<>();

Bu durumda veriler, doğal sıralarına göre şöyle olur:

    ["Behlül", "Behlül", "Fahrettin", "Selami", "Selami", "Veli"]

📈 Çünkü `PriorityQueue` verileri alfabetik (veya doğal) sıralamaya göre işler.

---

##### 💻 KOD ÖRNEĞİ (Kopyalayıp derleyebilirsin)

    import java.util.*;
    import java.util.concurrent.ArrayBlockingQueue;

    public class QueueDemo {
        public static void main(String[] args) {
            Queue<String> studentList1 = new ArrayBlockingQueue<>(25);
            studentList1.add("Hüseyin");
            studentList1.add("Beyhan");
            studentList1.add("Fahrettin");
            // studentList1.add(null); // Olmaz!! Null değer kabul edilmez.
            studentList1.add("Ahmet");
            studentList1.add("Selami");
            studentList1.add("Hamit");
            System.out.println(studentList1);

            System.out.println("----------------------");

            Queue<String> studentList2 = new PriorityQueue<>();
            studentList2.add("Selami");
            studentList2.add("Behlül");
            studentList2.add("Fahrettin");
            // studentList2.add(null); // Olmaz!! Null kabul edilmez.
            studentList2.add("Veli");
            studentList2.add("Selami");
            studentList2.add("Behlül");
            System.out.println(studentList2);
        }
    }

🧠 Bu kodda iki farklı `Queue` türünü karşılaştırıyoruz:
- **ArrayBlockingQueue:** Sabit kapasite + thread-safe + FIFO
- **PriorityQueue:** Doğal sıralama + dinamik kapasite + öncelikli işleme

---

##### 🚀 Neden Kullanmalıyım?

| Durum | Kullanılacak Yapı | Açıklama |
|-------|-------------------|----------|
| Thread-safe (çoklu iş parçacığı) ortamı | ✅ ArrayBlockingQueue | Eşzamanlı erişim güvenliği sağlar |
| Öncelik sırasına göre işlem | ✅ PriorityQueue | Elemanları doğal sırayla işler |
| Basit kuyruk mantığı | ✅ LinkedList (Queue olarak) | FIFO ama thread-safe değildir |
| Performans önemliyse | ⚡ PriorityQueue | Hızlıdır, ancak sıralama yükü eklenir |

>PriorityQueue, verileri diziler (array) üzerinde tutar ve heap (öncelikli yığın) mantığıyla çalıştığı için, eleman ekleme (offer) ve çıkarma (poll) işlemlerini O(log n) karmaşıklıkta yapar; bu da kilit mekanizması kullanan ArrayBlockingQueue’ya göre daha hızlı olmasını sağlar.

>ArrayBlockingQueue tamamen senkronize (thread-safe) bir yapıdır; her ekleme ve çıkarma işleminde kilit (lock) mekanizması kullanarak iş parçacıkları arasında güvenliği garanti eder, bu da doğal olarak ek bir işlem maliyeti yaratıp hızını düşürür.

---

##### 🌟 Avantajları

- 🔒 **Thread-safe:** `ArrayBlockingQueue` ile veri tutarlılığı sağlanır.
- 📏 **Sabit kapasite:** Sistem kaynaklarını önceden kontrol edebilirsin.
- 🔄 **FIFO mantığı:** Veriler tahmin edilebilir sırada işlenir.
- ⚙️ **Basit API:** `add()`, `offer()`, `poll()`, `peek()` gibi net metotlar.

---

##### ⚠️ Dezavantajları

- 🚧 `ArrayBlockingQueue` sabit kapasitelidir → Dinamik genişleme yapamaz.
- 🐢 Blocking mekanizması beklemeye neden olabilir → Performans düşebilir.
- 🔡 `PriorityQueue` sıralama maliyeti taşır.
- 🚫 **Null değer kabul etmez**, aksi halde `NullPointerException` fırlatır.

---

##### 🧭 Ne Katar, Ne Öğretir?

Bir programcı için `Queue` yapısını öğrenmek, yalnızca bir veri koleksiyonunu değil, **akış yönetimi felsefesini** kavramak demektir.  
Bu yapı, gerçek hayattaki süreçleri — *sıra, öncelik, bekleme, tüketim* — yazılım dünyasına taşır.

---

###### 🔄 Gerçek süreçleri modelleme
İşlemlerin sırasını kontrol ederek sistem davranışını **öngörülebilir** kılar.

###### 🧵 Eşzamanlı programlamayı içselleştirme
`Thread-safe` yapılarda verinin **güvenli paylaşımını** öğretir.

###### 🧱 Mimari düşünme alışkanlığı
Kodun **katmanlı**, **düzenli** ve **bakımı kolay** hale gelmesini sağlar.

###### ⚡ Verimlilik bilinci
Doğru veri yapısını seçmenin performansa nasıl **doğrudan etki ettiğini** gösterir.

---

Kısaca: `Queue`, yazılıma **disiplin**, **zaman yönetimi** ve **akış kontrolü** kazandırır.  
O, yalnızca bir veri yapısı değil; **mantıksal düzenin** ve **sistematik düşüncenin** yazılım karşılığıdır.

---

#### 🔚 Özetle

| Yapı | Katkısı |
|------|----------|
| `Queue` | Düzeni sağlar. |
| `ArrayBlockingQueue` | Disiplini ve güvenliği korur. |
| `PriorityQueue` | Önceliği ve hızı belirler. |

---

⚙️ **Hepsi bir araya geldiğinde:**  
Sistemin **kontrollü**, **güvenli** ve **tutarlı** şekilde çalışmasını sağlar.

📘 *Kısacası, Java’da `Queue` yapısı yazılım dünyasının “sessiz kahramanıdır” —  
arka planda işler yolunda gitsin diye tüm trafiği sessizce yönetir.*
