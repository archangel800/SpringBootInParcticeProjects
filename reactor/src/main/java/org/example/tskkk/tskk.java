package org.example.tskkk;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class tskk {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
            AtomicInteger atomicInteger = new AtomicInteger(100);
        Random random = new Random();
        Flux.interval(Duration.ofSeconds(1))
                .map(item -> {atomicInteger.set(atomicInteger.get() + random.nextInt(10)-5);
                return atomicInteger;})
                .subscribeWith(new Subscriber<AtomicInteger>() {
                    AtomicReference<Subscription> subscriptionAtomicReference = new AtomicReference<>();
                    @Override
                    public void onSubscribe(Subscription s) {
                        subscriptionAtomicReference.set(s);
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(AtomicInteger atomicInteger) {
                        int value = atomicInteger.get();
                        if(value <=90 || value >= 104) {
                            subscriptionAtomicReference.get().cancel();
                            countDownLatch.countDown();
                        }
                        System.out.println("output: " + value);
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
