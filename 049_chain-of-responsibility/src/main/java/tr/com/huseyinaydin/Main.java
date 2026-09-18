package tr.com.huseyinaydin;

public class Main {
    public static void main(String[] args) {
        BaseHandler validationHandler = new UserValidationHandler();
        BaseHandler roleHandler = new RoleCheckHandler();

        // Zinciri kuruyoruz: Doğrulama -> Yetki Kontrolü
        validationHandler.setNext(roleHandler);

        // İsteği oluşturup zincirin sadece ilk halkasına veriyoruz
        System.out.println("--- Birinci Deneme (Yetkisiz Kullanıcı) ---");
        Request request1 = new Request("ahmet", "USER");
        validationHandler.handle(request1);

        System.out.println("\n--- İkinci Deneme (Yetkili Kullanıcı) ---");
        Request request2 = new Request("ayse", "ADMIN");
        validationHandler.handle(request2);
    }
}