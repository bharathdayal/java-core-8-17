package com.example.java_all.core.ThreadLifeCycle;

import com.example.java_all.designpattern.SingletonLoggerClass;

public class NewState extends Thread{

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    @Override
    public void run() {
        Thread t = new Thread();
        logger.logThread(String.valueOf(t.getState()));

    }

}
