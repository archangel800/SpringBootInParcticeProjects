package org.example.MultipleSubscribers;

import reactor.core.publisher.Flux;

public class MultipleSubscribersLesson {


    public static void main(String[] args) {

        Flux<Integer> flux = Flux.just(1, 2, 3, 4);

        flux.filter(item -> item % 2 == 0)
                        .subscribe(yo -> System.out.println("Sub 3: " + yo));

        flux.subscribe(i -> System.out.println("Sub 1: " + i));
        flux.subscribe(i -> System.out.println("Sub 2: " + i));
    }
}
