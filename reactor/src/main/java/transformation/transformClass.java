package transformation;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class transformClass {
    public static void main(String[] args) {
        generatePeople()
                .transform(transformPeople())
                .subscribe(Util.subscriber());
    }
    public static Flux<Person> generatePeople() {
        return Flux.range(1, 15)
                .map(p -> new Person());
    }

    public static Function<Flux<Person>, Flux<Person>> transformPeople()
    {
        return item -> item.filter(i -> i.getAge() > 15)
                .doOnNext(p -> p.setName(p.getName().toUpperCase()))
                .doOnDiscard(Person.class, o -> System.out.println("Discarded: " + o));
    }
}
