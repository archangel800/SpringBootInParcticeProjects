import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;


public class TaskToBeDeleted {
    public static void main(String[] args) throws InterruptedException {
        eventStream().buffer(Duration.ofSeconds(5))
                .subscribe(Util.subscriber());
        Util.sleepSeconds(50);
    }
    public static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(300))
                .map(item -> "event: " + item);
    }
}
