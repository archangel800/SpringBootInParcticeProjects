package org.example.sec01;

import org.example.corseUtil.Util;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

public class App
{
    public static void main( String[] args )
    {
        Mono<Integer> mono = Mono.just("eight")
                .map(String::length)
                .map(item -> item/1);

        mono.subscribe(Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }
}
