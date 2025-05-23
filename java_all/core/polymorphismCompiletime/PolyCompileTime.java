package com.example.java_all.core.polymorphismCompiletime;

import com.example.java_all.designpattern.SingletonLoggerClass;

public class PolyCompileTime {
    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    public void message(String msg) {
        logger.logsCompiletimePoly("Message received "+msg);

    }

    public void message(Integer cnt) {
        logger.logsCompiletimePoly("Message count "+cnt);
    }
}
