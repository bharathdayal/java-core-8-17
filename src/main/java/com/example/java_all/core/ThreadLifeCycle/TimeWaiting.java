package com.example.java_all.core.ThreadLifeCycle;

import com.example.java_all.designpattern.SingletonLoggerClass;

public class TimeWaiting implements Runnable{

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    @Override
    public void run() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        logger.logThread(String.valueOf(Thread.currentThread().getState()));
    }
}
