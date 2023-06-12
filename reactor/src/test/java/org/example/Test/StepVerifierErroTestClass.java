package org.example.Test;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class StepVerifierErroTestClass {
    @Test
    public void test1() {
        Flux<Integer> just = Flux.just(1, 2, 3);
        Flux<Object> oops = Flux.error(new RuntimeException("oops"));
        Flux<Object> concat = Flux.concat(just, oops);

        StepVerifier.create(concat)
                .expectNext(1, 2, 3)
                .verifyError();
    }
    @Test
    public void test2() {
        Flux<Integer> just = Flux.just(1, 2, 3);
        Flux<Object> oops = Flux.error(new RuntimeException("oops"));
        Flux<Object> concat = Flux.concat(just, oops);

        StepVerifier.create(concat)
                .expectNext(1, 2, 3)
                .verifyError(RuntimeException.class);
    }

    @Test
    public void test3() {
        Flux<Integer> just = Flux.just(1, 2, 3);
        Flux<Object> oops = Flux.error(new RuntimeException("oops"));
        Flux<Object> concat = Flux.concat(just, oops);

        StepVerifier.create(concat)
                .expectNext(1, 2, 3)
                .verifyErrorMessage("oops");
    }
}
