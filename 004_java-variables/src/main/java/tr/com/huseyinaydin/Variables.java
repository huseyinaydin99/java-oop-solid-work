package tr.com.huseyinaydin;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java
 *
 */

public class Variables { // Değişkenler ve Kapsam (Scope)

/*
    Buradaki örnek malzemeler gibi düşün:
    Kaşık, Çorba Kaşığı, Kepçe, Tabak, Bardak, Tencere, Kazan.
    Her birinin kapasitesi ve kullanım amacı farklı, tıpkı değişkenler gibi.
*/
    public static void main(String[] args) {

        // Primitive/ilkel tipler: hafif, hızlı, referans gibi ağır tip olmayan, acık daha performanslı default değerleri var
        int myInt = 10; //Standart tamsayı tipi, çoğu hesaplama için optimize. Min: -2_147_483_648, Max: 2_147_483_647. Bellek: 4 byte.
        byte myByte = 20; //Küçük tam sayılar için minimal bellekle hızlı çözüm sağlar. Min: -128, Max: 127. Bellek: 1 byte.
        short myShort = 30; //tam sayı az
        long myLong = 40L; //Büyük sayılarda güvenli, bellek bedeli öder. Min: -9_223_372_036_854_775_808, Max: 9_223_372_036_854_775_807. Bellek: 8 byte.
        float myFloat = 50.123456f; //Hafif ondalık, küçük hassasiyet kayıplarını tolere eder. Min: 1.4e-45, Max: 3.4028235e38. Bellek: 4 byte.
        double myDouble = 60.123456789012345; //Yüksek hassasiyet ondalıklı sayı, geniş aralık, bilimsel hesaplar için. Min: 4.9e-324, Max: 1.7976931348623157e308. Bellek: 8 byte.
        char myChar = 'A'; //Tek Unicode karakter, hızlı metin erişimi sağlar. Min: '\u0000', Max: '\uffff'. Bellek: 2 byte.
        boolean myBoolean = true; //(1 bit teorik, JVM’de 1 byte): İki durumlu karar, minimal bellekle kontrol sağlar. Min: false, Max: true. Bellek: 1 byte.

        System.out.println("Primitive değerler:");
        System.out.println("Int: " + myInt);
        myInt = 1_000_000_000; // değer ataması, kapsam değişmedi
        System.out.println("Güncellenmiş Int: " + myInt);
        System.out.println("Byte: " + myByte);
        System.out.println("Short: " + myShort);
        System.out.println("Long: " + myLong);
        System.out.println("Float: " + myFloat);
        System.out.println("Double: " + myDouble);
        System.out.println("Char: " + myChar);
        System.out.println("Boolean: " + myBoolean);

        System.out.println("------------------------");

        /* Wrapper tipler, Java’da primitive veri tiplerini nesne olarak sarmalayan sınıflardır;
        böylece primitive’ler nesne gibi davranabilir ve koleksiyonlar,
        generics gibi nesne tabanlı yapılarda kullanılabilir. Örneğin Integer,
        Double, Boolean gibi sınıflar primitive tiplerin sınıf karşılıklarıdır
        ve ek metodlarla değer üzerinde daha esnek işlemler yapılmasını sağlar.
        */
        Integer myInt2 = 10;
        Byte myByte2 = 20;
        Short myShort2 = 30;
        Long myLong2 = 40L;
        Float myFloat2 = 50.123456F;
        Double myDouble2 = 60.123456789012345;
        Character myChar2 = 'A';
        Boolean myBoolean2 = true;

        System.out.println("Wrapper değerler:");
        System.out.println("Integer: " + myInt2);
        myInt2 = 1_000_000_000; // scope hâlâ aynı
        System.out.println("Güncellenmiş Integer: " + myInt2);
        System.out.println("Byte: " + myByte2);
        System.out.println("Short: " + myShort2);
        System.out.println("Long: " + myLong2);
        System.out.println("Float: " + myFloat2);
        System.out.println("Double: " + myDouble2);
        System.out.println("Char: " + myChar2);
        System.out.println("Boolean: " + myBoolean2);

        System.out.println("------------------------");

        // Kapsam (Scope) örneği
        {
            int localInt = 99; // sadece bu blok içinde geçerli
            System.out.println("Blok içinde tanımlı int: " + localInt);
        }
        // System.out.println(localInt); // ❌ Hata: localInt dışarıdan erişilemez habarın ossun dayı!

        // Null olabilirlik & nesne davranışı
        Integer nullableInt = null; // primitive bunu yapamaz nesne null atanabilir. null demek belleğin heap alanında nesne yok demek. NullPointerException olmayan nesneye erişim/işaretci/referans hatası.
        System.out.println("Null olabilen Integer: " + nullableInt);
    }
}