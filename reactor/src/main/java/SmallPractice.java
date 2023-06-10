import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

public class SmallPractice {
    public static void main(String[] args) {
        Flux<Object> flux = Flux.generate(synchronousSink -> {
            synchronousSink.next("giorgi");
        });

        flux.subscribe(Util.subscriber("Nodo"));

        flux.subscribe(Util.subscriber("Dato"));



    }
}
