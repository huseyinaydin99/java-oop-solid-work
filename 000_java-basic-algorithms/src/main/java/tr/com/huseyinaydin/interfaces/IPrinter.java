package tr.com.huseyinaydin.interfaces;

@FunctionalInterface
public interface IPrinter {
    void print(String text);
    
    public static void main(String[] args) {
    	IPrinter p = System.out::println;
    	p.print("Merhaba");
    }
}