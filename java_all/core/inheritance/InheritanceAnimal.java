package com.example.java_all.core.inheritance;

import com.example.java_all.designpattern.SingletonLoggerClass;

public class InheritanceAnimal {

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    public String name;

    public InheritanceAnimal(String name) {
        this.name = name;
    }

    public void Animaleat() {
        logger.logInheritance("Animal is eating...");
    }


}
