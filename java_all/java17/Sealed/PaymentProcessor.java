package com.example.java_all.java17.Sealed;

public class PaymentProcessor {

    public String handlePayment(Payment payment) {
        return switch (payment) {
            case CreditCardPayment c-> c.process();
            case PaypalPayment p-> p.process();
            case BankTransferPayment b->b.impsProcess();

        };
    }
}
