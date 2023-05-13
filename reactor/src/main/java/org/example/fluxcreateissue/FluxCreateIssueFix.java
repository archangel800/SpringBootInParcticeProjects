package org.example.fluxcreateissue;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

public class FluxCreateIssueFix {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
                    String country;

                    do{
                        country = Util.faker().country().name();
                        System.out.println("emmiting: " + country);
                        fluxSink.next(country);
                    }
                    while(!country.toLowerCase().equals("canada") && !fluxSink.isCancelled());

                    fluxSink.complete();
                })
                .take(3)
                .subscribe(Util.subscriber());
    }
}
