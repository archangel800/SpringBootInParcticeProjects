package org.example.fluxfrommono;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxFromMono {
    public static void main(String[] args) {
        Mono<String> a = Mono.just("A");

        Flux<String> from = Flux.from(a);

        doSomething(from);
    }

    private static void doSomething(Flux<String> flux) {
        flux.subscribe(System.out::println);
    }
}
