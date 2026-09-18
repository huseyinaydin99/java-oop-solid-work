package tr.com.huseyinaydin;

public interface AccountRepository {
    Account findById(String id);
    void update(Account account);
}