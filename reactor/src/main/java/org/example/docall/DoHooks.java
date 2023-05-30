package org.example.docall;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

public class DoHooks {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            System.out.println("------inside fluxSink------");
            for(int i = 0; i<20; i++) {
                fluxSink.next(Util.faker().country().name());
            }
            fluxSink.complete();
            System.out.println("-----Completed-----");
        })
                .doFinally(signalType -> System.out.println("doFinally: " + signalType))
                .doFirst(() -> System.out.println("DoFirst"))
                .doOnSubscribe(subscription -> System.out.println("DoOnSubscribe: " + subscription))
                .doOnRequest(value -> System.out.println("doOnRequest: " + value))
                .doOnNext(item -> System.out.println("doOnNext: " + item))
                .doOnDiscard(Object.class, item -> System.out.println("doOnDiscard: " + item.toString()))
                .doOnCancel(() -> System.out.println("doOnCancel"))
                .doOnError(err -> System.out.println("doOnErr: " + err.getMessage()))
                .doOnComplete(() -> System.out.println("doOnComplete"))
                .doOnTerminate(() -> System.out.println("doOnTerminate"))
                .subscribe(Util.subscriber());
    }
}
