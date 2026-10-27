package tr.com.huseyinaydin.shared.persistence;

import java.util.List;
import java.util.Optional;

/** Yalnızca okuma yapan istemciler için dar sözleşme. */
public interface ReadOnlyRepository<T, ID> {

    Optional<T> findById(ID id);

    List<T> findAll(PageRequest page);
}
