package com.example.java_all.core.Thread;

import com.example.java_all.designpattern.SingletonLoggerClass;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedThreadPool extends InterruptedException{

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    public void FixedThread() throws InterruptedException {
        int numThreads = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for (int i = 1; i <= 10; i++) {
            int taskId = i;
            executorService.submit(
                    () ->{
                System.out.println("Running Task " + taskId + " on thread " + Thread.currentThread().getName());
                simulateCpuTask();
            });
        }


        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        logger.log("All Tasks are completed");
    }

    private static void simulateCpuTask() {
        // Simulate CPU-bound task
        long sum = 0;
        for (int i = 0; i < 1_000_000; i++) {
            sum += i;
        }
    }
}
