package defaultifempty;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

public class DefaultIfEmply {
    public static void main(String[] args) {
getOrderNumbers()
        .filter(i -> i> 10)
        .defaultIfEmpty(100)
        .subscribe(Util.subscriber());
    }
    private static Flux<Integer> getOrderNumbers() {
        return Flux.range(1, 10);
    }
}
