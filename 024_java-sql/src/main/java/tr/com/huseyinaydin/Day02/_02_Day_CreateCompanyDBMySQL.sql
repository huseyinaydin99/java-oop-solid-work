-- VERITABANINI OLUSTURYORUZ
CREATE SCHEMA `my_company_db` DEFAULT CHARACTER SET utf32 COLLATE utf32_bin;
-- 🌐 'my_company_db' adında yeni bir veritabanı oluşturuldu ve karakter seti UTF-32 olarak ayarlandı. 🔠

-- MUSTERILER TABLOSU
CREATE TABLE `my_company_db`.`customers` (
    `ID` INT NOT NULL AUTO_INCREMENT,
    -- 🆔 ID sütunu otomatik artan birincil anahtar olarak tanımlandı, her müşteri için benzersizdir. 🚀
    `FIRST_NAME` VARCHAR(60) NULL,
    -- 👤 FIRST_NAME sütunu müşterinin adını saklar, 60 karaktere kadar veri alabilir. ✏️
    `LAST_NAME` VARCHAR(60) NULL,
    -- 👥 LAST_NAME sütunu müşterinin soyadını saklar, 60 karaktere kadar veri alabilir. ✏️
    PRIMARY KEY (`ID`)
    -- 🔒 ID sütunu PRIMARY KEY olarak atanmıştır, kayıtlar benzersiz ve güvenli hale gelir. ✅
);

-- URUNLER TABLOSU
CREATE TABLE `my_company_db`.`products` (
    `ID` INT NOT NULL AUTO_INCREMENT,
    -- 🆔 ID sütunu ürünler için otomatik artan benzersiz kimliktir. 📦
    `PROD_NAME` VARCHAR(200) NULL,
    -- 📌 PROD_NAME sütunu ürün adını saklar ve 200 karaktere kadar veri alabilir. ✏️
    `PROD_CODE` VARCHAR(45) NULL,
    -- 🔖 PROD_CODE sütunu ürün kodunu tutar ve 45 karaktere kadar veri kabul eder. ✏️
    PRIMARY KEY (`ID`)
    -- 🔒 ID sütunu PRIMARY KEY olarak atanmıştır, her ürün benzersizdir. ✅
);

-- TABLO UZERINDE DEGISIKLIK YAPAR
ALTER TABLE `my_company_db`.`products`
    CHANGE COLUMN `PROD_Code` `PROD_CODE` VARCHAR(45) NULL DEFAULT NULL;
-- 🛠️ PROD_Code sütunu PROD_CODE olarak düzeltildi ve varsayılan değer NULL yapıldı. 🔧

-- URUNLER TABLOLSUNU SILER
-- DROP TABLE `mycompany`.`products`;
-- ❌ Bu komut çalıştırılırsa ürün tablosu tamamen silinir ve veriler kaybolur. ⚠️

-- VERITABINI SILER
-- DROP DATABASE `my_company_db`;
-- ❌ Bu komut çalıştırılırsa veritabanı tamamen silinir, tüm tablolar ve kayıtlar kaybolur. ⚠️

-- MUSTERI TABLOSUNA KAYITLAR EKLENDI
INSERT INTO `my_company_db`.`customers` (`FIRST_NAME`, `LAST_NAME`) VALUES ('Mahmut', 'Sakarya');
-- ✍️ Mahmut Sakarya adlı müşteri tabloya eklendi ve benzersiz ID alır. 👤

INSERT INTO `my_company_db`.`customers` (`FIRST_NAME`, `LAST_NAME`) VALUES ('Hakan', 'Metin');
-- ✍️ Hakan Metin adlı müşteri tabloya eklendi ve sistem otomatik ID verdi. 👤

INSERT INTO `my_company_db`.`customers` (`FIRST_NAME`, `LAST_NAME`) VALUES ('Mücahit', 'Özcan');
-- ✍️ Mücahit Özcan tabloya eklendi ve benzersiz ID ile saklandı. 👤

-- MUSTERILERI LISTELE GOSTER
SELECT * FROM `my_company_db`.`customers`;
-- 🔍 Tüm müşteri kayıtlarını listeler ve veri doğruluğunu kontrol etmeme yardımcı olur. 📊

