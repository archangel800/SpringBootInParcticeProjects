package org.example.FluxFromStream;

import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class FluxFromStreamLesson {
    public static void main(String[] args) {
        List<Integer> integerList = List.of(1, 2, 3, 4);

        Stream<Integer> stream = integerList.stream();

   //     stream.forEach(System.out::println);

        Flux<Integer> flux = Flux.fromStream(stream);
        flux.subscribe(System.out::println);
//        flux.subscribe(System.out::println);//this is not gonna work because stream is already consumed

        //solution to this problem is
        Flux<Integer> flux1 = Flux.fromStream(() -> integerList.stream());
        flux1.subscribe(System.out::println);
        flux1.subscribe(System.out::println);
    }
}
