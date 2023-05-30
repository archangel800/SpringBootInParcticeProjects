package manyAtOnce;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class ErrorTransformDefaultSwitchIfEmpty {
    public static void main(String[] args) throws InterruptedException {

        getPerson()
                .switchIfEmpty(ifEmpty())
                .transform(transformationFunction())
                .subscribe(Util.subscriber());
        Thread.sleep(50000);

    }

    public static  Flux<Person> getPerson() {
        return Flux.range(1, 20)
                .map(i -> new Person())
                .filter(person -> person.getAge() > 15)
                .doOnDiscard(Person.class, o -> System.out.println("Discarded: " + o.toString()));

    }

    public static Flux<Person> ifEmpty() {
        return Flux.range(1, 20)
                .map(i -> new Person("no name", 0));
    }

    public static Function<Flux<Person>, Flux<Person>> transformationFunction() {
        return flux -> flux
                .doOnNext(item -> item.setName(item.getName().toUpperCase()));
    }
}
