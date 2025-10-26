package tr.com.huseyinaydin._01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        System.out.println("--Boş Kayıt/Akış--------------------------");
        Stream<String> bosKayit = Stream.empty();
        System.out.println(bosKayit.count());

        System.out.println("--Tek Bir Kayıt/Akış--------------------------");
        Stream<String> tekBirKayit = Stream.of("Hüseyin");
        System.out.println(tekBirKayit.count());

        System.out.println("--Çoklu Kayıt--------------------------");
        Stream<String> cokluKayit = Stream.of("Hüseyin", "Salih", "Cebrail");
        System.out.println(cokluKayit.count());

        System.out.println("----------------------------");

        List<String> myStudentList1 = new LinkedList<>();
        ArrayList<String> myStudentList2 = new ArrayList<>();
        myStudentList1.add("Ahmet");
        myStudentList1.add("Mehmet");
        myStudentList1.add("Necdet");
        System.out.println(myStudentList1);
        System.out.println("----------------------------");

        List<String> myStudentList3 = List.of("Veli", "Ali", "Selami");
        System.out.println(myStudentList3);

        for (String item : myStudentList3) {
            System.out.println(item);
        }

        System.out.println(myStudentList3);

        System.out.println("----LIST------------------------");

        List<String> myStudentList4 = new ArrayList<>(List.of("Hüseyin", "Ahmet", "Yasin", "Halit", "Cemil", "Vehmi", "Fehmi", "İsmail"));
        myStudentList4.add("Şakir");
        myStudentList4.add("Behlül");
        myStudentList4.remove("Mustafa");
        myStudentList4.remove(0);
        myStudentList4.add(1, "Nezir");

        System.out.println("----Stream------------------------");

        //Streamler tek kullanımlıktır!
        Stream<String> myStudentsStream = myStudentList4.stream();
        //System.out.println(myStudentsStream.count()); //8 olur.
        //System.out.println(myStudentsStream.count()); //Burada 8 olmaz! Çünkü Streamler tek kullanımlıktır! Stream akar, gider, biter.

        //myStudentsStream.forEach(System.out::println);
        myStudentsStream.forEach(ogrenci -> System.out.println(ogrenci));

        System.out.println("----ARRAY------------------");

        var myStudentsArr = new String[]{"Hüseyin", "Cemil", "Ozan", "Hamit", "Bayram", "Yunus", "Mahir", "Necip"};

        for (String student : myStudentsArr) {
            System.out.println(student);
        }
    }
}