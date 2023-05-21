package org.example.Handle;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

public class Handlee {
    public static void main(String[] args) {
        Flux.range(1, 30)
                .handle((integer, synchronousSink) -> {
                    String country = Util.faker().country().name();
                    synchronousSink.next(integer + ": " + country);

                    if (country.toLowerCase().equals("canada")) {
                        synchronousSink.complete();
                    }

                }).subscribe(Util.subscriber());

        Flux.generate(synchronousSink -> synchronousSink.next(Util.faker().country().name()))
                .map(Object::toString)
                .handle((country, synchronousSink) -> {
                    if (country.toLowerCase().equals("canada")) {
                        synchronousSink.complete();
                    }
                }).subscribe(Util.subscriber());
    }
}
