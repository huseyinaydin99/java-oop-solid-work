package tr.com.huseyinaydin;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Mutlu yol: ödeme → kargo → teslim
        Order deliveredOrder = new Order("SP-1001");
        deliveredOrder.pay();
        deliveredOrder.ship();
        deliveredOrder.deliver();

        // 2. Ödeme beklerken doğrudan iptal
        Order cancelledWhilePending = new Order("SP-1002");
        cancelledWhilePending.cancel();

        // 3. Ödendikten sonra iptal: önce iade başlatılır
        Order cancelledAfterPayment = new Order("SP-1003");
        cancelledAfterPayment.pay();
        cancelledAfterPayment.cancel();

        // 4. Yola çıkmış sipariş
        Order shippedOrder = new Order("SP-1004");
        shippedOrder.pay();
        shippedOrder.ship();

        // 5. Geçersiz işlemler: reddedilir ve siparişin durumu bozulmaz
        System.out.println("=== Reddedilen işlemler ===");
        List<Runnable> invalidActions = List.of(
                shippedOrder::cancel,
                shippedOrder::pay,
                deliveredOrder::cancel,
                cancelledWhilePending::pay
        );
        for (Runnable action : invalidActions) {
            try {
                action.run();
            } catch (IllegalStateTransitionException e) {
                System.out.println("  ✗ " + e.getMessage());
            }
        }

        // 6. Her siparişin son durumu ve geçmişi
        System.out.println();
        System.out.println("=== Sipariş özetleri ===");
        List<Order> orders = List.of(deliveredOrder, cancelledWhilePending, cancelledAfterPayment, shippedOrder);
        for (Order order : orders) {
            System.out.println(order.id() + " · " + order.status().label());
            order.history().forEach(event -> System.out.println("  • " + event));
        }
    }
}