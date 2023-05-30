package ErrorTimeoutDelayElements;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class ErrTimeDelay {
    public static void main(String[] args) throws InterruptedException {
        getCountries()
                .timeout(Duration.ofSeconds(2), getNamesfallBack())
                .subscribe(Util.subscriber());

        Thread.sleep(10000);
    }
    public static Flux<String> getCountries() {
        return Flux.range(1, 10)
                .map(i -> Util.faker().country().name())
                .delayElements(Duration.ofSeconds(5));
    }

    public static Flux<String> getNamesfallBack() {
        return Flux.generate(() -> 1, (count, synchronousSink) -> {
            if(count <= 10) {
                synchronousSink.next(Util.faker().name().fullName());
                count++;
            }
            else{
                synchronousSink.complete();
            }
            return count;
        });
    }

}
