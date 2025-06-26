package com.example.java_all.core.Thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableThread {

    public void completableThread() {

        List<String> input = Arrays.asList("password123", "secret", "hello world", "openai", "java rocks");

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<CompletableFuture<String>> futures =input.stream()
                .map(n->CompletableFuture.supplyAsync(() -> String.valueOf(hashCode()),executorService))
                .peek(System.out::println)
                .toList();
    }
}
