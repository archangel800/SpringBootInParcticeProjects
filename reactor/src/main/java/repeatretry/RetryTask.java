package repeatretry;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class RetryTask {
    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {

                getIntegers()
                        .retry(2)
                .subscribe(Util.subscriber());
    }
    private static Flux<Integer> getIntegers() {
        return Flux.range(1, 3)
                .doOnSubscribe(s -> System.out.println("Subscribed"))
                .doOnComplete(() -> System.out.println("--Completed"))
                .map(i -> atomicInteger.getAndIncrement())
                .map(i -> 1 / (Util.faker().random().nextInt(1,5) > 3 ? 0 : 1))
                .doOnError(err -> System.out.println(err.getMessage()));
    }
}
