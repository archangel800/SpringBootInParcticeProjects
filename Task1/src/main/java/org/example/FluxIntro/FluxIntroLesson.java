package org.example.FluxIntro;

import org.w3c.dom.ls.LSOutput;
import reactor.core.publisher.Flux;

public class FluxIntroLesson {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4);
        flux.subscribe(item -> System.out.println("Received: " + item),
                item -> System.out.println("Error"),
                () -> System.out.println("Completed"));
    }
}
