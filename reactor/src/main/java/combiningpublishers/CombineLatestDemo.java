package combiningpublishers;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CombineLatestDemo {
    public static void main(String[] args) throws InterruptedException {
        Flux.combineLatest(getString(), getNumber(), (item1, item2) -> item1+item2)
                .subscribe(Util.subscriber());

        TimeUnit.SECONDS.sleep(15);
    }
    private static Flux<String> getString() {
        return Flux.just("A", "B", "C", "D")
                .delayElements(Duration.ofSeconds(1));
    }
    private static Flux<Integer> getNumber() {
        return Flux.just(1, 2, 3)
                .delayElements(Duration.ofSeconds((3)));
    }
}
