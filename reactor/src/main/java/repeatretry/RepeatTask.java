package repeatretry;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class RepeatTask {
    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {

//        getIntegers()
//                .repeat(2)
//                .subscribe(Util.subscriber());
//        getIntegers()
//                .repeat()
//                .subscribe(Util.subscriber())
                getIntegers()
                .repeat(() -> atomicInteger.get() < 14)
                .subscribe(Util.subscriber());
    }
    private static Flux<Integer> getIntegers() {
        return Flux.range(1, 3)
                .doOnSubscribe(s -> System.out.println("Subscribed"))
                .doOnComplete(() -> System.out.println("--Completed"))
                .map(i -> atomicInteger.getAndIncrement());
    }
}
