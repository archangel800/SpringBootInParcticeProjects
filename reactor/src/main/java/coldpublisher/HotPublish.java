package coldpublisher;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class HotPublish {
    public static void main(String[] args) throws InterruptedException {

        Flux<String> stringFlux = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .refCount(1);

        stringFlux.subscribe(Util.subscriber("Sam"));
        TimeUnit.SECONDS.sleep(10);
        stringFlux.subscribe(Util.subscriber("Mike"));
        TimeUnit.SECONDS.sleep(10);
    }
    private static Stream<String> getMovie() {
        System.out.println("Got the movie streaming request");
        return Stream.of("Scene 1", "Scene 2", "Scene 3", "Scene 4", "Scene 5", "Scene 6");
    }
}
