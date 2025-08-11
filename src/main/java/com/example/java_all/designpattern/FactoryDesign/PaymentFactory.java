package com.example.java_all.designpattern.FactoryDesign;

public class PaymentFactory {

    public static PaymentProcess getPaymentMethod(String type) {

        if("Gpay".equalsIgnoreCase(type)) {
            return new GooglePay();
        } else if("Applepay".equalsIgnoreCase(type)) {
            return new ApplePay();
        }
        throw new IllegalArgumentException("Unknown Payment Method: " +type);
    }
}
