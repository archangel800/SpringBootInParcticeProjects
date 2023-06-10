package batching;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class WindowBatching {
    private static AtomicInteger atomicInteger = new AtomicInteger(1);
    public static void main(String[] args) throws InterruptedException {
        eventStream()
                .window(5)
                .flatMap(item -> saveEvents(item))
                .subscribe(Util.subscriber());
        TimeUnit.SECONDS.sleep(60);

    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(800))
                .map(item -> "event" + item);
    }

    private static Mono<Integer> saveEvents(Flux<String> flux) {
        return flux
                .doOnNext(i -> System.out.println("saving " + i))
                .doOnComplete(() -> {
                    System.out.println("Saved this batch");
                    System.out.println("----------------------");
                })
                .then(Mono.just(atomicInteger.getAndIncrement()));
    }
}
