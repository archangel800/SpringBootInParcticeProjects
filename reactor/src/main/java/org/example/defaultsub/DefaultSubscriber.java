package org.example.defaultsub;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.Flow;

public class DefaultSubscriber implements Subscriber<Object> {

    private String name;

    public DefaultSubscriber() {
        name="";
    }

    public DefaultSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Object o) {
        System.out.println(name + " Received: " + o);
    }

    @Override
    public void onError(Throwable t) {
        System.out.println(name + " Error: " + t.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println(name + " Completed " + Thread.currentThread().getName());
    }
}
