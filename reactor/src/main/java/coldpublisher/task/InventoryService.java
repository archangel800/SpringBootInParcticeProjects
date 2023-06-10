package coldpublisher.task;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.function.Consumer;

public class InventoryService {
    private HashMap<String, Integer> inventory = new HashMap<>();

    {
        inventory.put("Kids", 100);
        inventory.put("Adults", 120);
        inventory.put("Everyone", 80);
        inventory.put("Animals", 150);
        inventory.put("Babies", 300);
    }

    public Consumer<Order> calculateInventory() {
        return item -> inventory.computeIfPresent(item.getCategory(), (k, v) -> v - item.getQuantity());
    }

    public Flux<String> inventoryReactiveStream() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> "Inventory -> "  + inventory.toString());
    }
}
