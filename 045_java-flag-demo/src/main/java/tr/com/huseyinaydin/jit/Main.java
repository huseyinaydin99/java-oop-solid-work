package tr.com.huseyinaydin.jit;

public class Main {

    public static void main(String[] args) {

        for (int i = 0; i < 10_000_000; i++) {
            calculate(i); // calculate() yalnızca bir kez çalıştığı için JIT'in ilgisini çekmez; milyonlarca kez çalıştırarak JIT'in metodu derlemesini tetiklemeliyiz.
        }
    }

    static int calculate(int value) {
        return value * 2 + 10;
    }
}