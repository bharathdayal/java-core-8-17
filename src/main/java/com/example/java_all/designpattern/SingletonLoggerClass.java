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

    public String logsRuntimePoly(String message) {
        System.out.println("[Polymorphism-Runtime] " + message);
        return message;
    }

    public String logsCompiletimePoly(String message) {
        System.out.println("[Polymorphism-Compiletime] " + message);
        return message;
    }

    public void logInheritance(String message) {
        System.out.println("[Single Inheritance] " + message);
    }

    public void logThread(String message) {
        System.out.println("[Thread State- ] " + message);
    }

    public String logsSealedClass(String message) {
        System.out.println("[Sealed-Class] " + message);
        return message;
    }


}
