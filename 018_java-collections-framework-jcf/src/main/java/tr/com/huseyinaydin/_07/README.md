#### 🗺️ Java'da Map, HashMap, HashTable, LinkedHashMap, TreeMap Veri Ambarları

Java’da **Map** arayüzü, anahtar (key) ve değer (value) çiftlerini tutan, ilişkisel veri yapısını temsil eder. Her bir **key** benzersizdir ve bir **value**’ya karşılık gelir. `Map` arayüzü, verileri **anahtar üzerinden erişmek**, **arama hızını artırmak** ve **ilişkisel depolama** yapmak için kullanılır. Bu arayüzü uygulayan sınıflar: `HashMap`, `LinkedHashMap`, `TreeMap`, ve `Hashtable`’dır. Her biri farklı senaryolar için optimize edilmiştir.

---

##### ⚙️ Map Nedir, Ne Değildir?

Bir **Map**, klasik koleksiyonlardan farklı olarak yalnızca değerleri değil, **anahtar-değer ilişkilerini** saklar. Anahtarlar tekrarlanamaz; her anahtar yalnızca bir değere karşılık gelir. Eğer aynı anahtar tekrar eklenirse, önceki değer **üzerine yazılır**. Map yapıları **veri erişimini hızlandırır**, çünkü her değeri bulmak için listeyi baştan sona taramaya gerek yoktur. Anahtarın `hashCode()` değeri doğrudan erişim sağlar.

---

##### 💥 HashMap — Hızın ve Rastgeleliğin Gücü

`HashMap`, Java’daki en popüler Map implementasyonudur. Verileri **hash tablosu** mantığıyla depolar. Bu sayede ekleme, silme ve arama işlemleri **ortalama O(1)** zamanda gerçekleşir.  
Ancak en önemli özelliği: **Sıra korumaz!** Yani elemanlar eklenme sırasına göre değil, `hashCode()` değerlerine göre **rastgele** dizilir. `null` anahtar ve `null` değer kabul eder.  
Çok iş parçacıklı (multi-thread) ortamlarda **senkronize değildir**, bu nedenle dışarıdan `Collections.synchronizedMap()` ile korunması gerekir.

🧩 **Kullanım Alanı:** Performansın önemli olduğu, sıralamanın önemsiz olduğu durumlar.  
🧠 **Avantaj:** Hızlı erişim.  
⚠️ **Dezavantaj:** Sıra garantisi yok.

---

##### 🧵 LinkedHashMap — Hafızalı HashMap

`LinkedHashMap`, `HashMap`’in tüm özelliklerini taşır ama bir farkla: **eklenme sırasını hatırlar!**  
Arka planda, her elemanı birbirine bağlayan (linked list) bir yapı kullanır. Bu sayede elemanlar, ekleme sırasına göre veya istenirse erişim sırasına göre tutulabilir. `null` anahtar ve değer kabul eder.

🔗 **Kullanım Alanı:** Sıralı veri saklamak ve yine de `O(1)` erişim hızını korumak isteyenler için idealdir.  
🧠 **Avantaj:** Sıra korur.  
⚠️ **Dezavantaj:** Ek bellek tüketimi vardır.

>HashMap anahtarları hashCode() üzerinden doğrudan bucket’a yerleştirdiği için karşılaştırmaya gerek duymadan bir adet null key kabul edebilir. LinkedHashMap, HashMap’i miras aldığı için aynı hash mekanizmasını kullanır ve HashMap bir adet null key’i özel olarak yönettiğinden, LinkedHashMap de doğal olarak bir adet null anahtar kabul eder.

---

##### 🌳 TreeMap — Doğal Sıralamanın Efendisi

`TreeMap`, verileri **doğal sıralama** (alfabetik veya sayısal) düzenine göre sıralar.  
Arka planda **Red-Black Tree** veri yapısını kullanır. Bu yüzden ekleme, silme ve arama işlemleri **O(log n)** zaman alır. `null` anahtar kabul etmez ama `null` değer mümkündür.  
İsterseniz kendi sıralama kuralınızı `Comparator` ile belirleyebilirsiniz.

