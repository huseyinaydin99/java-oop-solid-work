package tr.com.huseyinaydin.app;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteIteratorExample {
    /*
        Bu aşamada aynı listenin iterator oluşturulduktan sonra değiştirilmesine rağmen
        iterator'ın neden eski snapshot'ı gördüğünü doğrudan test ediyoruz. 🔬 Ayrıca
        Iterator.remove() davranışını ayrı ele alarak, snapshot mantığının iterator
        üzerinden listeyi değiştirmeye izin vermediğini göreceğiz.
    */

    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> users =
                new CopyOnWriteArrayList<>();

        users.add("Hüseyin");
        users.add("Ahmet");
        users.add("Mehmet");

        Iterator<String> iterator = users.iterator();

        users.add("Ayşe");

        System.out.println("Güncel liste: " + users);

        System.out.println("Iterator:");

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        iterator = users.iterator();

        System.out.println("mevcut Haliyle Iterator: " + users + " Hala Eski Haliyle");

        try {
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
                iterator.remove(); // iterator.remove(); iterator üzerinden son döndürülen elemanı koleksiyondan silmeyi amaçlar, ancak CopyOnWriteArrayList iterator'ı snapshot tabanlı ve değişmez (immutable) olduğu için bu operasyonu desteklemez. 🔒
            }
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
            System.out.println(
                    "Iterator üzerinden remove desteklenmiyor."
            );
        }

        System.out.println("Son liste: " + users);
    }
}
