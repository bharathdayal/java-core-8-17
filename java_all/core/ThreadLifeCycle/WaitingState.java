package com.example.java_all.core.ThreadLifeCycle;

import com.example.java_all.designpattern.SingletonLoggerClass;

public class WaitingState implements Runnable{

    @Override
    public void run() {

            Thread t2 =  new Thread(new TimeWaiting());
            t2.start();
            try {
                t2.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }


    }
}
