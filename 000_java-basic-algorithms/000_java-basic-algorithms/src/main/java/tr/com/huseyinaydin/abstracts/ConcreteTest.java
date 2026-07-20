package tr.com.huseyinaydin.abstracts;

public class ConcreteTest extends AbstractTest {

	@Override
	void apply() {
		System.out.println("bu noktada override edilmek zorunda!");
		
	}
	
	public static void main(String[] args) {
		ConcreteTest concreteTest = new ConcreteTest();
		concreteTest.apply();
	}

	@Override
	public void testing() {
		
		
	}
}