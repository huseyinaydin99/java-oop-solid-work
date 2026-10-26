package tr.com.huseyinaydin._01;

// KURAL 3: 'non-sealed' -> Mühür kırıldı! Artık isteyen her sınıf PushBildirim'den miras alabilir.
public non-sealed class PushBildirim extends Bildirim {
    @Override
    public void gonder(String mesaj) {
        System.out.println("Push bildirim gönderildi: " + mesaj);
    }
}