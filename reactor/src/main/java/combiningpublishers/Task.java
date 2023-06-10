package combiningpublishers;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Task {
    public static void main(String[] args) throws InterruptedException {
        Flux.combineLatest(generateMonthlyReductionFlux(), generateQuarterValueFlux(), (a, b) -> a * b)
                .subscribe(Util.subscriber());

        TimeUnit.SECONDS.sleep(50);
    }
    private static Flux<Integer> generateMonthlyReductionFlux(){
        AtomicInteger atomicInteger = new AtomicInteger(10000);
        return Flux.interval(Duration.ZERO, Duration.ofSeconds(1))
                .map(value -> atomicInteger.accumulateAndGet(100, (a, b) -> a-b));
    }
    private static Flux<Double> generateQuarterValueFlux() {
        return Flux.interval(Duration.ofSeconds(3))
                .map(value -> Util.faker().random().nextInt(80, 120) / 100d).
                startWith(1d);
    }
}
