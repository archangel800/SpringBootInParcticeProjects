package org.example.Test;

import com.sun.jdi.InvalidTypeException;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class StepVerifierTestPractice {
    @Test
    public void test1() {
        Flux<Integer> just = Flux.just(1, 2, 3);
        StepVerifier.create(just)
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .verifyComplete();
    }
    @Test
    public void test2() {
        Flux<Integer> just = Flux.just(1, 2, 3);
        StepVerifier.create(just)
                .expectNext(1,2,3)
                .verifyComplete();
    }
}
