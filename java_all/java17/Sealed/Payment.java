package com.example.java_all.java17.Sealed;

public sealed abstract class Payment permits CreditCardPayment,PaypalPayment,BankTransferPayment{

    public abstract String process();

}
