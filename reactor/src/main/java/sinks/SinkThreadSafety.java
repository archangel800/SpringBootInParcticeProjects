package sinks;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SinkThreadSafety {
    public static void main(String[] args) throws InterruptedException {
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Object> flux = sink.asFlux();


        List<Object> list = new ArrayList<>();

        flux.subscribe(list::add);
//        for (int i = 0; i < 1000; i++) {
//            final int j = i;
//            CompletableFuture.runAsync(() -> {
//                sink.tryEmitNext(j);
//            });
//        }

        for (int i = 0; i < 1000; i++) {
            final int j = i;
            CompletableFuture.runAsync(() -> {
                sink.emitNext(j, (s, e) -> true);
            });
        }
        Util.sleepSeconds(5);
        System.out.println(list.size());
    }
}
