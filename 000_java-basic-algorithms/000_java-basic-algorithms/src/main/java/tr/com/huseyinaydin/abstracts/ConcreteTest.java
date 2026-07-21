package tr.com.huseyinaydin.abstracts;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConcreteTest extends AbstractTest {

	@Override
	void apply() {
		System.out.println("bu noktada override edilmek zorunda!");
		
	}
	
	public static void main(String[] args) {
		ConcreteTest concreteTest = new ConcreteTest();
		concreteTest.apply();
		concreteTest.testing();
	}

	@Override
	public void testing() {
		System.out.println(0.1 + 0.2 == 0.3);
		System.out.println(0.1 + 0.2);
		
		Stream.of(1, 2, 3).peek(i -> System.out.print("x")); // tetiklenmez çalışmaz devreyer girmez bir nevi
		Stream.of(1, 2, 3).peek(i -> System.out.print("x")).collect(Collectors.toList()); // tetiklenir çünkü peek() lazy (tembel) bir ara operasyondur ve tek başına stream'i çalıştırmaz; terminal operasyon (collect, forEach vb.) olmadığı için hiç çalışmaz ama biz collect kullandık o yüzden çalışır!
	
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> 1 / 0);
        try {
            future.get();
        } catch (Exception e) {
            System.out.println("Exception caught");
        }
        executor.shutdown();
	}
}