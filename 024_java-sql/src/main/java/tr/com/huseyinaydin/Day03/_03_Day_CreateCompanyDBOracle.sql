-- CREATE TABLE CUSTOMERS TABLE OLUŞTURULDU
CREATE TABLE CUSTOMERS (
    CUSTOMER_ID NUMBER
    GENERATED ALWAYS AS IDENTITY
    INCREMENT BY 1
    START WITH 1
    MAXVALUE 9999999
    MINVALUE 1
    NOT NULL,
    -- 🆔 CUSTOMER_ID, her yeni kayıt için otomatik artan benzersiz bir kimlik numarasıdır ve boş bırakılamaz. 🚀

    FIRST_NAME VARCHAR2(70),
    -- 📝 FIRST_NAME sütunu müşterinin ilk adını saklar ve 70 karaktere kadar veri kabul eder. 👤

    LAST_NAME VARCHAR2(75),
    -- 📝 LAST_NAME sütunu müşterinin soyadını tutar ve 75 karaktere kadar veri alabilir. 👥

    AGE INT,
    -- 🎂 AGE sütunu müşterinin yaş bilgisini saklar, sayısal olarak veri doğruluğu sağlar. 🔢

    COUNTRY VARCHAR2(65),
    -- 🌎 COUNTRY sütunu müşterinin ülke bilgisini tutar ve 65 karaktere kadar veri alabilir. 🗺️

    CONSTRAINT CUSTOMERS_PK PRIMARY KEY(CUSTOMER_ID) ENABLE
    -- 🔒 PRIMARY KEY olarak CUSTOMER_ID atanmıştır, böylece her kayıt benzersiz ve güvenli şekilde tanımlanır. 🛡️
);

-- KAYIT EKLEME İŞLEMLERİ
INSERT INTO "MY_COMPANY_DB"."CUSTOMERS" (FIRST_NAME, LAST_NAME, AGE, COUNTRY) VALUES ('Hüseyin', 'AYDIN', '31', 'TR');
-- ✍️ Bu komut, Hüseyin Aydın adlı müşteriyi 31 yaşında ve Türkiye ülkesi ile tabloya ekler. 🇹🇷

INSERT INTO "MY_COMPANY_DB"."CUSTOMERS" (FIRST_NAME, LAST_NAME, AGE, COUNTRY) VALUES ('Veli', 'Çirkin', '21', 'ABD');
-- ✍️ Bu komut, Veli Çirkin adlı müşteriyi 21 yaşında ve ABD ülkesi ile tabloya ekler. 🇺🇸

INSERT INTO "MY_COMPANY_DB"."CUSTOMERS" (FIRST_NAME, LAST_NAME, AGE, COUNTRY) VALUES ('Cihat', 'Uçmaz', '29', 'TR');
-- ✍️ Bu komut, Cihat Uçmaz adlı müşteriyi 29 yaşında ve Türkiye ülkesi ile tabloya ekler. 🇹🇷

-- TÜM KAYITLARI LİSTELEME
SELECT * FROM CUSTOMERS;
-- 🔍 Bu sorgu, tabloya eklenmiş tüm müşteri kayıtlarını gösterir ve veritabanındaki mevcut verileri hızlıca görmemi sağlar. 📊

-- YAŞI 22'DEN BÜYÜK MÜŞTERİLERİ LİSTELEME
SELECT * FROM CUSTOMERS
WHERE AGE > 22;
-- 🕵️‍♂️ Bu sorgu, yalnızca yaşı 22’den büyük müşterileri filtreler ve yaş kriterine uygun verileri seçmemi sağlar. 🎯