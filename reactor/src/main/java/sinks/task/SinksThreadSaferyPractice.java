package sinks.task;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SinksThreadSaferyPractice {
    public static void main(String[] args) throws InterruptedException {
        Sinks.Many<String> sink = Sinks.many().unicast().onBackpressureBuffer();

        Flux<String> flux = sink.asFlux();

        List<String> items = new ArrayList<>();


        flux.subscribe(items::add);


        IntStream.range(1, 1001)
                .forEach(item -> CompletableFuture.runAsync(() -> sink.emitNext("Hey", (res, result) -> true)));

        TimeUnit.SECONDS.sleep(1);
        System.out.println(items.size());
    }
}
