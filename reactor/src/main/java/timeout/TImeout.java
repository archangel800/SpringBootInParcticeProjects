package timeout;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class TImeout {
    public static void main(String[] args) throws InterruptedException {

        getOrderNumbers()
                .timeout(Duration.ofSeconds(2), fallBack())
                .subscribe(Util.subscriber());
        Thread.sleep(60000);
    }
    private static Flux<Integer> getOrderNumbers() {
        return Flux.range(1, 8)
                .delayElements(Duration.ofSeconds(1));
    }
    private static Flux<Integer> fallBack() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1));
    }
}
