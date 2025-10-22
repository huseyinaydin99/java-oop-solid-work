package tr.com.huseyinaydin;

import tr.com.huseyinaydin.base.Personel;
import tr.com.huseyinaydin.departman.Mudur;
import tr.com.huseyinaydin.departman.Muhasebe;
import tr.com.huseyinaydin.departman.Yazilimci;

public class AppMain {

    public static void main(String[] args) {
        Mudur mudur = new Mudur();
        mudur.setAdi("Hüseyin");
        mudur.setSoyadi("AYDIN");
        mudur.setMaasi(8_000f);
        mudur.setMakamAraci(true);
        mudur.setTelefonNo("322432432");
        mudur.setSicilNo(123);
        mudur.setDepartmani("Java");

        mudur.bilgisiniYaz(mudur);

        System.out.println("----------");

        Muhasebe muhasebe = new Muhasebe();
        muhasebe.setAdi("Cemre");
        muhasebe.setSoyadi("Sarıçizmeli");
        muhasebe.setMaasi(4_000f);
        muhasebe.setYeminDurumu(true);
        muhasebe.setTelefonNo("2323");
        muhasebe.setSicilNo(999);
        muhasebe.setDepartmani("Finans");

        muhasebe.bilgisiniYaz(muhasebe);

        System.out.println("----------");

        Yazilimci yazilimci = new Yazilimci();
        yazilimci.setAdi("Selami");
        yazilimci.setSoyadi("Zımba");
        yazilimci.setMaasi(9_000f);
        yazilimci.setUzmanlikAlani("Java");
        yazilimci.setTelefonNo("8888");
        yazilimci.setSicilNo(5555);
        yazilimci.setDepartmani("Arge");

        yazilimci.bilgisiniYaz(yazilimci);

        // polymorphism
        Personel personel = new Personel();
        personel.hesapla();
    }
}