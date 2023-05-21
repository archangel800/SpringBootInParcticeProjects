package org.example.docallbacks;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

public class DoCallbacks {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            System.out.println("inside create");
            for (int i = 0; i < 5; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
            System.out.println("Completed");
        }).subscribe(Util.subscriber());

    }
}
