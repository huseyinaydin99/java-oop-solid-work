# Java I/O vs. NIO.2: Mimari ve Derinlemesine Karşılaştırma

Java dünyasında dosya ve veri giriş/çıkış işlemlerinin evrimi üç ana döneme ayrılır: **Klasik I/O** (`java.io` - Java 1.0), **NIO** (`java.nio` - Java 1.4) ve **NIO.2** (`java.nio.file` - Java 7). 

Çoğu kaynak NIO ile NIO.2'yi birbirine karıştırır. Önce bu sınırı netleştirelim: **NIO**, veriyi akış (stream) yerine bloklar (buffer ve channel) halinde işleyen düşük seviyeli veri taşıma altyapısıdır. **NIO.2** ise doğrudan dosya sistemi mimarisini, işletim sistemi (OS) çekirdeğiyle kurulan iletişimi ve asenkron dosya işlemlerini baştan yazan modern katmandır.

---

## Aşama 1: Temel Felsefe ve Mimari Fark

Klasik `java.io` paketi tasarlanırken odak noktası sadelikti; ancak bu sadelik modern sistemlerde ciddi darboğazlar yarattı. NIO.2 bu darboğazları işletim sistemi seviyesinde çözer.

### 1. Soyutlama ve Sorumlulukların Ayrılması (Separation of Concerns)
* **Klasik I/O (`java.io.File`):** Mimari açıdan bir "God Object" (her işi yapmaya çalışan sınıf) anti-pattern örneğidir. `File` sınıfı hem dosyanın adresini (yolunu) temsil eder, hem meta verilerini okur, hem de dosyayı silme/oluşturma işlemlerini üstlenir. Üstelik adı `File` olmasına rağmen bir klasörü veya henüz var olmayan hayali bir yolu da temsil edebilir.
* **NIO.2 (`Path`, `Files`, `FileSystem`):** Sorumlulukları keskin biçimde ayırır:
  * **`Path`:** Sadece bir adres tarifidir (Interface). Dosyanın diskte var olup olmamasıyla ilgilenmez; yalnızca yol manipülasyonu yapar.
  * **`Files`:** Tüm dosya operasyonlarını (kopyalama, silme, okuma, izinler) barındıran statik yardımcı motordur.
  * **`FileSystem`:** Dosya sisteminin kendisini soyutlar. Sadece yerel diski değil, bir `.zip` veya `.jar` dosyasını bile sanki ayrı bir sabit diskmiş gibi bağlayıp (`FileSystems.newFileSystem`) içindeki dosyalarla `Path` üzerinden çalışmanı sağlar.

### 2. "Karadelik" Hatalar vs. Şeffaf İstisnalar
* **Klasik I/O:** `file.delete()` metodunu çağırdığında sana sadece `boolean` (`true` veya `false`) döner. Metot `false` döndüğünde dosyanın neden silinemediğini bilemezsin. Dosya gerçekten yok mu? Başka bir işlem (process) dosyayı kilitledi mi? Yoksa işletim sistemi yetkisi (permission) mi yetersiz?
* **NIO.2:** `Files.delete(path)` metodu `void` döner; başarısız olursa işletim sisteminden aldığı hata kodunu doğrudan anlamlı bir istisnaya dönüştürür: `NoSuchFileException`, `DirectoryNotEmptyException` veya `AccessDeniedException`.

---

## Aşama 2: İşletim Sistemi (OS Kernel) Seviyesinde Ne Değişti?

Java I/O ile NIO.2 arasındaki asıl uçurum kodun yazım şekli değil, kodun alt katmanda işletim sistemi çekirdeği (Kernel Space) ve donanımla konuşma biçimidir.

```text
KLASİK I/O (Stream Odaklı - Çoklu Kopyalama)
[Disk] ──> [OS Kernel Buffer] ──(Kopyalama)──> [JVM Heap (byte[])] ──> Uygulama

NIO / NIO.2 (Channel & Direct Buffer / Zero-Copy)
[Disk] ──(DMA)──> [OS Kernel / Direct Memory] <──(Doğrudan Erişim)──> Uygulama
```

### 1. Veri Taşıma: Stream (Akış) vs. Channel (Kanal) & Zero-Copy
* **Klasik I/O:** Veriyi bir su borusundan akar gibi **bayt bayt** taşır (`InputStream` / `OutputStream`). Bir dosyayı okurken işletim sistemi veriyi önce diskten kendi bellek alanına (Kernel Space) alır, oradan da JVM'in bellek alanına (User Space / Heap) kopyalar. Büyük dosyalarda bu çift kopyalama hem CPU'yu yorar hem de Garbage Collector (GC) üzerinde baskı oluşturur.
* **NIO.2:** Arka planda NIO kanallarını (`FileChannel`) ve doğrudan bellek tamponlarını (`DirectByteBuffer`) kullanır. Örneğin `Files.copy()` çağırdığında, veriyi JVM Heap'ine hiç çekmeden doğrudan işletim sisteminin kendi yerel (native) dosya kopyalama mekanizmasını (DMA / Zero-Copy) tetikler.

