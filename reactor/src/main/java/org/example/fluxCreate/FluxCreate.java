package org.example.fluxCreate;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

public class FluxCreate {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            String country;

            do{
                country = Util.faker().country().name();
                fluxSink.next(country);
            }
            while(!country.toLowerCase().equals("canada"));

            fluxSink.complete();
                })
                .subscribe(Util.subscriber());
    }
}
