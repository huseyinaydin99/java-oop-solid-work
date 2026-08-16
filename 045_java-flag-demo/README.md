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