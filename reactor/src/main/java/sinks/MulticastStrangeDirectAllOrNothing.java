package sinks;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MulticastStrangeDirectAllOrNothing {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("reactor.bufferSize.small", "16");
        Sinks.Many<Object> objectMany = Sinks.many().multicast().directAllOrNothing();
        Flux<Object> flux = objectMany.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));

        for (int i = 0; i < 100; i++) {
            objectMany.tryEmitNext(i);
        }
        TimeUnit.SECONDS.sleep(10);
    }
}
