package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.model.Order;
import tr.com.huseyinaydin.util.SleepUtil;

public class ShippingService {

    public String prepareShipment(Order order) {

        System.out.printf("[%s] Siparişiniz kargo için hazırlanıyor #%d\n",
                Thread.currentThread().getName(),
                order.getId());

        SleepUtil.sleep(1200);

        System.out.printf("[%s] Siparişiniz kargolandı #%d\n",
                Thread.currentThread().getName(),
                order.getId());

        return "Kargo hazır!";
    }

}