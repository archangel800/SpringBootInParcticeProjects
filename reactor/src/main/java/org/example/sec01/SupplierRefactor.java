package org.example.sec01;

import org.example.corseUtil.Util;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class SupplierRefactor {
    public static void main(String[] args) throws InterruptedException {

        getName();
        getName().subscribeOn(Schedulers.boundedElastic()).block();
        getName();
        Util.sleepSeconds(4);
    }

    private static Mono<String> getName() {
        System.out.println("Entered getName method");
        return Mono.fromSupplier(() -> {
            System.out.println("Generating name ...");
            try {
                Util.sleepSeconds(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
           return  Util.faker().name() .fullName();
        }).map(String::toUpperCase);
    }
}
