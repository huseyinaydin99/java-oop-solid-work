### 🧩 Docker Login

#### 🔐 Docker Hub hesabına giriş yapmam gerekir; çünkü imajlarımı yükleyebilmem için kimlik doğrulaması zorunludur.  
#### Bu komut, Docker Hub kullanıcı adımı ve şifremi kullanarak terminal üzerinden oturum açmamı sağlar.  
#### Giriş yapıldığında Docker CLI, kimliğimi doğrular ve push/pull işlemlerine izin verir. ⚙️  
```bash
docker login --username huseyin11 --password nahsanasifre
```

#### 💡 Alternatif kısa biçimdir; -u parametresi kullanıcı adını, -p parametresi ise şifreyi belirtir.  
#### Her iki komut da aynı sonucu verir, ancak güvenlik açısından doğrudan terminalde şifre yazmak önerilmez.  
#### Bunun yerine "docker login" komutu girilip, şifre istenince yazmak daha güvenlidir. 🔒  
```bash
docker login -u huseyin11 -p nahsanasifre
```

#### 📂 Projemin bulunduğu dizine giderim; çünkü Docker build komutu, bulunduğum klasördeki Dockerfile üzerinden çalışır.  
#### Bu adım, terminalin doğru konumda olduğunu garanti eder ve dosya yollarının hatasız okunmasını sağlar. 🧭  
```bash
cd PROJENIN_KONUMU
```

### ☕ MAVEN İLE DOCKER İMAJI OLUŞTURMA
#### 🧱 Bu komut, Maven ile derlenmiş projemin jar dosyasını Docker imajına dönüştürür.  
#### --build-arg parametresiyle JAR_FILE değişkenine, oluşturduğum jar dosyasının tam yolunu veririm.  
#### --tag parametresi, imaja özel bir isim ve sürüm etiketi (tag) ekler.  
#### Burada "v001" ilk sürümdür; imaj ismi "huseyin11/042_java-spring-maven-docker-hello" olarak Docker Hub’a gönderilmeye hazır hale gelir. 🚀  
```bash
docker build --build-arg JAR_FILE=target/042_java-spring-maven-docker-hello-1.0.0.jar --tag huseyin11/042_java-spring-maven-docker-hello:v001 .
```

#### 🔁 Aynı projenin yeni versiyonunu oluşturduğumda, sadece JAR dosyasını ve tag sürümünü değiştiririm.  
#### Bu şekilde Docker Hub üzerinde versiyon takibi yapabilirim.  
#### v002, önceki imajın geliştirilmiş sürümünü temsil eder — Docker, sürümler arası farkları “layer” bazında optimize eder. ⚡  
```bash
docker build --build-arg JAR_FILE=target/042_java-spring-maven-docker-hello-1.0.2.jar --tag huseyin11/042_java-spring-maven-docker-hello:v002 .
```

### ⚙️ GRADLE İLE DOCKER İMAJI OLUŞTURMA
#### 📦 Bu komut, Gradle tabanlı bir Spring Boot projesinden Docker imajı oluşturur.  
#### build/libs klasörü, Gradle tarafından oluşturulan jar dosyalarının varsayılan dizinidir.  
#### Yine aynı şekilde --build-arg ile JAR yolunu belirtip, --tag ile versiyon etiketi eklerim.  
#### v001 etiketiyle, Gradle projemin ilk Docker imajını oluştururum. 🧱  
```bash
docker build --build-arg JAR_FILE=build/libs/043_java-spring-gradle-docker-hello-1.0.0.jar --tag huseyin11/043_java-spring-gradle-docker-hello:v001 .
```

#### 🔄 Gradle projemin yeni sürümünü oluşturduğumda, jar dosyasının versiyonunu değiştirip imajı yeniden oluştururum.  
#### Bu yaklaşım, Continuous Integration (CI/CD) süreçlerinde sürüm takibini kolaylaştırır.  
#### Docker her sürümü ayrı bir “katman” olarak sakladığından, depolama ve yükleme işlemleri daha verimli olur. 💪  
```bash
docker build --build-arg JAR_FILE=build/libs/043_java-spring-gradle-docker-hello-1.0.2.jar --tag huseyin11/043_java-spring-gradle-docker-hello:v002 .
```
---