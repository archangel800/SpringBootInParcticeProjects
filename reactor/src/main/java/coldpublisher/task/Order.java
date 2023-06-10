package coldpublisher.task;

import lombok.Data;
import lombok.ToString;
import org.example.corseUtil.Util;

@Data
@ToString
public class Order {
    private long id;
    private double price;

    private String category;

    private int quantity;

    public Order (long id, String category) {
        this.category = category;
        this.id = id;
        this.price = Util.faker().random().nextDouble() + 4.3;
        this.quantity = Util.faker().random().nextInt(1, 10);
    }
}
