package tr.com.huseyinaydin._05;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class MySet {
    public static void main(String[] args) {
        System.out.println("--- HashSet rastgele -------------");

        Set<String> studentList1 = new HashSet<String>(10);
        HashSet<String> studentList2 = new HashSet<String>(10);
        studentList2.add("Hüseyin");
        studentList2.add("Bekir");
        studentList2.add("Fahrettin");
        studentList2.add(null); // olur
        studentList2.add("Ahmet");
        studentList2.add("Selami");
        studentList2.add("Halit");

        System.out.println(studentList2);

        System.out.println("\n\n----TreeSet sıralı ve null olmaz!!!--------------");

        Set<String> studentList3 = new TreeSet<>();
        TreeSet<String> studentList4 = new TreeSet<>();

        studentList4.add("Hüseyin");
        studentList4.add("Bekir");
        studentList4.add("Fahrettin");
        studentList4.add(null); // olur
        studentList4.add("Ahmet");
        studentList4.add("Selami");
        studentList4.add("Halit");

        System.out.println(studentList4);

        System.out.println("\n\n---LinkedHashSet bizim girdigimiz sırada---------------");

        Set<String> studentList5 = new LinkedHashSet<>();
        LinkedHashSet<String> studentList6 = new LinkedHashSet<>();
        studentList6.add("Hüseyin");
        studentList6.add("Bekir");
        studentList6.add("Fahrettin");
        studentList6.add(null); // olur
        studentList6.add("Ahmet");
        studentList6.add("Selami");
        studentList6.add("Halit");

        System.out.println(studentList6);

        System.out.println("------------------");
    }
}