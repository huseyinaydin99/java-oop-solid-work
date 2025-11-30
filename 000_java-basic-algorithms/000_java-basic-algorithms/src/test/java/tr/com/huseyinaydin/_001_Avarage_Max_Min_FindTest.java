// 📦 Bu satırda test sınıfımın hangi package altında olduğunu belirtiyor ve proje yapısını derli toplu tutuyorum.
package tr.com.huseyinaydin;

// ✅ JUnit 5'in @Test anotasyonunu kullanabilmek için gerekli sınıfı içeri alıyorum.
import org.junit.jupiter.api.Test;

// 📚 List yapısını kullanmak için java.util.List'i import ederek koleksiyonlarla rahat çalışıyorum.
import java.util.List;

// 🧪 Assertion metotlarını statik import ederek assertEquals, assertThrows gibi fonksiyonları kısaca kullanıyorum.
import static org.junit.jupiter.api.Assertions.*;

// 🧱 Bu sınıfı, yazdığım _001_Avarage_Max_Min_Find yardımcı metotlarının doğru çalışıp çalışmadığını otomatik olarak test eden birim test sınıfı olarak tanımlıyorum.
class _001_Avarage_Max_Min_FindTest {

    // 🏷️ Bu anotasyonla aşağıdaki metodu JUnit'e bir test metodu olarak kaydediyorum.
    @Test
    // 🧮 Bu testte, average metodumun basit bir sayı dizisi için beklenen ortalamayı üretip üretmediğini kontrol ediyorum.
    void avarage_shouldCalculateCorrectAverage() {
        // 🔢 Burada average metodunu 10, 20 ve 30 değerleriyle çağırıp dönen sonucu result değişkenine atıyorum.
        double result = _001_Avarage_Max_Min_Find.avarage(10, 20, 30);
        // ✔️ Beklediğim ortalamanın 20 olduğunu, küçük bir toleransla gerçek sonuçla eşleşip eşleşmediğini assertEquals ile doğruluyorum.
        assertEquals(20.0, result, 0.0001);
    }

    // 🏷️ Bu anotasyonla, boş dizi gönderildiğinde average metodunun nasıl davrandığını test eden metodu işaretliyorum.
    @Test
    // ⚠️ Bu testte, average metoduna hiç eleman verilmediğinde IllegalArgumentException fırlatmasını bekleyerek hata durumunu güvence altına alıyorum.
    void avarage_shouldThrowExceptionWhenArrayEmpty() {
        // 💥 Burada assertThrows ile average metodunu boş argümanla çağırıyor ve beklenen istisnanın gerçekten fırlatılıp fırlatılmadığını kontrol ediyorum.
        assertThrows(IllegalArgumentException.class,
                // 🧷 Lambda ifadesi içinde average metodunu parametresiz çağırarak istisna fırlatma davranışını test ediyorum.
                () -> _001_Avarage_Max_Min_Find.avarage());
    }

    // 🏷️ Bu anotasyonla, minimum değeri bulan metodu test eden bir başka test metodu tanımladığımı belirtiyorum.
    @Test
    // 📉 Bu testte, findMinFromArray metodumun karışık pozitif ve negatif sayılar içeren dizide en küçük değeri doğru şekilde döndürmesini bekliyorum.
    void findMinFromArray_shouldReturnSmallestElement() {
        // 🔎 Burada findMinFromArray metodunu 10, 20, 3, 5, -1 ve 100 ile çağırıp dönen minimum değeri result değişkenine alıyorum.
        double result = _001_Avarage_Max_Min_Find.findMinFromArray(10, 20, 3, 5, -1, 100);
        // ✅ En küçük değerin -1 olmasını beklediğim için assertEquals ile sonucu kontrol ederek metot davranışını doğruluyorum.
        assertEquals(-1.0, result, 0.0001);
    }

    // 🏷️ Bu anotasyonla, boş dizi üzerinde minimum arandığında ne olduğunu test eden metodu işaretliyorum.
    @Test
    // 🚫 Bu testte, findMinFromArray metoduna hiç eleman verilmediğinde IllegalArgumentException fırlatmasını bekleyerek yanlış kullanım durumunu test ediyorum.
    void findMinFromArray_shouldThrowExceptionWhenArrayEmpty() {
        // 💣 Burada assertThrows ile findMinFromArray metodunu boş argümanla çağırıyor ve beklenen istisnanın gerçekten fırlatıldığını kontrol ediyorum.
        assertThrows(IllegalArgumentException.class,
                // 🧷 Lambda ifadesi içinde findMinFromArray'i parametresiz çağırarak hata senaryosunu netleştiriyorum.
                () -> _001_Avarage_Max_Min_Find.findMinFromArray());
    }

