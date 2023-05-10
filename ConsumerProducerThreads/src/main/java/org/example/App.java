package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Message message = new Message();
        Producer producer = new Producer(message);
        Consumer consumer = new Consumer(message);
        new Thread(producer, "Producer").start();
        new Thread(consumer, "Consumer").start();
    }
}
