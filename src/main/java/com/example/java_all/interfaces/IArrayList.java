package com.example.java_all.interfaces;

import com.example.java_all.designpattern.SingletonLoggerClass;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public interface IArrayList {

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();


    default void duplicateArray() {
        List<Integer> list = Arrays.asList(1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 7, 7, 8, 9, 9, 10);
        List<Integer> result = list.stream().distinct().toList();
        logger.log(result.toString());
    }

    default  void countFrequentElement() {}

    default void reverseArray(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        Collections.reverse(list);
    }
}
