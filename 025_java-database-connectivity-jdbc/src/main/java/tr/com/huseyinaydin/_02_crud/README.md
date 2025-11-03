#### 🧩 JDBC CRUD Projesi 1

Bu proje, **Java JDBC** kullanarak temel **CRUD (Create, Read, Update, Delete)** işlemlerinin nasıl yapıldığını göstermektedir. PostgreSQL veritabanı üzerinde çalışan bu örnek, veritabanı bağlantısının nasıl kurulacağını, SQL komutlarının nasıl yürütüleceğini ve işlemlerden sonra bağlantının güvenli şekilde nasıl kapatılacağını öğretir.

---

##### ⚙️ **JDBC (Java Database Connectivity) Nedir?**

**Java** ile veritabanı arasındaki görünmeyen köprüdür 🌉. Aslında o, kodun veriyle konuştuğu dildir — bir bakıma dijital diplomat gibidir 🤝. JDBC sayesinde Java uygulamaları, SQL komutlarını doğrudan veritabanına iletir 🧠➡️💾; tablo oluşturabilir, kayıt ekleyebilir, silebilir veya güncelleyebilir. Bu yapı düşük seviyeli bir API’dir, yani perde arkasındaki tüm detayları elinle yönetirsin: bağlantıyı sen açar, sorguyu sen gönderir, sonucu sen okursun. ⚙️ Bu da geliştiriciye tam kontrol, maksimum esneklik ve doğrudan güç kazandırır. 💪 ORM gibi araçlar işin soyut kısmını yönetirken, JDBC saf çekirdektir — veriyle “çıplak elle” temas etmektir 🔥.

* Veritabanına bağlanır 🔗
* Komut gönderir 💬
* Veriyi çeker veya değiştirir 🔄
* Bağlantıyı kapatır 🔒

Yani JDBC, veritabanıyla “doğrudan temas” eden düşük seviyeli bir API’dir. ORM’ler (Hibernate, JPA vb.) bu işlemleri soyutlarken, JDBC bana **tam kontrol** ve **ham SQL gücü** sunar ⚔️

---

---

##### JDBC ile ORM Farkı?

JDBC, veritabanıyla doğrudan konuşmamı sağlar; her satır SQL’i ben yazar, her bağlantıyı ben yönetirim 🧱 — bu bana tam hakimiyet ama aynı zamanda ağır sorumluluk verir ⚖️.
ORM (Object Relational Mapping) ise bu karmaşayı soyutlar, nesneleri tablo gibi görmemi sağlar; SQL’i perde arkasında kendisi üretir 🎭.
JDBC “ellerimle işlemek” gibiyken, ORM “usta bir hizmetkârın” benim adıma işleri yürütmesidir 🧙‍♂️.
Biri **ham gücü ve kesin kontrolü**, diğeri ise **hız, düzen ve bakım kolaylığını** temsil eder 🚀.

---

##### 💾 **SQL Nedir?**

🧠 **SQL (Structured Query Language)**, veriyi yönetmenin matematiksel dilidir — veritabanına “ne yapması gerektiğini” emreder ama “nasıl yapacağını” söylemez ⚙️.
O, verinin kral yoludur: sorgular, ekler, günceller, siler ve düzenler 👑.
SQL bir programlama dili değildir; algoritmalar değil, **veri mantığı** konuşur — satır, sütun ve ilişki üzerine kuruludur 📊.
Neden vardır? Çünkü veri insanlık tarihinin yeni hammaddesidir ve SQL onun işlenmesini standartlaştırır 🏗️.
SQL olmasaydı her veritabanı kendi diliyle konuşur, sistemler birbiriyle anlaşamazdı; SQL bu kaosu düzenleyen “ortak akıl”dır 🌐.
Kısacası SQL, dijital dünyanın **sessiz ama mutlak otoritesidir** — verinin kaderini belirler 🔮.

---

##### 🧱 **Proje Yapısı**

##### 📁 `AppMain.java`

Uygulamanın giriş noktası.
Burada, `MyCrud` sınıfının metotları çağrılır ve CRUD operasyonları test edilir.

