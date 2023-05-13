package org.example.fluxfrommono;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxFromMono {
    public static void main(String[] args) {
//        Mono<String> a = Mono.just("A");
//
//        Flux<String> from = Flux.from(a);
//
//        doSomething(from);

        Flux.range(1, 10)
                .filter(i -> i > 3)
                .next()
                .subscribe(System.out::println, err-> System.out.println(err.getMessage()), () -> System.out.println("Completed"));
    }

    private static void doSomething(Flux<String> flux) {
        flux.subscribe(System.out::println);
    }
}
