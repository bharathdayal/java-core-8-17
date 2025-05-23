package com.example.java_all.java17.Sealed;

import com.example.java_all.designpattern.SingletonLoggerClass;

public non-sealed class BankTransferPayment extends Payment {

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    @Override
    public String process(){
       return logger.logsSealedClass("Bank payment process");
    }

    public String impsProcess() {
        return logger.logsSealedClass("IMPS payment process");
    }
}
