// 📦 Bu satırda test sınıfımı, üretim koduyla aynı package altında tanımlayarak metotlara doğrudan ve ekstra yansıma kullanmadan erişebilmeyi hedefliyorum.
package tr.com.huseyinaydin;

// 🧪 JUnit 5'in @Test anotasyonunu projeye dahil ederek belirlediğim metotları otomatik olarak test olarak koşturabilmeyi sağlıyorum.
import org.junit.jupiter.api.Test;

// ✅ Assertion metotlarını statik olarak içe aktararak testlerde kısa, net ve okunabilir doğrulama ifadeleri yazmayı tercih ediyorum.
import static org.junit.jupiter.api.Assertions.*;

// 🧱 Bu sınıfı, rastgele benzersiz sayı üreten ve bunları sıralayan temel algoritmamı davranışsal olarak doğrulamak için yazdığım kaslı bir birim test sınıfı olarak tasarlıyorum.
class _002_Random_Number_UniqueTest {

    // 🏷️ Bu anotasyon ile aşağıdaki metodu, JUnit gözünde bağımsız bir test senaryosu olarak işaretleyerek otomatik çalıştırılabilir hâle getiriyorum.
    @Test
    // 🎲 Bu testte, generateRandomUniqueNumber metodumun verilen boyutta bir dizi döndürdüğünü, elemanların aralık içinde kaldığını ve tekrar etmediğini kontrol ederek algoritmanın temel sözleşmesini garanti altına almaya çalışıyorum.
    void generateRandomUniqueNumber_shouldReturnUniqueValuesWithinRange() throws InterruptedException {
        // 🔢 Burada, test senaryosunda kullanacağım dizi boyutunu sabit bir değer olarak belirleyip daha sonra hem üretim hem de doğrulama kısmında referans olarak kullanıyorum.
        byte size = 11;
        // 🎰 Rastgele ama benzersiz sayı üreten metodumu çağırarak dönen dizi referansını numbers isimli değişkende saklıyor ve gerçek üretim davranışını test verisi olarak kullanıyorum.
        double[] numbers = invokeGenerateRandomUniqueNumber(size);
        // 📏 Dönen dizinin uzunluğunun beklediğim boyutla eşit olduğunu doğrulayarak metodun eksik ya da fazla eleman üretmediğinden emin oluyorum.
        assertEquals(size, numbers.length);

        // 🧮 Bu satırda, her olası sayı için görülüp görülmediğini tutacağım bir boolean dizisi oluşturuyor ve böylece benzersizliği kontrol etmek için pratik bir işaretleme alanı hazırlıyorum.
        boolean[] seen = new boolean[size];
        // 🔁 Üretilen bütün sayıları tek tek dolaşarak hem aralık kontrolü hem de daha önce görülüp görülmediği üzerinden benzersizlik testini gerçekleştiriyorum.
        for (double value : numbers) {
            // 🧱 Önce üretilen değeri int'e çevirerek algoritmamın aslında 0 ile dizi boyutu arasında tamsayı ürettiğini varsayan tasarımını kod tarafında da netleştiriyorum.
            int index = (int) value;
            // 🚧 Her bir değerin 0 ile size-1 aralığında olup olmadığını assertTrue ile kontrol ederek algoritmanın beklenen aralığın dışına taşmadığına emin oluyorum.
            assertTrue(index >= 0 && index < size, "Üretilen değer aralığın dışında: " + index);
            // 🚨 Daha önce aynı indeks için seen dizisinde true işaretlenmişse bunun tekrar eden bir sayı anlamına geldiğini bilerek testin burada patlamasını istiyorum.
            assertFalse(seen[index], "Aynı değer birden fazla kez üretildi: " + index);
            // 🧷 Bu satırda, ilk kez gördüğüm değeri seen dizisinde işaretleyerek ilerleyen adımlarda olası tekrarları tespit edebilmem için zemin hazırlıyorum.
            seen[index] = true;
        }
    }

    // 🏷️ Bu anotasyonla, sıralama algoritmamın küçükten büyüğe doğru doğru çalışıp çalışmadığını kontrol eden test metodunu JUnit'e tanıtıyorum.
    @Test
    // 📈 Bu testte, karışık verilere sahip sabit bir dizi üzerinden sortNumbers metodumu küçükten büyüğe sıralama modunda çalıştırarak sonuç dizinin gerçekten artan düzende olup olmadığını doğruluyorum.
    void sortNumbers_shouldSortAscendingWhenFlagIsFalse() {
        // 📊 Burada hem negatif hem pozitif hem de karışık sıralı elemanlar içeren, algoritmanın gerçek anlamda test edilmesine uygun bir dizi tanımlıyorum.
        double[] numbers = {5, 1, 4, 3, 2, 0, -1};
        // 🧬 Orijinal diziyi klonlayarak sıralama metoduna verirken ana veriyi bozmadan, dönüş değerini de test etmek üzere kullanmak istiyorum.
        double[] sorted = invokeSortNumbers(numbers.clone(), false);

        // 🔎 Bu döngüyle sıralı dizinin her bir komşu eleman çifti için öncekinin sonrakinden büyük olmadığını kontrol ederek dizinin gerçekten artan düzen şartını sağladığından emin oluyorum.
        for (int i = 0; i < sorted.length - 1; i++) {
            // ✅ Her adımda sorted[i] <= sorted[i+1] koşulunu assertTrue ile güvenceye alarak, sıralama algoritmamın küçükten büyüğe sıralama sözleşmesini ihlal etmediğini doğruluyorum.
            assertTrue(sorted[i] <= sorted[i + 1],
                    "Dizi artan sıralı değil: index " + i + " -> " + sorted[i] + " > " + sorted[i + 1]);
        }
    }

