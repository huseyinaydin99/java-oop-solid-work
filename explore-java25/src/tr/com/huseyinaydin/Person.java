package tr.com.huseyinaydin;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java
 *
 */

class Person {
    String name;
    int age;

    Person(String name, int age) {
        System.out.println("Person/kişi constructor/yapıcı metodu called/çağrıldı/çalıştırıldı nesne hazır/object ready");
        this.name = name;
        this.age = age;
    }
}