-- SADECE 1 KAYIT EKLE
INSERT INTO `my_company_db`.`customers` (`FIRST_NAME`, `LAST_NAME`) VALUES ('Tuana', 'Kaymaz');
-- ✍️ Tuana Kaymaz adlı müşteri tabloya eklendi. 👤

INSERT INTO my_company_db.customers (FIRST_NAME, LAST_NAME) VALUES('Sefa', 'Küçükarslan');
-- ✍️ Sefa Küçükarslan tabloya eklendi ve benzersiz ID aldı. 👤

-- TABLOYA 2 TANE YENI KOLON EKLEDIK VE 3 KOLONU DE DEGISIKLIK YAPTIK
ALTER TABLE `my_company_db`.`customers`
    ADD COLUMN `AGE` SMALLINT(3) NULL AFTER `LAST_NAME`,
    -- 🎂 AGE sütunu eklendi, müşterilerin yaş bilgisi tutulur. 🔢
    ADD COLUMN `COUNTRY` VARCHAR(65) NULL AFTER `AGE`,
    -- 🌎 COUNTRY sütunu eklendi ve müşterinin ülke bilgisi saklanır. 🗺️
    CHANGE COLUMN `ID` `CUSTOMER_ID` INT NOT NULL,
    -- 🆔 ID sütunu CUSTOMER_ID olarak değiştirildi, birincil anahtar görevi devam eder. ✅
    CHANGE COLUMN `FIRST_NAME` `FIRST_NAME` VARCHAR(70) NULL DEFAULT NULL,
    -- ✏️ FIRST_NAME sütunu boyutu 70 karaktere çıkarıldı ve varsayılan NULL olarak ayarlandı. 👤
    CHANGE COLUMN `LAST_NAME` `LAST_NAME` VARCHAR(75) NULL DEFAULT NULL;
    -- ✏️ LAST_NAME sütunu boyutu 75 karaktere çıkarıldı ve varsayılan NULL olarak ayarlandı. 👥

-- TABLODA YENI EKLENEN KOLONLARA DEGER GIRILDI
UPDATE `my_company_db`.`customers` SET `AGE` = '30', `COUNTRY` = 'TR' WHERE (`CUSTOMER_ID` = '1');
-- ✍️ ID 1 için yaş 30 ve ülke TR olarak güncellendi. 🔄

UPDATE `my_company_db`.`customers` SET `AGE` = '22', `COUNTRY` = 'BR' WHERE (`CUSTOMER_ID` = '2');
-- ✍️ ID 2 için yaş 22 ve ülke BR olarak güncellendi. 🔄

UPDATE `my_company_db`.`customers` SET `AGE` = '25', `COUNTRY` = 'DE' WHERE (`CUSTOMER_ID` = '3');
-- ✍️ ID 3 için yaş 25 ve ülke DE olarak güncellendi. 🔄

UPDATE `my_company_db`.`customers` SET `AGE` = '27', `COUNTRY` = 'GB' WHERE (`CUSTOMER_ID` = '4');
-- ✍️ ID 4 için yaş 27 ve ülke GB olarak güncellendi. 🔄

UPDATE `my_company_db`.`customers` SET `AGE` = '32', `COUNTRY` = 'TR' WHERE (`CUSTOMER_ID` = '5');
-- ✍️ ID 5 için yaş 32 ve ülke TR olarak güncellendi. 🔄

-- MUSTERILERI LISTELE GOSTER
SELECT * FROM `my_company_db`.`customers`;
-- 🔍 Tüm müşterileri ve güncel verilerini listeler, tablo kontrolü sağlar. 📊

-- SADECE 2 KOLON SECILDI
SELECT customers.FIRST_NAME, customers.LAST_NAME FROM `my_company_db`.`customers`;
-- 👀 Sadece ad ve soyadı sütunları seçildi ve görüntülendi. ✏️

-- SADECE 3 KOLON SECILDI
SELECT CUSTOMER_ID, FIRST_NAME, LAST_NAME FROM `my_company_db`.`customers`;
-- 👀 ID, ad ve soyadı sütunları seçildi ve listelendi. ✏️

-- ULKESI TR OLAN MUSTERILERI GETIR
SELECT * FROM my_company_db.customers WHERE COUNTRY = 'TR';
-- 🌎 Ülkesi TR olan müşteriler filtrelendi ve görüntülendi. 🔍

