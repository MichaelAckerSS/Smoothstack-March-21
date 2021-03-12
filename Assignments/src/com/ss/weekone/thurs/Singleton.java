package com.ss.weekone.thurs;

/**
 * @author Michael Acker
 * Assignment from 3/11/21
 * Singleton with double-checked locking
 */

public class Singleton {

    private static volatile Singleton instance;

    private Singleton(){

    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}
