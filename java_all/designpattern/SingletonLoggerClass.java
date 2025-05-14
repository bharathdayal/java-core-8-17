package com.example.java_all.designpattern;

public class SingletonLoggerClass {

    private static volatile SingletonLoggerClass instance =null;

    private SingletonLoggerClass() {

    }

    public static SingletonLoggerClass getInstance() {
        if(instance == null) {
            synchronized (SingletonLoggerClass.class) {
                if(instance==null) {
                    instance=new SingletonLoggerClass();
                }
            }
        }
        return instance;

    }

    public void log(String message) {
        System.out.println("[LOG] " + message);
    }


}