-- ULKESI TR ve SOYADI 'Sakarya' OLAN MUSTERILERI GETIR
SELECT * FROM my_company_db.customers WHERE COUNTRY = 'TR' AND customers.LAST_NAME = 'Sakarya';
-- 🌎🔗 TR ülkesinde soyadı Sakarya olan müşteri listelendi. 👤

-- ULKESI TR veya SOYADI 'Metin' OLAN MUSTERILERI GETIR
SELECT * FROM customers WHERE COUNTRY = 'TR' OR LAST_NAME = 'Metin';
-- 🌎🔀 TR ülkesinde veya soyadı Metin olan müşteriler filtrelendi. 👥

-- AD VE SOYAD KOLONLARINI SEC
SELECT FIRST_NAME, LAST_NAME FROM customers WHERE COUNTRY = 'TR' OR LAST_NAME = 'Metin';
-- ✏️ Ad ve soyad sütunları seçildi, TR ülkesinde veya soyadı Metin olanlar görüntülendi. 👤👥

-- YASI 27'DEN BUYUK OLANLARI GETIR
SELECT * FROM customers WHERE AGE > 27;
-- 🎂 Yaşı 27’den büyük olan müşteriler listelendi. 🔍

-- YASI 27'DEN BUYUK VE ULKESI ALMANYA OLANLARI GETIR
SELECT * FROM customers WHERE AGE > 27 AND COUNTRY = 'DE';
-- 🎂🌎 Yaşı 27’den büyük ve ülkesi DE olan müşteriler filtrelendi. 🔍

-- TABLOYA YENI 2 KOLON EKLENDI
ALTER TABLE `my_company_db`.`customers`
    ADD COLUMN `EMAIL` VARCHAR(100) NULL AFTER `COUNTRY`,
    -- 📧 Email sütunu eklendi, müşteri mail adresi saklanır. ✏️
    ADD COLUMN `GENDER` VARCHAR(1) NULL AFTER `EMAIL`;
    -- 🚻 Gender sütunu eklendi ve M/F bilgisi tutulur. ✏️

-- ID ICIN OTOMATIK SAYACI KULLANIYORUZ
ALTER TABLE `my_company_db`.`customers`
    CHANGE COLUMN `CUSTOMER_ID` `CUSTOMER_ID` INT NOT NULL AUTO_INCREMENT;
-- 🔢 CUSTOMER_ID artık otomatik artan bir sütun oldu, yeni kayıtlar için kolaylık sağlar. ✅

-- YENI BIR KAYIT EKLENECEK
INSERT INTO customers (FIRST_NAME, LAST_NAME, AGE, COUNTRY, EMAIL, GENDER)
VALUES ('Veli', 'Deli', 31, 'USA', 'shootabecigostik@fanfinifinfon.com', 'M');
-- ✍️ Yeni müşteri kaydı eklendi, tüm sütunlar dolu olarak sistemde yer aldı. 👤

-- BUTUN KAYITLI MUSTERILERI LISTELE
SELECT * FROM customers;
-- 🔍 Tüm müşteri kayıtlarını listeler, veri kontrolü sağlar. 📊

-- ULKESI ALMANYA OLAN MUSTERILER
SELECT * FROM customers WHERE COUNTRY = 'DE';
-- 🌎 Ülkesi DE olan müşteriler filtrelendi ve listelendi. 👀

-- ULKESI ALMANYA VE KADIN MUSTERILER
SELECT * FROM customers WHERE COUNTRY = 'DE' AND GENDER = 'F';
-- 🌎🚻 Ülkesi DE ve cinsiyeti F olan müşteriler filtrelendi. 👩

-- SADECE ADI, SOYADI VE ULKE KODU
SELECT COUNTRY, FIRST_NAME, LAST_NAME FROM customers WHERE COUNTRY = 'DE' AND GENDER = 'M';
-- 👤🌎 Ülkesi DE ve cinsiyeti M olan müşterilerin adı, soyadı ve ülke kodu seçildi. ✏️

-- ULKESI ALMANYA VEYA TURKIYE
SELECT * FROM customers WHERE COUNTRY = 'DE' OR COUNTRY ='TR';
-- 🌎🔀 DE veya TR ülkelerinde olan müşteriler filtrelendi. 👥

