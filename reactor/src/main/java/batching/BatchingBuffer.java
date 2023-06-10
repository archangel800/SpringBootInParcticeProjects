package batching;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BatchingBuffer {
    public static void main(String[] args) throws InterruptedException {
        eventStream()
                .bufferTimeout(5, Duration.ofSeconds(2))
                .subscribe(Util.subscriber());
        TimeUnit.SECONDS.sleep(60);

    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(800))
                .map(item -> "event" + item);
    }
}
