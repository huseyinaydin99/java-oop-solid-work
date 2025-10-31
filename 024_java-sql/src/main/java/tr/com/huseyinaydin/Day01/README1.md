#### 🗄️ MySQL Veritabanı Tasarımı ve Yönetimi 🌐

Merhaba! 👋 Bu projede **MySQL kullanarak bir şirket veritabanı tasarladım ve yönetiyorum**. Kodda, veritabanı ve tablolar oluşturuluyor, veri ekleniyor, tablolar üzerinde değişiklikler yapılıyor ve sonunda sorgulamalarla içerik kontrol ediliyor. SQL’in her bir cümlesi, yalnızca veri depolamak değil, aynı zamanda **veri bütünlüğünü, düzeni ve sürdürülebilirliği sağlamak** amacıyla kullanılıyor. Eğer bu adımlar atlanırsa, veri yönetimi kaotik hâle gelir, hatalar oluşur ve büyük veri kayıpları yaşanabilir. SQL kullanımı bir programcıya veri yönetiminde güçlü bir kontrol ve esneklik kazandırır. 💾 Büyük veri kümelerini hızlıca sorgulayıp analiz etme yeteneği sağlar, böylece uygulamalarda veri odaklı kararlar almak kolaylaşır. 🚀 Veritabanı ile program arasında etkin iletişim kurarak veri bütünlüğünü ve performansını artırır. 🔒 Ayrıca, SQL bilgisi sayesinde farklı veri tabanı sistemlerine kolay uyum sağlamak ve karmaşık veri operasyonlarını otomatikleştirmek mümkün olur. 🛠️ Dezavantajı ise yanlış kullanıldığında geri dönüşü olmayan kayıplara yol açmasıdır. Bu dosya, hem eğitim hem de uygulamalı proje için derin ve eksiksiz bir örnek teşkil ediyor. 🚀💾

---

```sql
-- VERITABANINI OLUSTURYORUZ
CREATE SCHEMA `my_company_db` DEFAULT CHARACTER SET utf32 COLLATE utf32_bin;
--🌐 my_company_db adında yeni bir veritabanı şeması oluşturur,
--🆗 karakter kümesini UTF-32 olarak ayarlar ve ⚖️ karşılaştırma sıralamasını binary düzeyde hassas ve büyük/küçük harf duyarlı yapar.

-- MUSTERILER TABLOSU
CREATE TABLE `my_company_db`.`customers` (
    `ID` INT NOT NULL AUTO_INCREMENT,
    `FIRST_NAME` VARCHAR(60) NULL,
    `LAST_NAME` VARCHAR(60) NULL,
    PRIMARY KEY (`ID`)
);
--🏢 customers tablosu oluşturulur, 🆔 ID sütunu otomatik artan birincil anahtar olur,
--📝 FIRST_NAME ve LAST_NAME 60 karaktere kadar veri saklar ve boş bırakılabilir, kayıtlar düzenli ve yönetilebilir şekilde depolanır.

-- URUNLER TABLOSU
CREATE TABLE `my_company_db`.`products` (
    `ID` INT NOT NULL AUTO_INCREMENT,
    `PROD_NAME` VARCHAR(200) NULL,
    `PROD_CODE` VARCHAR(45) NULL,
    PRIMARY KEY (`ID`)
);
--🏢 products tablosu oluşturulur, 🆔 ID otomatik artan birincil anahtar,
--📝 PROD_NAME ve PROD_CODE ürün bilgilerini tutar, boş bırakılabilir ve veri yönetimi güvenli hale gelir.

-- TABLO UZERINDE DEGISIKLIK YAPAR
ALTER TABLE `my_company_db`.`products`
    CHANGE COLUMN `PROD_Code` `PROD_CODE` VARCHAR(45) NULL DEFAULT NULL;
--🔧 PROD_Code sütunu PROD_CODE olarak yeniden adlandırılır, veri tipi korunur,
--📝 NULL değer kabul edilir ve tablo yapısında tutarlılık sağlanır.

-- URUNLER TABLOLSUNU SILER
-- DROP TABLE `mycompany`.`products`;
--⚠️ products tablosunu tamamen siler, geri dönüşü yoktur, dikkatli kullanılmalıdır.

-- VERITABINI SILER
-- DROP DATABASE `my_company_db`;
--⚠️ my_company_db veritabanını ve içindeki tüm tabloları, verileri ve ilişkileri siler, mutlak dikkat gerektirir.

-- MUSTERI TABLOSUNA KAYITLAR EKLENDI
INSERT INTO `my_company_db`.`customers` (`FIRST_NAME`, `LAST_NAME`) VALUES ('Hüseyin', 'AYDIN');
INSERT INTO `my_company_db`.`customers` (`FIRST_NAME`, `LAST_NAME`) VALUES ('Hasan', 'Fefer');
INSERT INTO `my_company_db`.`customers` (`FIRST_NAME`, `LAST_NAME`) VALUES ('Müstakime', 'Sütçü');

-- MUSTERILERI LISTELE GOSTER
SELECT * FROM `my_company_db`.`customers`;
--🧐 customers tablosundaki tüm sütun ve kayıtları seçer ve ekrana getirir, tablo içeriğini inceleme ve doğrulama imkanı sağlar.
