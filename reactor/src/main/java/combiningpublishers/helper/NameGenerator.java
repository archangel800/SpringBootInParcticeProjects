package combiningpublishers.helper;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NameGenerator {

    private List<String> list = new ArrayList<>();
    public Flux<String> generateNames() {
        return Flux.generate(stringSynchronousSink -> {
            System.out.println("generated fresh");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String name = Util.faker().name().firstName();
            list.add(name);
            stringSynchronousSink.next(name);
        }).
                cast(String.class)
                .startWith(getFromCache());
    }

    private Flux<String> getFromCache() {
        return Flux.fromIterable(list);
    }
}
