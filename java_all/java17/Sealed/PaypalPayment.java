package com.example.java_all.java17.Sealed;

import com.example.java_all.designpattern.SingletonLoggerClass;

public final class PaypalPayment extends Payment{

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    @Override
    public String process(){
        return logger.logsSealedClass("Paypal payment process");
    }
}
