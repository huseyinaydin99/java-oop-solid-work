package tr.com.huseyinaydin._01;

// KURAL 2: 'sealed' -> Kontrollü devam. Sadece KurumsalEmail bundan miras alabilir.
public sealed class EmailBildirim extends Bildirim permits KurumsalEmail {
    @Override
    public void gonder(String mesaj) {
        System.out.println("Email gönderildi: " + mesaj);
    }
}