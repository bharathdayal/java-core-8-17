package com.example.java_all.codinginterview;

import com.example.java_all.designpattern.SingletonLoggerClass;
import com.example.java_all.interfaces.IArrayList;

import java.util.*;

public class DuplicateArrayList implements IArrayList {

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    @Override
    public void duplicateArray() {
        List<Integer> list = Arrays.asList(1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 7, 7, 8, 9, 9, 10);

        Set<Integer> set = new LinkedHashSet<>(list);

        List<Integer> result = new ArrayList<>(set);

        logger.log(result.toString());
    }

    @Override
    public void countFrequentElement() {

        List<String> list = Arrays.asList("apple", "banana", "apple", "orange", "banana", "banana");

        Map<String,Integer> map = new HashMap<>();

        for(String item:list) {
            map.put(item,map.getOrDefault(item,0)+1);
        }
        logger.log(map.toString());
    }

    @Override
    public void reverseArray() {

        List<Integer> list = Arrays.asList(1,2,3,4,5);
        List<Integer> reverse = new ArrayList<>();

        for(int i=list.size()-1; i>=0;i--) {
            reverse.add(list.get(i));
        }
        logger.log(reverse.toString());
    }
}
