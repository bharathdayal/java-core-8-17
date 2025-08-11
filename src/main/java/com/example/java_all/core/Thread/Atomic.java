package com.example.java_all.core.Thread;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {

    AtomicInteger atomicInteger = new AtomicInteger(0);

    public void increment() {
        atomicInteger.incrementAndGet();
    }
}
