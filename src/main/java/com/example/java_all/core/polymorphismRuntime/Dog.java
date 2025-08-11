package com.example.java_all.core.polymorphismRuntime;

import com.example.java_all.designpattern.SingletonLoggerClass;

public class Dog extends Animal{
    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    @Override
    public void makeSound() {
        logger.logsRuntimePoly("Dog sound - Bark !");
    }

    @Override
    public String makeFood(String animal, Integer count) {
        return logger.logsRuntimePoly(animal+ " food Daily intake count "+count);
    }

    @Override
    public void makeFood(String food) {
        logger.log("Dog food "+food);

    }
}
