package org.example.FluxGeneratePackage;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

public class FluxGenerateSupplier {
    public static void main(String[] args) {
        Flux.generate(() -> 1,
                        (counter, sink) -> {
                            String country = Util.faker().country().name();
                            sink.next(country);
                            if (counter >= 10 || country.toLowerCase().equals("canada")) {
                                sink.complete();
                            }
                            return counter + 1;
                        })
                .subscribe(Util.subscriber());
    }
}
