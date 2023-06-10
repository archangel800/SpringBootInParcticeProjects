package batching;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BufferOverlapping {
    public static void main(String[] args) throws InterruptedException {
        eventStream()
                .buffer(5, 3  )
                .subscribe(Util.subscriber());
        TimeUnit.SECONDS.sleep(60);

    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(300))
                .map(item -> "event" + item);
    }
}
