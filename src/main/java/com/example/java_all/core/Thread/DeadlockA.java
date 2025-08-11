package com.example.java_all.core.Thread;

import com.example.java_all.designpattern.SingletonLoggerClass;

public class DeadlockA {

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    public synchronized void methodA(DeadlockB b) {

        logger.log("Thread-1: holding lock on A");
        try {
            Thread.sleep(1000);
        }catch (Exception e) {
            logger.log("ERROR"+e.getMessage());
        }
        b.last();

    }

    synchronized void last() {
        logger.log("Inside A.last()");
    }
}
