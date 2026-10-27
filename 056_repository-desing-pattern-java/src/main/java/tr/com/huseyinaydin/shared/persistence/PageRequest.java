package tr.com.huseyinaydin.shared.persistence;

public record PageRequest(int page, int size) {

    private static final int MAX_SIZE = 500;

    public PageRequest {
        if (page < 0) {
            throw new IllegalArgumentException("Sayfa numarası negatif olamaz: " + page);
        }
        if (size < 1 || size > MAX_SIZE) {
            throw new IllegalArgumentException("Sayfa boyutu 1 ile " + MAX_SIZE + " arasında olmalı: " + size);
        }
    }

    public static PageRequest of(int page, int size) {
        return new PageRequest(page, size);
    }

    public long offset() {
        return (long) page * size;
    }
}
