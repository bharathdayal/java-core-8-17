package com.example.java_all.core.polymorphismRuntime;

import com.example.java_all.designpattern.SingletonLoggerClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Animal {

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    public void makeSound() {
        logger.logsRuntimePoly("Animal Sound");
    }

    public String makeFood(String animal, Integer count) {
        return logger.logsRuntimePoly(animal+ "food Daily intake count "+count);
    }

    public void makeFood(String food) {
        logger.log("Animal food "+food);

    }

    public void  makeFood(Integer food) {
        logger.log("Animal count "+food);
    }
}
