package tr.com.huseyinaydin;

public class Main {
    public static void main(String[] args) {
        Image image = new ProxyImage("yuksek_cozunurluklu_fotograf.jpg");
        System.out.println("Vekil nesne oluşturuldu ancak asıl dosya henüz disken yüklenmedi.");

        image.display();
        System.out.println("---");
        image.display();
    }
}