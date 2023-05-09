package org.example;

public class DefaultRunnable implements Runnable{
    private String externalString;

    public DefaultRunnable(){}
    public DefaultRunnable(String externalString) {
        this.externalString = externalString;
    }

    public void run() {
        System.out.println("I'm a new thread! My name is " + Thread.currentThread().getName());
    }
}
