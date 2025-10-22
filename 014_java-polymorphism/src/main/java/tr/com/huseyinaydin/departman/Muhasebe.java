package tr.com.huseyinaydin.departman;

import tr.com.huseyinaydin.base.Personel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Muhasebe extends Personel {

    private Boolean yeminDurumu;
    private String unvani;

    /*
    Method does not override method from its superclass.
    Bu uyarı, @Override anotasyonu kullandığım bir metodun aslında üst sınıfta (superclass)
    aynı imzaya sahip bir metod olmadığını gösterir ⚠️. Yani derleyici,
    “Bu metodu override ediyorsun ama aynı isim ve parametre listesi üst sınıfta yok” diyor 🧩.
    Çözümü: ya parametreleri veya metod adını üst sınıfta birebir aynı yapacan veyahutda
    @Override anotasyonunu kaldır ✅.
     */
    //@Override
    public void bilgisiniYaz(Muhasebe muhasebe) {
        System.out.println(muhasebe.getAdi() + " " + muhasebe.getSoyadi());
        System.out.println(muhasebe.getTelefonNo());
        System.out.println(muhasebe.getDepartmani());
        System.out.println(muhasebe.getSicilNo());
        System.out.println("Medeni Durumu: " + (muhasebe.isMedeniDurumu() ? "Evli" : "Bekar"));
    }

    @Override
    public void bilgisiniYaz(Personel personel) {
        System.out.println(personel.getAdi() + " " + personel.getSoyadi());
        System.out.println(personel.getTelefonNo());
        System.out.println(personel.getDepartmani());
        System.out.println(personel.getSicilNo());
        System.out.println("Araç: " + (((Mudur)personel).isMakamAraci() ? "Var" : "Yok"));
    }
}