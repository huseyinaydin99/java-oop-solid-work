package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.model.Order;
import tr.com.huseyinaydin.util.SleepUtil;

public class NotificationService {

    public void sendNotification(Order order) {

        System.out.printf("[%s] Siparişiniz için bildirim gönderiliyor #%d\n",
                Thread.currentThread().getName(),
                order.getId());

        SleepUtil.sleep(800);

        System.out.printf("[%s] Siparişiniz için bildirim gönderildi #%d\n",
                Thread.currentThread().getName(),
                order.getId());

    }

}