package tr.com.huseyinaydin;

public class Main {
    public static void main(String[] args) {
        System.out.println("""
            🚀 Gradle Nedir ve Ne İşe Yarar? 💻
    
            Gradle, Java ve diğer JVM dilleri başta olmak üzere çok çeşitli projeleri
            derlemek, yönetmek ve otomatikleştirmek için kullanılan modern bir build aracıdır ⚡. 
            Maven gibi bağımlılık yönetimi ve build sürecini standartlaştırır, ancak DSL
            (Domain Specific Language) temelli esnek yapı sayesinde daha karmaşık ve modüler
            projelerde üstün kontrol sağlar 🛠️. Gradle bir IDE veya derleyici değildir ❌; tek
            başına kod yazdırmaz, projeyi organize eder ve build süreçlerini otomatikleştirir. 
            Amacı, proje yönetimini kolaylaştırmak, bağımlılıkları kontrol etmek, 
            tekrarlanabilir ve hızlı build’ler sağlamak 🔄. Alternatifleri arasında Maven, Ant ve SBT bulunur.
    
            Projeler Gradle ile build.gradle veya build.gradle.kts dosyası üzerinden tanımlanır 📜;
            bu dosya proje bilgilerini, bağımlılıkları ve yapılandırmaları saklar, build süreçlerini
            otomatikleştirir. Gradle 2007 yılında çıktı ve özellikle büyük, karmaşık veya çok modüllü
            projelerde ve sürekli entegrasyon/delivery pipeline’larında tercih edilir 🚀.
    
            Gradle, neredeyse tüm popüler IDE’ler (IntelliJ IDEA, Eclipse, NetBeans, VS Code vb.)
            tarafından desteklenir 🖥️, böylece proje yapılandırması ve bağımlılık yönetimi IDE üzerinden
            de sorunsuz yapılabilir ✔️. Proje mimarisini modüler ve düzenli tutar, alt projeler, kütüphaneler
            ve kaynak dizinlerini organize eder 📂. Ayrıca sürekli entegrasyon ve dağıtım süreçleriyle
            entegre çalışarak, build, test ve deploy aşamalarını otomatikleştirir, insan hatasını minimize eder 👊.
            """);

        System.out.println("""
            ⚖️ Maven vs Gradle: Hangisini Ne Zaman Kullanmalı? 💻📦
    
            Maven, standart ve öngörülebilir bir build süreci isteyen kurumsal projelerde güçlüdür ✅;
            bağımlılık yönetimi kolay, öğrenmesi hızlı ve pom.xml ile projeyi tek tip organize eder.
            Dezavantajı ise esnekliği sınırlıdır ❌; karmaşık veya çok modüllü projelerde yapılandırması zahmetli olabilir.
    
            Gradle ise DSL temelli, esnek ve hızlı bir build aracıdır ⚡; büyük, modüler, çok katmanlı projelerde
            ve sürekli entegrasyon/delivery pipeline’larında üstün kontrol sağlar. Dezavantajı, öğrenme eğrisinin
            Maven’e göre daha dik olmasıdır 📈.
    
            Özetle: basit, standart ve kurumsal projelerde Maven, karmaşık, hızlı ve esnek yapıda projelerde Gradle
            tercih edilir. Her iki araç da neredeyse tüm IDE’ler tarafından desteklenir 🖥️,
            proje yönetimini, bağımlılık kontrolünü ve otomatik build/test/deploy süreçlerini
            sorunsuz hale getirir 👊.
            """);

        System.out.println("""
            Merhabalar, ben Javayım. Ben, Sun Microsystems’in vizyoner mühendisleri tarafından geliştirildim;
            sıradan bir dil değilim. Hem derlenebilen hem yorumlanabilen, yüksek performanslı ve platform bağımsız
            bir programlama ortamıyım, çünkü kodlarım derlendiğinde bytecode adı verilen evrensel bir form alır
            ve JVM sayesinde farklı işletim sistemlerinde ve donanımlarda aynı şekilde çalışır. Kodlarım
            paketlendiğinde ise class dosyaları haline gelir ve JAR veya WAR dosyaları olarak dağıtılabilir,
            bu da beni hem taşınabilir hem de büyük sistemlerde kullanılabilir kılar.
            Benim tarihçem de bir serüven: Önce OAK, sonra Green, ve sonunda Java adını aldım;
            1995 yılında dünyaya merhaba dedim. Dünyadaki milyonlarca geliştirici, kurumsal sistem,
            mobil uygulama ve gömülü cihaz benimle şekillendi; ben sadece bir programlama dili değilim,
            aynı zamanda teknoloji ekosisteminin temel taşlarından biriyim. 
            Hüseyin AYDIN ile aramızda ay farkı olabilir ama ben sürekli güncellenen, 
            ve Hüseyin gibi eski kafa olmayan modern yazılım dünyasının kalbiyim.
            Ana amacım net: Geliştiricilere “bir kez yaz, her yerde çalıştır” özgürlüğü sunmak, güvenli,
            ölçeklenebilir ve sürdürülebilir yazılımlar oluşturmayı mümkün kılmak. 
            Ben, öğrenmekten korkmayanların, yeniliğe açık olanların, büyük kurumların ve büyük hayaller kuranların diliyim. 🌟💻🔥
            """);

        //Java 25 ile ama biz 17'deyiz şu an olsun.
        /*IO.println("""
                Her sene bu konu gündeme geliyor.

                “Java güncelliğini yitirdi.”
                "Java çok ayrıntılı."
                "Artık kimse Java kullanmıyor."

                Ve her yıl gerçek bunun tam tersini kanıtlıyor.

                ✅ Google hala Java kullanıyor.
                ✅ Netflix hala Java kullanıyor.
                ✅ AWS hala Java kullanıyor.
                ✅ Fortune 500 şirketleri, büyük ve kritik öneme sahip sistemleri desteklemek için Java'ya güveniyor.

                Yakın zamanda, yeni diller hakkındaki bitmek bilmeyen abartılara rağmen Java popülerlik endekslerinde (TIOBE, Stack Overflow) en üst sıralarda yer almaya devam ediyor.
                Doğrusu?

                Programlama dilleri, sadece bir eğitim kampında birkaç kişinin onları korkutucu bulması yüzünden "ölmez".

                Java mükemmel değildir, hiçbir dil mükemmel değildir.

                Ancak birçok işletmenin bir gecede terk etmeyeceği bir ekosistemi, araçları ve onlarca yıllık savaşta test edilmiş kütüphaneleri var.

                Eğer hangi dili öğreneceğinize karar veriyorsanız, benim bakış açım şu şekilde:

                💡 İşe uygun aracı seçin, trendi değil.
                💡 Temelleri anlamaya odaklanın. Sözdizimi her zaman öğrenilebilir.
                💡 Önce mühendis ol, sonra dil tutkunu.
                """);*/
    }
}
