package org.example;

public class Consumer implements Runnable {
    private Message message;

    public Consumer(Message message) {
        this.message = message;
    }
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                message.consume();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
