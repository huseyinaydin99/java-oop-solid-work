#### Java Stream API Giriş 🚀🌊

Java Stream API, koleksiyonlar ve diziler üzerinde **fonksiyonel ve akış tabanlı** işlem yapmamızı sağlayan güçlü bir araçtır 🌟. Temel amacı, veriyi **daha okunaklı, kısa ve etkili** bir şekilde işlemek, filtrelemek, dönüştürmek ve toplamak 🌳. Eğer Stream API kullanılmazsa, klasik döngüler ve koşullu ifadelerle uzun, karmaşık ve hataya açık kodlar yazmak zorunda kalırız 😅. Stream API, büyük veri setlerinde paralel işlem yapabilme olanağı sunar ⚡ ve programcıya **temiz, sürdürülebilir ve yeniden kullanılabilir kod yazma** özgürlüğü verir 💡. Özellikleri arasında **lazy evaluation**, **tek kullanımlık akışlar**, **koleksiyon dönüşümleri**, **filtreleme, sıralama, eşsizleştirme** ve **paralel işlem desteği** vardır 🛠️. Avantajları: okunabilirlik, paralel işlem kolaylığı, fonksiyonel programlama yaklaşımı 🌈. Dezavantajları: yanlış kullanılırsa performans kaybı ve tek kullanımlık akışlarda dikkat edilmesi gereken durumlar ⚠️. Kısaca, Stream API modern Java’da veri işleme paradigmasını değiştiren, **programcıya hem hız hem de estetik** kazandıran vazgeçilmez bir yapı 💻✨.

---

##### Lazy Evaluation ⏳💡
- Lazy evaluation, Stream’deki işlemlerin hemen değil, gerçekten ihtiyaç duyulduğunda yapılması demektir ⚡.
- Yani filter(), map() gibi ara işlemler tembel davranır, sadece terminal işlem (forEach(), collect()) çağrıldığında işler gerçekleşir 🏁.
- Bu sayede performans artar, gereksiz veri işleme önlenir ve bellek kullanımı optimize edilir 📈.

##### Tek Kullanımlık Akışlar 🔄🚫
- Java Stream’ler tek kullanımlıdır, yani bir Stream oluşturulduktan sonra sadece bir terminal işlemle tüketilebilir ⚠️.
- Aynı Stream üzerinde ikinci kez forEach() veya collect() gibi bir terminal işlem yapmak hata verir ❌.
- Yeni bir işlem yapmak için her seferinde yeni bir Stream oluşturmak gerekir 🔁.

##### Koleksiyon Dönüşümleri 🔄📚

- Stream API ile bir koleksiyon üzerindeki veriler, collect() veya toList(), toSet() gibi terminal işlemlerle başka bir koleksiyon türüne dönüştürülebilir 🌳.
- Örneğin, bir List’i Set’e veya TreeSet’e kolayca çevirmek mümkündür ⚡.
- Bu sayede veri işleme sırasında dönüştürme ve filtreleme tek adımda, okunaklı ve fonksiyonel şekilde yapılabilir 🛠️.

##### Paralel İşlem Desteği ⚡🖥️

- Stream API, parallelStream() veya .parallel() ile veriyi otomatik olarak parçalara böler ve çok çekirdekli işlemciyi kullanarak eş zamanlı işler 🌀.
- Her parça kendi içinde bağımsız olarak işlenir ve sonunda collect() gibi işlemlerle tek bir sonuçta birleştirilir 🔗.
- Arka planda bu paralel işlemler Java Thread havuzu (ForkJoinPool) üzerinden yürütülür, yani her parça ayrı bir thread üzerinde çalışır 🧵.
- Bu sayede büyük veri setlerinde performans artışı sağlanır ve işlem süresi ciddi şekilde kısalır ⏱️.

##### Filtreleme 🔍✅

Filtreleme, Stream’deki elemanları belirli bir koşula göre seçmek demektir.
filter() metodu ile sadece koşulu sağlayan elemanlar akışta ilerler.
Bu sayede gereksiz veriler işleme alınmaz, performans artar ⚡.

##### Sıralama 📊🔝

- Sıralama, Stream elemanlarını belirli bir düzene göre organize etmek demektir.
- sorted() metodu doğal sıra veya özel karşılaştırıcı ile kullanılır ⚙️.
- Sonuçta elde edilen akış, düzgün ve öngörülebilir bir sıraya sahip olur 📌.

##### Eşsizleştirme 🌟✂️

- Eşsizleştirme, Stream’deki tekrar eden elemanları tekilleştirmek için kullanılır.
- distinct() metodu ile akışta yalnızca benzersiz elemanlar kalır 🔑.
- Bu, veri temizliği ve tekil sonuç elde etmek için kritik bir işlemdir 🛠️.

##### Örnek Kodlar 📜

