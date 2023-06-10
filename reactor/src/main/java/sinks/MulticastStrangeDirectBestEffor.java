package sinks;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MulticastStrangeDirectBestEffor {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("reactor.bufferSize.small", "16");
        Sinks.Many<Object> objectMany = Sinks.many().multicast().directBestEffort();
        Flux<Object> flux = objectMany.asFlux();

        for (int i = 0; i < 100; i++) {
            objectMany.tryEmitNext(i);
        }

        flux.subscribe(Util.subscriber("sam"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));


        TimeUnit.SECONDS.sleep(10);
    }
}
