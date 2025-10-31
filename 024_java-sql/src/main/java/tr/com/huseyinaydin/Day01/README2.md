#### PostgreSQL ile Veritabanı Yönetimi ve Örnek Proje 💾🐘

PostgreSQL, güçlü ve açık kaynaklı bir ilişkisel veritabanı yönetim sistemidir ve programcıya veri odaklı uygulamalar geliştirme konusunda büyük bir esneklik kazandırır. 🌟 Bu sistem, veri bütünlüğünü korur, büyük veri kümelerinde yüksek performans sağlar ve farklı tablolar ile ilişkileri güvenle yönetmemize olanak tanır. Eğer veritabanı kullanılmazsa, uygulama veri kaybı riskiyle karşı karşıya kalır, veri yönetimi karmaşık hale gelir ve veri sorgulamaları yavaş ve hataya açık olur. 🔒 PostgreSQL’in ana amacı, programcıya organize, güvenli ve ölçeklenebilir bir veri altyapısı sunmaktır; özellikle müşteri ve ürün gibi ilişkili verileri saklamak ve yönetmek için ideal bir çözümdür. 🛠️ Kullanılması gereken durumlar arasında; kurumsal uygulamalar, finansal sistemler, e-ticaret platformları veya büyük veri analizi gerektiren projeler yer alır. PostgreSQL, tip güvenliği, ACID uyumluluğu ve güçlü SQL desteği ile programcıya veri manipülasyonu ve sorgulama konusunda hız, güvenlik ve kontrol kazandırır. 🚀 Avantajları arasında açık kaynak olması, geniş topluluk desteği, gelişmiş veri tipleri ve ölçeklenebilirlik yer alırken; dezavantajları biraz öğrenme eğrisi ve diğer hafif veritabanlarına göre daha fazla sistem kaynağı tüketimi olabilir. ⚖️ Aşağıdaki örnek proje, bir şirket için müşteri ve ürün tablosu oluşturmayı, kayıt eklemeyi ve verileri listelemeyi gösterir; böylece PostgreSQL’in temel kullanımını somut bir şekilde görebiliriz. 👨‍💻

```sql
-- VERITABANI DB OLUSTURULDU
CREATE SCHEMA my_company_db
    AUTHORIZATION postgres;
-- 🌟 Bu komut, "my_company_db" adında bir veritabanı şeması oluşturur ve tüm yetkileri "postgres" kullanıcısına verir, böylece tabloları güvenle organize edebilirim. 🚀

-- MUSTERILER TABLOSU OLUSTURULDU
CREATE TABLE my_company_db.customers
(
    "ID" serial NOT NULL,
    "FIRST_NAME" character varying(60),
    "LAST_NAME" character varying(60),
    PRIMARY KEY ("ID")
);
-- 📝 Bu tablo, müşteri bilgilerini saklamak için tasarlandı; her müşteri benzersiz bir ID ile tanımlanır ve isimleri kolayca yönetilir. 👥

ALTER TABLE IF EXISTS my_company_db.customers
    OWNER to postgres;
-- 🔒 Sahiplik değişikliği ile tablonun kontrolü "postgres" kullanıcısına verilerek yetki yönetimi güvence altına alınır. 🛡️

-- PRODUCT TABLOSU OLUSTURULDU
CREATE TABLE my_company_db.products
(
    "ID" bigserial NOT NULL,
    "PROD_NAME" character varying(200),
    "PROD_CODE" character varying(45),
    PRIMARY KEY ("ID")
);
-- 📦 Ürünleri saklamak için "products" tablosu oluşturuldu; her ürün benzersiz bir ID ile kaydedilir ve kodu sayesinde kolayca tanınır. 🔖

ALTER TABLE IF EXISTS my_company_db.products
    OWNER to postgres;
-- 🛠️ Bu komut, ürün tablosunun sahibi olarak "postgres" kullanıcısını atar ve yönetim yetkilerini netleştirir. ✅

-- MUSTERI TABLOSUNA KAYITLAR EKLENDI
INSERT INTO my_company_db.customers ("FIRST_NAME", "LAST_NAME") VALUES (Üseyin', 'AYDIN');
INSERT INTO my_company_db.customers ("FIRST_NAME", "LAST_NAME") VALUES ('Akan', 'ASLAN');
INSERT INTO my_company_db.customers ("FIRST_NAME", "LAST_NAME") VALUES ('Leyla', 'Ateş');
-- ✍️ Bu komutlar tabloya üç yeni müşteri ekler; isim ve soyisim bilgileriyle müşteri veri tabanına canlılık kazandırılır. 🌱

-- MUSTERILERI LISTELE GOSTER
SELECT * FROM my_company_db.customers;
-- 🔍 Bu sorgu, tüm müşteri kayıtlarını listeler ve mevcut verileri hızlıca gözden geçirmemi sağlar; veri kontrolü için ideal bir yöntemdir. 📊