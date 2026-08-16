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