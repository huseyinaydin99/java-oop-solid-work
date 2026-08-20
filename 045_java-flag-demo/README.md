### Java Flag Nedir?

Java’da flag, programın belirli bir davranışını kontrol etmek için kullandığımız genel bir kontrol değeri/parametresidir; özel bir Java dil özelliği değildir.

### JVM Flag ile Java Flag Farkı

JVM flag ise JVM’in çalışma davranışını yapılandırmak için JVM’e dışarıdan verdiğimiz -X veya -XX gibi seçeneklerdir.

### JVM Flag Örnek;

```text
java -Xmx256m JavaFlag
```

>Buradaki: -Xmx256m JVM flag'idir ve JVM'e kullanabileceği maksimum Heap belleğinin 256 MB olduğunu söyler.

### Temel fark

>Java flag'i → uygulamanın davranışını kontrol eder.
JVM flag'i → JVM'in çalışma şeklini kontrol eder.

Yani debugMode Java programının kendi içindeki basit bir if kararı, -Xmx256m ise JVM'e verdiğimiz çalışma yapılandırmasıdır.

### JVM Flag Kullanımı;

```java
public class Main {

    public static void main(String[] args) {

        long maxMemory = Runtime.getRuntime().maxMemory();

        System.out.println("JVM maksimum Heap: " + maxMemory / (1024 * 1024) + " MB");
    }
}
```

> Kodu normal çalıştırma; java Main

> Kodu JVM flag ile çalıştırma; java -Xmx256m Main

> Buradaki: -Xmx256m JVM'e “maksimum Heap belleğini 256 MB ile sınırla” demektir.

IntelliJ IDEA’da Run → Edit Configurations → çalıştırdığın uygulamayı seç → Modify options → Add VM options kısmından ekle:

-Xmx256m

### Max Min Heap Ayarlama;

```java
public class MinMaxHeap {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        // MB cinsine dönüştürme formülü
        long totalMemory = runtime.totalMemory() / (1024 * 1024); // şu an ki total
        long maxMemory = runtime.maxMemory() / (1024 * 1024); // ulaşılabilecek en fazla heap alanı

        System.out.println("Heap başlangıç: " + totalMemory + " MB");
        System.out.println("Heap maksimum: " + maxMemory + " MB");
    }
}
```

IntelliJ IDEA → VM options Run → Edit Configurations → VM options:

-Xms128m -Xmx512m

#### Örneğin:

Heap başlangıç: 128 MB
Heap maksimum: 512 MB

> -Xms128m → JVM'in başlangıç Heap boyutunu belirler.
-Xmx512m → JVM'in çıkabileceği maksimum Heap boyutunu belirler.

-Xms128m başlangıç Heap boyutunu 128 MB olarak belirler, -Xmx512m ise Heap’in ihtiyaç halinde büyüyebileceği üst sınırı 512 MB olarak belirler.

### Heap Gözlemi;

```java
public class MemoryMonitor {

    public static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime();

        printHeap(runtime);

        byte[] data = new byte[200 * 1024 * 1024]; // 200 MB lık Heap alanı maşaAllah!

        printHeap(runtime);
    }

    private static void printHeap(Runtime runtime) {

        long total = runtime.totalMemory() / (1024 * 1024); // MB formülü var
        long max = runtime.maxMemory() / (1024 * 1024);

        System.out.println("Kullanılabilir Heap: " + total + " MB");
        System.out.println("Maksimum Heap: " + max + " MB");
        System.out.println("--------------------");
    }
}
```

> IntelliJ IDEA → VM Options; -Xms128m -Xmx512m

### Gözlemci;

```text
/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home/bin/java -Xms128m -Xmx512m -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=49805 -Dfile.encoding=UTF-8 -classpath /Users/huseyinaydin99/Desktop/Projects/java-oop-solid-work/045_java-flag-demo/target/classes tr.com.huseyinaydin.minmaxheap.MemoryMonitor
Kullanılabilir Heap: 130 MB
Maksimum Heap: 512 MB
--------------------
Kullanılabilir Heap: 328 MB
Maksimum Heap: 512 MB
--------------------
```

### Heap için max Sınırın Aşılması OutOfMemoryException;

