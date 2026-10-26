package tr.com.huseyinaydin._01;

// 1. Ana Mühürlü Sınıfımız
public abstract sealed class Bildirim permits SmsBildirim, EmailBildirim, PushBildirim {
    public abstract void gonder(String mesaj);
}