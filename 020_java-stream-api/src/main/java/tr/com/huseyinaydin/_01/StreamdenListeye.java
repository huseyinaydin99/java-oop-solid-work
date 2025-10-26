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

        /*
        Stream’deki elemanları toplayarak yeni bir TreeSet oluşturur 🌳.
        Yani TreeSet::new yeni seti yaratır, TreeSet::add her elemanı ekler,
        TreeSet::addAll ise paralel akışlarda oluşan kısımları birleştirir ⚙️.

        Java Stream’ler paralel (çok çekirdekli) çalıştığında,
        veri birkaç küçük parçaya bölünür ve her parça kendi içinde işlenir ⚙️.
        TreeSet::addAll işte bu ayrı parçaları (küçük setleri)
        sonunda tek bir TreeSet içinde birleştirmeye yarar 🔗.

        Bir akış parallelStream() ile oluşturulursa veya stream() sonrası
        .parallel() çağrılırsa, işlem çok çekirdekli (paralel) çalışır ⚡.
        */
        TreeSet<String> myTreeSet1 = myStream1.collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
        System.out.println(myTreeSet1);

        System.out.println("----collect Collectors.toCollection --------------------------------");
        Stream<String> myStream2 = Stream.of("Hüseyin", "Hüsniye", "Hüsamettin", "Hüsrev", "Husain", "Üssüün", "Hüso", "Hüsnü", "Üsiin", "Üstün", "Ünzile", "Ülfer");

        /*
        Collectors.toCollection(TreeSet::new) ifadesi Stream’deki verileri doğrudan yeni bir TreeSet içine toplar 🌲.
        Yani TreeSet::new koleksiyonun türünü belirler, Collectors ise tüm toplama sürecini otomatik yönetir 🤖.
        */
        TreeSet<String> myTreeSet2 = myStream2.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(myTreeSet2);

        System.out.println("---Streamler tek kullanımlıktır!!!-------------------------------");
        Stream<String> myStream3 = Stream.of("Hüseyin", "Hüsniye", "Hüsamettin", "Hüsrev", "Husain", "Üssüün", "Hüso", "Hüsnü", "Üsiin", "Üstün", "Ünzile", "Ülfer");

        // myStream3.forEach(System.out::println); // Tüketti.

        /*
        Collectors.toCollection(TreeSet::new) ifadesi Stream’deki verileri doğrudan yeni bir TreeSet içine toplar 🌲.
        Yani TreeSet::new koleksiyonun türünü belirler, Collectors ise tüm toplama sürecini otomatik yönetir 🤖.
        */
        TreeSet<String> myTreeSet3 = myStream3.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(myTreeSet3);

        System.out.println("---Streamler akarken uzerinde birden fazla islem yapabiliriz!!!-------------------------------");
        Stream<String> myStream4 = Stream.of("Hüseyin", "Hüseyin", "Hüsniye", "Hüsamettin", "Hüsrev", "Husain", "Üssüün", "Hüso", "Hüsnü", "Üsiin", "Üstün", "Ünzile", "Ülfer");

        myStream4
                .distinct()  // benzersiz tekil olsun aynı sql'deki gibi
                .sorted() //sırala
                .forEach(System.out::println); //yaz

        System.out.println("----------------------------------");
        Stream<String> myStream5 = Stream.of("Hüseyin", "Hüsniye", "Hüsamettin", "Hüsrev", "Husain", "Üssüün", "Hüso", "Hüsnü", "Üsiin", "Üstün", "Ünzile", "Ülfer");
        myStream5
                //.sorted()
                //.distinct()
                .filter(isim -> isim.contains("e"))
                .forEach(System.out::println);

        System.out.println("----------------------------------");
        Stream<String> myStream6 = Stream.of("Hüseyin", "Hüsniye", "Hüsamettin", "Hüsrev", "Husain", "Üssüün", "Hüso", "Hüsnü", "Üsiin", "Üstün", "Ünzile", "Ülfer");
        myStream6
                .sorted()
                //.distinct()
                .filter(isim -> isim.contains("e") && isim.contains("E"))
                .forEach(System.out::println);

        System.out.println("----------------------------------");
        Stream<String> myStream7 = Stream.of("Hüseyin", "Hüsniye", "Hüsamettin", "Hüsrev", "Husain", "Üssüün", "Hüso", "Hüsnü", "Üsiin", "Üstün", "Ünzile", "Ülfer");
        /*myStream7
                .sorted()
                //.distinct()
                .filter(isim -> isim.contains("e") || isim.startsWith("H")   )
                .forEach(System.out::println);
        */

        HashSet<String> myHashSet = myStream7.collect(Collectors.toCollection(HashSet::new));

        //System.out.println(myHashSet);

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
                        .max(Map.Entry.comparingByValue()).get(); //anahtar değer eşlemlerin her bir entry'nin değerlerinin aslında en büyük olanı elde ederiz.

        System.out.println(maxEntry.getKey()); //max değerin anahtarı
        System.out.println(maxEntry.getValue()); //max değerin kendisi
        //ikisi eşlemdir. anahtar değer eşlem.
    }
}