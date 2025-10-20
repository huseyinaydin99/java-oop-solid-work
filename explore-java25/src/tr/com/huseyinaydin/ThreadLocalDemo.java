package tr.com.huseyinaydin;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java
 *
 */

public class ThreadLocalDemo {
    public static void main(String[] args) {
        /*
            ThreadLocal, her iş parçacığına (thread) kendine özel bir değişken kopyası verir.
            Yani bir thread bu değişkene değer atadığında, o değer sadece o threade aittir ve diğerleriyle paylaşılmaz.
            Bu sayede çoklu thread ortamlarında veri karışmadan güvenli şekilde işlem yapılabilir.
         */
        //Her thread kendi bacağından asılır. <<-- Ata sözü!
        ThreadLocal<String> currentUser = new ThreadLocal<>();
        currentUser.set("Hüseyin");
        IO.println(currentUser.get()); //Hüseyin döner.

        new Thread(() -> {
            currentUser.set("Selami");
            System.out.println("Kullanıcı 1: " + currentUser.get()); //Selami döner. Ne verirsen eliynen o gelir seniynen.
        }).start();

        new Thread(() -> {
            currentUser.set("Veli");
            System.out.println("Kullanıcı 2: " + currentUser.get()); //Veli döner.
        }).start();
    }
}