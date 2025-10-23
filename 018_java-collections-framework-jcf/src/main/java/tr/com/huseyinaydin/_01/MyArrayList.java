package tr.com.huseyinaydin._01;

import java.util.ArrayList;
import java.util.List;

public class MyArrayList {

    public static void main(String[] args) {
        /*
        int myNumber1 = 10;
        int myNumber2 = 20;
        int myNumber3 = 30;
        int [] myNumbers = { 10, 20, 30 };  //3 items, eleman
        */

        /*
        List<Integer> myList;  //nesne referansi tutabilen bir değişken stack alanında tanımlanır.

        //nesne heap alanında var edildi bellek referansı yani adresi değişkenimize atandı.
        myList = new ArrayList<Integer>();
        */

        List<Integer> myList1 = new ArrayList<>();

        myList1.add(10);   // index 0
        myList1.add(5);    // index 1
        myList1.add(25);   // index 2
        myList1.add(13);   // index 3
        myList1.add(null); // index 4
        //myList1.add("Ali"); //hata başta Integer belirttik ama String girdik kabul edilemez!

        System.out.println(myList1);

        System.out.println("---------------------------------");

        ArrayList<String> myList2 = new ArrayList<>();
        myList2.add("Hüseyin");
        myList2.add("Ahmet");
        myList2.add("Şakir");
        myList2.add(null);
        myList2.add("Veli");
        myList2.add("Selami");
        myList2.add("Hamit");

        System.out.println(myList2);

        System.out.println(myList2.get(4));
        //System.out.println(myList2.getFirst());
        //System.out.println(myList2.getLast());
        System.out.println(myList2.contains("Omer")); //false
        System.out.println(myList2.contains("Veli")); //true

        System.out.println("List1 ile List2 eşit midir? -->  " + myList1.equals(myList2));

        System.out.println("List1 eleman sayısı: " + myList1.size());
        System.out.println("List2 eleman sayısı: " + myList2.size());

        myList2.add("Sefa");
        System.out.println("List2 eleman sayısı: " + myList2.size());
        System.out.println(myList2);

        myList2.remove(3);
        System.out.println(myList2);
        System.out.println("List2 eleman sayısı: " + myList2.size());
        System.out.println(myList2.hashCode());

        myList2.remove(5);
        System.out.println("List2 eleman sayısı: " + myList2.size());
        System.out.println(myList2);
        System.out.println(myList2.hashCode());

        //System.out.println("List1 eleman sayısı: "+ myList1.length());

        /*
        String adi = "Koray";
        System.out.println(adi.length());

        Integer[] myNumbers = { 10, 20, 30 };  // 3 items, eleman
        System.out.println(myNumbers.length);
        */

        System.out.println("List2 dolu mu? : " + !myList2.isEmpty());

        myList2.add(1, "Süleyman");
        System.out.println(myList2);
        System.out.println(myList2.hashCode());

        myList2.remove(1);
        System.out.println(myList2);
        System.out.println(myList2.hashCode());

        myList2.clear();
        System.out.println(myList2);
        System.out.println(myList2.hashCode());
    }
}