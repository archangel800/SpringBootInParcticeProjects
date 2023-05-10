package org.example;

public class Message {
    private int message;
    private boolean isProduced=false;

    public static int counter = 0;

    public Message(int message) {
        this.message = message;
    }
    public Message() {
    }

    public void setMessage(int message) {
        this.message = message;
    }
    public int getMessage() {
        return message;
    }

    public synchronized void produce() throws InterruptedException {
        if(isProduced) {
            wait();
        }
        message = counter++;
        System.out.println("Produced: " + message);
        notify();
        isProduced=true;
    }

    public synchronized void consume() throws InterruptedException {
        if(!isProduced){
            wait();
        }
        System.out.println("Consumed: " + message);
        notify();
        isProduced=false;
    }
}
