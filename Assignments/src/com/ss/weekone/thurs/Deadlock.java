package com.ss.weekone.thurs;

/**
 * @author Michael Acker
 * Assignment from 3/11/21
 * This class intentionally creates deadlock between two threads
 */

public class Deadlock {

    public static void main(String[] args) {
        System.gc();
        Object lock1 = new Object();
        Object lock2 = new Object();

        System.out.println("Begin");

        Runnable t1 = () -> {
            try {
                synchronized (lock1) {
                    Thread.sleep(100);
                    synchronized (lock2) {
                        System.out.println("This will never print");
                    }
                }
            } catch (Exception e) {
            }
        };

        Runnable t2 = () -> {
            try {
                synchronized (lock2) {
                    Thread.sleep(100);
                    synchronized (lock1) {
                        System.out.println("This won't print either");
                    }
                }
            } catch (Exception e) {
            }
        };

        new Thread(t1).start();
        new Thread(t2).start();
    }
}
