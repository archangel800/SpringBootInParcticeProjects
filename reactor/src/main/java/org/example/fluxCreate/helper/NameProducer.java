package org.example.fluxCreate.helper;

import org.example.corseUtil.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameProducer implements Consumer<FluxSink<String>> {

    private FluxSink<String> fluxSink;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.fluxSink = stringFluxSink;
    }

    public void produce() {
        String name = Thread.currentThread().getName();
        for(int i = 0; i<15; i++) {
            this.fluxSink.next(name + " " + Util.faker().name().fullName());
        }
    }


}
