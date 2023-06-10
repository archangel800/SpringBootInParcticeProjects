package sinks;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkUnicastTask {
    public static void main(String[] args) {
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("Sam"));
        flux.subscribe(Util.subscriber("Mike"));

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");
    }
}
