package com.ss.weekone.thurs;

import java.util.LinkedList;
import java.util.Random;

/**
 * @author Michael Acker
 * Assignment from 3/11/21
 * This class facilitates symbiosis between two threads: a producer and a consumer.
 */

public class Threads {

    public static void main(String[] args) {

        LinkedList<Integer> buffer = new LinkedList<>();
        int capacity = 100;
        Random rand = new Random();

        Runnable producer = () -> {
            while (true) {
                synchronized (buffer) {
                    try {
                        if (buffer.size() < capacity) {
                            buffer.add(rand.nextInt());
                            Thread.sleep(100);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Runnable consumer = () -> {
            while (true) {
                synchronized (buffer) {
                    try {
                        if (!buffer.isEmpty()) {
                            System.out.println(buffer.removeFirst());
                        }
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
