### 🔢 Dizi Üzerinde Ortalama, Min, Max ve Çift/Tek Filtreleme Aracım

Bu küçük Java sınıfını, elimdeki sayısal veriler üzerinde hızlıca **ortalama, minimum, maksimum** hesaplamak ve aynı zamanda **çift–tek sayıları birbirinden ayırmak** için yazdım; böylece hem temel algoritma pratiği yapmış oldum hem de tekrar tekrar aynı kodu yazmak yerine tek bir yerden yönetilebilir, okunabilir ve genişletilebilir bir yardımcı araç elde ettim.

---

#### 📌 Bu Sınıf Nedir, Ne Değildir?

- Bu sınıf, günlük hayatta sık sık ihtiyaç duyduğum basit **sayısal analiz işlemlerini** tek bir yerde topladığım, dizi üzerinde çalışan küçük ama işlevsel bir yardımcı araçtır; yani bir istatistik kütüphanesi kadar kapsamlı değildir ama pratikte sık kullanılan temel ihtiyaçları sade bir şekilde karşılar.
- Bir “framework” ya da “genel amaçlı matematik motoru” olma iddiası yoktur; aksine, gerçek projelerde kullanabileceğim **temiz, anlaşılır ve örnek niteliğinde** bir kod parçası olmasını hedeflediğim, eğitsel değeri yüksek bir sınıftır.

---

#### 🎯 Ana Amacı, Neden Var ve Kullanılmazsa Ne Olur?

- Bu sınıfın temel amacı, bir dizi sayı üzerinde yapılacak **ortalama, min, max ve çift–tek ayrıştırma** işlemlerini tekrar yazmak yerine tek bir noktada toplamak ve bu sayede hem kod tekrarını azaltmak hem de hataya açık kısımları merkezileştirerek daha güvenilir bir yapı kurmaktır.
- Böyle bir yardımcı sınıf yazmazsam, aynı hesabı her seferinde farklı metotlar içinde kopyala–yapıştır yapmam gerekir ve bu da hem okunabilirliği düşürür hem de bir hata düzelttiğimde tüm noktalara tek tek dönüp müdahale etmemi gerektiren, teknik borç üreten bir geliştirme tarzına yol açar.

---

#### 🧮 Ortalama, Min ve Max Hesaplama (Özellikler ve Kullanım Amaçları)

Bu bölümde dizideki sayılara daha çok “istatistiksel bakış” ile yaklaşıyorum.

- `avarage(double... array)` metodu, dizi elemanlarının toplamını uzun uzun gezerek hesaplayıp eleman sayısına böler; böylece dışarıdan ekstra hiçbir bağımlılık olmadan, elimdeki veri grubunun genel eğilimini tek satırla görebildiğim, basit ama etkili bir ortalama hesaplama aracı sunar.
- `findMinFromArray(double... array)` metodu, dizinin ilk elemanını başlangıç kabul edip tüm diziyi dolaşarak her yeni daha küçük değer bulduğunda min değişkenini günceller; böylece ister küçük ister büyük sayılardan oluşsun, elimdeki veri kümesinin alt sınırını, yani en küçük değerini güvenle elde ederim.
- `findMaxFromArray(double... array)` metodu ise aynı mantığı maksimum için uygular; dizinin başından sonuna kadar tüm elemanları tarayıp her daha büyük değer bulduğunda güncelleyerek elimdeki sayıların tepe noktasını, yani en yüksek değerini bulmamı sağlar ve böylece veri kümesi hakkında hızlı bir özet çıkarırım.

---

#### ➗ Çift ve Tek Sayıları Ayırmak: Liste Bazlı Yaklaşım

Bu bölümde işin daha çok **filtreleme** tarafına odaklanıyorum.

- `twoDivideFromArray(double... array)` metodu, kendisine verilen diziyi `for` döngüsüyle gezerek **2 ile kalansız bölünen** (yani çift) tüm sayıları `List<Double>` yapısına ekler; böylece hem diziyle çalışmanın performans avantajını korur hem de çıktı tarafında esnek ve dinamik olarak büyüyebilen bir liste elde ederek koleksiyon API’leriyle uyumlu bir sonuç üretmiş olurum.
- `noneTwoDivideFromArray(double... array)` metodu ise bu kez **2 ile kalansız bölünemeyen**, yani tek olan sayıları aynı mantıkla farklı bir listeye toplar; bu sayede elimdeki veri kümesini iki anlamlı kümeye ayırır, sonrasında ister raporlama amacıyla ister iş kuralları için bu listeleri dilediğim gibi kullanabilirim.

---

#### 🧩 İkisi Bir Arada Nasıl Çalışıyor?

