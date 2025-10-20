package tr.com.huseyinaydin;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java
 *
 */
/*
Bu kodda Employee sınıfı Person sınıfından türemiş ve Java 25 ile birlikte constructor’da super çağrısı öncesinde
kod yazmaya izin veriliyor, bu sayede yaş kontrolü gibi mantıklar constructor başında yapılabiliyor. super(name, age)
ile üst sınıfın constructor’ı çağrılıyor ve ardından Employee yapıcı metodu tamamlanıyor, böylece alt sınıfın
başlatılması ve ön koşul kontrolleri daha esnek ve anlamlı hale geliyor.
 */
class Employee extends Person{

    Employee(String name, int age) {
        //super eskiden burada kullanılabilirdi. super'in üzerinde kod yazılamazdı! Nesne yani üst sınıf/super/base sınıf kod çalıştırdıktan sonrada hazırlanabilir.
        if(age > 18) {
            System.out.println("Çalışmak için yaşı yeterli");
        }
        super(name, age); //ama artık burada
        System.out.println("Employee yapıcı metodu çalıştırıldı!");
    }

}
//(JEP 506) :