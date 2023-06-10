package schedulersAndThreads;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.TimeUnit;

public class SchedDemo {
    public static void main(String[] args) throws InterruptedException {
        Flux<Object> flux = Flux.create(fluxSink -> {
            System.out.println("inside create: " + Thread.currentThread().getName());
            fluxSink.next(1 );
            fluxSink.next(2 );
            fluxSink.next(3 );
        })
                .doOnNext(item -> System.out.println("doOnNextFirst: " + item + ": " + Thread.currentThread().getName()))
                .publishOn(Schedulers.parallel());

        flux
                .doOnNext(item -> System.out.println("doOnNextSecond: " + item + ": " + Thread.currentThread().getName()))
                .doOnSubscribe(item -> System.out.println("onOnSubscribe: " + Thread.currentThread().getName()))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> System.out.println("doOnFirst " + Thread.currentThread().getName()))
                .doOnNext(item -> System.out.println("doOnNextThird: " + item + ": " + Thread.currentThread().getName()))
                .subscribe(item -> System.out.println("subscriber: " + item + ": " + Thread.currentThread().getName()));
        
        TimeUnit.SECONDS.sleep(5);
    }
}
