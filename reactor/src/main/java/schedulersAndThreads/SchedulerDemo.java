package schedulersAndThreads;

import org.example.corseUtil.Util;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulerDemo {
    public static void main(String[] args) throws InterruptedException {

        Flux<Object> flux = Flux.create(objectFluxSink -> {
            printThreadName("create");
            objectFluxSink.next(1);
        })
                .subscribeOn(Schedulers.newParallel("vins"))
                .doOnNext(i -> printThreadName("next" + i));

        flux
                .doFirst(() -> printThreadName("first2"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> printThreadName("first1"))
                .subscribe(v -> printThreadName("sub " + v));


        Util.sleepSeconds(5);

    }
    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread: " + Thread.currentThread().getName());
    }
}