```java
package tr.com.huseyinaydin._01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        System.out.println("--Boş Kayıt/Akış--------------------------");
        Stream<String> bosKayit = Stream.empty();
        System.out.println(bosKayit.count());

        System.out.println("--Tek Bir Kayıt/Akış--------------------------");
        Stream<String> tekBirKayit = Stream.of("Hüseyin");
        System.out.println(tekBirKayit.count());

        System.out.println("--Çoklu Kayıt--------------------------");
        Stream<String> cokluKayit = Stream.of("Hüseyin", "Salih", "Cebrail");
        System.out.println(cokluKayit.count());

        System.out.println("----------------------------");

        List<String> myStudentList1 = new LinkedList<>();
        ArrayList<String> myStudentList2 = new ArrayList<>();
        myStudentList1.add("Ahmet");
        myStudentList1.add("Mehmet");
        myStudentList1.add("Necdet");
        System.out.println(myStudentList1);
        System.out.println("----------------------------");

        List<String> myStudentList3 = List.of("Veli", "Ali", "Selami");
        System.out.println(myStudentList3);

        for (String item : myStudentList3) {
            System.out.println(item);
        }

        System.out.println(myStudentList3);

        System.out.println("----LIST------------------------");

        List<String> myStudentList4 = new ArrayList<>(List.of("Hüseyin", "Ahmet", "Yasin", "Halit", "Cemil", "Vehmi", "Fehmi", "İsmail"));
        myStudentList4.add("Şakir");
        myStudentList4.add("Behlül");
        myStudentList4.remove("Mustafa");
        myStudentList4.remove(0);
        myStudentList4.add(1, "Nezir");

        System.out.println("----Stream------------------------");

        // Streamler tek kullanımlıktır!
        Stream<String> myStudentsStream = myStudentList4.stream();
        myStudentsStream.forEach(ogrenci -> System.out.println(ogrenci));

        System.out.println("----ARRAY------------------");

        var myStudentsArr = new String[]{"Hüseyin", "Cemil", "Ozan", "Hamit", "Bayram", "Yunus", "Mahir", "Necip"};
        for (String student : myStudentsArr) {
            System.out.println(student);
        }
    }
}
```

```java
package tr.com.huseyinaydin._01;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamdenListeye {

    public static void main(String[] args) {
        System.out.println("----collect------------------------------");
        Stream<String> myStream1 = Stream.of("Hüseyin", "Hüsniye", "Hüsamettin", "Hüsrev", "Husain", "Üssüün", "Hüso", "Hüsnü", "Üsiin", "Üstün", "Ünzile", "Ülfer");

        TreeSet<String> myTreeSet1 = myStream1.collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
        System.out.println(myTreeSet1);

        System.out.println("----collect Collectors.toCollection --------------------------------");
        Stream<String> myStream2 = Stream.of("Hüseyin", "Hüsniye", "Hüsamettin", "Hüsrev", "Husain", "Üssüün", "Hüso", "Hüsnü", "Üsiin", "Üstün", "Ünzile", "Ülfer");

        TreeSet<String> myTreeSet2 = myStream2.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(myTreeSet2);

        System.out.println("---Streamler tek kullanımlıktır!!!-------------------------------");
        Stream<String> myStream3 = Stream.of("Hüseyin", "Hüsniye", "Hüsamettin", "Hüsrev", "Husain", "Üssüün", "Hüso", "Hüsnü", "Üsiin", "Üstün", "Ünzile", "Ülfer");

        TreeSet<String> myTreeSet3 = myStream3.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(myTreeSet3);

        System.out.println("---Streamler akarken üzerinde birden fazla işlem yapabiliriz!!!-------------------------------");
        Stream<String> myStream4 = Stream.of("Hüseyin", "Hüseyin", "Hüsniye", "Hüsamettin", "Hüsrev", "Husain", "Üssüün", "Hüso", "Hüsnü", "Üsiin", "Üstün", "Ünzile", "Ülfer");

        myStream4
                .distinct()  // benzersiz tekil olsun
                .sorted()   // sırala
                .forEach(System.out::println); // yazdır

        System.out.println("----------------------------------");
        Stream<String> myStream5 = Stream.of("Hüseyin", "Hüsniye", "Hüsamettin", "Hüsrev", "Husain", "Üssüün", "Hüso", "Hüsnü", "Üsiin", "Üstün", "Ünzile", "Ülfer");
        myStream5
                .filter(isim -> isim.contains("e"))
                .forEach(System.out::println);

        System.out.println("----------------------------------");
        Stream<String> myStream6 = Stream.of("Hüseyin", "Hüsniye", "Hüsamettin", "Hüsrev", "Husain", "Üssüün", "Hüso", "Hüsnü", "Üsiin", "Üstün", "Ünzile", "Ülfer");
        myStream6
                .sorted()
                .filter(isim -> isim.contains("e") && isim.contains("E"))
                .forEach(System.out::println);

        System.out.println("----------------------------------");
        Stream<String> myStream7 = Stream.of("Hüseyin", "Hüsniye", "Hüsamettin", "Hüsrev", "Husain", "Üssüün", "Hüso", "Hüsnü", "Üsiin", "Üstün", "Ünzile", "Ülfer");

        HashSet<String> myHashSet = myStream7.collect(Collectors.toCollection(HashSet::new));
        for (String student : myHashSet) {
            System.out.println(student);
        }

        System.out.println("----MAP------------------------------");

        HashMap<String, Integer> myHashMap = new HashMap<>();
        myHashMap.put("Hüseyin", 100);
        myHashMap.put("Cumali", 50);
        myHashMap.put("Can", 180);

        Map.Entry<String, Integer> maxEntry =
                myHashMap.entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue()).get();

        System.out.println(maxEntry.getKey()); // max değerin anahtarı
        System.out.println(maxEntry.getValue()); // max değerin kendisi
    }
}
```