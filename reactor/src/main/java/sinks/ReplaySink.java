package sinks;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class ReplaySink {
    public static void main(String[] args) {
        Sinks.Many<String> objectMany = Sinks.many().replay().all();
        Flux<String> flux = objectMany.asFlux();

        objectMany.tryEmitNext("hi");
        objectMany.tryEmitNext("how are you");

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));
        objectMany.tryEmitNext("?");
        flux.subscribe(Util.subscriber("Jake"));
        objectMany.tryEmitNext("new msg");
    }
}