**Örnek SQL komutları:**

```sql
-- Tüm müşterileri listele (READ)
SELECT * FROM public.musteriler;

-- 25 yaşından büyük müşterileri listele (READ)
SELECT * FROM public.musteriler WHERE yasi > 25;

-- ID’si 1 olan müşteriyi güncelle (UPDATE)
UPDATE public.musteriler
SET adi = 'Hüseyin',
    soyadi = 'AYDIN',
    sehir = 'Niğde',
    yasi = 31
WHERE id = 1;

-- Yeni müşteri ekle (CREATE)
INSERT INTO public.musteriler(adi, soyadi, sehir, yasi)
VALUES ('Veli', 'Bal', 'Nevşehir', 33);

-- ID’si 8 olan müşteriyi sil (DELETE)
DELETE FROM public.musteriler WHERE id = 8;
```

##### 📁 `MyCrud.java`

Tüm CRUD işlemlerinin kalbidir ❤️

##### 🟢 `getButunMusteriler(String sql)`

SQL sorgusu gönderir, verileri ResultSet üzerinden okur ve ekrana yazdırır.
`executeQuery()` kullanır çünkü veri **okuma (SELECT)** işlemi yapılır.

##### 🟡 `updateMusteriIdUzerinden(String sql)`

Veritabanında **güncelleme (UPDATE)** işlemi yapar.
`executeUpdate()` metodu kullanılır, çünkü veri değiştirilmektedir.

##### 🔵 `addBirMusteriEkle(String sql)`

Yeni bir kayıt ekler.
`executeUpdate()` burada da kullanılır çünkü tabloya veri **eklenmektedir**.

##### 🔴 `deleteBirMusteriSil(String sql)`

Kayıt silme işlemini yapar.
SQL tarafında `DELETE` komutu kullanılır.

##### ⚪ `deleteUpdateAddMusteri(String sql)`

Hem ekleme, hem silme, hem güncelleme işlemlerine uygun **tek metot**.
Yani çok amaçlı bir işlem yöneticisidir.

---

##### 🧠 **SQL Komutlarının Anlamı**

| Komut    | Anlamı   | Açıklama                          |
| -------- | -------- | --------------------------------- |
| `SELECT` | Oku      | Tabloyu sorgular ve veri getirir. |
| `INSERT` | Ekle     | Yeni kayıt oluşturur.             |
| `UPDATE` | Güncelle | Var olan kaydı değiştirir.        |
| `DELETE` | Sil      | Kaydı tamamen kaldırır.           |

SQL dünyasında bu dört emir bir araya gelince **CRUD** doğar:

> Create 🔨, Read 📖, Update ✏️, Delete ❌

---

##### 🧩 **Bağlantı Yönetimi (Connection Lifecycle)**

Bağlantı açma ve kapama kısmı, veritabanı güvenliği ve kaynak yönetimi açısından en önemli bölümdür.

* 🔓 `isOpenConnection()` → Veritabanına bağlantı açar
* 🔒 `isCloseConnection()` → İşlem bittikten sonra kapatır
* 🧹 Böylece “bağlantı sızıntısı” olmaz ve sistem stabil kalır.

---

##### 🚀 **Projenin Amacı**

Bu proje, JDBC mantığını **elle**, **açık biçimde** anlamak içindir.
ORM’ler gibi soyut yapılar kullanmadan önce, veritabanı işlemlerinin perde arkasında **neler olduğunu** görmek için idealdir.

> Kısaca: Bu proje, veritabanı ile konuşmayı öğrenen bir Java geliştiricisinin “alfabesi”dir. 🔤

---

##### 🧭 **Sonuç**

Bu çalışma bana şunları öğretir:

* Veritabanına bağlanmak, sorgu göndermek ve sonucu okumak
* CRUD döngüsünü kavramak
* Bağlantıyı doğru zamanda kapatmanın önemini anlamak
* SQL ile Java arasındaki etkileşimi gözlemlemek

🧠 **Son söz:**

> Kodun gücü, verinin akışıyla birleşince yazılım gerçekten “yaşamaya” başlar. 🌍✨
