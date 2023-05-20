package org.example.FluxGeneratePackage;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

public class FluxGenerateTask {
    public static void main(String[] args) {

        //when u see canada exit
        //but u can only emit 10 items
        //if the subscriber cancels - exit

        Flux.generate(synchronousSink -> {

            String country = Util.faker().country().name();
            synchronousSink.next(country);
            if(country.toLowerCase().equals("canada"))
                synchronousSink.complete();
        }).subscribe(Util.subscriber());
    }
}
