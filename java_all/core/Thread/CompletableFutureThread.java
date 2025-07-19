package com.example.java_all.core.Thread;

import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

public class CompletableFutureThread {

  public void CompletableFuture() {

      CompletableFuture<String> userFuture = CompletableFuture.supplyAsync( () -> {
          try {
              return getUser();
          } catch (InterruptedException e) {
              throw new RuntimeException(e);
          }
      });
      CompletableFuture<String> orderFuture = CompletableFuture.supplyAsync( () -> {
          try {
              return getOrders();
          } catch (InterruptedException e) {
              throw new RuntimeException(e);
          }
      });

      CompletableFuture<String> combine =userFuture.thenCombine(orderFuture,
              (user,order)->user + '-' + order);

      combine.thenAccept(System.out::println);
  }

  public static  String getUser() throws InterruptedException {
      sleep(1);
      return "User:Bharath";
  }

  public static String getOrders() throws InterruptedException{
      sleep(1);
      return "Orders: 1";

  }
}
