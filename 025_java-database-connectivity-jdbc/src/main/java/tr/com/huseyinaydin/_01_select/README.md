#### JDBC Nedir? Ne Değildir? 🤝💾

Java tarafında geliştirme yaparken veritabanı ile iletişim kurmam gerektiğinde elimdeki en temel, en çıplak ve en doğrudan araç JDBC (Java Database Connectivity) oluyor. JDBC, programım ile veritabanı arasında bir köprü görevi üstleniyor ve benim yazdığım SQL ifadelerini veritabanına iletip, dönen sonuçları işlememe imkan tanıyor. Yani kısaca, Java ile PostgreSQL, MySQL, Oracle, MSSQL veya başka herhangi bir veritabanı arasında standart bir konuşma dili sağlıyor. Eğer JDBC olmasaydı, her veritabanının kendine özgü sürücü yapısı ve bağlantı yöntemiyle ayrı ayrı uğraşmak zorunda kalırdım ve bu durum hem yazılımın karmaşıklığını artırır hem de projeyi uzun vadede sürdürülemez hale getirirdi. Bu nedenle JDBC, yazılım geliştirmede geliştiricinin ellerine kontrolün tamamını veren, doğrudan ve yalın bir temel altyapı sunuyor. 🧱

##### Neden JDBC Kullanılır? 🎯

>JDBC, veriyi yönetmenin temel mantığını öğretir.

Veritabanı nasıl çalışır, sorgu nasıl optimize edilir, bağlantı nasıl açılır, kaynaklar nasıl kapatılır, veri satır satır nasıl okunur — bunların hepsi JDBC ile bizzat görülerek öğrenilir.

Bir yazılımcı ORM kullanmadan önce mutlaka JDBC öğrenmelidir; çünkü temeli bilmeyen biri ORM kullandığında bir sorun çıktığında çözemez, nerede ne döndüğünü, neden döndüğünü anlayamaz ve dışarıdan bakar hale gelir. JDBC seni bilgiye mahkum değil, bilgiye hakim olan tarafa geçirir. 💡

##### Avantajları ✅

- Her işlemin kontrolü sende olduğu için en ince ayrıntıya kadar müdahale edebilirsin.
- Framework bağımlılığı yoktur; proje ne kadar sade olursa o kadar net ilerler.
- Performans kritik uygulamalarda, doğru yönetildiğinde son derece hafif ve hızlıdır.

##### Dezavantajları ❌

- Kod tekrarı fazladır; connection, statement, resultset kapatma rutinleri sürekli tekrar eder.
- Modelleme, ilişki yönetimi, cache, lazy-loading gibi üst düzey kolaylıkları doğal olarak sunmaz.
- Proje büyüdükçe sorguların yönetimi ve okunabilirliği zorlaşabilir.

##### JDBC İçinde Üç Ana Yapı: Connection – Statement – ResultSet 🔗📄📦
| 🧩 **Yapı**     | 💡 **Görevi** |
|------------------|---------------|
| **Connection**   | Veritabanına açılan kapı gibidir; bu kapı olmadan konuşma başlayamaz. |
| **Statement**    | Açılan kapı üzerinden SQL komutlarını gönderir ve işlem yapılmasını sağlar. |
| **ResultSet**    | Sorgu sonucunda dönen veriyi satır satır ve kolon kolon okumamı sağlayan yapı. |

>Bu üçü, aynı masada oturan üç oyuncu gibidir.
Biri olmadan diğeri iş yapamaz, her adım birbirine bağlı olarak yürür. 🎼

##### ORM ile JDBC Arasındaki Fark 🔁 vs ⚙️

| ⚙️ **Özellik** | 💻 **JDBC** | 🧱 **ORM (Hibernate, JPA vb.)** |
|----------------|-------------|----------------------------------|
| **Yaklaşım** | SQL'i ben yazarım ve tamamen kontrol bendedir | SQL'i gizler, nesne modeli üzerinden işlem yapılır |
| **Esneklik** | Her sorguyu istediğim gibi yönetebilirim | Kolaydır ama bazı durumlarda otomasyon engel olabilir |
| **Öğrenme** | Başlangıçta daha kolay, fakat tekrar barındırır | Başlangıcı zordur fakat uzun vadede konfor sağlar |
| **Kullanım Amacı** | Tam hakimiyet ve ince ayar gereken durumlar | İş geliştirme hızının ve düzenliliğin önde olduğu durumlar |

- JDBC = “Her şeyi ben bilirim, ben yönetirim, gerekirse ince ayar çekerim.”
- ORM = “Veri zaten nesne, bana SQL detaylarını göstermeden hallet gitsin.”

```java
package tr.com.huseyinaydin._01_select;

import java.sql.*;

public class MySqlConnection {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/huseyin_aydin_db",
                    "root",
                    "toor");

            statement = connection.createStatement();

            String sql1 = "SELECT * FROM musteriler";
            String sql2 = "SELECT * FROM musteriler";
            String sql3 = "SELECT * FROM musteriler WHERE yasi > 25";

            resultSet = statement.executeQuery(sql1);

            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt("id") + "  " +
                                resultSet.getString("adi") + "  " +
                                resultSet.getString("soyadi") + "  " +
                                resultSet.getString("sehir") + "  " +
                                resultSet.getInt("yasi"));
                System.out.println("-------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Hata: " + e);
        } finally {
            if (resultSet != null || statement != null || connection != null) {
                try {
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
```

```java
package tr.com.huseyinaydin._01_select;

import java.sql.*;

public class MyPostgreSqlConnection {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "toor");

            statement = connection.createStatement();

            String sql1 = "SELECT * FROM public.musteriler";
            String sql2 = "SELECT * FROM mycompanydb.musteriler";
            String sql3 = "SELECT * FROM public.musteriler WHERE yasi > 25";

            resultSet = statement.executeQuery(sql3);

            while(resultSet.next()){
                System.out.println(
                        resultSet.getInt("id") + "  " +
                        resultSet.getString("adi") + "  " +
                        resultSet.getString("soyadi") + "  " +
                        resultSet.getString("sehir") + "  " +
                        resultSet.getInt("yasi"));
                System.out.println("-------------------------------------");
            }
        } catch (Exception e){
            System.out.println("Hata: " + e);
        } finally {
            if(resultSet != null || statement != null || connection != null){
                try {
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
```

##### JDBC Finally Kullanımı Amacı;
**finally** bloğu JDBC kodlarında, ister başarı ister hata olsun bağlantı kaynaklarının mutlaka kapatılmasını garanti eden son savunma hattı gibi çalışır; yani ResultSet, Statement ve Connection gibi veritabanıyla iletişim kuran nesneler bellek sızıntısı oluşturmadan her koşulda düzgünce serbest bırakılır 🤝🔒. Bunu yapmak, hem uygulamanın stabil kalmasını sağlar hem de "açık kalan bağlantı" gibi uzun vadede sistemi sessizce çökerten görünmez hataların önüne geçerek, yazılımcıya temiz iş yapmanın iç huzurunu verir 😌🔥.

> **Killa Hakan;** Dalma, dalana dalga, eli salla, Allah’a emanet ol ve hadi yalla! 🤲✨🚀