#### 🧩 1️⃣ Önce projemi derledim, tertemiz hale getirdim.
#### Testleri atlayarak sadece jar dosyasını oluşturuyorum, çünkü Docker’da testlere gerek yok.
mvn clean package -DskipTests

#### 🐳 2️⃣ Şimdi projemi dockerize ettim!
#### Docker’a “benim imajımı bu Dockerfile’dan üret” diyorum.
#### İsmine myapp dedim, istersem sonra farklı bir etiketle sürüm de verebilirim.
docker build -t myapp .

#### 🔍 3️⃣ Merak ettim, gerçekten imaj oluşmuş mu diye kontrol ettim.
#### Tüm imajları listeliyorum, benim myapp orada görünüyorsa işlem tamamdır.
docker images

#### 🚀 4️⃣ Artık çalıştırma zamanı! 🎯
#### 8080 portunu dış dünyaya açarak uygulamamı ayağa kaldırıyorum.
#### -d parametresiyle arka planda sessizce çalışmasını sağlıyorum.
docker run -d -p 8080:8080 myapp

#### 🧭 5️⃣ “Gerçekten çalışıyor mu?” diye baktım.
#### Aktif konteynerleri listeliyorum, ID’siyle birlikte çalışan uygulamamı görebiliyorum.
docker ps

#### 🧹 6️⃣ İşi bitince durdurmak istedim.
#### container_id yazdığımda o konteyneri nazikçe kapatıyorum. 🔚
docker stop <container_id>

#### 🧼 7️⃣ Tamamen silmek istersem bu komutu kullanıyorum.
#### Konteyneri kaldırıp tertemiz bir ortam bırakıyorum. ✨
docker rm <container_id>