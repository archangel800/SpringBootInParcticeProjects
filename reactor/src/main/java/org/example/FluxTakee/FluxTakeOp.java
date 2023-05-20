package org.example.FluxTakee;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

public class FluxTakeOp {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .log()
                .take(3)
                .log()
                .subscribe(Util.subscriber());
    }
}
