#### 🎨 Decorator Design Pattern

Decorator, mevcut bir nesnenin kodunu değiştirmeden ona dinamik olarak yeni davranışlar eklemek için kullandığım tasarım şablonudur.

##### Temel fikir: 

Bir sınıfa sürekli yeni özellikler ekleyip sınıfı büyütmek yerine, ihtiyacım olan özellikleri Decorator nesneleriyle üst üste sararak eklerim. Böylece Open/Closed Principle'a uygun şekilde mevcut kodu değiştirmeden davranışı genişletmiş olurum.

Üst üste sarmak, bir decorator’ın başka bir Coffee nesnesini içine alması ve onun üzerine kendi davranışını ekleyerek yeni bir Coffee oluşturmasıdır.

##### 🎯 Ana amacı nedir?

Decorator'ın ana amacı, mevcut bir nesnenin kodunu değiştirmeden veya sınıfı kalıtımla büyütmeden, ona ihtiyaç oldukça yeni davranışlar eklemektir; yani davranışı nesnenin etrafına katmanlar ekleyerek dinamik biçimde genişlettim. 🧩

##### 🛠️ Hangi soruna çözüm getirir?

Aynı sınıfa sürekli yeni özellikler eklemek gerektiğinde sınıfın gereksiz şekilde büyümesi ve her özellik kombinasyonu için ayrı alt sınıflar oluşturma problemini çözer; böylece kalıtımın oluşturduğu sınıf patlaması yerine composition kullanarak özellikleri istediğim gibi birleştirdim. 🔄

##### 📌 Hangi durumlarda kullanılmalıdır?

Bir nesneye çalışma zamanında farklı özellikleri bağımsız ve birbiriyle kombinlenebilir şekilde eklemek, bu özellikleri tek tek veya üst üste kullanmak ve mevcut sınıflara dokunmadan davranışı genişletmek istediğim durumlarda Decorator'ı kullanmalıyım. ⚙️

##### 🔗 Polymorphism ile ilişkisi nedir?

Decorator'ın polymorphism ile ilişkisi, hem gerçek nesnenin (SimpleCoffee) hem de decorator'ların (MilkDecorator, SugarDecorator) aynı Coffee interface'ini kullanmasıdır; böylece Coffee türünde bir referansla farklı nesneleri birbirinin yerine kullanabildim ve hangi davranışın çalışacağını çalışma zamanındaki gerçek nesne belirledi. 🔄

>Bir interface kendisini implemente eden bir sınıftan yaratılan bir instance'ın bellek referansını elinde tutabilir bundan dolayı ne verirsen eliynen o gelir seniynen. İşte interface ile polymorphism budur. Camiye yardım (-:

---

##### Örnek olarak temel bir Coffee tanımlayalım:

```java
public interface Coffee {
    String getDescription();
    double getCost();
}
```

Bu arayüz, hem gerçek kahvenin hem de kahveye sonradan eklenecek decorator'ların uyması gereken ortak yapıyı belirler.

Temel nesne
```java
public class SimpleCoffee implements Coffee {

    @Override
    public String getDescription() {
        return "Kahve";
    }

    @Override
    public double getCost() {
        return 50.0;
    }
}
```

SimpleCoffee, sistemdeki en temel nesnedir; herhangi bir ekstra özelliği yoktur ve başlangıç maliyetini temsil eder.

Decorator'ın temel yapısı

```java
public abstract class CoffeeDecorator implements Coffee {

    protected final Coffee coffee;

    protected CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
}
```

Burada decorator da Coffee olduğu için(yani Coffee interface'ini implemente ettiği için), normal bir kahve beklenen her yerde kullanılabilir(MilkDecorator da Coffee interface’ini uyguladığı için Coffee türünde bir parametre alan metoda doğrudan verilebilir); ayrıca içeride başka bir Coffee tutarak onun davranışını genişletebilir.

Süt Decorator'ı 🥛

```java
public class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Süt";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 10.0;
    }
}
```

Burada mevcut kahvenin davranışını değiştirmedim; onun üzerine süt özelliğini ekleyerek yeni bir davranış oluşturdum.

Şeker Decorator'ı 🍬

```java
public class SugarDecorator extends CoffeeDecorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Şeker";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 5.0;
    }
}
```

Şeker de aynı yapıyı kullanıyor; böylece yeni ekstralar eklemek için SimpleCoffee sınıfına dokunmam gerekmiyor, orijinalliği bozulmuyor kod değişime kapalı ama gelişimede açık oluveriyor.

Kullanımı

```java
public static void main(String[] args) {
    Coffee coffee = new SimpleCoffee();

    coffee = new MilkDecorator(coffee);
    coffee = new SugarDecorator(coffee);

    System.out.println(coffee.getDescription());
    System.out.println(coffee.getCost());
}
```

Çıktı:

> Kahve, Süt, Şeker
> 65.0

En önemli nokta: Decorator'ları birbirinin üzerine sarabiliyorum. SugarDecorator, MilkDecorator'ı; MilkDecorator da SimpleCoffee'yi sarıyor ve her katman kendisine ait davranışı ekliyor.

🎯 Fikir;

Decorator ile kalıtım kullanarak sınıf sayısını sürekli artırmak yerine, nesneleri birbirine sararak davranışları çalışma zamanında birleştirdim. Böylece mevcut sınıfları değiştirmeden yeni özellikler ekledim ve Open/Closed Principle'a uygun, daha esnek ve kompozisyon tabanlı bir yapı kurdum.

>SimpleCoffee nesnesi var; MilkDecorator oluşturulurken bu nesne içine enjekte ediliyor, ardından SugarDecorator oluşturulurken de MilkDecorator içine alınıyor. SugarDecorator.getDescription() çağrıldığında çağrı zinciri içten dışa doğru ilerliyor: önce MilkDecorator, kendi içindeki SimpleCoffee'nin getDescription() metodunu çağırıyor; SimpleCoffee sonucu döndürdükten sonra Milk kendi bilgisini, ardından Sugar da kendi bilgisini ekliyor.

```text
                     DECORATOR — ÜST ÜSTE SARMA
                     ===========================

Oluşturma / Enjeksiyon
──────────────────────────────────────────────────────────────►

SimpleCoffee
    │
    │  new MilkDecorator(simpleCoffee)
    ▼
MilkDecorator
    │
    │  new SugarDecorator(milkDecorator)
    ▼
SugarDecorator


Çağrı Zinciri
──────────────────────────────────────────────────────────────►

SugarDecorator
      │
      │ getDescription()
      ▼
MilkDecorator
      │
      │ getDescription()
      ▼
SimpleCoffee
      │
      │ "Kahve"
      ▼
MilkDecorator
      │
      │ + ", Süt"
      ▼
SugarDecorator
      │
      │ + ", Şeker"
      ▼
"Kahve, Süt, Şeker"


          DIŞARIDAN İÇERİ              İÇERİDEN DIŞARI
          ──────────────              ───────────────
          SugarDecorator   ───────►   SimpleCoffee
          MilkDecorator    ───────►   "Kahve"
          SimpleCoffee                  ↓
                                       "Kahve, Süt"
                                           ↓
                                       "Kahve, Süt, Şeker"
```

>SugarDecorator, MilkDecorator'ı inherit etmez; onu Coffee olarak içinde tutar ve çağrıyı içteki nesneye aktararak kendi davranışını sonucun üzerine ekler.

