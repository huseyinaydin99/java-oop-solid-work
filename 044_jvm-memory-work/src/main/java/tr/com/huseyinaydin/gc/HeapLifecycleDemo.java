package tr.com.huseyinaydin.gc;

public class HeapLifecycleDemo {

    /*
        new Person("Ali") ile oluşturulan nesne Heap'te yaşarken person1 ve person2
        Stack'te bulunan iki ayrı reference olarak aynı nesneyi gösterir; yani
        iki reference olması iki ayrı nesne olduğu anlamına gelmez. 🔗
    */

    public static void main(String[] args) {

        Person person1 = new Person("Ali");

        Person person2 = person1;

        System.out.println(person1.name);
        System.out.println(person2.name);

        person1 = null;
        person2 = null;
        /*
        Garbage Collector (GC), Heap üzerinde artık program tarafından erişilemeyen (unreachable)
        nesneleri tespit edip onların kullandığı belleği otomatik olarak geri kazanan JVM
        mekanizmasıdır. ♻️ Böylece Java’da nesnelerin belleğini C++’taki gibi manuel olarak
        free/delete ile yönetmek gerekmez; bellek yönetiminin önemli bir kısmı JVM’ye bırakılır.
        🧠 Ancak GC’nin ne zaman çalışacağı, ne kadar bellek temizleyeceği veya belirli bir
        nesneyi hemen toplayacağı garanti değildir; JVM bunu çalışma koşullarına göre kendisi belirler. ⚙️
        */

        System.gc();
        /*
        System.gc() JVM'ye Garbage Collection çalıştırmayı denemesini önerir, ancak GC'nin
        gerçekten o anda çalışacağını veya belirli bir nesneyi temizleyeceğini garanti etmez. ♻️🧠
        */
    }

    /*
    Önce person1, ardından person2 null olduğunda Heap'teki Person nesnesine ulaşan güçlü
    reference kalmaz ve nesne unreachable(uzay boşluğunda kaybolmuş o yüzden takip edilemez,
    erişilemez) hale gelir; System.gc() GC'yi çalıştırmayı
    garanti etmez, yalnızca JVM'ye GC talebinde bulunur. ♻️🧠
    */

    static class Person {

        String name;

        Person(String name) {
            this.name = name;
        }
    }
}