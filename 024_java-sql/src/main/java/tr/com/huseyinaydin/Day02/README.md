#### 🌟 Veritabanı Çalışmam 💾⚔️

Bu çalışma, bir şirket yapısını temel alarak müşteri ve ürün verilerinin saklanabileceği bir veritabanının oluşturulması ve geliştirilmesini içerir 🏢📊. İlk olarak MySQL tarafında başlayan süreç, daha sonra PostgreSQL ortamına uyarlanarak genişletildi 🌐💥. Amaç; tablo oluşturma, tablo değiştirme, veri ekleme, güncelleme, filtreleme ve analiz işlemlerinin sıfırdan öğrenilmesi ve uygulanmasıdır 📚🛠️.

##### 1️⃣ Veritabanı Oluşturma 🏯

```sql
CREATE SCHEMA my_company_db AUTHORIZATION postgres;
```

* Bu komut, verileri rastgele değil belirli bir düzen‐alan içinde toplamak amacıyla, my_company_db adıyla bir mantıksal çerçeve (şema) oluşturur 📦🗂️ ve bu alanın yönetim yetkisini postgres kullanıcısına vererek veritabanı mimarisine disiplin ve sahiplik kazandırır 🛡️.
* AUTHORIZATION, oluşturulan şema veya nesnenin sahibinin kim olacağını belirleyerek o yapının üzerinde yetki ve kontrolün kime ait olduğunu tanımlar 🔑👑.

##### 2️⃣ Tabloların Oluşturulması 🏗️

##### 👥 Customers Tablosu

```sql
CREATE TABLE my_company_db.customers(
    "CUSTOMER_ID" serial PRIMARY KEY,
    "FIRST_NAME" character varying(70),
    "LAST_NAME" character varying(75),
    "AGE" smallint,
    "COUNTRY" character varying(65),
    "EMAIL" character varying(100),
    "GENDER" character varying(1)
);
```

* Bu tablo, müşteri verilerini tek bir yerde düzenli, tutarlı ve ilişkisel bir yapıda saklayabilmek için tasarlanmıştır 📊🧾; böylece sisteme eklenen her kayıt belirli bir anlam bütünlüğü içinde yer alır 🏅.
* CUSTOMER_ID birincil anahtar olarak her müşteriye kimlik kazandırır 🆔, yani sistemde hiç kimse diğerinin yerine geçemez ⚔️; veri bütünlüğü ve arama işlemlerinin hızı buradan doğar 🚀.
* Diğer alanlar (isim, yaş, ülke, e-posta, cinsiyet), müşteriyi çok boyutlu şekilde tanımlayarak veriyi yalnızca depolanan bilgi olmaktan çıkarır 📖🧬 ve analiz edilebilir bir profil haline getirir; böylece işletme kararlarının dayanağı haline gelir 🏛️💡.

##### 📦 Products Tablosu

```sql
CREATE TABLE my_company_db.products(
    "ID" bigserial PRIMARY KEY,
    "PROD_NAME" character varying(200),
    "PROD_CODE" character varying(45)
);
```

* Bu tablo, sistemde yer alan her bir ürünü tekil ve tanımlanabilir bir veri varlığı haline getirmek için oluşturulmuştur 🏷️🔒; böylece ürünler kaybolan satırlar değil, anlamlı nesneler olarak yönetilir 📦✨.
* ID sütunu bigserial olarak tanımlanmış birincil anahtar sayesinde her ürün, kendi benzersiz kimliğine sahip olur 🔑 ve veri tabanı içinde karışmadan, izlenebilir bir konumda durur 👀.
* PROD_NAME ve PROD_CODE alanları, ürünün hem insan tarafından anlaşılabilir hem de sistem tarafından referans alınabilir biçimde temsil edilmesini sağlayarak, ticari süreçlerde netlik, tutarlılık ve sorgulanabilirlik sağlar 📜📌.

##### 3️⃣ Veri Ekleme ✍️

```sql
INSERT INTO my_company_db.customers ("FIRST_NAME", "LAST_NAME") VALUES ('Hüseyin', 'AYDIN');
```

* Her kayıt, **disiplinli ve düzenli şekilde** tabloya eklenir 🖋️📥.

##### 4️⃣ Veri Sorgulama 🔍

```sql
SELECT COUNTRY, COUNT(*) AS Kisi_Sayisi
FROM my_company_db.customers
GROUP BY COUNTRY
ORDER BY Kisi_Sayisi DESC;
```

