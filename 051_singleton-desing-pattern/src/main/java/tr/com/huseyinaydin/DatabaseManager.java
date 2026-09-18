package tr.com.huseyinaydin;

public class DatabaseManager {

    // 1. Kendi örneğimizi tutacağımız statik ve volatile değişken (bellek tutarsızlığını önler)
    private static volatile DatabaseManager instance;

    // 2. Dışarıdan 'new' anahtar kelimesiyle üretimi engellemek için private yapıcı metot
    private DatabaseManager() {
        System.out.println("Veritabanı yöneticisi hafızada oluşturuldu!");
    }

    // 3. Tekil instance'a ulaşmak için global erişim noktası (Double-Checked Locking)
    public static DatabaseManager getInstance() {
        if (instance == null) { // İlk kontrol (Performans için kilit beklemeden geçiş)
            synchronized (DatabaseManager.class) {
                if (instance == null) { // İkinci kontrol (Thread güvenliği için)
                    instance = new DatabaseManager();
                }
            }
        }
        return instance;
    }

    public void query(String sql) {
        System.out.println("Sorgu çalıştırılıyor: " + sql);
    }

    // Kullanım Senaryosu
    public static void main(String[] args) {
        // İki farklı referans çağrısı yapsak da aslında aynı bellekteki nesneye ulaşıyoruz.
        DatabaseManager db1 = DatabaseManager.getInstance();
        DatabaseManager db2 = DatabaseManager.getInstance();

        // İkisi de aynı referansı işaret ettiği için "true" dönecektir.
        System.out.println("İki nesne aynı mı? " + (db1 == db2));
    }

}