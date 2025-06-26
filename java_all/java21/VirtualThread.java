package com.example.java_all.java21;

import com.example.java_all.designpattern.SingletonLoggerClass;
import org.apache.tomcat.util.threads.VirtualThreadExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VirtualThread {

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    public void virtualExecutorService() throws InterruptedException {
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

        Runnable task =() -> {
            logger.log("Started by " +Thread.currentThread());
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            logger.log("Completed by " +Thread.currentThread());
        };

        for(int i=-0;i <10;i++) {
            executorService.submit(task);
        }

        executorService.shutdown();
        executorService.awaitTermination(2, TimeUnit.SECONDS);
    }
}
