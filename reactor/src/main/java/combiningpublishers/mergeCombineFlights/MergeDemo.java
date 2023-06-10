package combiningpublishers.mergeCombineFlights;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.TimeUnit;

public class MergeDemo {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> merge = Flux.merge(QatarFlights.getFlights(), EmirateFlights.getFlights(), AmericanFlights.getFlights());
        Flux<String> merge1 = Flux.mergeSequential(QatarFlights.getFlights(), EmirateFlights.getFlights(), AmericanFlights.getFlights());
        merge.subscribe(Util.subscriber());

        TimeUnit.SECONDS.sleep(10);
    }
}
