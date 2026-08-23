package tr.com.huseyinaydin.interfaces;

public interface ITestInherit extends ITest<String>, ICheck {

	@Override
	public default String convert(String value) {
		StringBuilder builder = new StringBuilder();
		builder.append("selamlar: ");
		builder.append(value);
		return builder.toString();
	}
	
	void run();
	public void stop();
	/*
	 
	 interface içindeki bir metotta sadece void metot(); 
	 yazarsan derleyici onu otomatik olarak public abstract void metot(); kabul eder.
	 
	 */
}