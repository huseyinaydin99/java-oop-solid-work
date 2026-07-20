package tr.com.huseyinaydin.interfaces;

public interface ITest<T> {
	
	// private int number = 0; private olmaz!
	public int number = 3;
	// protected int number2 = 0; protected olmaz!
	int number2 = 2;
	static int number3 = 5;
	// private static number4 = 0; olmaz!
	// protected static int number4 = 0; olmaz!
	
	String APP_NAME = "Demo";
	double PI = 3.14159;
	boolean ACTIVE = true;
	
	T convert(T value);
	void run();
	
	default void testApply() {
		// ACTIVE = false; sabit olduğı için değiştirilemez!
		// number = 2; bu da değiştirilemez!
		System.out.println("selam testApply");
		staticApply();
		publicStaticApply();
		privateApply();
		
		int total = number + number2;
		System.out.println(total);
		
		int total2 = number2 + number3;
		System.out.println(total2);
		
		this.<String>genericApply("Selam");
	}
	
	default int sum(int... numbers) {
	    int total = 0;
	    for (int n : numbers)
	        total += n;
	    return total;
	}
	
	static void staticApply() {
		System.out.println("staticApply");
		int total = number + number2;
		System.out.println(total);
		
		int total2 = number2 + number3;
		System.out.println(total2);
	}
	
	public static void publicStaticApply() {
		System.out.println("publicStaticApply");
		int total = number + number2;
		System.out.println(total);
		
		int total2 = number2 + number3;
		System.out.println(total2);
	}
	
	private void privateApply() {
		System.out.println("privateApply");
		int total = number + number2;
		System.out.println(total);
		
		int total2 = number2 + number3;
		System.out.println(total2);
	}
	
	private <T> void genericApply(T args) {
		System.out.println(args);
	}
	
	static ITest create() {
	    return new ITest<String>() {
	        @Override
	        public void run() {
	            System.out.println("run");
	        }

			@Override
			public String convert(String value) {
				return value;
			}
	    };
	}
	
	public static void main(String[] args) {
		System.out.println("main");
		
		ITest<String> iTest = new ITest<String>() {

			@Override
			public String convert(String value) {
				StringBuilder builder = new StringBuilder();
				builder.append("selamlar: ");
				builder.append(value);
				return builder.toString();
			}

			@Override
	        public void run() {
	            System.out.println("run");
	        }
		};
		
		iTest.testApply();
		
		ITest.create().privateApply();
	}
	
	interface InnerTest {
	    void execute();
	    
	    static InnerTest create() {
			return new InnerTest() {
				
				@Override
				public void execute() {
					System.out.println("selamlar");
				}
			};
		}
	}
}