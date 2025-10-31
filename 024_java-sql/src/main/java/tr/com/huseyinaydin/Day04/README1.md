##### SQL JOIN & Temel Sorgular Rehberi

Bu doküman, müşteri ve sipariş veritabanı üzerinde gerçekleştirilen temel SQL sorgularını ve JOIN işlemlerini açıklayıcı örneklerle göstermektedir. Her sorgunun üstünde **kapsayıcı ve derin tek cümlelik açıklama** yer almaktadır.

---

##### 📂 Tablo Sorgulama

```sql
-- Kategoriler tablosundaki tüm kayıtları getirir; temel referans veri yapısını incelemek için kullanılır.
SELECT * FROM my_company_db.categories;

-- Ürünler tablosundaki mevcut tüm ürün bilgilerini listeler; stok, fiyat ve ürün tanımlarını görmemizi sağlar.
SELECT * FROM my_company_db.products;

-- Müşteriler tablosundaki tüm müşteri bilgilerini görüntüler; sistemde kayıtlı kişi tabanını anlamak için temel sorgudur.
SELECT * FROM my_company_db.customers;

-- Siparişler tablosundaki tüm sipariş kayıtlarını gösterir; işlem geçmişi ve satış verilerinin temel kaynağıdır.
SELECT * FROM my_company_db.orders;

-- Kargolama (sevkiyat) bilgilerini getirir; sipariş sonrası lojistik süreçlerin takibini sağlar.
SELECT * FROM my_company_db.shippings;
```

---

##### 🔗 JOIN İşlemleri

```sql
-- INNER JOIN, iki tabloda eşleşen kayıtları birleştirerek kesişim kümesini oluşturur.
SELECT *
FROM my_company_db.customers
INNER JOIN my_company_db.orders
    ON customers."CUSTOMER_ID" = orders."CUSTOMER_ID";
```

```sql
-- LEFT JOIN, sipariş vermemiş müşterileri de dahil ederek müşteri odaklı liste oluşturur.
SELECT *
FROM my_company_db.customers
LEFT JOIN my_company_db.orders
    ON customers."CUSTOMER_ID" = orders."CUSTOMER_ID";
```

```sql
-- RIGHT JOIN, tüm siparişleri koruyarak müşterisi olmayan siparişleri bile listede tutar.
SELECT *
FROM my_company_db.customers
RIGHT JOIN my_company_db.orders
    ON customers."CUSTOMER_ID" = orders."CUSTOMER_ID";
```

```sql
-- FULL JOIN, iki tabloda da eşleşmeyen kayıtları dahil ederek en geniş veri setini sunar.
SELECT *
FROM my_company_db.customers c
FULL JOIN my_company_db.orders o
    ON c."CUSTOMER_ID" = o."CUSTOMER_ID";
```

---

##### 🎯 FİLTRELEME & KOŞULLU SORGULAR

```sql
-- Yaşı 20’den küçük olan müşterileri listeler.
SELECT * FROM my_company_db.customers WHERE "AGE" < 20;

-- Tutarı 4000'den yüksek olan siparişleri listeler.
SELECT * FROM my_company_db.orders WHERE "AMOUNT" > 4000;

-- Büyük tutarlı siparişleri müşteri bilgileriyle birlikte getirir.
SELECT *
FROM my_company_db.customers
INNER JOIN my_company_db.orders
    ON customers."CUSTOMER_ID" = orders."CUSTOMER_ID"
WHERE "AMOUNT" > 4000;
```

---

##### 🧮 Sayım ve Veri Analizi

```sql
-- Toplam müşteri sayısını verir.
SELECT COUNT(*) FROM my_company_db.customers;

-- Toplam sipariş sayısını verir.
SELECT COUNT(*) FROM my_company_db.orders;
```

---

##### 🏗️ VIEW Oluşturma

```sql
-- INNER JOIN sonucu view olarak saklanır.
CREATE VIEW my_company_db.KESISEN_ORTAK_KAYITLAR AS
SELECT cus."CUSTOMER_ID" c_cusid, cus."FIRST_NAME", cus."LAST_NAME",
       ord."CUSTOMER_ID" o_cusid, ord."ORDER_BRANCH"
FROM my_company_db.customers cus
INNER JOIN my_company_db.orders ord
    ON cus."CUSTOMER_ID" = ord."CUSTOMER_ID";
```

---

##### ⚡ Performans - INDEX Kullanımı

```sql
-- EMAIL alanında index oluşturarak arama hızını artırır.
CREATE INDEX INDEX_CUSTOMER_EMAIL ON my_company_db.customers ("EMAIL");

-- Oluşturulan indexi kaldırır.
DROP INDEX my_company_db.INDEX_CUSTOMER_EMAIL;
```

---

##### ✅ Açıklama

Bu rehber, **JOIN mantığını**, **sorgulama tekniklerini**, **VIEW oluşturmayı** ve **index ile performans artırmayı** gerçek veri seti üzerinden anlamayı kolaylaştıracak şekilde hazırlanmıştır.

---