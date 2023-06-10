package combiningpublishers;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

public class ConcatWithDemo {
    public static void main(String[] args) {
        Flux<String> just = Flux.just("a", "b");
        Flux<String> just1 = Flux.just("c", "d", "e");

        Flux<String> just2 = Flux.error(new RuntimeException("oops"));

        Flux<String> flux = just.concatWith(just1);

        Flux<String> flux1 = Flux.concat(just, just1);

        Flux<String> flux2 = Flux.concatDelayError(just, just2, just1);

        //flux.subscribe(Util.subscriber());
        //flux1.subscribe(Util.subscriber());`
        flux2.subscribe(Util.subscriber());
    }
}
