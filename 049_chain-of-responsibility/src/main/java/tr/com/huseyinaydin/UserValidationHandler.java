package tr.com.huseyinaydin;

class UserValidationHandler extends BaseHandler {
    @Override
    public boolean handle(Request request) {
        if (request.getUsername() == null || request.getUsername().isEmpty()) {
            System.out.println("❌ Hata: Kullanıcı adı boş olamaz!");
            return false;
        }
        System.out.println("✅ Kullanıcı adı geçerli, yetki kontrolüne geçiliyor...");
        return checkNext(request);
    }
}