package coldpublisher.task;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.*;

public class OrderService {
    private List<String> categories = List.of("Kids", "Adults", "Everyone", "Animals", "Babies");
    private Flux<Order> orders;

    public Flux<Order> getOrders() {
        if (Objects.isNull(orders)) {
            orders = getOrdersStream();
            return orders;

        }
        return orders;
    }

    private Flux<Order> getOrdersStream() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(item -> new Order(item, categories.get(Util.faker().random().nextInt(0,4))))
                .publish()
                .refCount(2);
    }
}
