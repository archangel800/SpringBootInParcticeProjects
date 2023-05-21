package transformation;

import lombok.Data;
import lombok.ToString;
import org.example.corseUtil.Util;

@Data
@ToString
public class Person {
    private String name;
    private int age;

    public Person () {
        this.name = Util.faker().name().fullName();
        this.age = Util.faker().random().nextInt(15) + 10;
    }
}
