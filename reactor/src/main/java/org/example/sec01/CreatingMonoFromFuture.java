package org.example.sec01;

import org.example.corseUtil.Util;
import reactor.core.publisher.Mono;

import javax.crypto.spec.PSource;
import java.util.concurrent.CompletableFuture;

public class CreatingMonoFromFuture {

    public static void main(String[] args) throws InterruptedException {
//        Mono.fromFuture(getName()).subscribe(Util.onNext());
//        Util.sleepSeconds(1);

        Mono<String> mono1 = Mono.fromSupplier(() -> {
            // unchecked exception, no need to declare or handle it
            if (Math.random() < 0.5) {
                throw new RuntimeException("Error occurred");
            }
            return "hello";
        });

// using Mono.fromCallable
        Mono<String> mono2 = Mono.fromCallable(() -> {
            // checked exception, must be declared or handled
            if (Math.random() < 0.5) {
                throw new Exception("Error occurred");
            }
            return "hello";
        });

        mono2.subscribe(item -> System.out.println(item));
    }
    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> Util.faker().name().fullName());
    }
}
