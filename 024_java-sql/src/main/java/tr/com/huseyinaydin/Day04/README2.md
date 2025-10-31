#### 📘 SQL JOIN & Temel Sorgular Rehberi

Bu doküman, müşteri ve sipariş veritabanı üzerinde gerçekleştirilen temel SQL sorgularını ve JOIN işlemlerini açıklayıcı bir şekilde ele alır. Aşağıdaki her başlık belirli bir konuyu kapsamlı bir biçimde açıklar. Amacımız kavramları sade, canlı ve **anlaşılır** biçimde anlatmaktır. 🚀

---

##### 📂 Tablo Sorgulama

Bu bölümdeki sorgular, veritabanındaki temel tabloları incelemek ve başlangıç seviyesinde veri yapısını anlamak için kullanılır. Her tablo belirli bir iş sürecine hizmet eder ve sistemin nasıl çalıştığını kavramak için bu tabloların içerikleri incelenir. Bu yapı sayesinde müşteri, ürün, sipariş ve sevkiyat süreçleri bir bütün olarak görülebilir. 🎯

```sql
SELECT * FROM my_company_db.categories;
SELECT * FROM my_company_db.products;
SELECT * FROM my_company_db.customers;
SELECT * FROM my_company_db.orders;
SELECT * FROM my_company_db.shippings;
```

---

##### 🔗 JOIN İşlemleri

JOIN işlemleri, ilişkisel veritabanlarının kalbidir diyebiliriz. Farklı tablolar arasında ilişki kurarak tek bir büyük tablo gibi veri okumamıza olanak sağlar. INNER JOIN yalnızca eşleşen kayıtları döndürürken, LEFT ve RIGHT JOIN taraflara göre öncelik belirler. FULL JOIN ise tüm kayıtları kapsayarak veri kaybını engeller. 🔥

```sql
SELECT *
FROM my_company_db.customers
INNER JOIN my_company_db.orders
    ON customers."CUSTOMER_ID" = orders."CUSTOMER_ID";

SELECT *
FROM my_company_db.customers
LEFT JOIN my_company_db.orders
    ON customers."CUSTOMER_ID" = orders."CUSTOMER_ID";

SELECT *
FROM my_company_db.customers
RIGHT JOIN my_company_db.orders
    ON customers."CUSTOMER_ID" = orders."CUSTOMER_ID";

SELECT *
FROM my_company_db.customers c
FULL JOIN my_company_db.orders o
    ON c."CUSTOMER_ID" = o."CUSTOMER_ID";
```

---

##### 🎯 Filtreleme & Koşullu Sorgular

Veri her zaman ham haliyle yeterli olmayabilir; çoğu durumda belirli koşullara göre filtreleme yapmak gerekebilir. Bu sorgular, yaş, tutar, tarih gibi kriterlere göre verilerin süzülmesini sağlar. Böylece analiz hedefe yönelik ve verimli hale gelir. 💡

```sql
SELECT * FROM my_company_db.customers WHERE "AGE" < 20;

SELECT * FROM my_company_db.orders WHERE "AMOUNT" > 4000;

SELECT *
FROM my_company_db.customers
INNER JOIN my_company_db.orders
    ON customers."CUSTOMER_ID" = orders."CUSTOMER_ID"
WHERE "AMOUNT" > 4000;
```

---

##### 🧮 Sayım ve Veri Analizi

Veritabanında kaç müşteri ya da kaç sipariş olduğunu bilmek, sistemin büyüklüğü ve kullanım yoğunluğu hakkında önemli bilgiler verir. COUNT fonksiyonu bu tip analizlerde temel araçlardan biridir. Bu sorgular, raporlama ve planlama süreçlerinde sıkça kullanılır. 📊

```sql
SELECT COUNT(*) FROM my_company_db.customers;
SELECT COUNT(*) FROM my_company_db.orders;
```

---

##### 🏗️ VIEW Oluşturma

View'lar, sık kullanılan join veya rapor sorgularını tekrar tekrar yazmamak için kullanılır. Bir view oluşturulduğunda, karmaşık bir sorgu basit bir tablo görünümüne dönüşür. Böylece hem okunabilirlik artar hem de yazılım tarafında kod tekrarı engellenir. 🏛️

```sql
CREATE VIEW my_company_db.KESISEN_ORTAK_KAYITLAR AS
SELECT cus."CUSTOMER_ID" c_cusid, cus."FIRST_NAME", cus."LAST_NAME",
       ord."CUSTOMER_ID" o_cusid, ord."ORDER_BRANCH"
FROM my_company_db.customers cus
INNER JOIN my_company_db.orders ord
    ON cus."CUSTOMER_ID" = ord."CUSTOMER_ID";
```

---

##### ⚡ Performans - INDEX Kullanımı

Index, veritabanı performansını doğrudan etkileyen önemli yapılardan biridir. Sık arama yapılan alanlarda index oluşturmak sorgu hızlarını ciddi şekilde artırır. Ancak fazla index kullanımı da güncelleme işlemlerini yavaşlatabileceğinden dengeli kullanılmalıdır. ⚙️

```sql
CREATE INDEX INDEX_CUSTOMER_EMAIL ON my_company_db.customers ("EMAIL");
DROP INDEX my_company_db.INDEX_CUSTOMER_EMAIL;
```

---

##### ✅ Açıklama

Bu rehber, SQL sorgulamayı yalnızca komut ezberlemekten ibaret görmeyip mantığıyla öğrenmek isteyenler için hazırlanmıştır. JOIN yapılarını anlamak, filtreleme tekniklerini doğru kullanmak ve performans optimizasyonlarını bilmek veritabanı yönetiminde büyük avantaj sağlar. 🧠