    // 🏷️ Bu anotasyonla, sıralama algoritmamın büyükten küçüğe modda da doğru çalıştığını test eden metodu çerçeveye bildiriyorum.
    @Test
    // 📉 Bu testte, aynı karışık veri kümesini bu kez büyükten küçüğe sıralama bayrağı ile işleyerek sonuç dizinin gerçekten azalan düzende olup olmadığını detaylı şekilde kontrol ediyorum.
    void sortNumbers_shouldSortDescendingWhenFlagIsTrue() {
        // 📊 Yine farklı büyüklükte ve işarette sayılar içeren bir dizi hazırlayarak algoritmanın deterministik olmayan basit bir örnek üzerinde düzgün davranmasını sağlamaya çalışıyorum.
        double[] numbers = {5, 1, 4, 3, 2, 0, -1};
        // 🔁 Diziyi klonlayarak sortNumbers metodunu bu defa isMaxToMin parametresini true vererek çağırıyor ve beklenen azalan sıralamayı elde etmeyi amaçlıyorum.
        double[] sorted = invokeSortNumbers(numbers.clone(), true);

        // 🔍 Bu döngüde her komşu çifti kontrol ederek listedeki her bir elemanın kendisinden sonra gelen elemandan küçük olmadığını doğrulamak için sistematik bir kontrol yapısı kuruyorum.
        for (int i = 0; i < sorted.length - 1; i++) {
            // ✅ Her iterasyonda sorted[i] >= sorted[i+1] koşulunu assertTrue ile doğrulayarak algoritmanın büyükten küçüğe sıralama beklentisini eksiksiz karşıladığını teyit ediyorum.
            assertTrue(sorted[i] >= sorted[i + 1],
                    "Dizi azalan sıralı değil: index " + i + " -> " + sorted[i] + " < " + sorted[i + 1]);
        }
    }

    // 🧩 Bu yardımcı metotta, üretim kodundaki generateRandomUniqueNumber metoduna doğrudan delege ederek test sınıfındaki çağrıların derli toplu ve değişikliğe daha dayanıklı hâle gelmesini sağlıyorum.
    private double[] invokeGenerateRandomUniqueNumber(byte size) throws InterruptedException {
        // 🔗 Burada, _002_Random_Number_Unique sınıfındaki generateRandomUniqueNumber metodunu aynı imzayla çağırarak gerçek algoritmanın ürettiği diziyi test senaryolarına geri döndürüyorum.
        return callGenerateRandomUniqueNumber(size);
    }

    // 🧠 Bu metotta, üretim sınıfını tek bir yerden referans alarak generateRandomUniqueNumber çağrısını topluyor ve gerekirse erişim türü veya sınıf adı değiştiğinde sadece burayı güncellemeyi planlıyorum.
    private double[] callGenerateRandomUniqueNumber(byte size) throws InterruptedException {
        // 🧵 Burada, üretim kodunda yer alan generateRandomUniqueNumber metoduna boyutu parametre olarak ileterek gerçek rastgele benzersiz dizi üretimini tetikliyorum.
        return _002_Random_Number_Unique.generateRandomUniqueNumber(size);
    }

    // 🧩 Bu yardımcı metotla, sortNumbers çağrılarını soyutlayarak hem test kodunu daha okunur kılıyor hem de olası imza değişikliklerinde tek noktadan uyarlama yapmayı amaçlıyorum.
    private double[] invokeSortNumbers(double[] numbers, boolean isMaxToMin) {
        // 🔗 Burada, üretim kodundaki sortNumbers metodunu parametreleriyle aynen çağırarak sıralanmış diziyi test senaryolarına döndürmüş oluyorum.
        return callSortNumbers(numbers, isMaxToMin);
    }

    // 🧠 Bu metotla, sortNumbers metoduna giden çağrıyı tek noktada topluyor ve sınıf adı ya da metot erişimi değiştiğinde minimum temas yüzeyiyle düzeltme yapabilmek istiyorum.
    private double[] callSortNumbers(double[] numbers, boolean isMaxToMin) {
        // 🔄 Burada, _002_Random_Number_Unique sınıfındaki sortNumbers metodunu çağırarak verilen diziyi belirtilen yönde sıralıyor ve bu sonucu testlerime geri döndürüyorum.
        return _002_Random_Number_Unique.sortNumbers(numbers, isMaxToMin);
    }
}