package org.example.sec01;

import org.example.corseUtil.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class MonoFromSupplier {
    public static void main(String[] args) {
      //  Mono<String> just = Mono.just(getName());
        Supplier<String> stringSupplier = () -> getName();

        Callable<String> stringCallable = () -> getName();

        Mono.fromCallable(stringCallable)
                .subscribe(Util.onNext());

        Mono<String> stringMono = Mono.fromSupplier(() -> getName());
        stringMono.subscribe(Util.onNext());
    }


    private static String getName() {
        System.out.println("Generating name ...");
        return Util.faker().name().fullName();
    }
}