-- ULKESI ALMANYA VEYA TURKIYE VE 25 YAŞINDAN BUYUK
SELECT * FROM customers WHERE (COUNTRY = 'DE' OR COUNTRY = 'TR') AND AGE > 25;
-- 🌎🎂 DE veya TR ülkelerinde yaşı 25’ten büyük müşteriler filtrelendi. 👤

-- ULKESI ALMANYA OLMAYANLAR
SELECT * FROM customers WHERE COUNTRY != 'DE';
-- 🌎❌ Ülkesi DE olmayan müşteriler listelendi. 👀

-- BUTUN ULKELER TEKIL
SELECT DISTINCT COUNTRY FROM customers;
-- 🌎✨ Müşterilerin ülkeleri tekil olarak listelendi. 🔍

-- KAC MUSTERI VAR?
SELECT count(*) FROM customers;
-- 🔢 Toplam müşteri sayısı hesaplandı. 📊

-- KAC MUSTERININ EMAILI VAR?
SELECT count(EMAIL) FROM customers;
-- 📧 Email adresi olan müşteri sayısı hesaplandı. 🔍

-- MUSTERILERIN YASLARI TOPLAMI
SELECT sum(AGE) FROM customers;
-- 🎂 Tüm müşterilerin yaşlarının toplamı bulundu. 🔢

-- MUSTERI ORTALAMA YAS
SELECT AVG(AGE) FROM customers;
-- 🎂 Ortalama müşteri yaşı hesaplandı. 🔢

-- EN KUCUK YAŞ
SELECT MIN(AGE) FROM customers;
-- 🎂 En küçük müşteri yaşı bulundu. 🔢

-- EN BUYUK YAŞ
SELECT MAX(AGE) FROM customers;
-- 🎂 En büyük müşteri yaşı bulundu. 🔢

-- ALIAS KULLANIMI
SELECT MAX(AGE) AS EnBuyuk FROM customers;
-- 🏷️ MAX(AGE) sütunu 'EnBuyuk' adıyla gösterildi. ✏️

SELECT MAX(AGE) AS "En Buyuk" FROM customers;
-- 🏷️ Birden fazla kelimeli alias tırnak içinde yazıldı. ✏️

SELECT MAX(AGE) EnBuyuk FROM customers;
-- 🏷️ AS kullanmadan alias atandı. ✏️

SELECT MAX(AGE) "En Buyuk Yas" FROM customers;
-- 🏷️ Alias tırnakla ve birden fazla kelimeyle gösterildi. ✏️

-- TABLOYU ADA GORE SIRALA
SELECT * FROM customers ORDER BY FIRST_NAME;
-- 🔤 Varsayılan olarak adları A’dan Z’ye sıralar. 🆙

SELECT * FROM customers ORDER BY FIRST_NAME ASC;
-- 🔤 Adları artan düzende sıralar (0-9 A-Z). 🆙

SELECT * FROM customers ORDER BY FIRST_NAME DESC;
-- 🔤 Adları azalan düzende sıralar (Z-A). ⬇️

SELECT * FROM customers ORDER BY AGE;
-- 🎂 Yaşa göre küçükten büyüğe sıralar. 🆙

SELECT * FROM customers ORDER BY AGE DESC;
-- 🎂 Yaşa göre büyükten küçüğe sıralar. ⬇️

-- ULKELERI VE KISI SAYISINI GRUPLA
SELECT COUNTRY, COUNT(*) AS KISI FROM customers GROUP BY COUNTRY ORDER BY KISI DESC;
-- 🌎👥 Ülke bazında müşteri sayısını gruplar ve en çoktan aza doğru sıralar. 🔢

-- TRUNCATE TABLE
-- TRUNCATE `my_company_db`.`customers`;
-- 🗑️ Tüm tablo verileri silinir, sayaç sıfırlanır ve tablo boş hale gelir. ⚠️

-- DROP TABLE
-- DROP TABLE `my_company_db`.`customers`;
-- ❌ Tablo tamamen silinir, geri dönüşü yoktur. ⚠️

-- UPDATE ÖRNEK
UPDATE `my_company_db`.`customers` SET `FIRST_NAME` = 'Cumali', `LAST_NAME` = 'Yavuz' WHERE (`CUSTOMER_ID` > 11);
-- ✏️ ID’si 11’den büyük olan müşterilerin adı ve soyadı güncellendi. 🔄