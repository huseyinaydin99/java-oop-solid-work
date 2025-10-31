#### 📂 My Company Database - SQL Çalışmaları

Bu proje, **müşteri ve ürün verilerini yönetmek** için tasarlanmış bir veritabanı üzerinde yapılan SQL uygulamalarını içerir. Amacım, verileri anlamlı bir şekilde saklamak, sorgulamak ve analiz edebilmektir. Aşağıda kullandığım başlıca SQL ifadeleri ve kendi deneyimlerimle hazırladığım açıklamalar yer almaktadır.

---

##### 1️⃣ CUSTOMERS Tablosu Oluşturma

```sql
CREATE TABLE CUSTOMERS (
    CUSTOMER_ID NUMBER
        GENERATED ALWAYS AS IDENTITY
        INCREMENT BY 1
        START WITH 1
        MAXVALUE 9999999
        MINVALUE 1
        NOT NULL,
    FIRST_NAME VARCHAR2(70),
    LAST_NAME VARCHAR2(75),
    AGE INT,
    COUNTRY VARCHAR2(65),
    CONSTRAINT CUSTOMERS_PK PRIMARY KEY(CUSTOMER_ID) ENABLE
);
```
- 🆔 CUSTOMER_ID: Her yeni kayıt için otomatik artan benzersiz kimlik numarasıdır; veritabanında eksiksiz kayıt ve referans güvenliği sağlar.
- 👤 FIRST_NAME ve LAST_NAME: Müşterilerin isim ve soyisimlerini saklar, karakter sınırları ile veri bütünlüğünü korur.
- 🎂 AGE: Sayısal veri ile yaş bilgisini tutar, veri doğruluğu ve analiz kolaylığı sağlar.
- 🌎 COUNTRY: Müşterinin bulunduğu ülke bilgisi; coğrafi raporlama ve filtreleme için kritik.
- 🔒 PRIMARY KEY: CUSTOMER_ID ile her kaydı benzersiz ve güvenli şekilde tanımlar.
- 2️⃣ Kayıt Ekleme İşlemleri

```sql
INSERT INTO "MY_COMPANY_DB"."CUSTOMERS" (FIRST_NAME, LAST_NAME, AGE, COUNTRY) 
VALUES ('Hüseyin', 'AYDIN', '31', 'TR');
```
>✍️ Müşteri eklerken tüm temel bilgileri giriyorum; yaş ve ülke verisi analitik sorgular için kritik.

>Benzer şekilde diğer müşteriler de tabloya eklenebilir, her biri için otomatik ID üretilir.

##### 3️⃣ Kayıtları Listeleme
```sql
SELECT * FROM CUSTOMERS;
```
>🔍 Tüm müşteri kayıtlarını görüntülerim; tablo yapısını ve veriyi eksiksiz görme ihtiyacımı karşılar.

>🎯 Farklı koşullar ile filtreleyerek örneğin yaşı 22’den büyük müşteriler gibi spesifik veri çekebilirim.

```sql
SELECT * FROM CUSTOMERS WHERE AGE > 22;
```
##### #️⃣ Kolon Seçimi ve Alias Kullanımı
```sql
SELECT "CUSTOMER_ID", "FIRST_NAME", "LAST_NAME", "COUNTRY", "AGE"
FROM my_company_db.customers;
```
>📝 Sadece ihtiyacım olan kolonları seçiyorum; gereksiz veriyi çekmeden analiz yapmamı sağlar.

```sql
SELECT c."CUSTOMER_ID", c."FIRST_NAME", c."LAST_NAME", c."AGE", c."COUNTRY"
FROM my_company_db.customers AS c;
```
>🏷️ Alias (c) kullanımıyla sorgular daha okunabilir ve kısa hale gelir.

>SQL büyük/küçük harf duyarlılığına esnek davranır; AS kullanılmasa bile alias ile aynı sonucu alabilirim.

##### 5️⃣ Fonksiyonlar ile İstatistikler
```sql
SELECT MAX("AGE"), MIN("AGE"), SUM("AGE"), COUNT("AGE"), AVG("AGE")
FROM my_company_db.customers;
```
>📊 Yaşla ilgili kapsamlı analiz yapabilirim: en büyük ve en küçük yaş, toplam yaş, yaş ortalaması ve toplam kayıt sayısı.

```sql
SELECT MAX("FIRST_NAME"), MIN("FIRST_NAME")
FROM my_company_db.customers;
```
##### 🔤 İsimleri alfabetik olarak inceleyebilirim; A’dan Z’ye sıralamada başta ve sonda hangi isimler var görebilirim.

##### 6️⃣ Alt Sorgular (Subquery) ve Sıralama
```sql
SELECT *
FROM my_company_db.customers
WHERE "AGE" = (
    SELECT MIN("AGE")
    FROM my_company_db.customers
);
```
>🏆 Alt sorgular ile dinamik filtreleme yapabilirim; örneğin en genç müşterileri çekmek için MIN fonksiyonunu kullandım.

```sql
SELECT *
FROM my_company_db.customers
ORDER BY "AGE" LIMIT 1;
```
>📉 Yaşı artan şekilde sıralayıp en genç müşteriyi hızlıca bulabilirim.

##### 7️⃣ GROUP BY ve HAVING Kullanımı
```sql
Kodu kopyala
SELECT "COUNTRY", COUNT(*) CUSTOMER_COUNT
FROM my_company_db.customers
GROUP BY "COUNTRY"
HAVING COUNT(*) > 30
ORDER BY CUSTOMER_COUNT DESC;
```
>🏅 Ülke bazında müşteri sayısını grupladım, belirli bir sayıdan fazla olanları filtreledim ve en çok müşterisi olan ülkeden en az olana doğru sıraladım.

>🔗 GROUP BY + HAVING kombinasyonu, toplu veri analizi ve raporlama için vazgeçilmezdir.

##### 8️⃣ Koşullu Filtreleme ve LIKE
```sql
SELECT * FROM my_company_db.customers
WHERE "AGE" BETWEEN 18 AND 26;
```

##### 🔢 Aralık filtreleme için BETWEEN kullandım; hem okunabilir hem doğru sonuç verir.

```sql
SELECT * FROM my_company_db.customers
WHERE "FIRST_NAME" LIKE 'Er%';
```
##### 🔡 Baş harfe göre arama yapmak istediğimde LIKE çok kullanışlıdır; aynı mantıkla sondan veya ortadan arama da yapılabilir.

```sql
SELECT * FROM my_company_db.customers
WHERE "FIRST_NAME" LIKE '%e%' AND "FIRST_NAME" NOT LIKE '%h%';
```
>🚫 Karmaşık filtreler için AND ve NOT LIKE ile detaylı kontrol sağlayabilirim.

##### 9️⃣ FOREIGN KEY ile İlişkilendirme
```sql
ALTER TABLE IF EXISTS my_company_db.orders
    ADD FOREIGN KEY ("ORDER_ID")
        REFERENCES my_company_db.products ("PRODUCT_ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;
```
>🔗 Tablolar arası referans bütünlüğünü korumak için foreign key ekledim.

>ON UPDATE/DELETE ile veri bütünlüğünü korurken, NOT VALID ile mevcut veri doğrulamasını atlayarak ekleme yaptım.