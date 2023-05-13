package org.example.fluxCreate.helper;

import org.example.corseUtil.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameProducer implements Consumer<FluxSink<String>> {

    private FluxSink<String> fluxSink;


    @Override
    public void accept(FluxSink<String> fluxSinkVar) {
        this.fluxSink = fluxSinkVar;
    }

    public void produce() {
        String name = Util.faker().name().fullName();
        String threadName = Thread.currentThread().getName();
        this.fluxSink.next(threadName + ": " + name);
    }
}