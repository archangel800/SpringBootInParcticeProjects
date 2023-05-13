package org.example.Subscription;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

public class SubscriptionLecture {
    public static void main(String[] args) throws InterruptedException {
        AtomicReference<Subscription> atomicReference = new AtomicReference<>();
        Flux.range(1, 20)
                .log()
                .subscribeWith(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        System.out.println("Received Subscription: " + subscription);
                        atomicReference.set(subscription);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("onError: " + throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });

        Thread.sleep(3000);
        atomicReference.get().request(3);
        Thread.sleep(5000);
        atomicReference.get().request(3);
        Thread.sleep(5000);
        System.out.println("Going to cancel ");
        atomicReference.get().cancel();
        Thread.sleep(3000);
        atomicReference.get().request(4);
        Thread.sleep(3000);
    }
}
