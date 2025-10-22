package tr.com.huseyinaydin;

import tr.com.huseyinaydin.base.Hayvan;
import tr.com.huseyinaydin.veteriner.Kedi;
import tr.com.huseyinaydin.veteriner.Kopek;
import tr.com.huseyinaydin.veteriner.Koyun;
import tr.com.huseyinaydin.veteriner.Kus;

public class AppMain {

    public static void main(String[] args) {
        Kedi kedi = new Kedi();
        kedi.sesVer();
        System.out.println("----------------------");

        Kopek kopek = new Kopek();
        kopek.sesVer();
        System.out.println("----------------------");

        Kus kus = new Kus(); //
        kus.sesVer();
        System.out.println("----------------------");

        Hayvan hayvan = new Hayvan();
        hayvan.hareketeGec();

        System.out.println("----------------------");

        Koyun koyun = new Koyun();

        if (koyun instanceof Hayvan) {
            System.out.println("Evet");
        } else {
            System.out.println("Hayır");
        }

        System.out.println("----------------------");

        Hayvan hayvan0bj;

        hayvan0bj = new Hayvan(); //run time polymorphism
       // System.out.println(hayvan0bj);

        hayvan0bj = new Kedi();
       // System.out.println(hayvan0bj);

        hayvan0bj = new Kopek();
       // System.out.println(hayvan0bj);

        hayvan0bj = new Koyun();
        System.out.println(hayvan0bj);

    }
}