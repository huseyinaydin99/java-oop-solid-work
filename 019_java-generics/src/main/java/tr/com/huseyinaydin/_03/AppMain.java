package tr.com.huseyinaydin._03;

import java.util.ArrayList;

public class AppMain {

    public static void main(String[] args) {
        Personel<Integer/*Number*/, String> personel = new Personel();
        //Personel<String, Integer> personel2 = new Personel();

        //Personel<Number, String, Boolean> personel = new Personel();

        //Personel<Number, String, Boolean, String, Departman> personel = new Personel();

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

        personel.ekranaYaz((short) 5, "Hüseyin");
        personel.cizgiCek();

        int value = personel.ekranaYaz(1, "Hüseyin");
        System.out.println("Değer: " + value);
    }
}