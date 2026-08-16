package tr.com.huseyinaydin.javaflag;

public class JavaFlag {

    /*
    Java’da flag, programın belirli bir davranışını kontrol etmek için kullandığımız
    genel bir kontrol değeri/parametresidir; özel bir Java dil özelliği değildir.

    JVM flag ise JVM’in çalışma davranışını yapılandırmak için JVM’e dışarıdan
    verdiğimiz -X veya -XX gibi seçeneklerdir.
     */
    public static void main(String[] args) {

        boolean debugMode = true;

        if (debugMode) {
            System.out.println("Debug modu aktif.");
        }

        System.out.println("Uygulama çalışıyor.");
    }
}