//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java
 *
 */

int i = 10;

void main() {
    System.out.println("Merhaba dünya");
    System.out.println("Değer i değişkeni: " + i);
    doSomething();
}

public void doSomething() {
    System.out.println("Doing something metodu çalıştı!");
}

//Basit görünümlü, sınıf içinde olmayan metotlar ve main metodu. Adeda C dili gibi.
/*
Bu kod, Java 25’in top-level statements özelliğini kullanıyor; yani artık sınıf tanımı olmadan main ve metodlar yazılabiliyor.
i değişkeni tanımlanıyor, main içinde ekrana mesajlar yazdırılıyor ve doSomething() çağrılıyor; doSomething() kendi mesajını
konsola basıyor. Bu sayede küçük, doğrudan çalışan ve C tarzı script gibi bir Java 25 örneği ortaya çıkıyor.
*/