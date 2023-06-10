package schedulersAndThreads;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import javax.sound.midi.Soundbank;
import java.util.concurrent.TimeUnit;

public class CombineSchedulersDemo {
    public static void main(String[] args) throws InterruptedException {
        Flux.range(1, 5)
                .subscribeOn(Schedulers.single()) // Set the scheduler for subscription and initial part of pipeline
                .doOnNext(i -> {
                    System.out.println("Processing value: " + i + " on Thread: " + Thread.currentThread().getName());
                })
                .publishOn(Schedulers.parallel())
                .map(i -> i * 2)
                .subscribe(i -> System.out.println(i + ": on thread: " + Thread.currentThread().getName()));

        TimeUnit.SECONDS.sleep(5);
           }
}
