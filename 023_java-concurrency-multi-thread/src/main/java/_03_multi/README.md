#### ⚙️ Multi Threading — Java’da Paralel Dünyalar 🌍

Programlama dünyasında **multi-threading**, bir uygulamanın aynı anda birden fazla işi yürütebilmesini sağlayan sihirli bir güç gibidir. 🧠  
Amaç; işlem gücünü bölmek değil, **aynı anda birden fazla işi akıcı şekilde yürütmektir**. Eğer bu yapı kullanılmazsa, program her işi tek tek, sırayla yapar — yani bir iş bitmeden diğeri başlayamaz. ⏳  
Multi-threading sayesinde uygulama; bir yandan dosya indirirken, diğer yandan kullanıcı etkileşimine cevap verebilir veya arka planda veri işleyebilir. ⚡  
Bu yaklaşım, yazılımcıya **eşzamanlılık (concurrency)** kavramını öğretir, yazılıma ise **hız, verimlilik ve gerçek zamanlılık** kazandırır. 🚀  
Thread isimlendirme (naming) ve önceliklendirme (priority), çoklu görevler arasında düzeni ve kontrolü sağlar. İsimlendirme, hata ayıklama ve log takibinde büyük kolaylık sunarken; önceliklendirme, hangi işin daha kritik olduğuna programın karar vermesini sağlar.  
Kullanılmazsa, sistemdeki tüm işlemler “tek sıra”da bekler ve performans dramatik biçimde düşer. 🐢  
Avantajları; **paralel çalışma**, **kaynak kullanım optimizasyonu** ve **reaktif sistemler geliştirme becerisi** sunmasıdır.  
Dezavantajı ise yanlış yönetildiğinde **senkronizasyon hataları**, **kaynak çatışmaları** ve **karmaşık hata senaryoları** doğurabilmesidir. 🧩  
Kısacası, multi-threading programcıya sadece hız değil, **sistemsel düşünmeyi** öğretir — yazılımı bir orkestra gibi yönetmeyi sağlar. 🎶

##### Kodun Dansı

```java
package _03_multi;

//inner
class MyBaseThread extends Thread {

    @Override
    public void run() {
        System.out.println("Thread sınıfını implemente eden donatan/imzalayan MyBaseThread sınıfının run metodu çalışıyor: "
                + Thread.currentThread().getName());
    }
}

public class AppMain {

    public static void main(String[] args) {
        MyBaseThread myBaseThread1 = new MyBaseThread();
        MyBaseThread myBaseThread2 = new MyBaseThread();
        MyBaseThread myBaseThread3 = new MyBaseThread();
        MyBaseThread myBaseThread4 = new MyBaseThread();

        System.out.println("Thead sınıfı inherit eden MyBaseThread sınıfları hazır!");

        myBaseThread1.setName("FB");
        myBaseThread2.setName("TS");
        myBaseThread3.setName("BJK");
        myBaseThread4.setName("GS");

        myBaseThread1.setPriority(Thread.MAX_PRIORITY); //10
        myBaseThread2.setPriority(Thread.NORM_PRIORITY); //5
        myBaseThread3.setPriority(Thread.NORM_PRIORITY); //5
        myBaseThread4.setPriority(Thread.MIN_PRIORITY); //1

        myBaseThread1.start();
        myBaseThread2.start();
        myBaseThread3.start();
        myBaseThread4.start();
    }
}
```

#### 🧩 Java’da Düzeni Sağlamak ⚙️

Multi-threading yapısında, birden fazla iş (thread) aynı anda çalıştığı için, her bir thread’in **ne yaptığını ve ne kadar önemli olduğunu bilmek** büyük bir ihtiyaçtır. 🧠  
İşte bu noktada **isimlendirme (naming)** ve **önceliklendirme (priority)** kavramları devreye girer. 🎯

##### 🏷️ Thread İsimlendirme (Naming)
Bir thread’e anlamlı bir isim vermek, özellikle karmaşık sistemlerde **izlenebilirlik** sağlar.  
Örneğin, bir log kaydında `"BJK"` adlı thread’in hata verdiğini görmek, `"Thread-3"` yazmasından çok daha anlamlıdır. 🔍  
İsimlendirme, **debug sürecini kolaylaştırır**, kodun okunabilirliğini artırır ve ekip içinde iletişimi güçlendirir. 👨‍💻

##### ⚖️ Thread Önceliklendirme (Priority)
Her thread eşit öneme sahip değildir — kimisi daha kritik, kimisi bekleyebilir. ⏱️  
Java’da öncelikler `1 (MIN_PRIORITY)` ile `10 (MAX_PRIORITY)` arasında belirlenir.  
Yüksek öncelikli thread, işlemciye **daha erken erişim hakkı** elde eder. Ancak bu, düşük öncelikli thread’in asla çalışmayacağı anlamına gelmez.  
Amaç, **kaynak paylaşımında denge** kurmak ve sistem performansını optimize etmektir. ⚙️

##### 💡 Neden Kullanılır?
- Programın akışını **daha yönetilebilir** hale getirmek için.
- Kritik işlemlerin **öncelikli** yürütülmesini sağlamak için.
- Hata ayıklama ve bakım süreçlerinde **görünürlüğü artırmak** için.
- Yazılım mimarisinde **kontrollü çoklu görev bilinci** oluşturmak için.

##### ⚠️ Kullanılmazsa Ne Olur?
Thread’ler **anonim ve eşit öncelikli** olacağından, hangi thread’in hangi işi yaptığını ayırt etmek zorlaşır.  
Bu da özellikle büyük sistemlerde **kaosa ve performans düşüşüne** neden olabilir. 💥

##### 🌟 Kazandırdıkları
İsimlendirme ve önceliklendirme, yazılımcıya **disiplin, organizasyon, sistematik düşünme** yeteneği kazandırır.  
Yazılım ise bu sayede **kontrollü, izlenebilir ve kararlı** hale gelir. 🧠
