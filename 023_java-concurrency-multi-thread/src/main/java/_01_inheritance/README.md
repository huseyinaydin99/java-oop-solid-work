#### ⚡ THREAD — Paralel Evrenlerin Java’daki Gerçek Hali ❤️

> 🧠 Yazılımın kalbinde akan görünmez damarlar gibidir **thread** yapısı. Bir programın aynı anda birden fazla işi yürütebilmesini sağlar. Eğer thread kullanmazsak, tüm işlerimizi sıraya koyup tek tek çalıştırmak zorunda kalırız; bu da uygulamamızı yavaş, hantal ve donmaya meyilli hale getirir. Thread sayesinde bir yandan veri okurken diğer yandan hesap yapabilir, hatta kullanıcı arayüzünü canlı tutarken arkada devasa işlemleri döndürebiliriz. ⚙️  
> Thread’ler hız ve verim sağlar ama aynı zamanda dikkat ister — çünkü birden fazla akış aynı kaynağa erişmeye çalışırsa “sen mi ben mi” kavgası çıkar 😅. Doğru kullanıldığında mucize yaratır, yanlış kullanıldığında kabusa döner. Ama yazılım dünyasında **eşzamanlılık (concurrency)** denilen o büyülü kavramın kapısını işte bu thread’ler açar. 🚪✨

---

##### 💡 Kodun Ruhunu Anlatayım

Bu örnekte kendi sınıfım **`MyBaseThread`**, Java’nın `Thread` sınıfını miras alarak **çoklu iş parçacığı (multi-threading)** mantığını uyguluyor.  
Buradaki `run()` metodu, sanki bir sahnede kendi repliğini söyleyen oyuncu gibi; thread çalışmaya başlayınca sahneye çıkıyor 🎭.  
Ben de `main` metodunda önce diyorum ki:  
“Thread sınıfını miras alıp genişleten MyBaseThread iş akışı henüz başlatılmadı.”  
Yani henüz motor çalışmıyor, sadece aracı tanıtıyorum.

Sonra `myBaseThread.start();` diyerek motoru çalıştırıyorum — işte burada Java arka planda `run()` metodumu çağırıyor 🚀.  
Sonra konsola şu mesaj geliyor:
> “Thread sınıfını miras alıp genişleten MyBaseThread ahanda başlatıldı inanmazsan aha dayıya sor..”  
Bu da demek oluyor ki, artık iki farklı akış aynı anda yaşam buldu.  
Bir yanda `main` thread kendi mesajını basarken, diğer yanda `MyBaseThread` kendi işini yapıyor — **iki paralel dünya aynı anda çalışıyor!** 🌍🌍

---

##### 🧩 Thread Kullanmanın Özeti

✅ **Avantajları:**
- İşlerin eşzamanlı yürümesi sayesinde hız ve performans artar ⚡
- Kullanıcı arayüzü donmaz, arka planda işler akmaya devam eder 🎡
- Sistem kaynaklarını verimli kullanmayı öğretir 🧠

⚠️ **Dezavantajları:**
- Doğru yönetilmezse veri karışıklığı ve senkronizasyon sorunları çıkar 🧨
- Debug (hata ayıklama) süreci tek akışa göre daha karmaşıktır 🕵️‍♂️
- Gereksiz yere fazla thread açmak sistemi boğabilir 🐢

---

##### 🧠 Sonuç

Thread benim için yalnızca bir Java özelliği değil, bir **zihin disiplini**.  
Aynı anda birden fazla düşünceyi yürütmek gibi — dikkat, denge ve koordinasyon ister.  
Ama bir kez doğru şekilde kullanmayı öğrendin mi, yazılımına **canlılık** katarsın.  
İşte benim kodumun ruhu da tam olarak bu. 💫

---

##### 💻 Kodun Dansı

```java
package _01_inheritance;

//inner
class MyBaseThread extends Thread {

    @Override
    public void run() {
        System.out.println("Thread sınıfını miras alıp genişleten MyBaseThread sınıfının run metodu çalışıyor: "
                + Thread.currentThread().getName());
    }
}

public class AppMain {

    public static void main(String[] args) {
        MyBaseThread myBaseThread = new MyBaseThread();
        System.out.println("Thread sınıfını miras alıp genişleten MyBaseThread iş akışı henüz başlatılmadı.");
        myBaseThread.start();
        System.out.println("Thread sınıfını miras alıp genişleten MyBaseThread ahanda başlatıldı inanmazsan aha dayıya sor..");
    }
}
```