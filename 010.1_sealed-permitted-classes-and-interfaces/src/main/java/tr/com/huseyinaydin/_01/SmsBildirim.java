package tr.com.huseyinaydin._01;

// KURAL 1: 'final' -> Zincir bitti. Kimse SmsBildirim'den extends edemez.
public final class SmsBildirim extends Bildirim {
    @Override
    public void gonder(String mesaj) {
        System.out.println("SMS gönderildi: " + mesaj);
    }
}