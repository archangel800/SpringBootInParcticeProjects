package sinks;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkForUnicastPractice {
    public static void main(String[] args) {
        Sinks.Many<String> sink = Sinks.many().unicast().onBackpressureBuffer();

        Flux<String> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("Giorgi"));


        sink.tryEmitNext("Hi Giorgi!");;
        sink.tryEmitComplete();

    }
}
