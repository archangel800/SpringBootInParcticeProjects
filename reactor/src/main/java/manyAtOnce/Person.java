package manyAtOnce;

import lombok.Data;
import lombok.ToString;
import org.example.corseUtil.Util;

@Data
@ToString
public class Person {
    private String name;
    private int age;

    public Person() {
        this.name = Util.faker().name().fullName();
        this.age = Util.faker().random().nextInt(20) + 10;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
