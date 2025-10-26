package tr.com.huseyinaydin._02;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Company {

    public static void main(String[] args) {
        List<Product> productsList1 = new ArrayList<>();

        Product product1 = new Product(); // Normal Object

        productsList1.add(new Product(1, "Ekmek", 7.0f, "Beyaz")); // Anonim Object
        productsList1.add(new Product(2, "Süt", 2.1f, "Yağlı"));
        productsList1.add(new Product(3, "Su", 0.5f, "Mineralli"));
        productsList1.add(new Product(4, "Elma", 2.1f, "Yeşil"));
        productsList1.add(new Product(5, "Çikolata", 1.2f, "Sütlü"));
        productsList1.add(new Product(6, "Domates", 3.3f, "Sera"));

        List<Product> productsList2 = productsList1.stream()
                .filter(product -> product.getPrice() > 2 && product.getPrice() < 5) //süt, elma, domates
                .limit(2) //süt, elma ilk ikisini alır
                .collect(Collectors.toList());
        //.toList();

        System.out.println(productsList2);

        List<Product> productsList3 = productsList1.stream()
                .filter(product -> product.getPrice() > 2 && product.getPrice() < 5) //süt, elma, domates
                .limit(10) //süt, elma ilk 10'u alır ama toplamda 10 yok o zaman 3'ü alır ne varsa o kadarını alabilir!
                .toList();

        Double total = productsList3.stream()
                .mapToDouble(Product::getPrice)
                .sum(); //fiyat kolonunu Float'dan Double türüne dönüştürüp toplamını alır.

        System.out.println(total);
    }
}