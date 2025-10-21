package tr.com.huseyinaydin;

public class IfControl {
    public static void main(String[] args) {
        //SART  // true
        if (1 > 0) { //scope / kapsam / kod bloğu // if true görürse kod bloğuna girer false görürse asla ama asla girmez. true olması için şartın karşılanması gerekmektedir.
            System.out.println("Evet bu doğrudur.");
        }

        int adayYasi = 31;

        if (adayYasi >= 18) {
            System.out.println("Evet adayın yaşı uygundur.");
        }
    }
}