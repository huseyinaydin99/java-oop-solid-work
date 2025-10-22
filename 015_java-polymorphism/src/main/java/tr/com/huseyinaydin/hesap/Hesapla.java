package tr.com.huseyinaydin.hesap;

public class Hesapla<T> { // T tipi parametre
    public void yaz(T veri) {
        System.out.println("Değer: " + veri);
    }

    public static void main(String[] args) {
        Hesapla<Integer> intHesapla = new Hesapla<>();
        intHesapla.yaz(123);  // Integer tipi ile çalışır

        Hesapla<String> stringHesapla = new Hesapla<>();
        stringHesapla.yaz("Merhaba"); // String tipi ile çalışır
    }
}