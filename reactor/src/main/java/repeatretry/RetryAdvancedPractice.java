package repeatretry;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

public class RetryAdvancedPractice {
    public static void main(String[] args) throws InterruptedException {
        generateFromDatabase()
                .doOnError(err -> err.getMessage())
                .retryWhen(Retry.from(flux ->
                    flux.doOnNext(item -> {
                        System.out.println(item.totalRetries());
                        System.out.println(item.failure());
                    })
                            .handle((retrySignal, synchronousSink) -> {
                                if (retrySignal.failure().getMessage().equals("500")) {
                                    synchronousSink.next(1);
                                }
                                else {
                                    synchronousSink.error(retrySignal.failure());
                                }
                            })
                            .delayElements(Duration.ofSeconds(1))
                ))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }
    public static Mono<String> generateFromDatabase() {
        return Mono.fromSupplier(() -> {
            processPayment();
            return Util.faker().idNumber().valid();
        });
    }
    public static void processPayment(){
        int number = Util.faker().random().nextInt(1, 10);
        if(number < 8 ) {
            throw new RuntimeException("500");
        }
        if(number < 10) {
            throw new RuntimeException("400");
        }
    }
}