🌲 **Kullanım Alanı:** Sıralı veri gerektiren durumlar (ör. sözlük uygulamaları, sıralı raporlar).  
🧠 **Avantaj:** Otomatik sıralama.  
⚠️ **Dezavantaj:** HashMap’e göre daha yavaştır çünkü her işlemde(ekle, sil, güncelle) tekrar sıralar, iş yükü fazladır, `null` key yasaktır çünkü null ile kıyaslama yapılamaz sıralamayı key kısmından karşılaştırarak yapıyor.

>TreeMap, anahtarları doğal sıralama veya Comparator ile karşılaştırma yaparak düzenlediği için, null key ile karşılaştırma yapılamayacağından null key kabul etmez.

---

##### 🧱 Hashtable — Eski Nesil Ama Güvenli

`Hashtable`, `HashMap`’in atasıdır. Java 1.0’dan beri vardır. Tüm metotları **synchronized** (yani thread-safe) çalışır, bu da onu **çoklu iş parçacıklı** ortamlarda güvenli kılar.  
Ancak bu güvenlik, performans maliyetiyle gelir. Ayrıca `null` anahtar veya değer kabul etmez.

🛡️ **Kullanım Alanı:** Eski sistemlerde veya çoklu thread erişimi gerekiyorsa tercih edilir.  
🧠 **Avantaj:** Thread-safe’tir.  
⚠️ **Dezavantaj:** Yavaş ve esnek değildir.

>HashMap 🗃️, bir null key 🔑 veya null value 📦 durumunu özel bir durum olarak ele alır ve bunları ayrı bir bucket 🪣 veya kontrol mekanizmasıyla yönetir; bu sayede null referanslarla işlem yaparken ❌ hata oluşmaz.
Hashtable 🏛️ ise tüm metodları synchronized 🔒 olarak çalıştırır ve hash tablosuna erişimde doğrudan karşılaştırmalar yapar ⚖️; bu yüzden null key veya value ile işlem yapmak hem uyumsuz olur ⚠️ hem de hata riskini artırır.
Ayrıca, Hashtable eski Java sürümlerinde 🕰️ tasarlandığı için null güvenliği için özel bir yöntem içermez.
Sonuç olarak, HashMap modern tasarımı sayesinde null key ve value’yu güvenle destekler ✅, Hashtable ise bunu kabul etmez ❌.

---

##### 🧮 hashCode() — Hash Tabanlı Yapıların Kalbi ❤️

`hashCode()` metodu, bir nesnenin hafızadaki **benzersiz sayısal temsilini** döndürür.  
`HashMap` ve `Hashtable`, bu sayısal değeri kullanarak veriyi **hangi bölmeye (bucket)** koyacaklarını belirler.  
Eğer `hashCode()` düzgün tanımlanmazsa, aynı anahtar gibi görünen objeler farklı bölmelere düşer ve `equals()` kontrolleri gereksiz yere artar.  
Bu da **performans düşüşü** ve **yanlış veri erişimi** anlamına gelir.

##### #️⃣ hashCode() override edilmezse:
Her nesne, varsayılan olarak Object sınıfının hashCode() metodunu kullanır; bu metod nesnenin hafızadaki adresine dayalı benzersiz bir sayı döndürür. Sonuç olarak, içeriği aynı olan iki nesne bile farklı hashCode değerlerine sahip olur. Hash tabanlı yapılar (HashMap, HashSet) bu nesneleri farklı anahtarlar gibi algılar, bu da gereksiz bucket kullanımı, performans düşüşü ve veri tekrarı anlamına gelir.

##### 2️⃣ equals() override edilmezse:
Varsayılan equals() metodu da Object sınıfından gelir ve iki nesneyi yalnızca referanslarının aynı olup olmadığına göre karşılaştırır. İçerikleri aynı olsa bile farklı referanslı nesneler eşit kabul edilmez. Bu durumda, HashMap veya HashSet gibi yapılar aynı veriyi tekrar saklayabilir ve beklenen mantıksal eşitlik sağlanamaz.

