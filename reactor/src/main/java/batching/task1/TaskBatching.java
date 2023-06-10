package batching.task1;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.sql.Time;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TaskBatching {
    public static void main(String[] args) throws InterruptedException {
        List<String> categories = List.of("Science fiction", "Fantasy", "Suspense/Thriller");
        Map<String, Integer> revenue = new HashMap<>();

        orderFlux()
                .buffer(Duration.ofSeconds(2))
                .map(list -> list.stream().filter(item -> categories.contains(item.getCategory()))
                        .collect(Collectors.groupingBy(Order::getCategory, Collectors.summingDouble(Order::getPrice))))
                .subscribe(Util.subscriber());

        TimeUnit.SECONDS.sleep(100);
    }

    private static Flux<Order> orderFlux() {
        return Flux.range(1, 100)
                .delayElements(Duration.ofMillis(300))
                .map(item -> new Order(item));
    }
}
