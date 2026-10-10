================================================================================
#### 1. SINGLE_TABLE (Tüm hiyerarşi tek tabloda birleşir)

================================================================================

- Her şey tek tabloda toplanır.
- Hangi satırın hangi sınıfa ait olduğu "DTYPE" ile ayrılır.
- Alt sınıflara ait olmayan kolonlar veritabanında "NULL" olarak kalır.

```text
+-------------------------------------------------------------------+
|                          PERSON_TABLE                             |
+----+----------+-----------+-------------------+-------------------+
| ID | DTYPE    | FIRSTNAME | SALARY (Employee) | TITLE (Owner)     |
+----+----------+-----------+-------------------+-------------------+
| 1  | Person   | Hüseyin   | NULL              | NULL              |
| 2  | Employee | Zımzımet  | 10000             | NULL              |
| 3  | Owner    | Hasan     | NULL              | Project Manager   |
+----+----------+-----------+-------------------+-------------------+
```

>Hiçbir tablo birleştirme (JOIN veya UNION) işlemine ihtiyaç duymadığı için veri okuma hızı ve performansı en yüksek stratejidir. Ancak tüm hiyerarşiyi tek tabloya yığdığı için çok fazla boş (NULL) kolon yaratır ve alt sınıflara özel alanlarda veri bütünlüğünü (NOT NULL kısıtlaması) sağlamayı imkansız hale getirir.

================================================================================
#### 2. JOINED (Ortak veriler ana tabloda, özel veriler alt tablolarda)

================================================================================

- Ana sınıf (Person) için bir tablo, alt sınıflar için ayrı tablolar açılır.
- Alt tablolar sadece kendilerine özel verileri tutar ve ana tabloya
  "ID" (Foreign Key) üzerinden bağlanır. (En verimli ve normalize yöntem)

```text
               +-----------------------+
               |     PERSON_TABLE      |
               +----+------------------+
               | ID | FIRSTNAME        |
               +----+------------------+
                 /                  \
  (ID=2)        /                    \         (ID=3)
  +-----------v-----------+      +---v-----------------------+
  |    EMPLOYEE_TABLE     |      |       OWNER_TABLE         |
  +----+------------------+      +----+----------------------+
  | ID | SALARY           |      | ID | TITLE                |
  +----+------------------+      +----+----------------------+
  | 2  | 10000            |      | 3  | Project Manager      |
  +----+------------------+      +----+----------------------+
```
> JOINED stratejisi, ortak verileri ana tabloda tutup sadece alt sınıflara özel verileri ayrı tablolara bölerek veri tekrarını ve gereksiz NULL kolon karmaşasını tamamen ortadan kaldırır. Ayrıca, sınıflar arasındaki hiyerarşik bağı Foreign Key (Yabancı Anahtar) ile fiziksel olarak zorunlu kıldığı için ilişkisel veritabanı standartlarına en uygun ve veri bütünlüğü en yüksek mimariyi sunar.

================================================================================
#### 3. TABLE_PER_CLASS (Her somut sınıf için tüm özellikleri içeren bağımsız tablo)

================================================================================

- Her sınıf (ana sınıf olan Person dahil) kendi bağımsız tablosuna sahiptir.
- Ana sınıfın özellikleri (ID, FIRSTNAME vb.) her alt tablonun içine kopyalanır.
- Tablolar arası JOIN yoktur, veri çekilirken "UNION" (birleştirme) kullanılır.

```text
+----------------------------------+   +--------------------------------------+
|          EMPLOYEE_TABLE          |   |             OWNER_TABLE              |
+----+--------------+--------------+   +----+--------------+------------------+
| ID | FIRSTNAME    | SALARY       |   | ID | FIRSTNAME    | TITLE            |
+----+--------------+--------------+   +----+--------------+------------------+
| 2  | Zımzımettin  | 10000        |   | 3  | Şebelebettin | Project Manager  |
+----+--------------+--------------+   +----+--------------+------------------+
```

#### Aynı seviyede olan alt tabloların alanları birbirine kopyayanmıyor değil mi?

>Hayır, kesinlikle kopyalanmaz; her alt tablo yalnızca kendi özel alanlarını ve ana sınıftan miras aldığı temel alanları barındırır, aynı seviyedeki diğer alt (kardeş) sınıfların alanlarıyla hiçbir bağlantısı yoktur.

>Hiyerarşi ne kadar dallanıp budaklanırsa budaklansın kural değişmez; her yeni somut alt sınıf için yine bağımsız bir tablo oluşturulur ve bu tablo, silsilede kendi üstünde yer alan tüm ata sınıfların özelliklerini kendi içine kopyalayarak barındırır.

>Genellikle en kötüsü TABLE_PER_CLASS stratejisidir, çünkü üst sınıfa ait tüm kolonları her alt tabloya kopyalayarak normalizasyon kurallarını yıkar ve ciddi bir veri tekrarına (duplikasyon) neden olur. Ayrıca, üst sınıf üzerinden genel bir veri çekmek istediğinizde (polimorfik sorgularda) arka planda tüm tabloları birbirine bağlayan çok maliyetli ve yavaş UNION sorguları ürettiği için performansı dibe çeker.