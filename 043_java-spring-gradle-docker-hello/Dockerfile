# ☕ Uygulamanın çalışabilmesi için bir Java çalışma ortamı (JDK) gereklidir.
# Amazon Corretto 17, OpenJDK’nın güvenli ve AWS tarafından optimize edilmiş sürümüdür;
# bu sayede hem lisans açısından sorun yaşamam, hem de kararlı bir JVM ortamı elde ederim. 💪
FROM amazoncorretto:17

# 💡 Alternatif olarak OpenJDK 17 kullanılabilir; ancak Corretto, kurumsal projelerde uzun vadeli destek (LTS) avantajı sağlar.
# FROM openjdk:17

# 📦 Bu satırda, projemde derleme sonrası oluşan JAR dosyasının yolunu tanımlıyorum.
# `ARG` ifadesi, JAR ismini sabitlemek yerine dinamik olarak belirlememi sağlar;
# yani hangi isimle JAR üretirsem üretim, Docker bunu otomatik olarak bulup kopyalayabilir. 🧩
ARG JAR_FILE=target/*.jar

# 🚚 Bu komut, belirtilen JAR dosyasını Docker imajının içine kopyalar.
# Artık projemin çalıştırılabilir dosyası konteynerin içinde “application.jar” adıyla yer alır.
# Böylece uygulamayı dış dünyadan tamamen izole biçimde çalıştırabilirim. 🔒
COPY ${JAR_FILE} application.jar

# 🌐 Docker konteynerinin dış dünyayla iletişim kuracağı portu açıyorum.
# Spring Boot varsayılan olarak 8080 portunu dinler, bu yüzden burada da o portu expose ederim.
# Böylece dış kullanıcılar veya servisler, uygulamama HTTP üzerinden erişebilir. 📡
EXPOSE 8080

# 🚀 Bu satır, Docker konteyneri çalıştırıldığında hangi komutun otomatik olarak başlatılacağını belirtir.
# “java -jar application.jar” komutu, JAR dosyamı JVM üzerinde çalıştırır ve uygulamamı canlı hale getirir.
# ENTRYPOINT komutu sayesinde, konteyner kapanırsa uygulama da durur — sistemsel bütünlük korunur. ⚙️
ENTRYPOINT ["java","-jar","application.jar"]