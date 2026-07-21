package tr.com.huseyinaydin._06;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class MyQueue {

    public static void main(String[] args) {
        Queue<String> studentList1 = new ArrayBlockingQueue<>(25);
        studentList1.add("Hüseyin");
        studentList1.add("Beyhan");
        studentList1.add("Fahrettin");
        // studentList1.add(null); // Olmaz!!
        studentList1.add("Ahmet");
        studentList1.add("Selami");
        studentList1.add("Hamit");
        System.out.println(studentList1);

        System.out.println("----------------------");

        Queue<String> studentList2 = new PriorityQueue<>();
        studentList2.add("Selami");
        studentList2.add("Behlül");
        studentList2.add("Fahrettin");
        // studentList1.add(null); // Olmaz!!
        studentList2.add("Veli");
        studentList2.add("Selami");
        studentList2.add("Behlül");
        System.out.println(studentList2);

        Queue<String> studentList3 = new PriorityQueue<>(Comparator.reverseOrder());
        studentList3.add("Selami");
        studentList3.add("Behlül");
        studentList3.add("Fahrettin");
        // studentList3.add(null); // Olmaz!!
        studentList3.add("Veli");
        studentList3.add("Selami");
        studentList3.add("Behlül");
        System.out.println(studentList3);

        System.out.println("----------------------");

        while (!studentList3.isEmpty()) {
            System.out.println(studentList3.poll());
        }

        Queue<String> studentList4 = new PriorityQueue<>(Comparator.naturalOrder());
        studentList4.add("Selami");
        studentList4.add("Behlül");
        studentList4.add("Fahrettin");
        // studentList4.add(null); // Olmaz!!
        studentList4.add("Veli");
        studentList4.add("Selami");
        studentList4.add("Behlül");
        System.out.println(studentList4);

        System.out.println("----------------------");

        while (!studentList4.isEmpty()) {
            System.out.println(studentList4.poll());
        }

        if(studentList4.isEmpty()) System.out.println("kuyruk boştur boşalmıştır");
    }
}