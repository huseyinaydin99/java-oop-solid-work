-- VERITABANINI OLUSTURYORUZ
CREATE SCHEMA `my_company_db` DEFAULT CHARACTER SET utf32 COLLATE utf32_bin;
--Bu SQL cümlesi, 🌐 my_company_db adında yeni bir veritabanı şeması oluşturur,
--🆗 karakter kümesini UTF-32 olarak ayarlar ve ⚖️ karşılaştırma sıralamasını (COLLATE utf32_bin) binary
--düzeyde hassas ve büyük/küçük harf duyarlı yaparak, metin verilerinin
--💾 tam doğrulukla ve uluslararası uyumlulukla saklanmasını garanti eder.

--  MUSTERILER TABLOSU
CREATE TABLE `my_company_db`.`customers` (
    `ID` INT NOT NULL AUTO_INCREMENT,
    `FIRST_NAME` VARCHAR(60) NULL,
    `LAST_NAME` VARCHAR(60) NULL,
    PRIMARY KEY (`ID`));
--Bu SQL cümlesi, 🏢 my_company_db veritabanında customers adında bir tablo oluşturur,
--🆔 ID sütunu otomatik artan ve benzersiz birincil anahtar olarak tanımlanır,
--📝 FIRST_NAME ve LAST_NAME sütunları 60 karaktere kadar metin saklayabilir
--ve boş bırakılabilir, böylece müşteri kayıtlarının benzersiz, düzenli ve
--yönetilebilir bir şekilde depolanmasını sağlar.


-- URUNLER TABLOSU
CREATE TABLE `my_company_db`.`products` (
    `ID` INT NOT NULL AUTO_INCREMENT,
    `PROD_NAME` VARCHAR(200) NULL,
    `PROD_CODE` VARCHAR(45) NULL,
    PRIMARY KEY (`ID`));
--Bu SQL cümlesi, 🏢 my_company_db veritabanında products adında bir tablo oluşturur,
--🆔 ID sütunu otomatik artan ve benzersiz birincil anahtar olarak tanımlanır, 📝
--PROD_NAME 200 karaktere kadar ürün adını, PROD_CODE 45 karaktere kadar
--ürün kodunu saklayabilir ve boş bırakılabilir, böylece ürün kayıtlarının benzersiz,
--düzenli ve yönetilebilir bir şekilde depolanmasını sağlar.

-- TABLO UZERINDE DEGISIKLIK YAPAR
ALTER TABLE `my_company_db`.`products`
    CHANGE COLUMN `PROD_Code` `PROD_CODE` VARCHAR(45) NULL DEFAULT NULL;
--Bu SQL cümlesi, 🔧 my_company_db veritabanındaki products tablosunda PROD_Code
--adlı sütunu PROD_CODE olarak yeniden adlandırır, 📝 veri tipini VARCHAR(45)
--olarak korur, boş değer kabul edebilir ve varsayılan değerini NULL olarak ayarlar,
--böylece tablo yapısında tutarlılık ve veri bütünlüğü sağlanır ve kodlama standartlarına uyum garantilenir.


-- URUNLER TABLOLSUNU SILER
-- DROP TABLE `mycompany`.`products`;
--Bu SQL cümlesi, ⚠️ mycompany veritabanındaki products tablosunu tamamen siler,
--böylece tabloya ait tüm veri, yapı ve ilişkiler geri alınamaz şekilde kaldırılır,
--bu nedenle kullanılırken dikkatli olunması ve yedek alınması şarttır.

-- VERITABINI SILER
-- DROP DATABASE `my_company_db`;
--Bu SQL cümlesi, ⚠️ my_company_db veritabanını ve içindeki tüm tablolar, verileri
--ve ilişkileri tamamen siler, böylece geri dönüşü olmayan bir işlemle tüm veritabanı
--yapısını ortadan kaldırır, bu yüzden kullanılırken mutlak dikkat ve yedekleme şarttır.

-- MUSTERI TABLOSUNA KAYITLAR EKLENDI
INSERT INTO `my_company_db`.`customers` (`FIRST_NAME`, `LAST_NAME`) VALUES ('Hüseyin', 'AYDIN');
INSERT INTO `my_company_db`.`customers` (`FIRST_NAME`, `LAST_NAME`) VALUES ('Hasan', 'Fefer');
INSERT INTO `my_company_db`.`customers` (`FIRST_NAME`, `LAST_NAME`) VALUES ('Müstakime', 'Sütçü');

-- MUSTERILERI LISTELE GOSTER
SELECT * FROM `my_company_db`.`customers`;
--Bu SQL cümlesi, 🧐 my_company_db veritabanındaki customers tablosundaki tüm sütun ve
--kayıtları seçer ve ekrana getirir, böylece tablo içeriğinin tam görünümünü inceleme,
--analiz etme ve doğrulama imkânı sağlar.