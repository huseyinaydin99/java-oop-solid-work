-- VERITABANI DB OLUSTURULDU
CREATE SCHEMA my_company_db
    AUTHORIZATION postgres;
-- 🌐 'my_company_db' isimli veritabanı şeması oluşturuldu ve sahibi postgres olarak belirlendi. 🔐

-- MUSTERILER TABLOSU OLUSTURULDU
CREATE TABLE my_company_db.customers(
    "ID" serial NOT NULL,
    -- 🆔 ID sütunu serial tipinde, otomatik artan birincil anahtar olarak ayarlandı. 🚀
    "FIRST_NAME" character varying(60),
    -- 👤 FIRST_NAME sütunu müşterinin adını saklar, 60 karaktere kadar veri alabilir. ✏️
    "LAST_NAME" character varying(60),
    -- 👥 LAST_NAME sütunu müşterinin soyadını saklar, 60 karaktere kadar veri alabilir. ✏️
    PRIMARY KEY ("ID")
    -- 🔒 ID sütunu birincil anahtar olarak tanımlandı, kayıtlar benzersizdir. ✅
);

ALTER TABLE IF EXISTS my_company_db.customers
    OWNER to postgres;
-- 👑 Tablo sahibi postgres olarak ayarlandı. 🔐

-- PRODUCT TABLOSU OLUSTURULDU
CREATE TABLE my_company_db.products(
    "ID" bigserial NOT NULL,
    -- 🆔 ID sütunu bigserial tipinde, otomatik artan benzersiz kimliktir. 📦
    "PROD_NAME" character varying(200),
    -- 📌 PROD_NAME sütunu ürün adını saklar, 200 karaktere kadar veri alabilir. ✏️
    "PROD_CODE" character varying(45),
    -- 🔖 PROD_CODE sütunu ürün kodunu saklar, 45 karaktere kadar veri alabilir. ✏️
    PRIMARY KEY ("ID")
    -- 🔒 ID sütunu birincil anahtar olarak tanımlandı, her ürün benzersizdir. ✅
);

ALTER TABLE IF EXISTS my_company_db.products
    OWNER to postgres;
-- 👑 Ürün tablosunun sahibi postgres olarak belirlendi. 🔐

-- MUSTERI TABLOSUNA KAYITLAR EKLENDI
INSERT INTO my_company_db.customers ("FIRST_NAME", "LAST_NAME") VALUES ('Hüseyin', 'AYDIN');
-- ✍️ Hüseyin AYDIN adlı müşteri tabloya eklendi, ID otomatik atanır. 👤

INSERT INTO my_company_db.customers ("FIRST_NAME", "LAST_NAME") VALUES ('Hüsamettin', 'Vansızoğlu');
-- ✍️ Hüsamettin Vansızoğlu tabloya eklendi. 👤

INSERT INTO my_company_db.customers ("FIRST_NAME", "LAST_NAME") VALUES ('Mücahitdin', 'Dördüncü');
-- ✍️ Mücahitdin Dördüncü tabloya eklendi. 👤

-- MUSTERILERI LISTELE GOSTER
SELECT * FROM my_company_db.customers;
-- 🔍 Tüm müşteri kayıtlarını listeler ve veri doğruluğunu kontrol etmeme yardımcı olur. 📊

-- SADECE 1 KAYIT EKLE
INSERT INTO my_company_db.customers
("FIRST_NAME", "LAST_NAME")
VALUES ('Zühre', 'Yıldız');
-- ✍️ Zühre Yıldız tabloya eklendi. 👤

INSERT INTO my_company_db.customers
("FIRST_NAME", "LAST_NAME")
VALUES('Şakir', 'İki');
-- ✍️ Şakir İki tabloya eklendi. 👤

-- MUSTERILERI LISTELE GOSTER
SELECT * FROM my_company_db.customers;
-- 🔍 Tüm müşterileri listeler, eklenen kayıtları doğrular. 📊

-- TABLOYA 2 TANE YENI KOLON EKLEDIK VE 3 KOLON UZERINDE DE DEGISIKLIK YAPTIK
ALTER TABLE IF EXISTS my_company_db.customers
    RENAME "ID" TO "CUSTOMER_ID";
-- 🆔 ID sütunu CUSTOMER_ID olarak değiştirildi ve benzersiz kimlik görevine devam ediyor. 🔧

ALTER TABLE my_company_db.customers
    ALTER COLUMN "FIRST_NAME" TYPE character varying(70) COLLATE pg_catalog."default";
-- ✏️ FIRST_NAME sütunu boyutu 70 karaktere çıkarıldı, veri standardı PostgreSQL default kollasyon ile ayarlandı. ✅

ALTER TABLE my_company_db.customers
    ALTER COLUMN "LAST_NAME" TYPE character varying(75) COLLATE pg_catalog."default";
-- ✏️ LAST_NAME sütunu boyutu 75 karaktere çıkarıldı ve kollasyon default yapıldı. ✅

ALTER TABLE IF EXISTS my_company_db.customers
    ADD COLUMN "AGE" smallint;
-- 🎂 AGE sütunu eklendi, müşterilerin yaş bilgisi saklanacak. 🔢

ALTER TABLE IF EXISTS my_company_db.customers
    ADD COLUMN "COUNTRY" character varying(65);
-- 🌎 COUNTRY sütunu eklendi, müşterinin ülke bilgisi tutulacak. 🗺️

-- MUSTERILERI LISTELE GOSTER
SELECT * FROM my_company_db.customers;
-- 🔍 Tüm müşterileri ve tablo yapısını kontrol eder. 📊

-- TABLODA YENI EKLENEN KOLONLARA DEGER GIRILDI
UPDATE my_company_db.customers SET "AGE" = '30', "COUNTRY" = 'TR' WHERE ("CUSTOMER_ID" = '1');
-- ✍️ ID 1 için yaş 30 ve ülke TR olarak güncellendi. 🔄

UPDATE my_company_db.customers SET "AGE" = '22', "COUNTRY" = 'BR' WHERE ("CUSTOMER_ID" = '2');
-- ✍️ ID 2 için yaş 22 ve ülke BR olarak güncellendi. 🔄

UPDATE my_company_db.customers SET "AGE" = '25', "COUNTRY" = 'DE' WHERE ("CUSTOMER_ID" = '3');
-- ✍️ ID 3 için yaş 25 ve ülke DE olarak güncellendi. 🔄

UPDATE my_company_db.customers SET "AGE" = '27', "COUNTRY" = 'GB' WHERE ("CUSTOMER_ID" = '4');
-- ✍️ ID 4 için yaş 27 ve ülke GB olarak güncellendi. 🔄

UPDATE my_company_db.customers SET "AGE" = '32', "COUNTRY" = 'TR' WHERE ("CUSTOMER_ID" = '5');
-- ✍️ ID 5 için yaş 32 ve ülke TR olarak güncellendi. 🔄

-- MUSTERILERI LISTELE GOSTER
SELECT * FROM my_company_db.customers;
-- 🔍 Güncel müşteri kayıtlarını listeler. 📊

-- SADECE AD VE SOYAD SEC
SELECT "FIRST_NAME", "LAST_NAME" FROM my_company_db.customers;
-- ✏️ Sadece müşterilerin ad ve soyad bilgileri görüntülendi. 👤

-- SADECE ID, AD VE SOYAD SEC
SELECT "CUSTOMER_ID", "FIRST_NAME", "LAST_NAME" FROM my_company_db.customers;
-- 👀 Müşteri ID, ad ve soyadı listelendi. 📋