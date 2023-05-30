package flatmapDemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.corseUtil.Util;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private int age;
    private String name;
    private int id;

    public User(int userId) {
        this.age = Util.faker().random().nextInt(15) + 15;
        this.name = Util.faker().name().fullName();
        this.id = userId;
    }

}