* GROUP BY, birbirine benzeyen satırları tek tek listelemek yerine, ortak bir niteliğe göre bir araya toplayarak veriyi daha yüksek bir anlam düzeyinde görmemizi sağlar 🧩📊; böylece tekil kayıtlarla uğraşmak yerine kümeler üzerinden konuşuruz 🗂️.
* Bu sorguda COUNTRY, kümelendirme eksenidir 🌍; sistem her ülkeyi ayrı bir kategori olarak kabul eder ve her kategoride kaç müşteri olduğunu sayarak ham veriyi özet bilgiye dönüştürür 🏛️📈.
* ORDER BY Kisi_Sayisi DESC ile en yoğun ülkeler en üstte görünür 📊⬆️; veri artık yalnızca kayıt değil, yorumlanabilir bir tabloya dönüşür 🧠💡.
* HAVING kullanılırsa, “müşteri sayısı 10’dan fazla olan ülkeler” gibi filtreleme yapılabilir ⚖️, böylece **özetlenmiş ve seçici veri analizi** sağlanır 🏯✨.

##### 5️⃣ Kazanımlar 🏆

* SQL sorgularını sadece yazmak değil, **neden öyle yazıldığını anlamak** ⚔️🧠.
* Veri modelleme mantığını öğrenme: tablo ilişkileri, birincil/ yabancı anahtar yapısı 📚🔗.
* MySQL ve PostgreSQL arasındaki farkları uygulamalı olarak deneyimleme 🌐💻.
* Gerçek iş senaryolarında veri temizleme, filtreleme, gruplama ve analiz pratiği 🧹📊.
* Performanslı sorgu yazma mantığına giriş: indeks kullanımı, sorgu maliyet analizi ⚡📈.
* HAVING ile gruplar üzerinde **analitik seçicilik** sağlamak, tekil veriden özet bilgiye geçmek ⚖️📌.

---

#### 🌐 Java ve SQL İlişkisi: Derinlemesine Anlatım

##### 🏁 Giriş: Java ile SQL’in Buluşması
Java ve SQL, yazılım dünyasının iki güçlü unsuru olarak birbirini tamamlayan iki farklı ama birbiriyle sıkı ilişkili teknolojidir. Java, nesne yönelimli bir programlama dili olarak veri işleme, mantık kurma ve kullanıcı ile etkileşim kurma görevlerinde üstündür. SQL ise veritabanı yönetim dili olarak veriyi depolama, sorgulama ve yönetme konusunda uzmanlaşmıştır. Java olmadan SQL’in gücü sınırlıdır, çünkü uygulama mantığı ve kullanıcı etkileşimi eksik olur. SQL olmadan Java veriyi kalıcı olarak saklayamaz, analiz edemez veya yönetemez. Birlikte kullanıldığında, Java uygulamaları veritabanı ile konuşabilir, veri alabilir, güncelleyebilir ve raporlayabilir. 💻🗄️

##### 🕰️ SQL Tarihçesi
SQL (Structured Query Language), 1970'lerin başında IBM’de Edgar F. Codd’un geliştirdiği ilişkisel model üzerine inşa edildi. 1974’te “SEQUEL” olarak başlayan bu dil, veri yönetiminde devrim yarattı ve 1986’da ANSI standartı olarak kabul edildi. SQL’in temel amacı, veritabanındaki verileri etkili ve hızlı bir şekilde sorgulamak, eklemek, güncellemek veya silmektir. Java ile entegrasyonu ise JDBC (Java Database Connectivity) sayesinde mümkün oldu. JDBC, Java’nın SQL veritabanlarıyla iletişimini sağlayan bir köprü görevi görür ve her SQL komutu Java üzerinden yönetilebilir hale gelir. 📜🔗

##### ⚙️ Java ve SQL’in Amacı ve Kullanım Alanları
- Java ve SQL’in birlikte kullanılması, uygulama geliştiricilere **veri odaklı çözümler** üretme gücü verir.
- Eğer SQL kullanılmazsa, Java uygulamaları yalnızca geçici bellekte veri tutabilir ve uygulama kapandığında tüm veriler kaybolur. ❌💾
- Java-SQL entegrasyonu, **kurumsal uygulamalar, bankacılık, e-ticaret, sosyal medya platformları** gibi büyük veri işleme gerektiren alanlarda olmazsa olmazdır.
- Bu entegrasyon, programcıya **veri bütünlüğünü sağlama, raporlama yapma, veri analizi ve otomatik veri güncellemeleri** yapabilme imkanı sunar. 🌎📊

#### 💎 Özellikleri, Avantajları ve Dezavantajları
#### Özellikler:
- SQL’in tablolar, satırlar ve sütunlar mantığı Java ile kolayca kullanılabilir.
- JDBC sayesinde Java, tüm popüler veritabanları ile uyumludur.
- Dinamik veri sorgulama ve veri yönetimi yapılabilir.

##### Avantajlar:
- Veri güvenliği ve bütünlüğü sağlar. 🔒
- Büyük veriyi yönetmek ve analiz etmek kolaydır. 📈
- Tekrar kullanılabilir kod ve sürdürülebilir sistemler yaratır. 🔄

