package tr.com.huseyinaydin;

public class MoneyTransferService {
    // Veritabanı bağlantısı üreten bir metod (örneğin DataSource üzerinden)
    private ConnectionProvider connectionProvider;

    public MoneyTransferService(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public void transfer(String fromId, String toId, double amount) {
        try (UnitOfWork uow = new JdbcUnitOfWork(connectionProvider.getConnection())) {
            AccountRepository repo = uow.getAccountRepository();

            Account fromAccount = repo.findById(fromId);
            Account toAccount = repo.findById(toId);

            fromAccount.withdraw(amount);
            toAccount.deposit(amount);

            repo.update(fromAccount);
            repo.update(toAccount); // Bu noktada henüz veritabanına yazılmadı

            uow.commit(); // Her şey yolunda, tüm değişiklikleri tek seferde kaydet
            System.out.println("Transfer başarıyla gerçekleşti!");

        } catch (Exception e) {
            // uow.close() AutoCloseable sayesinde otomatik çağrılsa da,
            // Hata durumunda rollback mekanizması devreye girmelidir.
            System.err.println("Transfer sırasında hata oluştu, işlem geri alınıyor.");
            // Loglama ve rollback yönetimi...
        }
    }
}