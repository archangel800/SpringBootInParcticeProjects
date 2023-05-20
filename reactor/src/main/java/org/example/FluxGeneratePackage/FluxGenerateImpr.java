package org.example.FluxGeneratePackage;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

public class FluxGenerateImpr {
    public static void main(String[] args) {

        //this goes into loop because generate method creates this loop, provides one object of synchronousSink and it is used, after that it provides another object again
        Flux.generate(synchronousSink -> {
            System.out.println("emitting");
            //generate is responsible for emitting data again and again in loop
            synchronousSink.next(Util.faker().country().name());
           // synchronousSink.next(Util.faker().country().name()); // we can only emit one item
            // this is not the case in terms of fluxsink
           // synchronousSink.complete();//if we call this this is goint to be cancelled after it emits the first item;

        })
                .take(2)
                .subscribeWith(Util.subscriber());
    }
}
