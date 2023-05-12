package org.example;

import java.util.concurrent.TimeUnit;

public class MyRunnable implements Runnable{
    private ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public void run() {
        threadLocal.set(Thread.currentThread().getName() + " -- threadLocal");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(threadLocal.get());

    }
}
