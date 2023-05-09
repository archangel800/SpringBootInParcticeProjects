package org.example;

/**
 * Hello world!
 *
 */
public class App extends Thread
{
    public static void main( String[] args )
    {
        Runnable task = new DefaultRunnable();
        Thread t1 = new Thread(task);

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                System.out.println("I'm a new Thread! My name is " + Thread.currentThread().getName());
            }
        });

        Thread t3 = new App();

        Thread t4 = new Thread(() -> System.out.println("I'm a new thread! My name is " + Thread.currentThread().getName()));

        Thread t5 = new Thread(App::execute);

        System.out.println("Current thread name is " + Thread.currentThread().getName());

        t1.run();

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }
    public static void execute() {
        System.out.println("I'm a new thread! My name is " + Thread.currentThread().getName());
    }
    @Override
    public void run() {
        System.out.println("I'm a new thread! My name is " + Thread.currentThread().getName());
    }
}
