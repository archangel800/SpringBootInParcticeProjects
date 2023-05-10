package org.example;

public class Producer implements Runnable{
    private Message message;

    public Producer(Message message) {
        this.message = message;
    }
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                message.produce();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
