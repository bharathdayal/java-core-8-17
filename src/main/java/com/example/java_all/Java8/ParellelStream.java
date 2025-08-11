package com.example.java_all.Java8;

import java.util.List;
import java.util.stream.IntStream;

public class ParellelStream {

    public void parellelStream() {

        List<Integer> list= IntStream.rangeClosed(1,100)
                .boxed()
                .toList();

        list.parallelStream()
                .forEachOrdered(n->System.out.println(Thread.currentThread().getName() +" =>"+n));



    }
}
