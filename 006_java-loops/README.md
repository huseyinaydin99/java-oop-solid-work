##### 🔹Java’da Döngüler: For, While, Do-While ve ForEach 🚀 Başım Döndü (:

Java’da döngüler 🔁, tekrarlayan işlemleri otomatikleştirerek kodu sade, verimli ve dinamik hale getirir ⚙️. for döngüsü özellikle başlangıç değeri, şart ve artış/azalış adımlarının net olarak tanımlandığı durumlarda kullanılır 🎯. Döngü her tekrarında koşulu kontrol eder, doğruysa gövdesindeki kodu çalıştırır ve ardından sayaç değerini artırır veya azaltır 🔄. Örneğin for (int i = 0; i < 5; i++) ifadesinde, i sıfırdan başlar, 5’ten küçük olduğu sürece çalışır ve her seferinde 1 artar ➕. i += 2 veya i = i + 2 gibi ifadelerle artış miktarı değiştirilebilir 🔢. Azaltım yönünde çalışmak içinse i-- veya i -= 1 kullanılır ⬇️. İç içe döngüler (nested loops) 🧩, bir döngü içinde başka bir döngü çalıştırarak çok boyutlu işlemler yapılmasını sağlar — örneğin i satırları, j sütunları temsil edebilir 📊. Sonsuz döngüler ise koşul belirtilmediğinde (for(;;)) oluşur ve manuel olarak durdurulmadıkça sürekli çalışır ⚠️. Kısacası döngüler, programın akışını düzenli ve kontrollü biçimde tekrar ettirerek zaman kazandırır ⏱️, hatayı azaltır 🧠 ve kodun okunabilirliğini artırır 💪.

```
🔹 Klasik for döngüsü: Belirli bir sayıda işlemi düzenli ve kontrollü biçimde tekrarlar 🔁.
🔹 Artış adımı değişen for: Döngü sayacını özel aralıklarla artırarak adım hızını belirler ⚙️.
🔹 Azalan for döngüsü: Geriye doğru sayarak işlemleri ters sırada yürütür ⬇️.
🔹 İç içe for döngüsü: Çok boyutlu veya ilişkili işlemleri katmanlı şekilde yürütür 🧩.
🔹 Sonsuz for döngüsü: Koşul verilmediğinde kesintisiz olarak çalışır ⚠️.
🔹 While döngüsü: Koşul doğru olduğu sürece çalışır, koşul sağlanmazsa hiç başlamaz; akış koşula tamamen bağlıdır ⚙️.
🔹 Do-While döngüsü: En az bir kez çalışır çünkü koşul sona bırakılır; kullanıcı girişi gibi durumlarda idealdir 🔂.
🔹 ForEach döngüsü: Koleksiyon veya dizilerdeki her öğeye tek tek erişir; temiz, okunabilir ve hata olasılığı düşük bir yapı sunar 🧩.
🔹 Sonsuz while döngüsü: Koşulu her zaman true olduğu için durmaksızın çalışır 🔄; genellikle sürekli izleme, servis dinleme veya kullanıcıdan çıkış bekleyen sistemlerde kullanılır ⚠️.
```

##### Note;

Java’da for döngüsünde i++ ile ++i arasında pratikte bir fark yoktur, çünkü artışın sonucu döngünün gövdesinde kullanılmıyorsa ikisi de i değerini 1 artırıp bir sonraki iterasyona geçer.

```java
for (int i = 0; i < 5; i++) {
    System.out.println(i);
}
```

// ++i kullansak da aynı çıktıyı verir:
```java
for (int i = 0; i < 5; ++i) {
    System.out.println(i);
}
```

Anlamı: i++ önce mevcut değeri kullanıp sonra artırır, ++i ise önce artırıp sonra değeri kullanır; ancak for döngüsünün artış bölümünde bu üretilen değer kullanılmadığı için sonuç değişmez. Asıl fark, int x = i++ ve int x = ++i gibi bir ifadenin içinde ortaya çıkar: ilki x'e eski değeri, ikincisi yeni değeri verir.