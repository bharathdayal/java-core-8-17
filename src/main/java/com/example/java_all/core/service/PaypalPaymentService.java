package com.example.java_all.core.service;

import com.example.java_all.designpattern.SingletonLoggerClass;
import com.example.java_all.interfaces.PaymentInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PaypalPaymentService implements PaymentInterface {

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    @Override
    public void processPayment(double amount) {

        logger.log("Processing paypal  payment: " + amount);
    }
}
