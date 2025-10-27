#### 🌀 DO-WHILE & TRY-CATCH-FINALLY BİRLİKTELİĞİ

---

##### 🔁 DO-WHILE NEDİR?

`do-while`, Java dünyasında “**en az bir kere çalışmayı garanti eden**” özel bir döngüdür.  
Normal `while` döngüsünde şart **önce** kontrol edilir, sonra çalışır.  
Ama `do-while` tam tersine, “**önce çalışırım, sonra duruma bakarım**” der. 😎

Bu sayede, ister doğru ister yanlış bir durum olsun, döngü **en az bir defa çalışır.**  
Bu benim için kullanıcı etkileşimli uygulamalarda, özellikle “girdi alma” veya “işlem denetleme” gibi durumlarda büyük avantaj sağlar.

---

##### ⚙️ BU KODDA NE YAPIYORUM?

Benim amacım, kullanıcıya bir mini hesap makinesi deneyimi sunmak:  
1️⃣ İki sayı alıyorum.  
2️⃣ Hangi işlemi yapmak istediğini soruyorum.  
3️⃣ Kullanıcının seçimine göre sonucu gösteriyorum.

Ancak işler her zaman planlandığı gibi gitmeyebilir. 🙃  
Kullanıcı yanlış girdi yapabilir, sıfıra bölme hatası olabilir, ya da giriş akışı bozulabilir.  
İşte burada `try-catch-finally` devreye giriyor! ⚡

---

#### 🧩 DO-WHILE İLE TRY-CATCH NASIL BİRLEŞTİ?

Ben aslında iki katmanlı bir kontrol sistemi kurdum:

##### 🧱 1. Katman: DIŞ DO-WHILE
Bu katman, **programın genel akışını kontrol ediyor.**  
Yani uygulama hata alsa bile `try-catch` sayesinde çökmeden devam ediyor.  
Her çalışmadan sonra `finally` bloğunda “Teşekkür ederiz” mesajını gösteriyorum,  
ardından `hataDurumFlag = false;` diyerek döngüyü sonlandırıyorum.

Bu, “her koşulda düzgün kapanan bir sistem” mantığıdır.  
Tıpkı bir servis programının, hata alsa da kapanış işlemlerini düzgün yapması gibi. 🧰

---

##### 🔂 2. Katman: İÇ DO-WHILE
Bu katmanda, kullanıcıdan hangi işlemi yapmak istediğini alıyorum.  
Burada `do-while`, “kullanıcı en az bir kere işlem seçimi yapmalı” prensibiyle çalışıyor.  
Yani kullanıcı hiç seçim yapmasa bile, döngü bir kez çalışır ve seçim ister.

Ancak `hataDurumFlag` dış döngüde `false` yapıldığı için,  
bu iç döngü sadece bir defalık etkileşim için tasarlanmış durumda.

---

##### 💥 TRY-CATCH-FINALLY ÜÇLÜSÜNÜN ROLÜ

| Blok | Görev | Duygusal Yorumu 😅 |
|------|--------|--------------------|
| `try` | Riskli kodları (örneğin kullanıcı girişi veya matematiksel işlem) güvenle dener. | “Bir şeyler ters gidebilir ama denemekten vazgeçmem!” |
| `catch` | Her türlü hatayı yakalar, kontrolsüz çöküşü engeller. | “Sorun varsa çözüm de vardır.” |
| `finally` | Ne olursa olsun çalışır, kapanışı şık yapar. | “Son sözü ben söylerim.” |

---

#### 🔒 SONUÇ: DO-WHILE + TRY-CATCH-FINALLY = KONTROLLÜ GÜVENLİ DÖNGÜ

Bu birleşim sayesinde:
- Kodum **her zaman en az bir kez** çalışıyor ✅
- Hatalar **yakalanıyor ve yönetiliyor** 💪
- Sistem **düzgün kapanıyor** ve kullanıcıya teşekkür ediliyor 🎯

Yani aslında ben burada, **bir döngü yapısı ile hata yönetimini evlendirip**,  
stabil, kullanıcı dostu bir etkileşim akışı kurmuş oluyorum. 🧩

---

> 🗣️ *Kısaca:*  
> “Bu kod sadece işlem yapmıyor; hata karşısında da sakin kalmayı, mantıklı düşünmeyi ve daima düzgün bir kapanış yapmayı öğretiyor. Kod yazmıyor kodla(sevgilim Java) adeta sevişiyorum. (:” 💭💡