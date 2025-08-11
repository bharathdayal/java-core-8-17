package com.example.java_all.core.Thread;

import com.example.java_all.designpattern.SingletonLoggerClass;

public class DeadlockB {

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    public synchronized void methodB(DeadlockA a) {

        logger.log("Thread-2: holding lock on B");
        try {
            Thread.sleep(1000);
        }catch (Exception e) {
            logger.log("ERROR"+e.getMessage());
        }
        a.last();

    }

    synchronized void last() {
        logger.log("Inside B.last()");
    }
}
