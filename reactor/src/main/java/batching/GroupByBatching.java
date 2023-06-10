package batching;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class GroupByBatching {
    private static AtomicInteger atomicInteger = new AtomicInteger(1);
    public static void main(String[] args) throws InterruptedException {
       Flux.range(1, 30)
               .delayElements(Duration.ofSeconds(1))
               .groupBy(i -> i % 2)
               .subscribe(gf -> process(gf, gf.key()));

       TimeUnit.SECONDS.sleep(50);
    }

    private static  void process(Flux<Integer> flux, int key) {
        System.out.println("Called");
        flux.subscribe(i -> System.out.println("Key: " + key + ", Item: " + i));
    }
}
