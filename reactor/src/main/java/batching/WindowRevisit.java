package batching;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class WindowRevisit {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        generateFlux()
                .window(Duration.ofSeconds(2))
                .flatMap(item -> item
                        .doOnNext(System.out::println)
                        .doOnComplete(() -> {
                            System.out.println("Saved this batch");
                            System.out.println("---------------------------");
                        }).then(Mono.just(atomicInteger.getAndIncrement())))
                .subscribe(Util.subscriber());

        TimeUnit.SECONDS.sleep(60);
    }
    public static Flux<String> generateFlux() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> "event" + i);
    }
}
