package tr.com.huseyinaydin;

public abstract class EtlProcessTemplate {

    public final void executeProcess() {
        System.out.println("--- İşlem Başlıyor ---");
        String rawData = extractData();
        String transformedData = transformData(rawData);
        loadData(transformedData);
        if (shouldSendNotification()) {
            sendNotification();
        }
        System.out.println("--- İşlem Tamamlandı ---\n");
    }

    protected abstract String extractData();
    protected abstract String transformData(String rawData);
    protected abstract void loadData(String transformedData);

    protected boolean shouldSendNotification() {
        return true;
    }

    private void sendNotification() {
        System.out.println("Bildirim: ETL süreci başarıyla tamamlandı.");
    }
}