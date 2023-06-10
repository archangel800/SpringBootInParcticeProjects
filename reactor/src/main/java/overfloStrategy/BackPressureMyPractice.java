package overfloStrategy;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BackPressureMyPractice {
    public static void main(String[] args) throws InterruptedException {
        Flux.create(fluxSink -> {
                    System.out.println("in flux create");
                    for(int i = 0; i < 1000; i++) {
                        System.out.println("Emitting: " + i);
                        fluxSink.next(i);
                    }
                })
                .onBackpressureLatest()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(item -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("doOnNext: " + item);
                })
                .subscribe(item -> System.out.println("Received: " + item));

        TimeUnit.SECONDS.sleep(10);
    }
}