>Kısaca: hashCode() → hangi bucket’a gideceğini belirler, equals() → aynı bucket içindeki eşitliği kontrol eder. İkisi birlikte doğru tanımlanmazsa hash tabanlı veri yapıları yanlış veya verimsiz çalışır.

```java
import java.util.HashMap;
import java.util.Objects;

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Eğer hashCode ve equals tanımlanmazsa
    // her yeni nesne farklı kabul edilir
    // ve aynı isim/yaş olsa bile farklı bucket’a gider
    /*
            Çünkü Java’da Object sınıfının varsayılan hashCode() ve equals() metotları, 
            her nesneyi hafızadaki adresine göre benzersiz kabul eder, bu yüzden 
            aynı içerikte olsalar bile yeni nesneler farklı sayılır.
     */
}

public class Main {
    public static void main(String[] args) {
        HashMap<Person, String> map = new HashMap<>();

        Person p1 = new Person("Ahmet", 25);
        Person p2 = new Person("Ahmet", 25); //dikkat ikisininde değeri aynı!!! ama referanslar farklı ancak ikisi değer olarak farklı zannedildiği için(equals ve hashcode tanımlı/override değil) veri tekrarı olur.
        /*
                p1 ve p2 aynı değerlerde olsalar da farklı referanslara sahip oldukları için,
                hashCode() ve equals() tanımlı değilse HashMap onları farklı anahtarlar gibi
                algılar ve veri tekrarı oluşur.
                
         */
        map.put(p1, "Developer");
        map.put(p2, "Manager"); // p1 ile aynı gibi görünse de hashCode ve equals tanımlı değilse farklı bucket’a gider

        System.out.println(map.size()); // 2 yazar ❌ veri tekrarı kötü bir durum!
        System.out.println(map.get(p1)); // "Developer"
        System.out.println(map.get(p2)); // "Manager"
    }
}
```

---

##### 🧾 Örnek Kod

```java
System.out.println("---HashMap rastgele yazar------");
Map<String, String> mapList1 = new HashMap<String, String>();

//Key Value Pairs
mapList1.put("Ankara", "06");
mapList1.put("İzmir", "35");
mapList1.put("Balıkesir", "10");
mapList1.put("Ağrı", null);
mapList1.put("Trabzon", "61");
mapList1.put("Niğde", "51");
mapList1.put(null, null);
mapList1.put(null, "01");

System.out.println(mapList1);

System.out.println("---LinkedHashMap bizim yazdığımız sırayla girişleri yapar------");

Map<String, String> mapList2 = new LinkedHashMap<>();
mapList2.put("Ankara", "06");
mapList2.put("İzmir", "35");
mapList2.put("Balıkesir", "10");
mapList2.put("Ağrı", null);
mapList2.put("Trabzon", "61");
mapList2.put("Niğde", "51");
mapList2.put(null, "01");

System.out.println(mapList2);

System.out.println("---TreeMap------");

TreeMap<String, String> mapList3 = new TreeMap<>();
mapList3.put("Ankara", "06");
mapList3.put("İzmir", "35");
mapList3.put("Balıkesir", "10");
mapList3.put("Ağrı", null);
mapList3.put("Trabzon", "61");
mapList3.put("Niğde", "51");
mapList3.put("Adana", "01"); // null key olamaz

System.out.println(mapList3);

System.out.println("---------");

for (String key : mapList3.keySet()) {
   System.out.println(key + " : " + mapList3.get(key));
}

System.out.println("---Hashtable Key ve Value null olamaz!!------");

Map<String, String> mapList4 = new Hashtable<>();
mapList4.put("Ankara", "06");
mapList4.put("İzmir", "35");
mapList4.put("Balıkesir", "10");
mapList4.put("Trabzon", "61");
mapList4.put("Niğde", "51");
mapList4.put("Adana", "01");

System.out.println(mapList4);
```