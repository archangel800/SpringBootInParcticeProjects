package org.example.FluxRange;

import reactor.core.publisher.Flux;

public class FluxRangeLEsson {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .log()
                .map(item -> "name")
                .subscribe(System.out::println);
    }
}
