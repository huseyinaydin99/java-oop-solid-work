#### ⚙️ Hangi Durumlarda Thread Sınıfı Inherit Edilmeli? - Hangi Durumlarda Runnable Arayüzü Implemente Edilmeli? ❤️

> 🧠 Çoklu iş parçacığı (multi-threading) dünyasında iki temel yol vardır: **Thread sınıfını miras almak** 🧬 veya **Runnable arayüzünü uygulamak (implement etmek)** 🔗.  
> Bu iki yaklaşım ilk bakışta benzer görünür ama aslında yazılım mimarisinde ciddi farklar yaratır. Eğer `Thread` sınıfını miras alırsam, Java bana “sen artık bir thread’sin” der — yani o sınıf doğrudan kendi başına bir iş parçacığı gibi davranır. Ama `Runnable` arayüzünü uygularsam, “sen sadece yapılacak işi tarif et, çalıştırma işini ben hallederim” der Java ☕. "Sen bir iş akışı değil sadece işsin demek" istiyor.  
> Yani **Runnable** yaklaşımı daha esnek, daha temiz ve daha nesne yönelimli bir çözümdür. Çünkü ben hem başka bir sınıfı miras alabilir, hem de thread görevini ayrı tutabilirim. Thread mirası ise “tek yönlü bir yolculuk” gibidir — bir kez Thread sınıfını miras aldıysan başka bir üst sınıf alamazsın 🚫.  
> İşte bu yüzden **Thread miras almak** kısa vadede kolay, ama **Runnable** kullanmak uzun vadede doğru stratejidir. 💡

---

##### 🚀 Kodun Kalbi: Ne Yaptım, Neden Yaptım?

Bu örnekte ben `MyBaseThread` sınıfını **`Runnable` arayüzüyle imzaladım** 🖋️.  
Yani “ben çalıştırılabilir bir işim” dedim ama doğrudan bir thread olmadım.  
`run()` metodunun içinde thread’in yapacağı işi belirttim — konsola hangi thread’in çalıştığını basıyorum.  
Sonra `AppMain` içinde bir `Thread` nesnesi oluşturdum ve `new Thread(myBaseThread)` diyerek bu Runnable’ı içine enjekte ettim 💉.  
Yani bir anlamda “çalışacak işi” bir “taşıyıcı”ya verdim.  
Ardından `thread.start();` diyerek motoru çalıştırdım 🚗💨.  
Bu noktada artık iki farklı akış var: biri `main` thread, diğeri ise benim `Runnable` nesnemin çalıştığı thread.

Kodun en tatlı kısmı şu:  
Thread miras alsaydım sadece o sınıfla çalışabilirdim, ama Runnable ile bu işi **farklı Thread objelerine bağlayarak** tekrar tekrar kullanabilirim 🔄.  
Bu da bana büyük bir esneklik kazandırıyor.

---

##### 🧩 Thread vs Runnable Karşılaştırması

| 💬 Özellik | 🧬 Thread Miras | 🔗 Runnable Arayüz |
|-------------|----------------|-------------------|
| **Kalıtım (Inheritance)** | Thread sınıfını miras aldığı için başka bir sınıf miras alınamaz ❌ | Başka sınıflarla birlikte kullanılabilir ✅ |
| **Yeniden Kullanılabilirlik** | Kısıtlı – her thread kendi başına var olur ⚠️ | Esnek – aynı Runnable birçok thread içinde çalışabilir ♻️ |
| **Tasarım Esnekliği** | Daha az | Daha fazla |
| **Basitlik** | Hızlı başlatılır | Daha profesyonel ve yönetilebilir yapı |
| **Önerilen Kullanım** | Küçük, basit işler için | Büyük, ölçeklenebilir projeler için |

---

##### ⚡ Avantaj & Dezavantaj

**✅ Avantajlar:**
- Runnable sayesinde kodun yeniden kullanılabilirliği artar 🔁
- Thread yönetimi daha kontrollü hale gelir 🕹️
- Mimari daha temiz olur; görev (task) yani iş/Runnable ve yürütme (execution) yani iş akışı/Thread ayrılır 🧩

**⚠️ Dezavantajlar:**
- Thread nesnesini ayrıca oluşturmak gerekir, bu da ilk etapta biraz daha fazla kod anlamına gelir ✍️
- Yanlış thread yönetimi, performans düşüşüne yol açabilir 🐌

---

##### 💬 Son Söz

Benim için `Runnable` kullanmak, kodda **disiplin** ve **esneklik** kazandıran bir alışkanlık.  
Bir nevi “işi yapan” ile “işi yöneten”i ayırmak gibi — bu sayede yazılım hem okunaklı hem de ölçeklenebilir olur.  
Eğer yazılımımda birden fazla görevi paralel olarak yönetmek istiyorsam, `Runnable` benim tercihimdir.  
Çünkü ben sadece işi tanımlarım, çalıştırma işini Java’nın Thread motoruna bırakırım. ❤️‍🔥

---

##### 💻 Kodumuz

```java
package _02_impl;

//inner
class MyBaseThread implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread sınıfını implemente eden donatan/imzalayan MyBaseThread sınıfının run metodu çalışıyor: "
                + Thread.currentThread().getName());
    }
}

public class AppMain {

    public static void main(String[] args) {
        MyBaseThread myBaseThread = new MyBaseThread();
        System.out.println("Thread sınıfını implemente eden donatan/imzalayan MyBaseThread iş akışı henüz başlatılmadı.");

        Thread thread = new Thread(myBaseThread);
        thread.start();
        System.out.println("Thread sınıfını implemente eden donatan/imzalayan MyBaseThread ahanda başlatıldı inanmazsan aha dayıya sor..");
    }
}
```