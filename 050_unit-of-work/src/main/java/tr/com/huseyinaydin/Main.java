package tr.com.huseyinaydin;

public class Main {
    public static void main(String[] args) {
        // 1. Veritabanı bağlantı sağlayıcımızı ayağa kaldırıyoruz
        ConnectionProvider provider = new SimpleConnectionProvider();

        // 2. İş mantığı servisimizi, bu sağlayıcı ile başlatıyoruz
        MoneyTransferService transferService = new MoneyTransferService(provider);

        // 3. Unit of Work mimarisinin güvencesiyle işlemi tetikliyoruz
        System.out.println("Para transferi başlatılıyor...");
        transferService.transfer("HESAP_A", "HESAP_B", 1500.0);
    }
}