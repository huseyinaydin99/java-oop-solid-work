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
public class Yazilimci extends Personel {

    private String uzmanlikAlani;

    /*
    Method does not override method from its superclass.
    Bu uyarı, @Override anotasyonu kullandığım bir metodun aslında üst sınıfta (superclass)
    aynı imzaya sahip bir metod olmadığını gösterir ⚠️. Yani derleyici,
    “Bu metodu override ediyorsun ama aynı isim ve parametre listesi üst sınıfta yok” diyor 🧩.
    Çözümü: ya parametreleri veya metod adını üst sınıfta birebir aynı yapacan veyahutda
    @Override anotasyonunu kaldır ✅.
     */
    //@Override
    public void bilgisiniYaz(Yazilimci yazilimci) {
        System.out.println(yazilimci.getAdi() + " " + yazilimci.getSoyadi());
        System.out.println(yazilimci.getTelefonNo());
        System.out.println(yazilimci.getDepartmani());
        System.out.println(yazilimci.getSicilNo());
        System.out.println("Araç: " + (yazilimci.isMedeniDurumu() ? "Var" : "Yok"));
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