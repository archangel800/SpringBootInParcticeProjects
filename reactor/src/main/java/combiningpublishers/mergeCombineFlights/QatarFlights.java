package combiningpublishers.mergeCombineFlights;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class QatarFlights {
        public static Flux<String> getFlights() {
            return Flux.range(1, Util.faker().random().nextInt(1, 5))
                    .delayElements(Duration.ofSeconds(1))
                    .map(i -> "Qatar " + Util.faker().random().nextInt(100, 999) + " " + Thread.currentThread().getName())
                    .filter(i -> Util.faker().random().nextBoolean());
        }

}
