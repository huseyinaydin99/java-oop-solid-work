package tr.com.huseyinaydin.veteriner;

import tr.com.huseyinaydin.base.Hayvan;

public class Kedi extends Hayvan {

    public Kedi() {
        super();
        System.out.println("Kedi");
    }

    @Override
    public void sesVer() {
        System.out.println("Kedi : miyav miyav");
    }

    @Override
    public void hareketeGec() {
        System.out.println("Kedi : hareketeGec");
    }
}