```java
public class HeapOutOfMemory {
    public static void main(String[] args) {

        List<byte[]> memory = new ArrayList<>();

        while (true) {
            memory.add(new byte[10 * 1024 * 1024]); // 10 MB her seferinde - sonsuza kadar - heap taşar max sınır aşılır - outofmemory fırlatılır

            System.out.println("10 MB bellek tahsis edildi.");
        }
    }
}
```

IntelliJ IDEA → VM Options -Xms128m -Xmx256m ver sonra çalıştır

Program her döngüde 10 MB yeni bellek ister:

```text
10 MB bellek tahsis edildi.
10 MB bellek tahsis edildi.
10 MB bellek tahsis edildi.
...
```

Heap 256 MB sınırına yaklaştığında artık yeni nesneler için yeterli alan kalmaz ve sonunda:

java.lang.OutOfMemoryError: Java heap space 

hatası oluşur. Fırlatılan şey: OutOfMemoryError bir Error’dır, bir Exception değildir.
Farkı: Exception genellikle uygulamanın çalışma sırasında karşılaşabileceği ve ele alınabilecek durumları ifade ederken, Error JVM veya çalışma ortamının ciddi bir problem yaşadığını ve çoğunlukla uygulama tarafından kurtarılmasının beklenmediğini ifade eder.

Burada gördüğümüz şey: -Xmx256m JVM'in Heap'ini 256 MB'dan fazla büyütmesine izin vermez; uygulama bundan daha fazla bellek istediğinde JVM OutOfMemoryError ile durumu bildirir.

### -XX Flag’leri

-XX flag'leri, JVM'in gelişmiş ve JVM'e özgü çalışma davranışlarını yapılandırmak için kullanılır.

```java
// sıradan Java kodu;
public class Main {
    public static void main(String[] args) {
        System.out.println("Uygulama çalışıyor.");
    }
}
```

>IntelliJ IDEA → VM Options → -XX:+UseG1GC

Buradaki: -XX: JVM'e özgü gelişmiş bir seçenek kullandığımızı belirtir.

+UseG1GC ise G1 Garbage Collector'ı etkinleştirir.

```text
-XX:+FlagName   → flag'i etkinleştir
-XX:-FlagName   → flag'i devre dışı bırak

Değer alan -XX flag'leri de vardır:
-XX:MaxGCPauseMillis=200
Bu ise JVM'e GC için hedeflenen maksimum duraklama süresini 200 ms olarak ayarla demektir.
```

>Özet: -Xmx256m gibi -X seçenekleri temel JVM ayarlarında kullanılırken, -XX seçenekleri GC, JIT ve JVM'in diğer iç çalışma mekanizmalarını daha ayrıntılı biçimde yapılandırmak için kullanılır.

### -X Flag’leri

-X flag'leri JVM'in temel bellek alanlarını yapılandırmak için kullanılır.

```text
-Xms128m → Heap başlangıç en az alan: 128 MB
-Xmx512m → Heap maksimum en fazla alan: 512 MB
-Xss256k → Her thread için Stack alanı: 256 KB
```

```java
// Stack alanını dolduralım daşıralım
public class Main {
    public static void main(String[] args) {
        recursiveMethod();
    }
    
    static void recursiveMethod() {
        recursiveMethod();
    }
}

// recursiveMethod() kendisini sürekli çağırdığı için her çağrıda Stack'te yeni bir stack frame oluşur, dolayısıyla taşar.
// Bir süre sonra Stack dolacak ve: java.lang.StackOverflowError göreceğiz.
```

> Yani: -Xss256k ile thread Stack'ini küçülttük ve Stack'in sınırına ulaştığımızda JVM'in StackOverflowError verdiğini doğrudan gözlemledik.

### GC Flag’leri

Garbage Collector’ın hangi algoritmayı kullanacağını JVM flag’i ile belirleyebiliriz.

#### G1 GC
-XX:+UseG1GC

> JVM'e G1 Garbage Collector'ı kullan der.

### ZGC
-XX:+UseZGC

> JVM'e ZGC kullan der.

### Shenandoah
-XX:+UseShenandoahGC

> JVM'e Shenandoah kullan der.

### IntelliJ IDEA

Run → Edit Configurations → VM options:
```text
-Xmx512m
-XX:+UseG1GC
```

>Burada -Xmx512m Heap sınırını, -XX:+UseG1GC ise Heap'i temizleyecek Garbage Collector'ı belirler.

