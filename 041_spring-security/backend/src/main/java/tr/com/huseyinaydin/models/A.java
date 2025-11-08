package tr.com.huseyinaydin.models;

public class A {
    /*
    @NotNull(message = "Lütfen kullanıcı adını gir.")
    @Size(min = 4, max = 16, message = "Kullanıcı adınız en az 4 en fazla 16 karakterden oluşmalıdır.")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "Kullanıcı adınız belirtilen karakterlerinden oluşmalıdır.")
    private String username;

    @NotNull(message = "Lütfen e-posta adresini gir.")
    @Size(min = 10, max = 80)
    @Email
    private String email;

    @NotNull(message = "Lütfen şifre gir.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
            message = "{FriendlyMessageCodes__CONSTRAINT_PASSWORD_PATTERN}") //"Şifreniz a-z, A-Z, 0-9 karakterlerinden oluşmalıdır."
    private String password;

    @NotNull(message = "Lütfen telefon numaranızı giriniz.")
    @Size(min = 12, max = 12, message = "Telefon numarası tam 12 karakter olmalıdır.")
    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
            message = "Lütfen geçerli formatta telefon numarası girin.")
    private String phoneNumber;

    @NotNull
    @Pattern(regexp = "^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$",
            message = "Lütfen geçerli bir SSN numarası girin.")
    private String ssn;

    @NotNull(message = "Lütfen doğum tarihinizi girin.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Past //@Past dipnotu, bir tarih alanının bugünden önceki bir zamanı temsil etmesi gerektiğini doğrulamak için kullanılır; örneğin doğum tarihi gibi değerlerin geleceğe ait olmamasını sağlar. ⏰
    private LocalDate birthDay;
    */
}