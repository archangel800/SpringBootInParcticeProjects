package coldpublisher.task;

import org.example.corseUtil.Util;

import java.util.concurrent.TimeUnit;

public class HotPublisherTask {
    public static void main(String[] args) throws InterruptedException {
        OrderService orderService = new OrderService();
        RevenueService revenueService = new RevenueService();
        InventoryService inventoryService = new InventoryService();


        orderService.getOrders().subscribe(revenueService.calculateRevenue());
        orderService.getOrders().subscribe(inventoryService.calculateInventory());

        revenueService.revenueReactiveStream().subscribe(Util.subscriber());
        inventoryService.inventoryReactiveStream().subscribe(Util.subscriber());

        TimeUnit.SECONDS.sleep(60);
    }
}