- Ortalama, min ve max hesaplayan metotlarla çift–tek ayrıştıran metotları aynı sınıfta toplamamın sebebi, tek bir veri seti üzerinde hem **istatistiksel özet** çıkarmak hem de **kural bazlı filtreleme** yapmak istediğim senaryolarda, tek hareketle tüm bu bilgileri elde edebileceğim bir akış kurmak; yani önce dizi üzerinden ortalama/min/max alıp hemen ardından aynı dizi üzerinde çift ve tek listeleri çıkararak, veri kümesiyle ilgili hem “büyük resmi” görebildiğim hem de detay seviyesinde çalışabildiğim pratik bir çalışma ortamı oluşturmaktır.

---

#### 🧪 Nerede Kullanılır, Yazılımcıya Ne Katar?

- Bu tarz bir yardımcı sınıfı, özellikle algoritma pratiği yaptığım, teknik mülakatlara hazırlandığım ya da projede ufak yardımcı modüller oluşturarak kod tabanını sadeleştirmek istediğim durumlarda kullanmayı tercih ederim; böylece hem temel Java bilgimi taze tutarım hem de tekrar eden operasyonları soyutlayarak iş mantığına daha fazla odaklanırım.
- Yazılımcı olarak bana, diziler, döngüler, koleksiyonlar, varargs ve hata yönetimi (`IllegalArgumentException`) gibi temel yapıtaşlarını tek bir örnek üzerinde görmek ve uygulamak açısından oldukça fazla şey katar; aynı zamanda **temiz kod**, **tek sorumluluk** ve **yeniden kullanılabilirlik** gibi iyi pratikleri küçük ama somut bir örnekle pekiştirmemi sağlar.

---

#### ✅ Avantajları ve ⚠️ Dezavantajları

##### ✅ Avantajları

- Kod, tek bir sınıf içinde toplandığı için bakım yapmak, genişletmek ve gerektiğinde farklı projelere taşımak son derece kolaydır; bu da beni tekrar eden işlerden kurtarıp daha büyük problemleri çözmeye odaklanmamı sağlar.
- Metotlar statik ve sade olduğu için öğrenme ve kullanma eşiği düşüktür; bu sayede yeni başlayan biri bile kodu okuduğunda neyin neden yapıldığını rahatça takip edebilir.

##### ⚠️ Dezavantajları

- Bu sınıf, sadece belirli türdeki (double dizisi) işlemleri desteklediği için genelleştirilmiş bir çözüm değildir; farklı veri tipleri veya daha karmaşık istatistiksel ihtiyaçlar için ya generic yapıya geçmek ya da daha kapsamlı kütüphaneler kullanmak gerekir.
- Çok büyük veri setlerinde, her seferinde diziyi baştan sona tarayan bu lineer algoritmalar performans açısından her zaman en optimal çözüm olmayabilir; fakat eğitim ve temel uygulamalar için fazlasıyla yeterlidir.

---

#### 📊 Özet Tablo

| Özellik                     | Açıklama                                                                                                                                         | Kullanım Amacı                                                                                                                                                   |
|-----------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Ortalama / Min / Max        | Aynı dizi üzerinde gezerek genel eğilimi, en küçük ve en büyük değeri bulan, basit ama işlevsel istatistiksel özet metotları sunar.             | Veri kümesinin genel karakterini hızlıca anlamak, raporlama veya karar mekanizmalarında temel referans değerler (ortalama, sınırlar) elde etmek için kullanırım. |
| Çift / Tek Listeleme        | Dizi elemanlarını mod 2 kontrolüyle ayırarak ayrı listelerde toplayan, filtreleme odaklı çalışma imkanı veren yardımcı metotlar içerir.         | İş kurallarında çift ve tek sayıları ayrı işleyeceğim senaryolarda, veriyi önceden ayrıştırarak daha okunabilir ve modüler bir akış kurmak için kullanırım.     |

---

#### 💻 Kullandığım Kod

Aşağıda bu mantığı gerçekleştirdiğim sınıfımın tamamını, README’ye doğrudan kopyalanabilir şekilde bırakıyorum:

