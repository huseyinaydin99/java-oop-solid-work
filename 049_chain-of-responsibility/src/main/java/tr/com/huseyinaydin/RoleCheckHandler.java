package tr.com.huseyinaydin;

class RoleCheckHandler extends BaseHandler {
    @Override
    public boolean handle(Request request) {
        if (!"ADMIN".equals(request.getRole())) {
            System.out.println("❌ Erişim Reddedildi: Sadece yöneticiler girebilir!");
            return false;
        }
        System.out.println("✅ Yetki onaylandı, sisteme giriş yapıldı!");
        return checkNext(request);
    }
}