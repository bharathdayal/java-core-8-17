package com.example.java_all.java17.Record;

public record  LoginEvent(String username,Long timestamp) implements IEvent {
    public void test() {
        System.out.println("TEST");
    }
}