```java
package tr.com.huseyinaydin;

import java.util.ArrayList;
import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java.
 *
 */

public class _001_Avarage_Max_Min_Find {

    public static void main(String[] args) {
        double[] array = new double[] { 10, 20, 30, 2, 8, 100, 3, 5, 7, 9, 101 };
        System.out.println("Dizi ortalaması: " + avarage(array));
        System.out.println("Dizideki en küçük sayı: " + findMinFromArray(array));
        System.out.println("Dizideki en büyük sayı: " + findMaxFromArray(10, 20, 30, 2, 8, 100));
        System.out.print("Sıfıra tam bölünenler: ");
        twoDivideFromArray(array).forEach(i -> System.out.print(" - " + i));
        System.out.println("");
        System.out.print("Sıfıra tam bölünemeyenler: ");
        noneTwoDivideFromArray(array).forEach(i -> System.out.print(" - " + i));
    }

    private static double avarage(double... array) {
        if (array.length <= 0)
            throw new IllegalArgumentException("Geçersiz argüman...");
        double avarage = 0;
        for (double number : array) {
            avarage += number;
        }
        avarage = avarage / array.length;
        return avarage;
    }

    private static double findMinFromArray(double... array) {
        if (array.length <= 0)
            throw new IllegalArgumentException("Geçersiz argüman...");
        double min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    private static double findMaxFromArray(double... array) {
        if (array.length <= 0)
            throw new IllegalArgumentException("Geçersiz argüman...");
        var min = array[0];
        for (var i = 0; i < array.length; i++) {
            if (array[i] > min) {
                min = array[i];
            }
        }
        return min;
    }

    private static List<Double> twoDivideFromArray(double... array) {
        List<Double> twoDivide = new ArrayList<Double>();
        for (var number : array) {
            if (number % 2 == 0) { // çift
                twoDivide.add(number);
            }
        }
        return twoDivide;
    }

    private static List<Double> noneTwoDivideFromArray(double... array) {
        List<Double> twoDivide = new ArrayList<Double>();
        for (var number : array) {
            if (number % 2 != 0) { // tek
                twoDivide.add(number);
            }
        }
        return twoDivide;
    }
}
```

---

### Sıralama Algoritmaları;

#### Quick Sort;

Quick Sort’u anlamanın en kolay yolu, pivot seçip diziyi pivotun etrafında ikiye ayırmak ve aynı işlemi alt dizilerde tekrarlamaktır.

#### Pivot Nedir? 

Pivot, Quick Sort’ta diziyi **küçük ve büyük elemanlar olarak ikiye ayırmak için referans alınan elemandır.

#### Pivot Neye Göre Belirlenir?

Pivotu biz belirleriz; en yaygın yöntemlerden biri dizinin ilkini, sonuncusunu veya ortadaki elemanını pivot olarak seçmektir.
Teknik olarak herhangi bir elemanı seçebiliririz, ama seçilen pivotun konumu performansı etkiler; iyi pivot bölmeyi dengeler, kötü pivot algoritmayı yavaşlatır.

##### Şu diziyi sıralayalım:

```java
int[] numbers = {7, 2, 1, 6, 8, 5, 3, 4};
```

İlk olarak bir pivot seçelim. En sağdaki elemanı seçelim:

```text
[7, 2, 1, 6, 8, 5, 3, 4]
                      ↑
                    pivot
                      4
```

Amacımız: pivot'tan küçükler | pivot | pivot'tan büyükler şeklinde ikiye ayırmaktır.

##### 1. Partition

4 pivot olduğuna göre 4'ten küçük değerleri sol tarafa, büyükleri sağ tarafa taşırız.

Sonuç örneğin şöyle olabilir:

```text
[2, 1, 3] [4] [7, 6, 8, 5]
           ↑
         pivot
```

Burada önemli nokta şu:

4 artık doğru konumundadır.

Çünkü solunda 4'ten küçük, sağında 4'ten büyük elemanlar vardır.

Quick Sort bundan sonra 4 ile ilgilenmez.

##### 2. Sol tarafı tekrar Quick Sort yap

```text
[2, 1, 3]
```

Pivot yine son eleman olsun:

```text
[2, 1, 3]
       ↑
     pivot
```

Partition sonucunda:

```text
[2, 1] [3]
```

Sonra [2, 1] üzerinde tekrar çalışırız:

```text
[2, 1]
↑
pivot
```

Sonuç:

```text
[1, 2]
```

Dolayısıyla sol taraf:

```text
[1, 2, 3]
```

olur.

#### 3. Sağ tarafı da aynı şekilde yap

Başlangıçta sağ tarafımız:

```text
[7, 6, 8, 5]

Pivot:

[7, 6, 8, 5]
          ↑
        pivot
          5
```

Partition:

```text
[ ] [5] [7, 6, 8]
↑

Sonra [7, 6, 8]:

[7, 6, 8]
↑
pivot
```

Partition:

```text
[7, 6] [8]

[7, 6] de sıralanır:

[6, 7]
```

Sonuç:

```text
[5, 6, 7, 8]
```
Sonuç

İlk partition'dan sonra elimizde:

