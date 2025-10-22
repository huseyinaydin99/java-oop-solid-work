package tr.com.huseyinaydin.base;

import tr.com.huseyinaydin.departman.Muhasebe;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Personel {
    private String adi;
    private String soyadi;
    private String telefonNo;
    private String departmani;
    private int sicilNo;
    private String tahsilDurumu;
    private String adres;
    private float maasi;
    private boolean medeniDurumu;

    public void bilgisiniYaz(Personel personel) {
        System.out.println(personel.getAdi() + " " + personel.getSoyadi());
        System.out.println(personel.getTelefonNo());
        System.out.println(personel.getDepartmani());
        System.out.println(personel.getSicilNo());
        System.out.println("Medeni Durumu: " + (personel.isMedeniDurumu() ? "Evli" : "Bekar"));
    }

    public void hesapla() {
        // Parametre almaz, hiçbir ek bilgi olmadan hesaplama yapılacağını belirtir
    }

    public void hesapla(int yil) {
        // Tek parametre alır, yalnızca yıl bilgisiyle hesaplama yapılacağını belirtir
    }

    public void hesapla(int yil, int ay) {
        // İki parametre alır, yıl ve ay bilgisiyle hesaplama yapılacağını belirtir
    }

    public void hesapla(float ay, int yil) {
        // Parametrelerin türleri farklıdır, float ay ve int yıl ile hesaplama yapılacağını belirtir
    }

    public void hesapla(int yil, int ay, int gun) {
        // Üç parametre alır, yıl, ay ve gün bilgisiyle hesaplama yapılacağını belirtir
    }

    public void hesapla(int yil, short ay) {
        // İki parametre alır, yıl ve kısa tip ay ile hesaplama yapılacağını belirtir
    }

    public void hesapla(int yil, short ay, int gun) {
        // Üç parametre alır, yıl, kısa tip ay ve gün ile hesaplama yapılacağını belirtir
    }

    public void hesapla(int yil, short ay, short gun) {
        // Üç parametre alır, yıl, kısa tip ay ve kısa tip gün ile hesaplama yapılacağını belirtir
    }

    public void hesapla(short yil, short ay, int gun) {
        // Üç parametre alır, kısa tip yıl, kısa tip ay ve gün ile hesaplama yapılacağını belirtir
    }
}