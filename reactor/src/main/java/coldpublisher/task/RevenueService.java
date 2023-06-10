package coldpublisher.task;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RevenueService {

    private  HashMap<String, Double> revenue = new HashMap<>();

    {
       revenue.put("Kids", 2.5);
       revenue.put("Adults", 3.0);
       revenue.put("Everyone", 2.0);
       revenue.put("Animals", 5.0);
       revenue.put("Babies", 9.5);
    }

    public Consumer<Order> calculateRevenue() {
        return item -> revenue.computeIfPresent(item.getCategory(), (k, v) -> v + item.getPrice());
    }

    public Flux<String> revenueReactiveStream() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> "Revenue -> " + revenue.toString());
    }
}
