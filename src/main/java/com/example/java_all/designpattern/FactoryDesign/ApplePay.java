package com.example.java_all.designpattern.FactoryDesign;

import com.example.java_all.designpattern.SingletonLoggerClass;

public class ApplePay implements PaymentProcess{

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    public void processPayment() {
        logger.log("Apple Pay Method");
    }
}
