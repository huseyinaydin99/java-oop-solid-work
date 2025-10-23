#### 🐾 Abstract Sınıflar - Soyut Gerçekliğin Somut Gücü

Java’da bazen öyle bir durum olur ki; hem ortak özellikleri tek bir yerde toplamak isteriz hem de bazı davranışların alt sınıflar tarafından mutlaka özelleştirilmesini bekleriz. İşte tam bu noktada abstract (soyut) kavramı devreye girer. 💡

Java’da bazen öyle bir durum olur ki; hem ortak özellikleri tek bir yerde toplamak isteriz hem de bazı davranışların alt sınıflar tarafından mutlaka özelleştirilmesini bekleriz. İşte tam bu noktada abstract (soyut) kavramı devreye girer. 💡

abstract kelimesi aslında “tamamlanmamış” anlamına gelir. Yani bir şeyin var olduğunu biliriz ama nasıl çalışacağını henüz tanımlamamışızdır. Bir abstract sınıf, hem normal (gövdesi olan) metotlar içerebilir, hem de abstract metotlar dediğimiz, sadece imzası olup gövdesi olmayan metotları barındırabilir. Bu sayede bir tür “yarı tamamlanmış şablon” görevi görür. 🧩

Mesela örneğimizde Hayvan sınıfı tam da böyle bir sınıf. Her hayvanın “yemek yemek, su içmek” gibi ortak davranışları var ama “ses çıkarma” biçimi türden türe değişiyor. O yüzden sesVer() metodunu abstract tanımladım. Bu metot, alt sınıflar tarafından override edilmek zorunda. Yani Kedi “miyav”, Köpek “hav”, Kuş “cik” diyecek ama bu kuralı ana sınıf (yani Hayvan) koymuş olacak. 🐶🐱🐦

Peki ya kullanmasaydık? Her sınıfta “sesVer” gibi metotları ayrı ayrı tanımlamak zorunda kalırdık. Kod tekrarı olurdu, düzen bozulurdu, yönetim zorlaşırdı. Abstract sınıflar bu yüzden var: ortak davranışı merkezde toplamak, özel davranışı ise alt sınıflara bırakmak için. ⚙️

##### 🤔 Neden Abstract, Neden Interface?

>Eğer bir grup sınıfın ortak davranışları ve bazı ortak kodları varsa ➡️ Abstract sınıf tercih edilir.
>Eğer sınıflar sadece belirli kurallara (sözleşmeye) uymalıysa, ama ortak kod paylaşımı yoksa ➡️ Interface daha uygundur.

> Örneğimizde TarimOrmanHayvancilikBakanligiKurallari ve SanayiTeknolojiBakanligiKurallari tam bir interface örneği. Çünkü onlar, “şu metotları mutlaka yazacaksın” diyor ama nasıl yapacağını söylemiyor. Yani tam bir sözleşme. 📝

#### ⚡️ Sonuç Olarak

Benim gözümde abstract sınıflar, bir projede sadece teknik bir kavram değil, adeta bir mimari rehberdir. Yazılımda karmaşa büyüdükçe, herkesin kendi tarzında metot yazması projenin dengesini bozar. İşte abstract sınıflar, bu dağınıklığı ortadan kaldırarak disiplin ve düzen getirir. 🧱

Ortak davranışları merkezde toplar, değişmesi gerekenleri alt sınıflara bırakır. Böylece hem kod tekrarı azalır, hem de sistem yeni türler eklendiğinde bile kırılmadan büyüyebilir. Yani sadece bugünü değil, yarını da düşünür. 🔁

Bu yüzden abstract sınıflar, yüzeyde soyut gibi dursa da aslında somut bir yönlendirici güçtür. Bir yazılımın “karakterini” belirleyen, onun iskele sistemidir. Nasıl ki bir bina, temeli olmadan ayakta duramazsa; büyük bir proje de doğru soyutlama (abstraction) olmadan ayakta duramaz. 🏗️

Kısacası, abstract sınıflar bana göre yazılımın görünmeyen omurgasıdır.
Ne kadar sade görünseler de, arkasında bütün sistemin istikrarını ve esnekliğini taşıyan sessiz kahramanlardır. 💪✨


#### 🧠 Kısacacı Gardaş:
- 🔹 abstract → “Şablon sınıf”, hem gövdeli hem soyut metotlar içerir.
- 🔹 interface → “Kurallar listesi”, sadece ne yapılacağını söyler, nasılını değil.
- 🔹 İkisi birlikte kullanıldığında ise kodun mimarisi adeta sanat eserine dönüşür. 🎨
