package org.example.limitrate;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;

public class LimitRateCl {
    public static void main(String[] args) {

        //75% of maximum rate
        Flux.range(1, 1000)
                .log()
                .limitRate(100)
                .subscribe(Util.subscriber());

//100% of maximum rate
        Flux.range(1, 1000)
                .log()
                .limitRate(100, 0)
                .subscribe(Util.subscriber());

// 99% of maximum rate
        Flux.range(1, 1000)
                .log()
                .limitRate(100, 99)
                .subscribe(Util.subscriber());


    }
}
