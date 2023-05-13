package org.example.FluxFromArrayList;

import reactor.core.publisher.Flux;

import java.util.List;

public class FluxFromArrayListLesson {
    public static void main(String[] args) {
       List<String> items = List.of("a", "b", "c");
        Integer[] arr = {2, 5, 8, 1};

        Flux.fromIterable(items)
                .subscribe(System.out::println);

        Flux.fromArray(arr)
                .subscribe(System.out::println);

    }
}
