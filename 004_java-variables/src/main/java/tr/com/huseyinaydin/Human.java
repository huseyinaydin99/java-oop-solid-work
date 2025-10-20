package tr.com.huseyinaydin;

public class Human {
    public static void main(String[] args) {
        System.out.println("1 ------------------------------");

        String firstName = "Hüseyin AYDIN";
        String lastName = "AYDIN";
        Integer age = 31;
        Float height = 1.82f;
        Double salary = 999_000.0;
        Boolean maritalStatus = false;
        Character gender = 'M';
        String eyesColor = "Black";

        System.out.println("FirstName: " + firstName);
        System.out.println("LastName: " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height);
        System.out.println("Salary: " +  salary);
        System.out.println("MaritalStatus: " + maritalStatus);
        System.out.println("Gender: " + gender);
        System.out.println("EyesColor: " + eyesColor);

        System.out.println("\n2 ------------------------------");

        firstName = "Ömer Faruk";
        lastName = "AYDIN";
        age = 15;
        height = 1.68f;
        salary = 998_000.0;
        maritalStatus = false;
        gender = 'M';
        eyesColor = "Black";

        System.out.println("FirstName: " + firstName);
        System.out.println("LastName: " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height);
        System.out.println("Salary: " +  salary);
        System.out.println("MaritalStatus: " + maritalStatus);
        System.out.println("Gender: " + gender);
        System.out.println("EyesColor: " + eyesColor);


        System.out.println("\n3 ------------------------------");

        firstName = "Zehra";
        lastName = "AYDIN";
        age = 75;
        height = 1.55f;
        salary = 147_000.0;
        maritalStatus = Boolean.FALSE; // false Bu atama, maritalStatus değişkenine mantıksal olarak “evli değil” anlamında false değerini nesne (Boolean) olarak atar.
        gender = 'F';
        eyesColor = "Green";

        System.out.println("FirstName: " + firstName);
        System.out.println("LastName: " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height);
        System.out.println("Salary: " +  salary);
        System.out.println("MaritalStatus: " + maritalStatus);
        System.out.println("Gender: " + gender);
        System.out.println("EyesColor: " + eyesColor);

        System.out.println("\n4 ------------------------------");

        firstName = "Alex";
        lastName = "BLACK";
        age = 26;
        height = 1.68f;
        salary = 999_999.0;
        maritalStatus = Boolean.FALSE; // false
        gender = 'F';
        eyesColor = "Blue";

        System.out.println("FirstName: " + firstName);
        System.out.println("LastName: " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height);
        System.out.println("Salary: " +  salary);
        System.out.println("MaritalStatus: " + maritalStatus);
        System.out.println("Gender: " + gender);
        System.out.println("EyesColor: " + eyesColor);
    }
}