### 2. Dizin Tarama ve Dosya İşaretçileri (File Descriptors)
* **Klasik I/O (`file.listFiles()`):** İçinde 200.000 log dosyası olan bir klasörde `listFiles()` çağırdığın an, Java tüm dosyaları tek seferde tarar ve bellekte 200.000 elemanlı bir `File[]` dizisi oluşturmaya çalışır. Sonuç: Uzun süren donmalar ve yüksek `OutOfMemoryError` riski.
* **NIO.2 (`Files.list()` / `Files.walk()`):** İşletim sisteminden bir dizin akışı (`DirectoryStream`) açar ve veriyi **Lazy (Tembel)** olarak okur. Bellekte dizi tutmaz; sen `Stream` üzerinde ilerledikçe işletim sisteminden sıradaki dosya kaydını ister. İsterse 10 milyon dosya olsun, bellek tüketimi sabit kalır.

### 3. Sembolik Linkler (Symlinks) ve POSIX Meta Verileri
* **Klasik I/O:** Sembolik linkleri gerçek dosyalardan ayırt etmekte zorlanır (sonsuz döngüye girme riski taşır). Dosya izinlerini sadece okuma/yazma/çalıştırma olarak çok kaba düzeyde görür.
* **NIO.2:** Sembolik linkleri doğal olarak tanır (`LinkOption.NOFOLLOW_LINKS`). Linux/macOS'taki POSIX izinlerini (`rwxr-xr--`), dosya sahibini (`UserPrincipal`) ve işletim sistemine özgü öznitelikleri tek bir sistem çağrısıyla (`Files.readAttributes`) topluca okuyabilir.

---

## Aşama 3: Kod Üzerinde Somut Karşılaştırmalar

Aşağıdaki dört senaryo, iki yaklaşım arasındaki mimari farkı doğrudan kod üzerinde gösterir.

### Senaryo 1: Güvenli Dosya Taşıma ve Atomik İşlemler
Klasik I/O'da `renameTo()` metodu, kaynak ve hedef farklı disk bölümlerindeyse (partition) sessizce başarısız olur. NIO.2 ise bunu işletim sistemi garantisiyle yönetir.

```java
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FileMoveComparison {

    // KLASİK I/O: Güvensiz ve sessiz hata riski
    public void moveLegacy() {
        File source = new File("/var/data/rapor.tmp");
        File target = new File("/var/archive/rapor.pdf");

        // Farklı disk mount noktaları arasında çalışmayabilir ve nedenini söylemez
        boolean moved = source.renameTo(target);
        if (!moved) {
            System.err.println("Taşınamadı! Ama neden? Bilinmiyor.");
        }
    }

    // NIO.2: Atomik, şeffaf ve OS garantili
    public void moveModern() throws IOException {
        Path source = Path.of("/var/data/rapor.tmp");
        Path target = Path.of("/var/archive/rapor.pdf");

        // ATOMIC_MOVE: Başka bir process hedef dizini okuyorsa, yarım yazılmış
        // dosya görmesini engeller. İşlem ya tam gerçekleşir ya hiç gerçekleşmez.
        Files.move(source, target, 
                StandardCopyOption.REPLACE_EXISTING, 
                StandardCopyOption.ATOMIC_MOVE);
    }
}
```

### Senaryo 2: Büyük Dizinleri Belleği Şişirmeden Tarama
Alt klasörlerle birlikte yüz binlerce dosya barındıran bir dizinde sadece `.log` uzantılı ve 10 MB'tan büyük dosyaları bulmak:

```java
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class DirectoryTraversalExample {

    public void findLargeLogs(Path rootDir) throws IOException {
        long tenMegabytes = 10L * 1024 * 1024;

        // DİKKAT: Files.walk() işletim sisteminde bir File Descriptor (dosya tanıtıcısı)
        // açar. Sızıntı (leak) olmaması için mutlaka try-with-resources içinde kullanılmalıdır.
        try (Stream<Path> pathStream = Files.walk(rootDir)) {
            pathStream
                .filter(Files::isRegularFile) // Klasörleri ve sembolik linkleri ele
                .filter(p -> p.getFileName().toString().endsWith(".log"))
                .filter(p -> {
                    try {
                        return Files.size(p) > tenMegabytes;
                    } catch (IOException e) {
                        return false;
                    }
                })
                .forEach(p -> System.out.println("Büyük log bulundu: " + p));
        }
    }
}
```

