package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        new Thread(new MyRunnable(), "Thread 1").start();
        new Thread(new MyRunnable(), "Thread 2").start();
    }
}
