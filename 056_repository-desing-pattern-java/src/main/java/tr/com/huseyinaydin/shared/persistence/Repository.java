package tr.com.huseyinaydin.shared.persistence;

/** Okuma sözleşmesini yazma yetenekleriyle genişletir. */
public interface Repository<T, ID> extends ReadOnlyRepository<T, ID> {

    /**
     * Yeni nesneyi ekler, var olanı günceller.
     *
     * @throws DuplicateKeyException benzersizlik kuralı ihlal edilirse
     */
    T save(T entity);

    boolean deleteById(ID id);
}
