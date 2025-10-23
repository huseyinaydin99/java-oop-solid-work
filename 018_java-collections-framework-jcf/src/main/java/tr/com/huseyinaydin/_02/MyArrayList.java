package tr.com.huseyinaydin._02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MyArrayList {

    public static void main(String[] args) {
        List<String> listStudent = new ArrayList<String>();
        listStudent.add("Hüseyin");
        listStudent.add("Beyhan");
        listStudent.add("Erdinç");
        //listStudent.add(null);
        listStudent.add("Veli");
        listStudent.add("Selami");
        //listStudent.add(null);
        listStudent.add("Lavuk");
        //listStudent.add(null);
        //listStudent.add("");

        System.out.println(listStudent);
        System.out.println("----remove----"); //null temizliği
        for (int i = 0; i < listStudent.size(); i++) {
            if (listStudent.get(i) == null) { //null mı?
                listStudent.remove(i); //null sa o anki değer yok et!
            }
            System.out.println(listStudent.get(i));
        }

        System.out.println("--------------");
        Collections.sort(listStudent);
        System.out.println(listStudent);

        System.out.println("--FOR----------");
        for (int i = 0; i < listStudent.size(); i++) {
            System.out.println(listStudent.get(i));
        }

        System.out.println("--FOR-EACH---------");
        for (String student : listStudent) {
            System.out.println(student);
        }

        System.out.println("---Lambda-----------");
        listStudent.forEach(System.out::println);

        System.out.println("--Lambda arrow-----------");
        listStudent.forEach(student -> System.out.println(student));

        System.out.println("--------------");
        Iterator<String> iterator = listStudent.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("--Iterator null temizliği için------------");
        Iterator<String> iterator2 = listStudent.iterator();
        String arananKelime;
        while (iterator2.hasNext()) {
            arananKelime = iterator2.next();

            if (arananKelime == null || arananKelime == "") {
                iterator2.remove();
            }
            System.out.println(listStudent);
        }
    }
}