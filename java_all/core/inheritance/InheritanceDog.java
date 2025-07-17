package com.example.java_all.core.inheritance;

import com.example.java_all.designpattern.SingletonLoggerClass;

public class InheritanceDog extends  InheritanceAnimal{

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    public InheritanceDog(String name) {
        super(name);
        logger.logInheritance("Constructor inherited from Animal");
    }

    public void dogBark() {
        logger.logInheritance("Dog is barking");

    }
}
