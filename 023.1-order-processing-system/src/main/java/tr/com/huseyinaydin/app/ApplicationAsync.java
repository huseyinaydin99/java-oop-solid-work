package tr.com.huseyinaydin.app;

import tr.com.huseyinaydin.config.ThreadPoolConfig;
import tr.com.huseyinaydin.model.Order;
import tr.com.huseyinaydin.service.OrderService;

public class ApplicationAsync {
    public static void main(String[] args) {

        OrderService orderService = new OrderService();

        orderService.process(
                new Order(
                        1,
                        "Hüseyin Aydın",
                        "Laptop"
                )
        );

        ThreadPoolConfig.shutdown();

    }
}