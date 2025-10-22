package tr.com.huseyinaydin.departman;

import tr.com.huseyinaydin.base.Personel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mudur extends Personel {

    private boolean makamAraci;

    public Mudur() {
    }

    public Mudur(String adi, String soyadi, String telefonNo,
                 String departmani, int sicilNo, String tahsilDurumu,
                 String adres, short maasi, boolean medeniDurumu,
                 boolean makamAraci) {
        super(adi, soyadi, telefonNo, departmani, sicilNo, tahsilDurumu, adres, maasi, medeniDurumu);
        this.makamAraci = makamAraci;
    }

    /*
    Method does not override method from its superclass.
    Bu uyarı, @Override anotasyonu kullandığım bir metodun aslında üst sınıfta (superclass)
    aynı imzaya sahip bir metod olmadığını gösterir ⚠️. Yani derleyici,
    “Bu metodu override ediyorsun ama aynı isim ve parametre listesi üst sınıfta yok” diyor 🧩.
    Çözümü: ya parametreleri veya metod adını üst sınıfta birebir aynı yapacan veyahutda
    @Override anotasyonunu kaldır ✅.
     */
    //@Override
    /*public void bilgisiniYaz(Mudur mudur) {
        System.out.println(mudur.getAdi() + " " + mudur.getSoyadi());
        System.out.println(mudur.getTelefonNo());
        System.out.println(mudur.getDepartmani());
        System.out.println(mudur.getSicilNo());
        System.out.println("Araç: " + (mudur.isMakamAraci() ? "Var" : "Yok"));
    }*/

    public void bilgisiniYaz(Personel personel) {
        System.out.println(personel.getAdi() + " " + personel.getSoyadi());
        System.out.println(personel.getTelefonNo());
        System.out.println(personel.getDepartmani());
        System.out.println(personel.getSicilNo());
        System.out.println("Araç: " + (((Mudur)personel).isMakamAraci() ? "Var" : "Yok"));
    }
}