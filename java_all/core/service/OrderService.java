package com.example.java_all.core.service;

import com.example.java_all.interfaces.PaymentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    @Qualifier("creditCardPaymentService")
    private PaymentInterface paymentInterface;


    public void placeOrder(double amount) {
        paymentInterface.processPayment(amount);
    }
}
