package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.model.Order;
import tr.com.huseyinaydin.util.SleepUtil;

public class OrderService {

    public void process(Order order) {

        System.out.printf(
                "[%s] Sipariş işleniyor #%d (%s)%n",
                Thread.currentThread().getName(),
                order.getId(),
                order.getProductName()
        );

        SleepUtil.sleep(2000);

        System.out.printf(
                "[%s] Sipariş işlendi #%d%n",
                Thread.currentThread().getName(),
                order.getId()
        );
    }
}