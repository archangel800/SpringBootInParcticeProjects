package flatmapDemo;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

public class flatMapDemoExample {
    public static void main(String[] args) {
        OrderService.populateDb();

        UserService.getUsers()
                .flatMap(item -> OrderService.getPurchaseOrderById(item.getId()))
                .subscribe(Util.subscriber());
    }
}
