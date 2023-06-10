package schedulersAndThreads;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class PublishOnDemo {
    public static void main(String[] args) throws InterruptedException {

        Flux<Object> flux = Flux.create(objectFluxSink -> {
                    printThreadName("create");
                    for(int i = 0; i < 4; i++) {
                        objectFluxSink.next(i);
                    }
                })
                .doOnNext(i -> printThreadName("next1 :" + i));


        flux
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> printThreadName("next2 :" + i))
                .publishOn(Schedulers.boundedElastic())
                .subscribe(v -> printThreadName("sub " + v));

        Util.sleepSeconds(5);

    }
    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread: " + Thread.currentThread().getName());
    }
}
