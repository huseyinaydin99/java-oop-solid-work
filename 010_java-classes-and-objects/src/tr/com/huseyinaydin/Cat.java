package tr.com.huseyinaydin;

public class Cat {
    // declaration - deklere - bildiri bildirmek
    int yas;
    String cinsi;
    String rengi;

    // Constructor - hazırlayıcı- yapıcı - ilk hazirligi yapar
    public Cat() {
        yas = 0;
        cinsi = "Bilinmiyor";
        rengi = "Belirtilmedi";
        System.out.println("-------Constructor---------");
        /*System.out.println(cinsi);
        System.out.println(yas);
        System.out.println(rengi);
        System.out.println("-------------------");
        */
    }

    public Cat(int yas, String cinsi, String rengi) {
        this.yas = yas;
        this.cinsi = cinsi;
        this.rengi = rengi;
    }

    /*
    // normal babanneden kalma metot
    public void Cat() {
    }
    */

    public int getYas() {
        return yas;
    }

    public void setYas(int yas) {
        this.yas = yas;
    }

    public static void main(String[] args) {
        Cat kediNesne = new Cat();

        System.out.println(kediNesne.cinsi);
        System.out.println(kediNesne.yas);
        System.out.println(kediNesne.rengi);

        System.out.println("-----------------------");

        kediNesne.cinsi = "Van";
        kediNesne.yas = 2;
        kediNesne.rengi = "Beyaz";

        System.out.println(kediNesne.cinsi);
        System.out.println(kediNesne.yas);
        System.out.println(kediNesne.rengi);

        System.out.println("-----------------------");

        kediNesne.cinsi = "Ankara";
        kediNesne.yas = 4;
        kediNesne.rengi = "Beyaz";

        System.out.println(kediNesne.cinsi);
        System.out.println(kediNesne.yas);
        System.out.println(kediNesne.rengi);

        System.out.println("-----------------------");

        Cat kedi1 = new Cat(8, "Kedi 1", "Sarı");
        System.out.println("Tür: " + kedi1.cinsi + "  - Yaşı: " + kedi1.yas + " - Rengi: " + kedi1.rengi);

        Cat kedi2 = new Cat(3, "Kedi 2", "Sarı");
        System.out.println("Tür: " + kedi2.cinsi + "  - Yaşı: " + kedi2.yas + " - Rengi: " + kedi2.rengi);

        Cat kedi3 = new Cat(10, "Kedi 3", "Siyah");
        System.out.println("Tür: " + kedi3.cinsi + "  - Yaşı: " + kedi3.yas + " - Rengi: " + kedi3.rengi);

        System.out.println("-----------------------");
        kedi1.setYas(3);

        System.out.println("Tür: " + kedi1.cinsi + "  - Yaşı: " + kedi1.yas + " - Rengi: " + kedi1.rengi);
        System.out.println("Tür: " + kedi1.cinsi + "  - Yaşı: " + kedi1.getYas() + " - Rengi: " + kedi1.rengi);
    }
}