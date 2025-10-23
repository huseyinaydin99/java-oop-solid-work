package tr.com.huseyinaydin.hayvanlar;

import tr.com.huseyinaydin.base.Canli;

public abstract class Hayvan extends Canli {
    public void yemekYe() {
        System.out.println("Hayvan: yemekYe");
    }

    public void suIc() {
        System.out.println("Hayvan: suIc");
    }

    public void hareketTuru() {
        System.out.println("Hayvan: hareketTuru");
    }

    public abstract void sesVer();

    public abstract void bilgisiniGetir();
}