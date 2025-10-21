package tr.com.huseyinaydin;

public class PersonInfo {
    public static void main(String[] args) {
        // Kişisel bilgi değişkenleri
        String firstName = "Ethan";
        String lastName = "Stone";
        Integer age = 29;
        Float height = 1.82f;
        Double salary = 98000.0;
        Boolean isMarried = false; // Evlilik durumu
        Character gender = 'M';
        String eyeColor = "Brown";

        printInfo(firstName, lastName, age, height, salary, isMarried, gender, eyeColor);

        firstName = "Sophia";
        lastName = "Clark";
        age = 24;
        height = 1.68f;
        salary = 105000.0;
        isMarried = true;
        gender = 'F';
        eyeColor = "Blue";

        printInfo(firstName, lastName, age, height, salary, isMarried, gender, eyeColor);

        firstName = "Liam";
        lastName = "Walker";
        age = 35;
        height = 1.90f;
        salary = 145000.0;
        isMarried = false;
        gender = 'M';
        eyeColor = "Hazel";

        printInfo(firstName, lastName, age, height, salary, isMarried, gender, eyeColor);

        firstName = "Ava";
        lastName = "Morris";
        age = 21;
        height = 1.65f;
        salary = 120000.0;
        isMarried = true;
        gender = 'F';
        eyeColor = "Green";

        printInfo(firstName, lastName, age, height, salary, isMarried, gender, eyeColor);

        // Değişmez değerlerle doğrudan yöntem çağrıları
        // Java’da literal, kodda sabit olarak yazılmış değişmeyen değerdir; örneğin 10, 'A', "Hello" veya true birer literaldir.
        printInfo("Noah", "Harris", 28, 1.78F, 110000.0, false, 'M', "Black");
        printInfo("Isabella", "Scott", 33, 1.72F, 130000.0, true, 'F', "Blue");
    }

    private static void printInfo(String firstName,
                                  String lastName,
                                  Integer age,
                                  Float height,
                                  Double salary,
                                  Boolean isMarried,
                                  Character gender,
                                  String eyeColor) {
        System.out.println("Full Name: " + firstName + " " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height);
        System.out.println("Gender: " + gender);
        System.out.println("Eye Color: " + eyeColor);
        System.out.println("Salary: " + salary);
        System.out.println("Married: " + isMarried);

        printLine();
    }

    private static void printLine() {
        System.out.println("------------------------------");
    }
}