#### Java'da Encapsulation Kapsülleme

Bu çalışmamda 💻 Java’da POJO (Plain Old Java Object) yapısını kullanarak HesapCuzdani adında tamamen kapsüllenmiş 🔒 bir model sınıfı tasarladım. Sınıfta müşteri bilgilerini (ad, soyad, şube, hesap numarası, IBAN, para cinsi, bakiye vb.) private alanlar olarak tuttum ve bu verilere sadece getter ve setter metodlarıyla erişim sağladım 🔑. Farklı senaryolarda nesne oluşturabilmek için üç ayrı yapıcı metot (constructor) tanımladım: parametresiz, iki parametreli ve tüm parametreli. toString() metodunu override ederek 🧩 nesneleri ekrana yazdırdığımda okunabilir bir çıktı elde ettim, böylece debug ve test aşamaları kolaylaştı. main metodunda üç farklı HesapCuzdani nesnesi oluşturarak 🧠 hem kapsülleme mantığını hem de nesne tabanlı yaklaşımı gösterdim. Son olarak kendi yazdığım getBilgiVer() metoduyla sınıfa özgü bir davranış ekleyip, nesne yönelimli programlamanın temel ilkesi olan veri + davranış birlikteliğini somutlaştırdım 🚀.

#### POJO (Plain Old Java Object):
Hiçbir özel Java kuralına, framework’e ya da miras yapısına bağlı olmayan, sade veri taşıyıcı (data holder) sınıflardır. Yani; sadece alan (field), getter/setter, ve gerekirse toString, equals, hashCode gibi temel metotlar içerir.

Model sınıfı ise genelde aynı POJO yapısına sahiptir, ancak genellikle veritabanındaki tabloyu temsil eder (örneğin ORM yapılarında @Entity olarak işaretlenir).

#### Encapsulation (Kapsülleme):
Veri gizliliği ve kontrolünü sağlar. Alanlar private olur, dışarıdan erişim getter/setter metodları ile sağlanır. Böylece hem veri koruma hem de istenirse kontrol (ör. negatif değer engelleme) yapılabilir.
Encapsulation’ın ana amacı, veriyi dış müdahalelerden koruyarak 🔒 yalnızca kontrol edilmiş yollarla erişim sağlamaktır. Böylece hem veri bütünlüğü korunur hem de nesneler üzerinde istenmeyen değişikliklerin önüne geçilerek güvenli ve bakımı kolay bir yapı oluşturulur 🧠.

#### 🧠 Object Sınıfından Gelen Metotlar ve Anlamları

Tüm sınıflar Java’da doğrudan veya dolaylı olarak Object sınıfından türetilir.
Bu nedenle her sınıf bu temel metotları miras alır:

##### 🔹 toString()
Nesneyi okunabilir bir metin biçimine dönüştürür, genellikle debug 🧠 veya loglama sırasında nesnenin içeriğini görmek için kullanılır.
Eğer override edilmezse, sınıf adı ve bellek adresi benzeri bir değer döndürür 📜.

##### 🔹 equals(Object obj)
İki nesnenin içerik olarak birbirine eşit olup olmadığını kontrol eder ⚖️.
Varsayılan hali referans karşılaştırması yapar ama genelde anlamlı eşitlik için override edilir 🧩.

##### 🔹 hashCode()
Nesneye özel bir tamsayı değeri (hash değeri) üretir 🔢.
Bu değer HashMap, HashSet gibi veri yapılarında hızlı erişim için kullanılır ⚙️.

##### 🔹 getClass()
Nesnenin çalışma anındaki sınıf bilgisini (runtime class) döndürür 🧱.
Reflection veya tip kontrolü yapılırken kullanılır 🔍.

##### 🔹 clone()
Nesnenin kopyasını oluşturur (shallow copy) 🧬.
Ancak kullanılabilmesi için sınıfın Cloneable arayüzünü implement etmesi gerekir ⚠️.

##### 🔹 finalize()
Nesne çöp toplayıcı (Garbage Collector) tarafından silinmeden hemen önce çağrılır 🧹.
Kaynak serbest bırakma işlemleri için kullanılırdı ancak Java 9’dan itibaren deprecated durumundadır 🚫.

##### 🔹 wait()
Bir thread’in (iş parçacığının) belirli bir koşul gerçekleşene kadar beklemesini sağlar ⏳.
Sadece synchronized blok veya metot içinde çağrılabilir 🔒.

##### 🔹 notify()
Bekleyen (wait durumundaki) bir thread’i uyandırır 🔔.
Bu sayede thread yeniden çalışmaya devam eder 🚀.

##### 🔹 notifyAll()
Bekleyen tüm thread’leri uyandırır 🗣️.
Thread senkronizasyonu yapılan ortamlarda, tüm bekleyen işlemleri devam ettirmek için kullanılır ⚙️.