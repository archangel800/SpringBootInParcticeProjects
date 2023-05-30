import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

public class timeoutPractice {
    public static void main(String[] args) throws InterruptedException {
        produce().timeout(Duration.ofSeconds(2), Flux.generate(() -> 1, (count, stringSynchronousSink) -> {
            if(count < 10) {
                stringSynchronousSink.next(Util.faker().country().name());
                count++;
            }
            else {
                stringSynchronousSink.complete();
            }
            return count;
        }))
                .subscribe(Util.subscriber());

        Thread.sleep(50000);
    }
    public static Flux<String> produce() {
        return Flux.range(1, 10)
                .map(item -> Util.faker().country().name())
                .delayElements(Duration.ofSeconds(3));
    }
}
