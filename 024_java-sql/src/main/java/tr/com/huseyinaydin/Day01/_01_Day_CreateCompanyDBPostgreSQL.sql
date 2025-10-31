-- VERITABANI DB OLUSTURULDU
CREATE SCHEMA my_company_db
    AUTHORIZATION postgres;
-- 🌟 Bu komut, "my_company_db" adında bir veritabanı şeması oluşturur ve
-- tüm yetkileri "postgres" kullanıcısına verir, böylece tabloları güvenle organize edebilirim. 🚀

-- MUSTERILER TABLOSU OLUSTURULDU
CREATE TABLE my_company_db.customers
(
    "ID" serial NOT NULL,
    "FIRST_NAME" character varying(60),
    "LAST_NAME" character varying(60),
    PRIMARY KEY ("ID")
);
-- 📝 Bu tablo, müşteri bilgilerini saklamak için tasarlandı; her müşteri benzersiz
-- bir ID ile tanımlanır ve isimleri kolayca yönetilir. 👥

ALTER TABLE IF EXISTS my_company_db.customers
    OWNER to postgres;
-- 🔒 Sahiplik değişikliği ile tablonun kontrolü "postgres" kullanıcısına
-- verilerek yetki yönetimi güvence altına alınır. 🛡️

-- PRODUCT TABLOSU OLUSTURULDU
CREATE TABLE my_company_db.products
(
    "ID" bigserial NOT NULL,
    "PROD_NAME" character varying(200),
    "PROD_CODE" character varying(45),
    PRIMARY KEY ("ID")
);
-- 📦 Ürünleri saklamak için "products" tablosu oluşturuldu; her ürün
-- benzersiz bir ID ile kaydedilir ve kodu sayesinde kolayca tanınır. 🔖

ALTER TABLE IF EXISTS my_company_db.products
    OWNER to postgres;
-- 🛠️ Bu komut, ürün tablosunun sahibi olarak "postgres" kullanıcısını atar
-- ve yönetim yetkilerini netleştirir. ✅

-- MUSTERI TABLOSUNA KAYITLAR EKLENDI
INSERT INTO my_company_db.customers ("FIRST_NAME", "LAST_NAME") VALUES ('Hüseyin', 'AYDIN');
INSERT INTO my_company_db.customers ("FIRST_NAME", "LAST_NAME") VALUES ('Hasan', 'Fefer');
INSERT INTO my_company_db.customers ("FIRST_NAME", "LAST_NAME") VALUES ('Özcan', 'Cussur');
-- ✍️ Bu komutlar tabloya üç yeni müşteri ekler; isim ve soyisim bilgileriyle
-- müşteri veri tabanına canlılık kazandırılır. 🌱

-- MUSTERILERI LISTELE GOSTER
SELECT * FROM my_company_db.customers;
-- 🔍 Bu sorgu, tüm müşteri kayıtlarını listeler ve mevcut verileri
-- hızlıca gözden geçirmemi sağlar; veri kontrolü için ideal bir yöntemdir. 📊