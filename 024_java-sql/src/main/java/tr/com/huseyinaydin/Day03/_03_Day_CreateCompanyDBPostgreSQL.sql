-- 🌟 Bu sorgu, veri tabanındaki "customers" tablosunda kayıtlı tüm müşterileri ve onların sahip olduğu her bir kolon bilgisini eksiksiz bir şekilde çeker; * sembolü tüm kolonları seçmek için kullanılır ve özellikle tablo yapısını hızlıca görmek istediğiniz durumlarda çok işe yarar 📋🧑‍🤝‍🧑.
SELECT *
FROM my_company_db.customers;

-- 📝 Bu sorgu, tüm müşterileri getirirken yalnızca istediğimiz kolonları, belirlediğimiz sıra ile seçmemizi sağlar; böylece gereksiz veriyi çekmeden sadece analiz veya raporlama için gerekli olan bilgileri elde ederiz 🎯.
SELECT "CUSTOMER_ID", "FIRST_NAME", "LAST_NAME", "COUNTRY", "AGE"
FROM my_company_db.customers;

-- 🏷️ "AS" anahtar kelimesi ile tabloya c lakabını verdik, böylece sorguda tablo adını kısa ve okunabilir bir şekilde kullanabiliriz; uzun tablo isimleri yerine alias kullanmak sorguyu hem temiz hem de anlaşılır kılar 💡.
SELECT c."CUSTOMER_ID", c."FIRST_NAME", c."LAST_NAME", c."AGE", c."COUNTRY"
FROM my_company_db.customers AS c;

-- 🏷️ SQL büyük/küçük harf duyarlılığına esnek davranır; "as" küçük harfle de kullanılabilir, burada tabloya yine kısa bir lakap verdik ve tüm kolonları c. ile prefixleyerek çağırıyoruz 🔖.
SELECT c."CUSTOMER_ID", c."FIRST_NAME", c."LAST_NAME", c."AGE", c."COUNTRY"
FROM my_company_db.customers as c;

-- 🔹 Alias (lakap) kullanımı burada AS anahtar kelimesi olmadan yapılmıştır; SQL bunu da kabul eder, yani tabloya kısa isim verip c. ile kolonlara erişim mümkündür, sorgu yine aynı şekilde çalışır 😎.
SELECT c."CUSTOMER_ID", c."FIRST_NAME", c."LAST_NAME", c."AGE", c."COUNTRY"
FROM my_company_db.customers c;

-- 📊 MAX, MIN, SUM, COUNT, AVG fonksiyonlarıyla yaş istatistiklerini çıkarır; en büyük ve en küçük yaş, toplam yaşların toplamı, yaş kayıtlarının sayısı ve yaş ortalaması gibi bilgileri tek sorguda toplar, veri analizi için çok kullanışlı 🧮.
SELECT MAX("AGE"), MIN("AGE"), SUM("AGE"), COUNT("AGE"), AVG("AGE")
FROM my_company_db.customers;

-- 🔤 Müşteri isimlerini alfabetik olarak inceler; MAX ve MIN fonksiyonları ile isimleri A’dan Z’ye tarayabiliriz ve hangi ismin alfabetik olarak başta veya sonda olduğunu öğrenebiliriz 🅰️➡️🆉.
SELECT MAX("FIRST_NAME"), MIN("FIRST_NAME")
FROM my_company_db.customers;

-- 🔎 Yaşı 22’den büyük olan müşterileri filtreler; WHERE şartı sorgunun hangi satırları alacağını belirler ve böylece gereksiz veriyi çekmeden sadece ilgili kayıtları getirir ✅.
SELECT *
FROM my_company_db.customers
WHERE "AGE" > 22;

-- 🧩 En küçük yaşı bulmak için MIN fonksiyonunu kullanır, sadece tek bir değer döndürür; daha sonra bu değeri kullanarak ilgili müşteri bilgilerini alt sorgu ile çekmek mümkün.
SELECT MIN("AGE")
FROM my_company_db.customers;

-- 🎯 Yaşı tam 22 olan müşterileri getirir; eşitlik operatörü (=) ile belirli bir koşula uyan kayıtlar seçilir, filtreleme ile hassas veri çekimi yapılabilir.
SELECT *
FROM my_company_db.customers
WHERE "AGE" = 22;

-- 🏆 Alt sorgu (subquery) kullanılarak en küçük yaşı bulan ve ardından o yaştaki tüm müşterileri çeken sorgu; alt sorgular bir sorgunun içinde başka sorgu çalıştırarak dinamik filtreleme sağlar ve karmaşık koşullar için çok kullanışlıdır 🔄.
SELECT *
FROM my_company_db.customers
WHERE "AGE" = (
    SELECT MIN("AGE")
    FROM my_company_db.customers
);