```text
[2, 1, 3] [4] [7, 6, 8, 5]
```

vardı.

Alt diziler de sıralanınca:

```text
[1, 2, 3] [4] [5, 6, 7, 8]
```

ve nihayet:

```text
[1, 2, 3, 4, 5, 6, 7, 8]
```

olur. Böyle bir mantık işliyor.

```java
public class QuickSort {

    static void main() {
        int[] numbers = {7, 2, 1, 6, 8, 5, 3, 4};

        quickSort(numbers, 0, numbers.length - 1);
    }

    public static void quickSort(int[] numbers, int low, int high) {

        if (low < high) {

            int pivotIndex = partition(numbers, low, high);

            quickSort(numbers, low, pivotIndex - 1);
            quickSort(numbers, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] numbers, int low, int high) {

        int pivot = numbers[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (numbers[j] <= pivot) {
                i++;

                int temp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
            }
        }

        int temp = numbers[i + 1];
        numbers[i + 1] = numbers[high];
        numbers[high] = temp;

        return i + 1;
    }
}
```

#### Quick Sort Modeli;
```text
                 QUICK SORT
                     │
                 pivot seç
                     │
              partition yap
               /          \
        küçükler          büyükler
            │                 │
    tekrar QS başa sar.  tekrar QS başa sar. recursive metot
            │                 │
            └────────┬────────┘
                     │
                   sıralı
```

### Quick Sort'un asıl fikri: 

Her seferinde bir pivotu doğru konumuna yerleştirip problemi daha küçük iki probleme bölmektir. Bu nedenle algoritmanın merkezindeki kavram partition işlemidir; onu anlarsak Quick Sort'un büyük kısmını anlamış oluruz.

---
### Merge Sort;

Merge Sort'un mantığı aslında “böl → sırala → birleştir” şeklindedir. En önemli nokta, diziyi küçük parçalara bölüp bu parçaları sıralı şekilde tekrar birleştirmesidir.

Şu dizimiz olsun:

```text 
[8, 3, 5, 4, 7, 6, 1, 2]
```

Önce ortadan ikiye böleriz:

```text 
[8, 3, 5, 4]    [7, 6, 1, 2]
```

Sonra tekrar böleriz:

```text
[8, 3] [5, 4]    [7, 6] [1, 2]
```

Tek elemana kadar devam eder:

```text
[8] [3] [5] [4] [7] [6] [1] [2]
```

Tek elemanlı diziler zaten sıralıdır.

Şimdi asıl olay: Birleştirme

```text
[8] ve [3] elimizde.
```

İlk elemanları karşılaştırırız:

```text
8 > 3
```

Küçük olanı alırız:

```
[3, 8]
```

Sonra:

```text
[5] + [4] → [4, 5]
[7] + [6] → [6, 7]
[1] + [2] → [1, 2]
```

Artık elimizde:

```
[3, 8] [4, 5]    [6, 7] [1, 2]
```

Bunları da aynı mantıkla birleştiririz.

Örneğin:

```text
[3, 8] + [4, 5]
```

Karşılaştırmalar:

```text
3 < 4  → 3
8 > 4  → 4
8 > 5  → 5
```

Sonuç:

```text
[3, 4, 5, 8]
```

Diğer taraf:

```text
[6, 7] + [1, 2]
→ [1, 2, 6, 7]
```

Son birleştirme:

```text
[3, 4, 5, 8]
+
[1, 2, 6, 7]
↓
[1, 2, 3, 4, 5, 6, 7, 8]
```

Java'da karşılığı

```java
public class MergeSort {

    public static void mergeSort(int[] array) {
        if (array.length < 2) {
            return;
        }

        int mid = array.length / 2;

        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, right.length);

        mergeSort(left);
        mergeSort(right);

        merge(array, left, right);
    }

    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        while (i < left.length) {
            array[k++] = left[i++];
        }

        while (j < right.length) {
            array[k++] = right[j++];
        }
    }
}
```

Kodun zihindeki karşılığı

```text
mergeSort()
        ↓
diziyi ikiye böl
        ↓
sol tarafı tekrar böl
        ↓
sağ tarafı tekrar böl
        ↓
tek elemanlara ulaş
        ↓
merge()
        ↓
küçük elemanı seçerek birleştir
        ↓
sıralı dizi

```

Kilit nokta: mergeSort() diziyi böler, merge() ise parçaları sıralı biçimde birleştirir.

Bu yüzden Merge Sort'u anlamanın en önemli kısmı merge() metodundaki “iki sıralı dizinin başındaki elemanları karşılaştırıp küçüğü sonuca koyma” mantığını kavramaktır.