### Senaryo 3: Dosya Sistemi Olaylarını Dinleme (`WatchService`)
Klasik I/O'da bir klasöre yeni dosya eklendiğini anlamak için `while(true)` döngüsü kurup `Thread.sleep()` ile klasörü sürekli taramak (Polling) gerekirdi. NIO.2'deki `WatchService`, doğrudan işletim sistemi çekirdeğinin dosya bildirim mekanizmasına (Linux'ta `inotify`, macOS'ta `kqueue`, Windows'ta `ReadDirectoryChangesW`) abone olur. CPU tüketimi sıfıra yakındır.

```java
import java.io.IOException;
import java.nio.file.*;

public class DirectoryWatcherExample {

    public void watchDirectory(Path dir) throws IOException, InterruptedException {
        try (WatchService watcher = FileSystems.getDefault().newWatchService()) {
            
            // İşletim sistemi çekirdeğine bu klasördeki olayları bana bildir diyoruz
            dir.register(watcher, 
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);

            System.out.println("Klasör OS seviyesinde dinleniyor: " + dir);

            while (true) {
                // take() metodu klasörde bir değişiklik olana kadar Thread'i uyutur (0 CPU kullanımı)
                WatchKey key = watcher.take();

                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path fileName = (Path) event.context();
                    System.out.printf("Olay: %s -> Dosya: %s%n", kind.name(), fileName);
                }

                // Anahtarı sıfırlamazsak bir sonraki olayları alamayız
                boolean valid = key.reset();
                if (!valid) {
                    break; // Klasör silindiyse veya erişilemez olduysa döngüden çık
                }
            }
        }
    }
}
```

### Senaryo 4: Gerçek Asenkron I/O (`AsynchronousFileChannel`)
Klasik I/O'da bir dosya okunurken mevcut Thread, diskten veri gelene kadar kilitlenir (Blocking). NIO.2 ile gelen `AsynchronousFileChannel` sayesinde okuma emrini işletim sistemine verirsin; Thread hiç beklemeden yoluna devam eder. Okuma bittiğinde işletim sistemi senin tanımladığın `CompletionHandler` metodunu tetikler.

```java
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class AsyncFileReadExample {

    public void readFileAsync(Path filePath) throws IOException {
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(filePath, StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocateDirect(4096); // OS ile doğrudan konuşan Direct Buffer

        // read(hedefBuffer, başlangıçPozisyonu, ekVeri, geriÇağrımMetodu)
        channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            
            @Override
            public void completed(Integer bytesRead, ByteBuffer attachment) {
                System.out.println("Okunan bayt sayısı: " + bytesRead);
                
                attachment.flip(); // Buffer'ı yazma modundan okuma moduna çevir
                byte[] data = new byte[attachment.limit()];
                attachment.get(data);
                System.out.println("İçerik:\n" + new String(data, StandardCharsets.UTF_8));
                
                closeChannel();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.err.println("Asenkron okuma hatası: " + exc.getMessage());
                closeChannel();
            }

            private void closeChannel() {
                try {
                    channel.close();
                } catch (IOException ignored) {}
            }
        });

        System.out.println("Bu satır dosya okuması bitmeden ANINDA çalışır, Thread bloklanmaz!");
    }
}
```

---

## Aşama 4: Mimari Özet ve Seçim Kriterleri

| Özellik | Klasik I/O (`java.io`) | NIO.2 (`java.nio.file`) |
| :--- | :--- | :--- |
| **Merkezdeki Yapı** | `File` (Tek sınıf, çok sorumluluk) | `Path`, `Files`, `FileSystem` (Ayrık mimari) |
| **Veri İşleme Modeli** | Stream (Bayt / Karakter akışı) | Buffer & Channel (Blok ve Kanal tabanlı) |
| **Çalışma Biçimi** | Senkron ve Bloklayıcı (Blocking) | Senkron, Non-Blocking ve Tam Asenkron (`AsynchronousFileChannel`) |
| **Hata Yönetimi** | Zayıf (`boolean` dönüşler, gizli hatalar) | Güçlü ve spesifik (`IOException` alt sınıfları) |
| **Dizin Tarama** | Eager (`File[]` dizisi - Yüksek bellek riski) | Lazy (`Stream` / `DirectoryStream` - Sabit bellek) |
| **OS Entegrasyonu** | Minimum (Ortak payda yaklaşımı) | Derin (Zero-Copy, POSIX, Symlink, `WatchService`) |

### Günümüzde Hangi Durumda Hangisi Kullanılmalı?

1. **`java.io.File` sınıfını tamamen unut:** Yeni yazılan hiçbir kodda `new File(...)` kullanılmamalıdır. Dosya yolu temsili, kopyalama, taşıma, silme, boyut kontrolü ve dizin tarama işlemlerinin tamamında standart olarak **`Path`** ve **`Files`** (NIO.2) kullanılmalıdır. Eski bir kütüphane zorunlu olarak `File` bekliyorsa `path.toFile()` ile anlık dönüşüm yapılabilir.
2. **`InputStream` / `OutputStream` ve `Reader` / `Writer` hala yaşıyor:** Klasik I/O paketindeki `File` sınıfı eskimiş olsa da, akış sınıfları (`InputStream`, `BufferedReader`) ağ soketlerinden gelen veriyi veya JSON/XML parser'larına (Jackson vb.) veri beslemeyi hala en temiz şekilde yapar. Ancak bu akışları bile `new FileInputStream(file)` ile değil, NIO.2 köprüsü olan **`Files.newInputStream(path)`** veya **`Files.newBufferedReader(path)`** üzerinden açmak en doğru yaklaşımdır.