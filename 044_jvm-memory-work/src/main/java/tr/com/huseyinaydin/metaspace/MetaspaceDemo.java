package tr.com.huseyinaydin.metaspace;

public class MetaspaceDemo {
    public static void main(String[] args) {

        Person person = new Person("Ali");

        System.out.println(person.getName());
        System.out.println(Person.class.getName());
    }

    /*
    Person nesnesinin kendisi Heap'te bulunurken, Person sınıfının JVM tarafından
    yüklenmesiyle oluşan class metadata Heap'in dışında, Java 8 ve sonrasında
    kullanılan Metaspace alanında tutulur. 📚 Yani person bir nesneye, Person.class
    ise JVM'nin yüklediği sınıfın kendisini temsil eden Class nesnesine erişim
    sağlar; burada önemli ayrım nesnenin Heap'te, sınıf metadata'sının Metaspace'te bulunmasıdır. 🔍
    */

    static class Person {

        private final String name;

        Person(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }
    }

    /*
    Metaspace'in boyutu sabit bir Heap bölgesi değildir; ihtiyaç arttıkça native memory'den alan
    kullanabilir ve kullanılmayan sınıfların metadata'sı, ilgili ClassLoader artık canlı değilse
    GC sonrasında geri kazanılabilir. ♻️ Bu nedenle Metaspace'i anlamanın anahtarı, yalnızca
    “sınıflar burada tutulur” demek değil, class loading + ClassLoader yaşam döngüsü + native
    memory arasındaki ilişkiyi kavramaktır. ⚙️
    */
}