-- 📉 Yaşa göre artan şekilde sıralayıp LIMIT 1 ile sadece en genç müşteriyi getirir; ORDER BY sıralama, LIMIT ise kaç kaydı almak istediğimizi belirler.
SELECT *
FROM my_company_db.customers
ORDER BY "AGE" LIMIT 1;

-- 🔢 Toplam müşteri sayısını döndürür; COUNT(*) tüm satırları sayar ve veri tabanındaki toplam kayıt sayısını hızlıca görmek için idealdir.
SELECT COUNT(*)
FROM my_company_db.customers;

-- 🔢 Alias kullanılarak müşteri sayısını daha anlamlı bir kolon adıyla döndürür; AS anahtar kelimesi ile sonucu raporlama veya analiz için daha okunabilir yapabiliriz 🏷️.
SELECT COUNT(*) AS MUSTERI_SAYISI
FROM my_company_db.customers;

-- 🔢 Alias yazımının başka bir şekli; burada AS kullanılmadan direkt isim verilmiş, SQL yine de çalışır.
SELECT COUNT(*) MUSTERI_SAYISI
FROM my_company_db.customers;

-- 🔢 Çift tırnak ile alias kullanımı, özel karakter veya boşluk içeren isimler için gereklidir.
SELECT COUNT(*) "MUSTERI SAYISI"
FROM my_company_db.customers;

-- 🇹🇷 Ülkesi TR olan müşterilerin sayısını filtreler; WHERE şartı ile sadece Türkiye’deki müşteriler sayılır.
SELECT COUNT(*) "MUSTERI SAYISI"
FROM my_company_db.customers
WHERE "COUNTRY" = 'TR';

-- 📋 Tüm müşterileri tekrar listeler; bazen GROUP BY veya filtreleme öncesi tabloyu görmek için kullanışlıdır.
SELECT *
FROM my_company_db.customers;

-- 🌍 DISTINCT kullanılarak tekil ülke sayısı bulunur; tekrar eden değerler göz ardı edilerek kaç farklı ülke bulunduğunu tespit ederiz.
SELECT COUNT(DISTINCT "COUNTRY")
FROM my_company_db.customers;

-- 🌎 GROUP BY ile her ülkedeki müşteri sayısını hesaplar; GROUP BY, verileri belirli bir kolona göre gruplar ve COUNT(*) ile her grubun büyüklüğü bulunur 👥.
SELECT "COUNTRY", COUNT(*)
FROM my_company_db.customers
GROUP BY "COUNTRY";

-- 📋 Tüm müşteriler tablosunu tekrar listeleme; veri kontrolü veya sonuçları doğrulamak için kullanılabilir.
SELECT *
FROM my_company_db.customers;

-- 📊 Her ülkeden müşteri sayısını bulur, ardından ORDER BY DESC ile en çok müşterisi olan ülkeden en az müşterisi olan ülkeye doğru sıralar; GROUP BY ve ORDER BY birlikte kullanılarak anlamlı raporlar oluşturulabilir 🔝⬇️.
SELECT "COUNTRY", COUNT(*) TOTAL_CUSTOMER_COUNT
FROM my_company_db.customers
GROUP BY "COUNTRY"
ORDER BY TOTAL_CUSTOMER_COUNT DESC;

-- 🔢 Benzer şekilde COUNT(*) DESC ile sıralama yapılır, fakat alias kullanılmamıştır; aynı sonucu verir.
SELECT "COUNTRY", COUNT(*)
FROM my_company_db.customers
GROUP BY "COUNTRY"
ORDER BY COUNT(*) DESC;

-- 🏅 HAVING ile filtreleme yapılır; GROUP BY ile grupladığımız ülkelerdeki müşteri sayısı 30’dan fazla olanları seçer ve ardından DESC ile sıralar; HAVING, GROUP BY sonrası koşul kontrolü için kullanılır.
SELECT "COUNTRY", COUNT(*) CUSTOMER_COUNT
FROM my_company_db.customers
GROUP BY "COUNTRY"
HAVING COUNT(*) > 30
ORDER BY CUSTOMER_COUNT DESC;