##### Dezavantajlar:
- Veritabanı yönetimi için ekstra bilgi gerekir. 📚
- Büyük veri tabanlarında performans optimizasyonu gerekebilir. ⚡
- SQL hataları uygulama hatalarına yol açabilir. ⚠️

#### 🧩 Java-SQL İlişkisi ve Kod Örneği
Java’da SQL kullanımı genellikle **JDBC** aracılığıyla yapılır. Örneğin, PostgreSQL veya MySQL veritabanına bağlanıp müşteri ve ürün tablosu ile işlem yapabiliriz:

```sql
-- VERITABANINI OLUSTURUYORUZ
CREATE SCHEMA my_company_db DEFAULT CHARACTER SET utf32 COLLATE utf32_bin;

-- MUSTERILER TABLOSU
CREATE TABLE my_company_db.customers (
    ID INT NOT NULL AUTO_INCREMENT,
    FIRST_NAME VARCHAR(60) NULL,
    LAST_NAME VARCHAR(60) NULL,
    PRIMARY KEY (ID)
);

-- URUNLER TABLOSU
CREATE TABLE my_company_db.products (
    ID INT NOT NULL AUTO_INCREMENT,
    PROD_NAME VARCHAR(200) NULL,
    PROD_CODE VARCHAR(45) NULL,
    PRIMARY KEY (ID)
);

-- TABLOYA YENI KOLON EKLENDI
ALTER TABLE my_company_db.customers ADD COLUMN AGE SMALLINT(3) NULL;
ALTER TABLE my_company_db.customers ADD COLUMN COUNTRY VARCHAR(65) NULL;

-- MUSTERI EKLEME
INSERT INTO my_company_db.customers (FIRST_NAME, LAST_NAME, AGE, COUNTRY) 
VALUES ('Mahmut', 'Sakarya', 30, 'TR');

-- MUSTERI LISTELEME
SELECT * FROM my_company_db.customers;
```

##### Java ile JDBC kullanarak SQL'e Bağlanma! Çokta Bağlanmamak Lazım (:

```java
import java.sql.*;

public class DatabaseExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Huseyin_aydin_db";
        String user = "root";
        String password = "toor";
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM customers");
                
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("ID") +
                                       ", Name: " + rs.getString("FIRST_NAME") +
                                       ", Country: " + rs.getString("COUNTRY"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
```

##### 🔍 Derin Düşünce

Java ve SQL ilişkisi, sadece kod yazmak değil, veriyi yönetebilmek, analiz edebilmek ve sürdürülebilir sistemler kurabilmek anlamına gelir. Bu birliktelik, veri kaybını önler, hataları minimize eder ve uygulama mantığını veriye dayandırır. SQL, veriyi güvenle saklarken; Java, bu veriyi işleyip kullanıcıya sunar. Bu nedenle her modern yazılım geliştiricisi için Java-SQL ilişkisini anlamak ve doğru kullanmak kritik bir beceridir. 🏆💡

##### 🌟 Özet

- SQL, verinin düzenli, güvenli ve hızlı bir şekilde yönetilmesini sağlayan temel araçtır; Java ise bu veriyi işleyen, kullanıcı ile etkileşim kuran ve uygulama mantığını yöneten güçlü bir programlama dilidir. İkisi birlikte çalışmadığında veri ve uygulama bütünlüğü ciddi şekilde zarar görür; bu nedenle her ikisi de modern yazılım geliştirme süreçlerinde vazgeçilmezdir.

- JDBC, Java ile SQL veritabanları arasındaki iletişimi sağlayan kritik bir köprüdür; sorguların güvenli bir şekilde iletilmesini, sonuçların Java nesnelerine dönüştürülmesini ve uygulama mantığı ile veri yönetiminin senkronize çalışmasını mümkün kılar.

- SQL olmadan Java, verileri yalnızca geçici hafızada tutabilir ve uygulama kapandığında tüm bilgiler kaybolur; Java olmadan SQL ise sadece bir veri deposu olarak kalır, veriyi işleyip anlamlı hale getiremez ve kullanıcıya sunamaz. Bu iki teknoloji arasındaki uyum, veri bütünlüğünü, performansı ve sürdürülebilirliği doğrudan etkiler.

- Avantajları arasında veri güvenliği, hızlı ve etkin analiz imkânı, yeniden kullanılabilir kod yapısı ve sürdürülebilir sistemler geliştirme yer alır; dezavantajları ise başlangıçta öğrenme eğrisi, veritabanı optimizasyonu gerekliliği ve karmaşık uygulamalarda hata yönetim zorluklarıdır.

- Kod örnekleri sayesinde hem verinin oluşturulması, güncellenmesi ve yönetilmesi hem de sorgulanması pratik bir şekilde gösterilir; böylece programcı hem mantığı hem de veri kontrolünü tek bir akış içinde yönetebilir. 📊💻
