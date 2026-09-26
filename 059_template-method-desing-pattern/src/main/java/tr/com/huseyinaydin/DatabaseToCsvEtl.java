package tr.com.huseyinaydin;

public class DatabaseToCsvEtl extends EtlProcessTemplate {

    @Override
    protected String extractData() {
        System.out.println("Veri, SQL sorgusu ile ilişkisel veritabanından çekiliyor...");
        return "Ad: Ahmet, Yas: 30";
    }

    @Override
    protected String transformData(String rawData) {
        System.out.println("Veritabanı satırları CSV virgül ayrım formatına dönüştürülüyor...");
        return rawData.replace("Ad: ", "").replace(", Yas: ", ",");
    }

    @Override
    protected void loadData(String transformedData) {
        System.out.println("Dönüştürülen veri 'kullanicilar.csv' dosyasına yazılıyor: " + transformedData);
    }
}