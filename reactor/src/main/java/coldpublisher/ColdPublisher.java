package coldpublisher;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class ColdPublisher {
    public static void main(String[] args) throws InterruptedException {

        Flux<String> stringFlux = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(2));

        stringFlux.subscribe(item -> System.out.println("First " + item + " Thread: " + Thread.currentThread().getName()));
       // TimeUnit.SECONDS.sleep(5);
        stringFlux.subscribe(item -> System.out.println("Second " + item + " Thread: " + Thread.currentThread().getName()));
        TimeUnit.SECONDS.sleep(60);
    }
    private static Stream<String> getMovie() {
        System.out.println("Got the movie streaming request");
        return Stream.of("Scene 1", "Scene 2", "Scene 3", "Scene 4", "Scene 5", "Scene 6");
    }

}
