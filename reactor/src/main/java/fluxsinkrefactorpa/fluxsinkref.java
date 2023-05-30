package fluxsinkrefactorpa;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

public class fluxsinkref {
    public static void main(String[] args) {
        Flux.generate(() -> 1, (counter, synchronousSink) -> {
            String country = Util.faker().country().name();
            synchronousSink.next(country);
            if (country.toLowerCase().equals("canada") || counter > 10) {
                synchronousSink.complete();
            }
            counter++;
            return counter;
        }).subscribe(Util.subscriber());
    }
}