-- 👀 Alt sorgu ve HAVING birlikte kullanılmıştır; WHERE ile yaşı 25’ten büyük olan müşterileri önce filtreler, sonra GROUP BY ile ülkeye göre sayar, HAVING ile yalnızca 30’dan fazla müşterisi olan ülkeleri seçer ve en sonunda DESC ile sıralar, böylece çok katmanlı veri analizi yapılmış olur.
SELECT "COUNTRY", COUNT(*) CUSTOMER_COUNT
FROM my_company_db.customers
WHERE "AGE" > 25
GROUP BY "COUNTRY"
HAVING COUNT(*) > 30
ORDER BY CUSTOMER_COUNT DESC;

-- 🌍 Çoklu şart: OR operatörü kullanarak TR, DE, CA, RU ülkelerindeki müşterileri filtreler; birden fazla alternatif koşul olduğunda OR ile birleştirilir.
SELECT * FROM my_company_db.customers
WHERE
    "COUNTRY" = 'TR' OR
    "COUNTRY" = 'DE' OR
    "COUNTRY" = 'CA' OR
    "COUNTRY" = 'RU';

-- 🌍 Daha kısa yazım: IN operatörü ile aynı ülkeler seçilir, kod okunabilirliği artar ve uzun OR zincirlerini önler 🏷️.
SELECT * FROM my_company_db.customers
WHERE
    "COUNTRY" IN ('TR' , 'DE',  'CA',  'RU');

-- ❌ Bu ülkeler dışındakileri seçmek için NOT IN kullanılır; belirtilen listede olmayan tüm kayıtlar gelir.
SELECT * FROM my_company_db.customers
WHERE
    "COUNTRY" NOT IN ('TR' , 'DE',  'CA',  'RU');

-- 🔢 Yaşı 18 ile 26 arasında olan müşterileri filtreler; AND operatörü ile birden fazla koşul aynı anda kontrol edilir.
SELECT * FROM my_company_db.customers
WHERE  "AGE" >18  AND "AGE" < 26;

-- 🔢 18 ve 26 dahil olacak şekilde ayarlanmış hali; >17 ve <27 ile sınırlar dahil edilmiş olur.
SELECT * FROM my_company_db.customers
WHERE  "AGE" >17  AND "AGE" < 27;

-- 🔢 Daha standart kullanım: >= ve <= ile aralık belirlenmiş, okunabilirliği yüksek.
SELECT * FROM my_company_db.customers
WHERE  "AGE" >= 18  AND "AGE" <= 26;

-- 🔢 BETWEEN operatörü ile aynı aralık; BETWEEN 18 AND 26 ifadesi 18 ve 26 dahil anlamına gelir.
SELECT * FROM my_company_db.customers
WHERE  "AGE" BETWEEN  18 AND 26;

-- ❌ BETWEEN NOT ile 18 ve 26 arasındaki değerler dışındaki müşteriler alınır.
SELECT * FROM my_company_db.customers
WHERE "AGE" NOT BETWEEN  18 AND 26;

-- 🎯 Belirli isimdeki müşterileri getirir; burada eşleşen isim tam olarak 'Ahmet' olmalıdır.
SELECT * FROM my_company_db.customers
WHERE "FIRST_NAME" = 'Ahmet';

-- 🔡 LIKE ile başlayan harfe göre filtreleme; 'Er%' ifadesi Er ile başlayan tüm isimleri getirir.
SELECT * FROM my_company_db.customers
WHERE "FIRST_NAME" LIKE 'Er%';

-- 🔡 Son kısmı 'er' olan isimleri seçer; '%er' kullanımı ile baş kısmı önemli değil.
SELECT * FROM my_company_db.customers
WHERE "FIRST_NAME" LIKE '%er';

-- 🔍 İsimlerin içinde 'er' geçen tüm kayıtlar; '%er%' hem başta hem ortada hem sonda geçenleri kapsar.
SELECT * FROM my_company_db.customers
WHERE "FIRST_NAME" LIKE '%er%';

-- 🚫 'e' harfi geçenleri al ama 'h' harfi geçenleri dahil etme; AND ve NOT LIKE birlikte koşul kontrolü sağlar.
SELECT * FROM my_company_db.customers
WHERE "FIRST_NAME" LIKE '%e%' AND "FIRST_NAME" NOT LIKE '%h%';

-- 🔗 FOREIGN KEY ekler; ALTER TABLE ile orders tablosuna products tablosundaki PRODUCT_ID ile ilişki kurar, referential integrity sağlar; ON UPDATE/DELETE ile kısıtlamalar belirtilir, NOT VALID ile mevcut veriyi doğrulamadan ekleme yapılabilir.
ALTER TABLE IF EXISTS my_company_db.orders
    ADD FOREIGN KEY ("ORDER_ID")
        REFERENCES my_company_db.products ("PRODUCT_ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;