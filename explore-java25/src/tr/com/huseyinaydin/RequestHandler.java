
/*
Java 25’te gelen Scoped Value, bir iş parçacığı içinde veriyi güvenli ve geçici olarak paylaşmanın modern yoludur.
Eskiden bu iş için kullanılan ThreadLocal, yönetimi zor ve hata yapmaya açık bir yapıydı;
Scoped Value ise bu sorunları ortadan kaldırır. Bir değeri belli bir “kapsam” içinde tanımlarsın ve bu değer
sadece o süre boyunca görünür olur, kapsam bittiğinde otomatik olarak silinir. Böylece bellek sızıntısı ya da
yanlış erişim riski kalmaz. Özellikle virtual thread yapısıyla birlikte kullanıldığında,
Scoped Value kodu daha temiz, daha güvenli ve tahmin edilebilir hale getirir.
 */

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java
 *
 */

//Scoped Value’nin ana amacı, veriyi iş parçacıkları arasında güvenli, geçici ve kontrollü biçimde paylaşmaktır.
private static final ScopedValue<String> REQUEST_ID = ScopedValue.newInstance();

void main() {
    handleRequest(UUID.randomUUID().toString());
}

static void handleRequest(String reqId) {
    ScopedValue.where(REQUEST_ID, reqId).run(() -> {
        log("İsteği işlemeye başladık");
        // işlemi ha burada simule edelim
        authenticate();
        try {
            fetchData();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log("İşleme isteği tamamlandı");
    });
}

static void authenticate() {
    log("İstek doğrulanıyor...");
}

static void fetchData() throws InterruptedException {
    log("Veritabanından veri alınıyor sanki...");

    // Geçerli bağlama değerini yakala
    String currentReqId = REQUEST_ID.get();

    // Alt thread içinde değeri elle yeniden tanımlaman gerekir.
    Thread t = new Thread(() ->
            ScopedValue.where(REQUEST_ID, currentReqId).run(() -> {
                log("Alt thread, ilişkili verileri çekiyor, aslında burada üst thread ile alt thread veri paylaşımı yapıyor, üstten alta veri aktarımı oluyor.");
            })
    );
    t.start();
    t.join(); // Alt thread’in tamamlanmasını bekle.
}

static void log(String message) {
    IO.println("[" + REQUEST_ID.get() + "] " + message);
}