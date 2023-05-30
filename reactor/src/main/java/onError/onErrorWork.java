package onError;

import org.example.corseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class onErrorWork {
//    public static void main(String[] args) {
//        Flux.range(1, 10)
//                .log()
//                .map(i -> 10 / (5 - i))
//                .onErrorReturn(-1) //some kind of fallback
//                .subscribe(Util.subscriber());

        public static void main(String[] args) {
//        Flux.range(1, 10)
//                .log()
//                .map(i -> 10 / (5 - i))
//                .onErrorResume(e -> fallback()) //on error resume with something, and do something
//                .subscribe(Util.subscriber());

            Flux.range(1, 10)
                    .log()
                    .map(i -> 10 / (5 - i))
                    .onErrorContinue((err, obj) -> {

                    })//in this case err is the exception itself, and obj is something that caused this error
                    .subscribe(Util.subscriber());

    }
    public static Mono<Integer> fallback() {
            return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 200));
    }
}
