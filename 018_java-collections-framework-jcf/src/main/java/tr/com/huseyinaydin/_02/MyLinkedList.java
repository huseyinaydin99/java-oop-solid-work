package tr.com.huseyinaydin._01;

import java.util.LinkedList;

public class MyLinkedList {

    public static void main(String[] args) {
        LinkedList<Integer> myList1 = new LinkedList();
        myList1.add(10);
        myList1.add(2);
        myList1.add(13);
        myList1.add(4);
        myList1.add(null);
        System.out.println(myList1);

        System.out.println("----------------");

        LinkedList<String> myList2 = new LinkedList();
        myList2.add("Hüseyin");
        myList2.add("Beyhan");
        myList2.add("Süleyman");
        myList2.add(null);
        myList2.add("Veli");
        myList2.add("Selami");
        myList2.add("Hasan");
        System.out.println(myList2);

        myList2.add(1, "Mahmut");
        myList2.set(0, "Ender");

        System.out.println(myList2);

        myList2.addFirst("Mehmet");
        myList2.addLast("Sarıçizmeli");
        System.out.println(myList2);

        System.out.println("---------------");

        for (int i = 0; i < myList2.size(); i++) {
            System.out.println(myList2.get(i));
        }
        System.out.println("---------------");

        // TEKIL COGUL
        for (String item : myList2) {
            // System.out.println( item);
            System.out.print(item + " - ");
        }

        System.out.println("---------------");
        System.out.println(myList2.get(0));
        System.out.println(myList2.getFirst());

        System.out.println("---------------");
        System.out.println(myList2.get(myList2.size() - 1));
        System.out.println(myList2.getLast());
    }
}