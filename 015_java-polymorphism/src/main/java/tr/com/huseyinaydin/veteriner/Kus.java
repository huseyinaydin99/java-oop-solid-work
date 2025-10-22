package tr.com.huseyinaydin.veteriner;

import tr.com.huseyinaydin.base.Hayvan;

public class Kus extends Hayvan {

    public Kus() {
        super();
        System.out.println("Kus");
    }

    @Override
    public void sesVer(){
        System.out.println("Kus : cik cik");
    }

    public void yemYe(){
        System.out.println("Kus : yemYe");
    }
}