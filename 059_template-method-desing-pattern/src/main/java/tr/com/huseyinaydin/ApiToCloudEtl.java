package tr.com.huseyinaydin;

public class ApiToCloudEtl extends EtlProcessTemplate {

    @Override
    protected String extractData() {
        System.out.println("Veri, REST API üzerinden JSON formatında alınıyor...");
        return "{\"name\": \"Ayşe\", \"age\": 25}";
    }

    @Override
    protected String transformData(String rawData) {
        System.out.println("JSON verisi bulut sistemine uygun şifreli bir formata çevriliyor...");
        return "ENCRYPTED_DATA_HASH_8832";
    }

    @Override
    protected void loadData(String transformedData) {
        System.out.println("Şifrelenmiş veri AWS S3 Bucket'ına yükleniyor: " + transformedData);
    }

    @Override
    protected boolean shouldSendNotification() {
        return false;
    }
}