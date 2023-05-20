package org.example.fluxCreate;

import org.example.corseUtil.Util;
import org.example.fluxCreate.helper.NameProducer;
import reactor.core.publisher.Flux;

public class FluxCreate {
    public static void main(String[] args) throws InterruptedException {
//        Flux.create(fluxSink -> {
//            String country;
//                    do {
//                        country = Util.faker().country().name();
//                        fluxSink.next(country);
//                    }
//                    while (!country.toLowerCase().equals("canada"));
//                    fluxSink.complete();
//                })
//                .subscribe(Util.subscriber());

        NameProducer nameProducer = new NameProducer();
        Flux.create(nameProducer)
                .subscribeWith(Util.subscriber());

       Runnable runnable = () ->  nameProducer.produce();

       for (int i =0; i<10; i++) {
           new Thread(runnable).start();
       }

       Thread.sleep(5000);



    }
}
