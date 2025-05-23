package com.example.java_all.java17.Record;

public record LogoutEvent(String username,Long timestamp) implements IEvent {
}