    // 🏷️ Bu anotasyonla, maksimum değeri bulan metodu test eden bir başka test metodu tanımladığımı gösteriyorum.
    @Test
    // 📈 Bu testte, findMaxFromArray metodumun karışık sayılardan oluşan dizide en büyük değeri doğru şekilde bulup bulmadığını kontrol ediyorum.
    void findMaxFromArray_shouldReturnLargestElement() {
        // 🔍 Burada findMaxFromArray metodunu 10, 20, 3, 5, -1 ve 100 ile çağırıp dönen maksimum değeri result değişkenine yazıyorum.
        double result = _001_Avarage_Max_Min_Find.findMaxFromArray(10, 20, 3, 5, -1, 100);
        // ✅ En büyük değerin 100 olmasını beklediğim için assertEquals ile sonucu kontrol ederek maksimum bulma fonksiyonunu doğruluyorum.
        assertEquals(100.0, result, 0.0001);
    }

    // 🏷️ Bu anotasyonla, findMaxFromArray metodunun boş dizi aldığında nasıl tepki verdiğini sınayan testi işaretliyorum.
    @Test
    // 🚫 Bu testte, maksimum arama fonksiyonuma hiç sayı vermediğimde IllegalArgumentException fırlatmasını bekleyerek koruma mekanizmasını test ediyorum.
    void findMaxFromArray_shouldThrowExceptionWhenArrayEmpty() {
        // 💣 Burada assertThrows ile findMaxFromArray metoduna boş argüman gönderiyor ve beklenen istisnanın gerçekten tetiklendiğini teyit ediyorum.
        assertThrows(IllegalArgumentException.class,
                // 🧷 Lambda içinde findMaxFromArray'i parametresiz çağırarak hatalı kullanım senaryosunu simüle ediyorum.
                () -> _001_Avarage_Max_Min_Find.findMaxFromArray());
    }

    // 🏷️ Bu anotasyonla, çift sayı filtreleme fonksiyonumu test eden metodu birim testi olarak işaretliyorum.
    @Test
    // ➗ Bu testte, twoDivideFromArray metodumun verilen diziden yalnızca çift sayıları çekip listeye ekleyip eklemediğini kontrol ediyorum.
    void twoDivideFromArray_shouldReturnOnlyEvenNumbers() {
        // 📊 Burada içinde hem çift hem tek sayılar bulunan bir dizi oluşturarak gerçekçi bir test verisi hazırlıyorum.
        double[] array = {10, 21, 30, 2, 7, 8};
        // 🧺 twoDivideFromArray metodunu çağırarak dizideki çift sayıları alıp evens isimli listeye topluyorum.
        List<Double> evens = _001_Avarage_Max_Min_Find.twoDivideFromArray(array);

        // 📏 Burada listenin boyutunun 4 olmasını bekleyerek yalnızca dört çift sayının seçildiğini doğruluyorum.
        assertEquals(4, evens.size());
        // ✅ Listedeki elemanlar arasında 10 değerinin bulunup bulunmadığını kontrol ederek doğru filtreleme yapıldığını teyit ediyorum.
        assertTrue(evens.contains(10.0));
        // ✅ Aynı şekilde 30 değerinin de listede yer aldığını kontrol ederek başka bir çift sayının da doğru eklendiğini görüyorum.
        assertTrue(evens.contains(30.0));
        // ✅ 2 değerinin de listede olmasını bekleyerek küçük değerli çift sayıların da hesaba katıldığını test ediyorum.
        assertTrue(evens.contains(2.0));
        // ✅ 8 değerinin varlığını kontrol ederek tüm çift sayıların düzgün şekilde toplandığını son kez teyit ediyorum.
        assertTrue(evens.contains(8.0));
    }

    // 🏷️ Bu anotasyonla, tek sayı filtreleme fonksiyonumu test eden metodu JUnit'e tanıtıyorum.
    @Test
    // ➕ Bu testte, noneTwoDivideFromArray metodumun verilen diziden yalnızca tek sayıları seçip listeye ekleyip eklemediğini kontrol ediyorum.
    void noneTwoDivideFromArray_shouldReturnOnlyOddNumbers() {
        // 📊 Burada içinde hem çift hem tek sayılar bulunan bir dizi tanımlayarak tek sayı filtrelemesini sınamak için veri hazırlıyorum.
        double[] array = {10, 21, 30, 3, 7, 8};
        // 🧺 noneTwoDivideFromArray metodunu çağırarak dizideki tek sayıları odds isimli listeye topluyorum.
        List<Double> odds = _001_Avarage_Max_Min_Find.noneTwoDivideFromArray(array);

        // 📏 Listenin boyutunun 3 olmasını bekleyerek yalnızca üç tek sayının seçildiğini kontrol ediyorum.
        assertEquals(3, odds.size());
        // ✅ 21 değerinin listede yer aldığını kontrol ederek ilk tek sayının doğru yakalandığını teyit ediyorum.
        assertTrue(odds.contains(21.0));
        // ✅ 3 değerinin de listede bulunmasını bekleyerek küçük tek sayıların da doğru filtrelenip filtrelenmediğini görüyorum.
        assertTrue(odds.contains(3.0));
        // ✅ 7 değerinin varlığını kontrol ederek tüm beklenen tek sayıların gerçekten listeye girdiğini netleştiriyorum.
        assertTrue(odds.contains(7.0));
    }
}