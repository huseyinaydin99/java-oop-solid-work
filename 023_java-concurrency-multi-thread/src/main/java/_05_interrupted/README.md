#### 🛌 Thread.sleep() ve Çoklu Döngülerin Büyüsü 🌀

Merhaba! 👋 Bugün Java'da **Thread.sleep()** metodunu, kullanım amacını, avantajlarını ve dezavantajlarını kendi deneyimlerimden yola çıkarak anlatacağım. Kod örneğimizde 6x6’lık bir sayı matrisi oluşturuyoruz ve her bir sayının ekrana yazdırılmasından önce 3 saniye bekletiyoruz. Bu basit gibi görünen bekletme işlemi, aslında **program akışını kontrol etmek, zamanlama yapmak ve thread yönetimi** konusunda bize derin bir anlayış kazandırıyor.

Thread.sleep(), Java'da **mevcut thread'i belirli bir süre duraklatmamızı** sağlar. Eğer bunu kullanmazsak, tüm döngü saniyeler içinde aniden çalışır ve kullanıcıya sonuçların ardı ardına patladığı bir ekran sunar; görsellik ve gerçek zamanlı izleme kaybolur. 🔄 Bu metot, özellikle **animasyonlar, oyun döngüleri, simülasyonlar veya zamanlama kritik uygulamalarda** hayat kurtarır. Avantajı, basit ve etkili bir şekilde thread’i duraklatabilmemizdir. Dezavantajı ise **CPU kullanımı düşük olsa da thread beklerken diğer işler bloklanabilir** ve bu nedenle UI thread üzerinde dikkatli kullanılmalıdır.

Kodumuza gelirsek, önce program başında zamanı kaydediyoruz (`startTime`). Sonra **Random** sınıfı ile rastgele sayılar üretiyoruz. İç içe iki for döngüsü ile her satır ve sütun için 3 saniye bekleyip sayıları ekrana yazdırıyoruz. Eğer bekleme sırasında bir kesinti olursa, `InterruptedException` catch bloğu yakalıyor. Her türlü beklenmedik hata için genel bir `Exception` catch’i ekledim. Ve elbette **finally bloğu** ile program her durumda "Teşekkür ederiz." diyerek nazikçe kapanıyor. En sonunda geçen süreyi hesaplayıp ekrana yazdırıyoruz. Bu, hem programın performansını gözlemlemeye hem de zaman yönetimini anlamaya yardımcı oluyor. 🕰️

Kısaca özetlersek: `Thread.sleep()` bize **kontrollü bekleme**, **zamanlama ve akış yönetimi**, **simülasyon ve test kolaylığı** sağlar. Kullanılmazsa program akışı kontrolden çıkar ve gerçek zamanlı gözlemler kaybolur. Kodun tamamı aşağıda yer almaktadır:

#### Kodun Dansı

```java
package _04_sleep;

import java.util.Random;

public class AppMain {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Random randomNo = new Random();

        try {
            for (int i = 1; i <= 6; i++) {
                System.out.println("\n" + i + ". Satır----------------------");
                for (int j = 0; j < 6; j++) {
                    Thread.sleep(3000);
                    System.out.print(randomNo.nextInt(50) + " Sütun ");
                }
                System.out.println();
            }
        } catch (InterruptedException e) {
            System.out.println("Hata 1: " + e);
        } catch (Exception e) {
            System.out.println("Hata 2: " + e);
        } finally {
            System.out.println("Teşekkür ederiz.");
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("startTime: " + startTime);
        System.out.println("endTime: " + endTime);
        System.out.println("elapsedTime: " + elapsedTime);
    }
}
```