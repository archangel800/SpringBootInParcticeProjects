package org.example.fluxinterval;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class FluxInterval {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch =  new CountDownLatch(1);
        AtomicInteger price = new AtomicInteger(100);
        Random random = new Random();
        Flux.interval(Duration.ofSeconds(1))
                .map(item -> new AtomicInteger(price.get()+random.nextInt(11)-5))
                .subscribeWith(new Subscriber<AtomicInteger>() {
                    private AtomicReference<Subscription> atomicReference = new AtomicReference<>();
                    @Override
                    public void onSubscribe(Subscription s) {
                        this.atomicReference.set(s);
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(AtomicInteger atomicInteger) {
                        System.out.println(atomicInteger.get());

                        if(atomicInteger.get() > 104 || atomicInteger.get() < 90) {
                            atomicReference.get().cancel();
                            countDownLatch.countDown();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println(t.getMessage());
                        countDownLatch.countDown();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Completed");
                        countDownLatch.countDown();
                    }
                });

        countDownLatch.await();

    }
}
