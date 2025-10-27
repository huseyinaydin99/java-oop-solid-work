package tr.com.huseyinaydin._02;

import java.util.Date;

public class AppMain {

    public static void main(String[] args) {
        try {
            //int sonuc = urunlerinKilosunuAlUnchecked(250, 85);
            int sonuc = urunlerinKilosunuAlChecked(250, 85);
            System.out.println("Ürünlerin sayısı: " + sonuc);
        } catch (Exception e) {
            System.out.println("Hata: " + e);
        } finally {
            System.out.println("Teşekkür ederiz. : " + new Date());
        }
    }

    //Unchecked
    private static int urunlerinKilosunuAlUnchecked(int kiloNo, int uretilenUrunlerinKilosu) {
        if (kiloNo < 1 || kiloNo > 50) {
            throw new ArithmeticException("Kilo sayısnı lütfen doğru giriniz!");
        } else {
            return uretilenUrunlerinKilosu;
        }
    }

    //Checked
    private static int urunlerinKilosunuAlChecked(int kiloNo, int uretilenUrunlerinKilosu) throws ArithmeticException {
        if (kiloNo < 1 || kiloNo > 50) {
            throw new ArithmeticException("Kilo sayısnı lütfen doğru giriniz!");
        } else {
            return uretilenUrunlerinKilosu;
        }
    }
}