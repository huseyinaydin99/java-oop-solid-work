package tr.com.huseyinaydin.app;

/*
    C++'da Pointer nedir? (Örnek olsun diye yazıyorum yoksa dersimiz zaten Java'dır).
    C++’ta pointer, başka bir değişkenin veya nesnenin bellekteki adresini tutan değişkendir;
    * ile o adresteki değere erişebilir, & ile bir değişkenin adresini alabilir ve böylece
    belleği doğrudan yönlendirebilirsin. 🧠📍
*/


/*
C++'da Java'dan farklı olarak stack'da bulunan bir değişkenin bellek adresini tutabilir mi?
Evet; C++’ta Stack’teki bir değişkenin adresi pointer ile tutulabilir (int* p = &x),
Java’da ise bellek adreslerine doğrudan erişim ve bunları pointer gibi saklama imkânı yoktur. 🧠📍

Java'da stack'da bulunan bir değişkenin bellek adresini yine stack'da bulunan başka bir değişken elinde tutabilir mi?
Galiba sadece heap'de kiler Java'da tutulabiliyor değil mi?
Hayır; Java’da Stack’teki bir değişkenin bellek adresini başka bir değişkende tutamazsın, çünkü Java pointer/address seviyesinde erişim sunmaz; Java’daki reference’lar ise nesnelere (genellikle Heap’te bulunan nesnelere) erişimi temsil eder. 🧠📍

C++'da stack'da ki bir değişken yine stack'daki bir değişkenin bellek adresini
tutabilir yine o değişkende yine stack'daki başka bir değişkenin
bellek adresini tutabilir o da yine aynı şekilde, bu duruma pointerin pointeri denilir adeta tren yapıyorlar. (:
Aynen 😄 C++’ta Stack’teki bir değişken başka bir Stack değişkeninin adresini tutabilir
ve bu zincir pointer → pointer → pointer şeklinde uzatılabildiği için buna pointer
to pointer (int**) denir; resmen bellek üzerinde tren kuruyorlar. 🚂🧠 Töwbe töwbe

Java’da pointer ve pointer-to-pointer (int**) gibi bir durum olmadığı için Stack’teki bir
değişkenin -> başka bir değişkenin bellek adresini tutması şeklinde C++’daki bu zincir kurulamaz. 🧠🚫
*/

public class MemoryDemo {

    public static void main(String[] args) {

        int number = 42;                    // Stack
        Person person = new Person("Ali");  // Heap + Stack reference

        printPerson(person);
    }

    private static void printPerson(Person person) {
        int age = 30;                      // Stack

        System.out.println(person.name);
        System.out.println(age);
    }

    static class Person {

        String name;                       // Heap

        Person(String name) {
            this.name = name;
        }
    }
}
/*
+--------------------------------------------------------------+
|                    JVM MEMORY - STACK / HEAP                 |
+--------------------------------------------------------------+
|                                                              |
|  STACK (Thread'e özel)              HEAP (Paylaşımlı)        |
|  -------------------------          ------------------------ |
|                                                              |
|  main()                             +----------------------+ |
|  +-----------------------+          | Person               | |
|  | number = 42           |          | name = "Ali"         | |
|  | person --------------+|--------->| age  = 30            | |
|  +-----------------------+          +----------------------+ |
|             |                                ^               |
|             | method call                    |               |
|             v                                |               |
|  printPerson()                               |               |
|  +-----------------------+                   |               |
|  | person ---------------+-------------------+               |
|  | local = 10            |                                   |
|  +-----------------------+                                   |
|                                                              |
+--------------------------------------------------------------+
| STACK                           | HEAP                      |
| - Method çağrıları burada yaşar | - Nesneler burada yaşar    |
| - Local değişkenler burada      | - Instance field'lar       |
| - Reference burada tutulabilir  | - GC tarafından yönetilir  |
| - Her thread'in Stack'i ayrıdır | - Thread'ler ortak erişir  |
| - LIFO mantığıyla çalışır       | - Boyutu dinamik olabilir  |
+--------------------------------------------------------------+
|                                                              |
|  "person" nesnenin kendisi DEĞİL, Heap'teki nesneye          |
|  ulaşmayı sağlayan Stack üzerindeki reference'tır.           |
|                                                              |
|  method() -> yeni Stack Frame -> local/reference             |
|           -> method biter -> Frame Stack'ten çıkar           |
|                                                              |
|  Heap nesnesine ulaşan hiçbir reference kalmazsa             |
|  nesne GC için erişilemez (unreachable) hale gelir.          |
|                                                              |
|  ÖZET:  Stack = çağrı bağlamı     Heap = nesne yaşam alanı   |
+--------------------------------------------------------------+
*/