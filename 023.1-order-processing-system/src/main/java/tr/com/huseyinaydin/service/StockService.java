package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.model.Order;
import tr.com.huseyinaydin.util.SleepUtil;

public class StockService {

    public boolean checkStock(Order order) {

        System.out.printf("[%s] Sipariş için stok kontrolü yapılıyor #%d\n",
                Thread.currentThread().getName(),
                order.getId());

        SleepUtil.sleep(1500);

        System.out.printf("[%s] Sipariş için stok kullanılabilir sorun yok #%d\n",
                Thread.currentThread().getName(),
                order.getId());

        return true;
    }

}