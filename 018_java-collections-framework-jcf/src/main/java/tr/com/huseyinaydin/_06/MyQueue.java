package tr.com.huseyinaydin._06;

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
    }
}