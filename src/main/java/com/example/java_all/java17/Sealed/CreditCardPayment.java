package com.example.java_all.java17.Sealed;

import com.example.java_all.designpattern.SingletonLoggerClass;

public final class CreditCardPayment extends Payment{

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    @Override
    public String process(){
        return logger.logsSealedClass("Credit card payment process");
    }
}
