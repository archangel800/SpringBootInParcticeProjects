package org.example.fluxGeneratee;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

public class FluxGenerate {
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> {
            int i = 1;
            synchronousSink.next(i++ + ": " + Util.faker().country().name());
        }).subscribe(Util.subscriber());
    }
}
