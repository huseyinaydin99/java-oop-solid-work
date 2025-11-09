# ☕ Uygulamanın çalışması için Java ortamı gerekiyor.
# Amazon Corretto 17 taban imajı, JDK 17'yi içerir ve güvenli bir çalışma ortamı sağlar.
FROM amazoncorretto:17
# FROM openjdk:17  # Alternatif olarak OpenJDK 17 kullanılabilir.

# 📦 Proje derlendiğinde oluşan JAR dosyasının yolunu belirtir.
# ARG sayesinde JAR ismini sabitlemeden dinamik bir şekilde kullanılabilir.
ARG JAR_FILE=target/*.jar

# 🚚 Derlenen JAR dosyasını Docker imajının/container'in içine kopyalar.
# application.jar ismiyle konteyner içinde kolayca erişilebilir hale getirir.
COPY ${JAR_FILE} application.jar

# 🌐 Konteynerin dış dünyayla iletişim kuracağı portu tanımlar.
# Spring Boot genellikle 8080 portunda çalışır.
EXPOSE 8080

# 🚀 Konteyner başlatıldığında/up olduğunda çalışacak komutu belirtir.
# Bu komut, Java uygulamasını başlatır: java -jar application.jar
ENTRYPOINT ["java","-jar","application.jar"]
