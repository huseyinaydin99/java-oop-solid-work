//Java 25 basit konsol

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java
 *
 */
void main(){
    //System.out.println("Selamun Aleyküm ey dünya ben Java 25. Bilge Java 25 31 yaşında.");
    IO.println("Selamun Aleyküm ey dünya ben Java 25. Bilge Java 25 31 yaşında.");
    //Scanner scanner = new Scanner(System.in);
    //System.out.print("Adını gir: ");
    //String name = scanner.nextLine();
    //System.out.println("Merhaba, " + name + " kardeş!");
    IO.print("Adını gir: ");
    String name = IO.readln();
    IO.println("Merhaba, " + name + " kardeş!");
}