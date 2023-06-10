package overfloStrategy;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DropOverflow {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("reactor.bufferSize.small", "16");
        List<Object> droppedValues = new ArrayList<>();
        Flux.create(fluxSink -> {
                    for (int i = 1; i < 501; i++) {
                        fluxSink.next(i);
                        System.out.println("Pushed: " + i);
                        try {
                            TimeUnit.MILLISECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    fluxSink.complete();
                })
                .onBackpressureDrop(item -> droppedValues.add(item))
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }).subscribe(Util.subscriber());

        Util.sleepSeconds(20);
        System.out.println(droppedValues);
    }
    }
