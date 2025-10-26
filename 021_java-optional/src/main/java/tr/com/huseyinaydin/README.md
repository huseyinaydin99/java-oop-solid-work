#### 🌟 Java Optional Nedir, Neden Kullanılır? 🌟

Java dünyasında `Optional` adeta bir süper kahraman gibi! 🦸‍♂️ Null değerlerle uğraşırken bizi NullPointerException 🛑 tuzağından kurtarıyor. `Optional`, bir değerin **var mı yok mu** olduğunu açıkça ifade eden bir kapsayıcıdır. 🤓 Normalde bir değişken `null` olabiliyor ve her seferinde `if (obj != null)` kontrolleri yapmak zorunda kalıyoruz. İşte `Optional`, bu kontrolleri daha okunabilir, güvenli ve modern bir şekilde yapmamıza olanak sağlar. 🎯 Ana amacı net: **null güvenli kod yazmak ve programcıya kafa karışıklığı yaratmadan “var/yok” durumunu göstermek**. Eğer kullanılmazsa, null değerlerle çalışmak zorlaşır, hatalar artar ve kod okunabilirliği düşer. 😅

`Optional` şunlar için kullanılır:
- Metodun **null dönebileceğini** belirtmek 🌈
- Null kontrolü yapmayı **zorunlu ve okunabilir** hale getirmek 📖
- Daha **akıcı ve fonksiyonel** kod yazmak 🌀

##### Avantajları:
- NullPointerException riskini azaltır ✅
- Kod daha okunabilir ve anlaşılır olur 👓
- Fonksiyonel programlama ile kolay entegre edilir 💻

##### Dezavantajları:
- Gereksiz kullanımı performansı etkileyebilir 🐢
- Koleksiyonlar veya basit sınıf alanlarında aşırı kullanım kafa karıştırabilir 🤯

Kendi tecrübemden söylüyorum; Optional, null kontrolünü programcıdan alıp **sistemsel hale getiriyor**. Kullanmadığınızda her satırda “null mu değil mi?” diye uğraşırsınız, kod karmaşıklaşır. 😎

---

#####  💻 Örnek Kod

```java
package tr.com.huseyinaydin;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        // --- Null durumları ---
        Student student1 = new Student();
        System.out.println(student1);
        System.out.println("firstName: " + student1.getFirstName());

        Student student2 = new Student("İsa");
        System.out.println("ilk durum: " + student2);
        student2 = null;

        try {
            System.out.println("firstName: " + student2.getFirstName());
        } catch (Exception e) {
            System.out.println("Hata: " + e);
        }

        // --- Array null durumu ---
        String[] myStudentArr = new String[10];
        myStudentArr[0] = "Ahmet";
        String myFirstName = myStudentArr[0].toUpperCase();
        System.out.println("firstName: " + myFirstName);

        myStudentArr = null;
        try {
            for (int i = 0; i < myStudentArr.length; i++) {
                System.out.println(myStudentArr[i]);
            }
        } catch (Exception e) {
            System.out.println("Hata: " + e);
        }

        // --- Optional ile null kontrolü ---
        Optional<String[]> nullKontrol = Optional.ofNullable(myStudentArr);
        System.out.println("nullKontrol: " + nullKontrol);

        if (nullKontrol.isPresent()) {
            System.out.println("Dizi var. Dolu.");
        } else {
            System.out.println("Dizi null'dır ama ben onu sana boş diyeceğim.");
        }

        if (!nullKontrol.isEmpty()) {
            System.out.println("Dizi var. Dolu.");
        } else {
            System.out.println("Dizi null'dır ama ben onu sana boş diyeceğim.");
        }
    }
}
```

#####  💻 Örnek Kod Öğrenci Modeli

```java
package tr.com.huseyinaydin;

// Model Sınıf
public class Student {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Boolean registerStatus;

    public Student() {}
    public Student(String firstName) { this.firstName = firstName; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Boolean getRegisterStatus() { return registerStatus; }
    public void setRegisterStatus(Boolean registerStatus) { this.registerStatus = registerStatus; }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", registerStatus=" + registerStatus +
                '}';
    }
}
```

##### ⚡ Java Optional Fonksiyonları: map(), flatMap(), orElse(), orElseGet(), ifPresent() ⚡

