package flatmapDemo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class OrderService {
    private static HashMap<Integer, List<PurchaseOrder>> db = new HashMap<>();

    public static void populateDb() {
        db.put(1, Arrays.asList(new PurchaseOrder(1), new PurchaseOrder(1), new PurchaseOrder(1)));
        db.put(2, Arrays.asList(new PurchaseOrder(2), new PurchaseOrder(2)));
        db.put(3, Arrays.asList(new PurchaseOrder(3), new PurchaseOrder(3)));

    }
    public static Flux<PurchaseOrder> getPurchaseOrderById(int id) {
      return  Flux.create((FluxSink <PurchaseOrder> fluxSink)-> {
            List<PurchaseOrder> items = db.get(id);
            for(var item : items)
                fluxSink.next(item);
        }).delayElements(Duration.ofSeconds(2));
    }
}
