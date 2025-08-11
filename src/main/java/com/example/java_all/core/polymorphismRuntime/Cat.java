package com.example.java_all.core.polymorphismRuntime;

import com.example.java_all.designpattern.SingletonLoggerClass;

public class Cat extends Animal{

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    @Override
    public void makeSound() {
        logger.logsRuntimePoly("Cat sound - Meow !");
    }

    @Override
    public String makeFood(String animal, Integer count) {
        return logger.logsRuntimePoly(animal+ " food Daily intake count "+count);
    }
}
