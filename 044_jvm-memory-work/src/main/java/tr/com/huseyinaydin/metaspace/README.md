### Metaspace nedir? 🧠

Metaspace, JVM’nin yüklediği sınıflara ait class metadata bilgilerinin tutulduğu, Java 8 ile birlikte PermGen’in yerini alan native memory alanıdır. 📚 Burada sınıfın yapısı, method bilgileri, field bilgileri ve runtime’ın sınıfı yönetebilmesi için ihtiyaç duyduğu metadata bulunur.

### Metaspace ne değildir? 🚫

Metaspace, oluşturduğun Person gibi nesnelerin kendisinin veya bu nesnelerin instance field değerlerinin tutulduğu Heap alanı değildir. 🔍 Ayrıca Java kodundaki normal local değişkenlerin ve primitive değerlerin tutulduğu Stack’in de alternatifi değildir.

### Ne için vardır? ⚙️

JVM’nin yüklediği sınıflara ait runtime metadata’yı nesnelerden ayrı bir bellek alanında yönetmesini sağlar. 🧩 Böylece Person, String veya başka bir sınıfın yapısı ve JVM’nin o sınıf hakkında bilmesi gereken bilgiler, o sınıftan oluşturulan instance’lardan ayrı tutulur.

### Hangi soruna çözüm getirir? 🎯

Java 7 ve öncesindeki PermGen alanının sabit boyutlu olması ve yoğun class loading durumlarında OutOfMemoryError: PermGen space oluşturabilmesi önemli bir problemdi. 🚀 Java 8 ile Metaspace’e geçilerek class metadata’nın native memory'de dinamik olarak büyüyebilmesi sağlandı ve bu sabit boyut sınırlaması ortadan kaldırıldı.

### Heap’e dahil midir, hariç midir? 🗂️

Metaspace Heap’in dışındadır ve JVM’nin kullandığı native memory içerisinde yer alır; dolayısıyla -Xmx ile belirlenen Java Heap boyutunun parçası değildir. 💾 Ancak bu, Metaspace’in sınırsız olduğu anlamına gelmez; -XX:MaxMetaspaceSize ile üst sınırı belirlenebilir ve aşılırsa OutOfMemoryError: Metaspace oluşabilir.

### Metadata Nedir?

Metadata, verinin kendisinden ziyade JVM’nin o veriyi nasıl tanıyıp yorumlayacağını ve çalıştıracağını açıklayan bilgidir; örneğin bir sınıfın adı, superclass’ı, method’ları, field’ları ve erişim özellikleri gibi yapısal bilgiler bu kapsamdadır. 🧩📚