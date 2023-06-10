package batching.task2;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

public class Task2 {
    public static void main(String[] args) throws InterruptedException {
        Set<String> category = Set.of("Kids", "Automotive");
        Flux.interval(Duration.ofMillis(10))
                .map(item -> new PurchaseOrder())
                .filter(item -> category.contains(item.getCategory()))
                .groupBy(item -> item.getCategory())
                .flatMap(item -> process(item, item.key()))
                .subscribe(item -> System.out.println(item));

        Util.sleepSeconds(50);
    }
    public static Flux<PurchaseOrder> process (Flux<PurchaseOrder> purchaseOrderFlux, String key) {
        return key.equals("Automotive") ? purchaseOrderFlux.doOnNext(item -> item.setPrice(item.getPrice() * 1.1))
                .doOnNext(item -> item.setItem("{{ " + item.getItem() + " }}")) :
                purchaseOrderFlux.doOnNext(item -> item.setPrice(item.getPrice() * 0.5))
                        .doOnNext(item -> Flux.just(item, new PurchaseOrder(1)));
    }
}
