package flatmapDemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.corseUtil.Util;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class PurchaseOrder {
    private Double price;
    private int userId;

    public PurchaseOrder(int userId) {
        this.price = Util.faker().random().nextDouble() + 29.5;
        this.userId = userId;
    }
}