>Kısacası: -XX:+UseG1GC, -XX:+UseZGC veya -XX:+UseShenandoahGC gibi flag'ler JVM'e “bellek temizliğini hangi GC algoritmasıyla yapacağını” söyler.

### İlgili GC'ler Nedir?

### G1 GC: 
Heap’i bölgelere ayırarak çöp toplama işini kontrollü biçimde yapar ve özellikle büyük Heap’lerde duraklama sürelerini dengede tutmayı hedefler.

### ZGC:
Garbage Collection işleminin büyük bölümünü uygulama çalışırken eşzamanlı yaparak çok düşük duraklama sürelerini hedefler.

### Shenandoah:
Nesneleri taşıma ve belleği düzenleme işlerini uygulamayla büyük ölçüde eşzamanlı yürütüp uzun GC duraklamalarını azaltmayı hedefler.

### JIT Flag’leri

JIT (Just-In-Time) derleyicisi, sık çalışan Java kodlarını çalışma sırasında optimize ederek makine koduna dönüştürür; -XX flag’leriyle JIT'in davranışını gözlemleyebiliriz.

```text
JIT'i kapatalım

IntelliJ IDEA → Run → Edit Configurations → VM options:

-Xint

-Xint, JVM'i yalnızca yorumlayarak çalıştırmaya zorlar ve JIT derlemesini devre dışı bırakır.

2. JIT'i açalım

Normal çalıştırmada:

java Main

JVM JIT'i kullanabilir.

3. JIT derlemelerini görelim
-XX:+PrintCompilation

ile çalıştırdığımızda JVM'in derlediği metotları konsolda görebiliriz:

100   42       3       Main::calculate (25 bytes)

Buradaki Main::calculate, JIT tarafından derlenen metottur.

Kısacası: -Xint JIT'i kapatıp farkı görmemizi, -XX:+PrintCompilation ise JIT'in hangi metotları derlediğini gözlemlememizi sağlar.

--

-Xint -XX:+PrintCompilation ekledim ancak console'a basmadı!

Evet, çünkü -Xint ile JIT'i tamamen kapattın; dolayısıyla -XX:+PrintCompilation yazdıracak bir JIT derlemesi gerçekleşmiyor.
```

### Nükleer Deneyler;

```text
Deney 1 — Normal JVM

VM Options:

-Xms128m -Xmx512m

Çalıştır ve süreyi kaydet:

Süre: 45 ms

--

Deney 2 — JIT kapalı

VM Options:

-Xms128m -Xmx512m -Xint

Tekrar çalıştır:

Süre: 320 ms

Karşılaştır
Çalışma	JIT	Süre
Normal	✅ Açık	45 ms
-Xint	❌ Kapalı	320 ms

Buradaki deneyin amacı: Aynı kodu değiştirmeden yalnızca JVM flag'ini değiştirerek JIT'in performans üzerindeki gerçek etkisini ölçmek. Ciddi fark var!
```

### Pratik Senaryolar;

Şimdi tek bir senaryoda Heap, GC ve JIT için doğru flag’i seçelim.

Senaryo

Uygulama çalışıyor ama:

Heap yetersiz kalıyor.
GC duraklamaları fazla.
Hesaplama yapan kodlar yavaş çalışıyor.

#### 1. Heap problemi
Heap sınırını artır:

-Xms256m
-Xmx1g

-Xmx1g → JVM'in Heap'i en fazla 1 GB olabilir.

#### 2. GC problemi
G1 kullan:

-XX:+UseG1GC

G1, Heap'i yönetirken GC duraklamalarını daha kontrollü tutmayı hedefler.

#### 3. JIT problemi
JIT derlemelerini gözlemle:

-XX:+PrintCompilation

JIT'i kapatma:

-Xint

çünkü -Xint, JIT optimizasyonlarını devre dışı bırakarak genellikle performansı düşürür.

#### 4. Hepsini birlikte
   -Xms256m
   -Xmx1g
   -XX:+UseG1GC
   -XX:+PrintCompilation

Mantık: Bellek sorunu → -Xms / -Xmx, GC davranışı → -XX:+UseG1GC, JIT'i inceleme → -XX:+PrintCompilation; yani problemi önce belirleyip ona karşılık gelen flag'i seçiyoruz.