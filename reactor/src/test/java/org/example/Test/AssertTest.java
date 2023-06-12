package org.example.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

public class AssertTest {
    @Test
    public void test1() {
        Mono<BookOrder> bookOrderMono = Mono.fromSupplier(() -> new BookOrder());
        StepVerifier.create(bookOrderMono)
                .assertNext(b -> Assertions.assertNotNull(b.getName()))
                .verifyComplete();
    }
    @Test
    public void test2() {
        Mono<BookOrder> bookOrderMono = Mono.fromSupplier(() -> new BookOrder()).delayElement(Duration.ofSeconds(3));
        StepVerifier.create(bookOrderMono)
                .assertNext(b -> Assertions.assertNotNull(b.getName()))
                .expectComplete()
                .verify(Duration.ofSeconds(4));
    }
}
