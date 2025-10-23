package tr.com.huseyinaydin.hayvanlar.kuslar;

import tr.com.huseyinaydin.hayvanlar.Hayvan;

public class Kus extends Hayvan {

    @Override
    public void sesVer() {
        System.out.println("Cik cik");
    }

    @Override
    public void bilgisiniGetir() {
        System.out.println("Kuslar alemi");
    }
}