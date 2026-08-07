package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.model.Order;
import tr.com.huseyinaydin.util.SleepUtil;

public class PaymentService {

    public boolean processPayment(Order order) {

        System.out.printf("[%s] Sipariş için ödeme işleniyor #%d\n",
                Thread.currentThread().getName(),
                order.getId());

        SleepUtil.sleep(2000);

        System.out.printf("[%s] Sipariş için ödeme tamamlandı #%d\n",
                Thread.currentThread().getName(),
                order.getId());

        return true;
    }

}