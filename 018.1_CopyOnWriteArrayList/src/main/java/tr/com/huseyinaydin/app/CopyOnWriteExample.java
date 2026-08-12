package tr.com.huseyinaydin.app;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteExample {
    public static void main(String[] args) {

        CopyOnWriteArrayList<String> users = new CopyOnWriteArrayList<>();

        users.add("Hüseyin");
        users.add("Ahmet");
        users.add("Mehmet");

        for (String user : users) {

            System.out.println("Okunan kullanıcı: " + user);

            if (user.equals("Ahmet")) {
                users.add("Ayşe");
            }
        }

        System.out.println("Son liste: " + users);

        /*
        🔑 Çok Önemli nokta: Ayşe, mevcut for-each döngüsüne dahil olmaz; çünkü iterator
        oluşturulduğunda kullanılan array değişmez, ekleme ise listenin yeni bir array
        kopyası üzerinde gerçekleşir. 📦 Bu nedenle CopyOnWriteArrayList, çok okuma + az yazma
        senaryolarında thread-safe koleksiyon olarak özellikle değerlidir.
         */

        /*
        ArrayList mevcut yapı üzerinde değişiklik yaptığı için eklenen eleman iterasyonun
        davranışını bozabilirken, CopyOnWriteArrayList her yazmada yeni bir array kopyası
        oluşturur. 🔄 Böylece mevcut iterator eski array üzerinde çalışmaya devam eder;
        yeni eklenen eleman mevcut iterasyona dahil olmaz. 🧠
         */
    }
}