Java Optional ile null güvenli kod yazmak sadece null kontrolü yapmakla bitmiyor. 🤓 Asıl gücü, **fonksiyonel metodlarla** ortaya çıkıyor. `map()`, `flatMap()`, `orElse()`, `orElseGet()` ve `ifPresent()` gibi metodlar, null olabilecek veriyi **zarif ve güvenli bir şekilde** dönüştürmemizi sağlıyor. 🌀

- `map()`: Optional içindeki değeri dönüştürür, null ise Optional boş kalır. 🔄
- `flatMap()`: map gibi ama zaten Optional olan değeri katmanlaştırmadan düzleştirir. 🔧 Diyelim ki bir öğrenci sınıfın var ve öğrencinin isim bilgisini Optional ile alıyoruz.  map() kullanırsak, Optional içine bir Optional daha sarılıyor. Yani Optional<Optional<String>> oluşuyor. 😵‍💫  flatMap() kullanırsak, bu ekstra katmanı kaldırıyor, direkt Optional<String> elde ediyoruz. ✅
- `orElse()`: Eğer değer yoksa **varsayılan bir değer** döndürür. 🎁 orElse("Varsayılan İsim"); gibi.
- `orElseGet()`: orElse gibi ama değer üretimini **lazy** yapar, yani sadece ihtiyaç olursa üretir performans sağlar. ⏱️ orElseGet("Varsayılan İsim")
- `ifPresent()`: “Optional içindeki değer varsa bunu kullan, yoksa hiç dokunma” demek. Yani null kontrolünü manuel yapmana gerek kalmaz. 🕵️‍♂️ nameOpt1.ifPresent(name -> System.out.println("İsim: " + name));

>Kendi tecrübemden söylüyorum; bu metodlar null kontrolünü **programcıdan alıp sistemsel hale getiriyor**, kodu hem okunabilir hem de güvenli yapıyor. 😎

---

##### 💻 Örnek Kod

```java
package tr.com.huseyinaydin;

import java.util.Optional;

public class OptionalExample {

    public static void main(String[] args) {

        Student student = new Student("Ahmet");

        // map() ile firstName'i büyük harfe çevir
        Optional<String> upperName = Optional.ofNullable(student.getFirstName())
                                             .map(String::toUpperCase);
        System.out.println("Upper Name: " + upperName.orElse("İsim yok 😅"));

        // flatMap() örneği: Optional ile Optional döndüren metod
        Optional<Student> studentOpt = Optional.ofNullable(student);
        Optional<String> flatName = studentOpt.flatMap(s -> Optional.ofNullable(s.getFirstName()));
        System.out.println("Flat Name: " + flatName.orElse("İsim yok 😎"));

        // orElse() ile varsayılan değer
        String lastName = Optional.ofNullable(student.getLastName())
                                  .orElse("Soyad yok 🤷‍♂️");
        System.out.println("Last Name: " + lastName);

        // orElseGet() ile lazy varsayılan değer
        String email = Optional.ofNullable(student.getEmail())
                               .orElseGet(() -> "email@default.com 📧");
        System.out.println("Email: " + email);

        // ifPresent() ile değer varsa yazdır
        Optional.ofNullable(student.getPhone())
                .ifPresent(phone -> System.out.println("Phone: " + phone));
        // Eğer null ise hiçbir şey yapmaz
    }
}
```

##### Map vs FlatMap
```java
import java.util.Optional;

class Student {
    private String firstName;
    public Student(String firstName) { this.firstName = firstName; }
    public Optional<String> getOptionalFirstName() {
        return Optional.ofNullable(firstName);
    }
}

public class FlattenOptionalExample {
    public static void main(String[] args) {

        Optional<Student> studentOpt = Optional.of(new Student("Ahmet"));

        // map() ile
        Optional<Optional<String>> nameMap = studentOpt.map(s -> s.getOptionalFirstName());
        System.out.println("map sonucu: " + nameMap);
        // Çıktı: Optional[Optional[Ahmet]]
        // -> Bir kutu daha var: Optional içinde Optional

        // flatMap() ile
        Optional<String> nameFlat = studentOpt.flatMap(s -> s.getOptionalFirstName());
        System.out.println("flatMap sonucu: " + nameFlat);
        // Çıktı: Optional[Ahmet]
        // -> Sadece tek seviye Optional, ekstra kutu yok ✅
    }
}
```
