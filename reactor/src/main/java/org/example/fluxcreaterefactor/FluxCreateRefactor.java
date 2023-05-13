package org.example.fluxcreaterefactor;

import org.example.corseUtil.Util;
import org.example.fluxCreate.helper.NameProducer;
import reactor.core.publisher.Flux;

public class FluxCreateRefactor {

    public static void main(String[] args) {
        NameProducer nameProducer = new NameProducer();

        Flux.create(nameProducer)
                .subscribe(Util.subscriber());

        nameProducer.produce();
    }
}
