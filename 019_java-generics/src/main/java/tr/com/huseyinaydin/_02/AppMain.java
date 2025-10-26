package tr.com.huseyinaydin._02;

public class AppMain {

    public static void main(String[] args) {
        Personel personel = new Personel();

        personel.ekranaYaz();
        personel.cizgiCek();

        personel.ekranaYaz(1, "Hüseyin");
        personel.cizgiCek();

        personel.ekranaYaz(2.0, "Hüseyin");
        personel.cizgiCek();

        personel.ekranaYaz(3.0f, "Hüseyin");
        personel.cizgiCek();

        personel.ekranaYaz(4L, "Hüseyin");
        personel.cizgiCek();
    }
}