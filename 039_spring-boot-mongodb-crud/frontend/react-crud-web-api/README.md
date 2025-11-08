#### 📘 React CRUD Uygulaması — Kurs Yönetim Sistemi

Bu proje, **React Class Component yapısıyla** geliştirilmiş tam işlevli bir CRUD (Create, Read, Update, Delete) uygulamasıdır. 🎯  
Arka planda **RESTful API** ile haberleşen bir **Kurs Yönetim Arayüzü** sunar. Her adımı, bir bileşenin sorumluluk prensibiyle yazılmıştır.  
Aşağıda hem mimarisi hem de bileşenlerin görevleri derinlemesine açıklanmıştır. ⚙️

---

#### ⚡ `withRouter` – Yönlendirme Köprüsü

React Router v6 ile gelen **Hook yapısı** (`useNavigate`, `useParams`, `useLocation`) artık Class Component’lerde doğrudan kullanılamaz.  
Bu nedenle `withRouter` adında özel bir **Higher-Order Component (HOC)** oluşturularak, fonksiyonel hook’lar sınıf bileşenlerine aktarılmıştır. 🔄

#### 🎯 Ne Yapar?
- `useLocation` → URL’nin konum bilgisine erişir.
- `useNavigate` → Sayfalar arası yönlendirme sağlar.
- `useParams` → Dinamik rota parametrelerini yakalar.

👉 Böylece, Class Component’ler modern hook’ların gücünü dolaylı yoldan kullanabilir.

---

#### 📝 `AddTutorial` – Yeni Kurs Ekleme Bileşeni

Yeni bir kurs oluşturmak için kullanılan bileşendir.  
Form tabanlı çalışır ve **iki yönlü veri akışını (two-way binding)** `this.state` aracılığıyla yönetir. ⚙️

#### 🔍 Özellikleri:
- Kullanıcıdan **başlık (title)** ve **açıklama (description)** alır.
- `saveTutorial()` metodu ile **POST isteği** atarak veriyi backend’e gönderir.
- `submitted` state’i true olduğunda, form kapanır ve "Gönderim Başarılı" mesajı görünür. 🎉
- `newTutorial()` metodu formu sıfırlar ve yeni girişe izin verir.

🧠 Bu yapı, klasik React form yönetiminin özünü temsil eder:  
Her giriş alanı (input), `state` ile birebir bağlıdır; böylece her değişiklik anında yansır.

---

#### 🧩 `Tutorial` – Kurs Detayı ve Güncelleme Bileşeni

Seçilen kursun ayrıntılarını görüntüler, düzenler veya siler.  
Tam anlamıyla **CRUD’ün "R", "U", "D"** kısımlarını yürütür. ⚒️

#### ⚙️ Temel İşlevleri:
- **Veri Çekme (`getTutorial`)**: ID’ye göre kursu API’den getirir.
- **Durum Güncelleme (`updatePublished`)**: Kursun yayın durumunu açar/kapatır.
- **Veri Güncelleme (`updateTutorial`)**: Kursun başlık ve açıklamasını değiştirir.
- **Silme (`deleteTutorial`)**: Kursu kalıcı olarak siler ve liste sayfasına yönlendirir.

🧭 `withRouter` sayesinde `navigate` ve `params` bilgileri kullanılabilir hale gelir.  
🧩 `setState` yapısı, eski state’i kopyalayarak sadece değişen alanı günceller — bu da **immutability prensibini** korur.

---

#### 📋 `TutorialsList` – Kurs Listesi Bileşeni

Tüm kursları listeler, arama yapılmasını sağlar ve aktif kursu seçer.  
Kullanıcı, bir kursa tıklayarak onun detayına geçebilir.

#### 🔎 Fonksiyonel Özellikler:
- **`retrieveTutorials()`** → Tüm kursları backend’den çeker (GET).
- **`setActiveTutorial()`** → Kullanıcının seçtiği kursu aktif hale getirir.
- **`searchTitle()`** → Başlığa göre arama yapar.
- **`removeAllTutorials()`** → Tüm kursları siler.

🧱 Liste üzerinde gezinmek, kurs seçmek veya toplu silme işlemleri tek merkezden yönetilir.  
Bu yapı, **kapsülleme** ve **sorumluluk ayrımı** ilkelerini net biçimde uygular.

---

#### 🌐 `TutorialDataService` – Veri Katmanı

Tüm HTTP isteklerinin merkezi servisidir.  
React bileşenleri, doğrudan API çağrısı yapmak yerine bu servisle iletişim kurar.

#### 🚀 Metotlar:
- `getAll()` → Tüm kursları getirir.
- `get(id)` → Tek kursu getirir.
- `create(data)` → Yeni kurs ekler.
- `update(id, data)` → Mevcut kursu günceller.
- `delete(id)` → Tek kursu siler.
- `deleteAll()` → Tüm kursları temizler.
- `findByTitle(title)` → Başlığa göre arama yapar.

💡 Bu katman, **separation of concerns** prensibinin canlı örneğidir.  
Sunum (UI) ve veri erişimi (Data) birbirinden tamamen ayrılmıştır.

---

#### 💬 Sonuç

Bu proje, React dünyasında **Class Component mimarisiyle CRUD uygulaması** geliştirmenin adım adım örneğidir.  
Veri akışı, yönlendirme, state yönetimi ve API iletişimi bir zincir gibi birbirine bağlıdır. ⛓️  
Her bileşen, sistemin bir halkasını oluşturur — sade, anlaşılır ve profesyonel. ✨

> 🧭 “Bütün parçalara hakim ol, çünkü yazılım bir orkestradır — her bileşen kendi notasını çalarken sen bestekâr olmalısın.” 🎶
