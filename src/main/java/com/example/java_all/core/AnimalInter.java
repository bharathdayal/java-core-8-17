package com.example.java_all.core;

@FunctionalInterface
public interface AnimalInter {

    void sound();

    default void sleep() {
        System.out.println("Default");
    }
}
