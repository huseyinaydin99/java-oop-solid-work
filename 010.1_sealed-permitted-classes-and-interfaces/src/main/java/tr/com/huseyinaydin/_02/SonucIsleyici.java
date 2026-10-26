package tr.com.huseyinaydin._02;

public class SonucIsleyici {

    public String yanitiCozumle(ServisSonucu sonuc) {
        // DİKKAT: 'default' bloğu YOK!
        // Çünkü Java, ServisSonucu'nun SADECE Basarili veya Hatali olabileceğini biliyor.
        return switch (sonuc) {
            case Basarili b -> "İşlem harika! Gelen veri: " + b.veri();
            case Hatali h   -> "Eyvah! Hata kodu: " + h.hataKodu() + " - Mesaj: " + h.hataMesaji();
        };
    }

    public static void main(String[] args) {
        SonucIsleyici sonuc = new SonucIsleyici();
        System.out.println(sonuc.yanitiCozumle(new Basarili("test başarılı", 1)));
        System.out.println(sonuc.yanitiCozumle(new Hatali("test başarısız", "2")));
    }
}