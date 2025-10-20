package tr.com.huseyinaydin;

import module java.base;
import module java.sql;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java
 *
 */

//modül içe aktarma tanımlamaları
public class ModularExample {

    void main() throws SQLException, IOException {
        List<String> names = new ArrayList<>();
        names.add("Hüseyin");
        names.add("Ahmet");
        names.add("Selami");

        Map<String, Integer> nameLength = new HashMap<>();
        for (String name : names) {
            nameLength.put(name, name.length());
        }

        System.out.println("İsim uzunluğu: " + nameLength);
        //IO.println("İsim uzunluğu: " + nameLength); fark yapmaz her yol Trabzon

        Set<Integer> numbers = IntStream.range(1, 10).boxed().collect(Collectors.toSet());
        System.out.println("Sayılar kümeler: " + numbers);

        Random random = new Random();
        System.out.println("Rastgele sayı: " + random.nextInt(100));

        File tempFile = new File("temp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(tempFile));
        // sadece demodur kardeş, içeriği neyim okumayacağız (;
        reader.close();

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/huseyin_aydin_db", "root", "toor");
        IO.println("Bağlantı durumu: " + (conn != null));

        BigInteger bigInt = new BigInteger("12345678901234567890");
        BigDecimal bigDec = new BigDecimal("12345.67890");
        IO.println("BigInteger/Büyük Tamsayı Nesnesi: " + bigInt);
    }
}