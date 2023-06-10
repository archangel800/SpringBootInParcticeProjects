package repeatretry;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class RetryWhenAdvanced {

    public static void main(String[] args) throws InterruptedException {
        orderService(Util.faker().business().creditCardNumber())
                .doOnError(err -> System.out.println(err.getMessage()))
                .retryWhen(Retry.from(retrySignalFlux -> retrySignalFlux.doOnNext(rs ->{
                    System.out.println(rs.totalRetries());
                    System.out.println(rs.failure());
                })
                                .handle((rs, synchronousSink) -> {
                                    if(rs.failure().getMessage().equals("500")){
                                        synchronousSink.next(1);
                                    }
                                    else {
                                        synchronousSink.error(rs.failure());
                                    }
                                })
                                .delayElements(Duration.ofSeconds(1))

                ))
                .subscribe(Util.subscriber());
Util.sleepSeconds(30);
    }
    private static Mono<String> orderService(String ccNumber) {
        return  Mono.fromSupplier(() -> {
            processPayment(ccNumber);
            return Util.faker().idNumber().valid();
        });
    }
    private static void processPayment(String ccNumber) {
        int random = Util.faker().random().nextInt(1, 10);
        if(random < 8) {
            throw new RuntimeException("500");
        }
        else if( random < 10) {
            throw new RuntimeException("404");
        }
    }
}
