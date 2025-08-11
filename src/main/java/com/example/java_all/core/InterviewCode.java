package com.example.java_all.core;

import com.example.java_all.designpattern.SingletonLoggerClass;

import java.util.*;
import java.util.stream.Collectors;

public class InterviewCode {

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();


    //Find the first non-repeating character in a String using Java Streams.

    public void findFirstNonRepChar() {

        String input = "swiss";
        Map<Character,Long> map = new HashMap<>();

        for(char ch:input.toCharArray()) {
            map.put(ch,map.getOrDefault(ch,0L)+1);
        }

        logger.log(map.toString());

        for(Map.Entry<Character,Long> entry:map.entrySet()) {
            if(entry.getValue()==1) {
                System.out.println("First non-repeating character: " + entry.getKey());
                return;
            }
        }
    }

    public void findSecondNonRepChar() {


        String input = "swiss";
        Map<Character,Long> map = new HashMap<>();

        for(char ch:input.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0L)+1);
        }

        List<Character> second = new ArrayList<>();

        for(Map.Entry<Character,Long> entry: map.entrySet()) {
            if(entry.getValue()==1) {
                second.add(entry.getKey());
            }
        }
        logger.log(second.toString());
        if(second.size() >=2) {

            System.out.println("Second non-repeating character: " + second.get(1));
        } else {
            System.out.println("Second non-repeating character not found.");

        }

    }

    public void findFirstNonRepCharStream() {

        String s = "banana";

        Optional<Character> str = s.chars()
                                   .mapToObj(c->(char) c)
                                   .collect(Collectors.groupingBy(
                                           c->c,
                                           Collectors.counting()
                                   )).entrySet()
                                     .stream()
                                     .filter(n->n.getValue()==1)
                                     .map(Map.Entry::getKey)
                                     .findFirst();

        logger.log(str.toString());

    }

    public static void printHello() {
        System.out.println("Hello");
        printHello();  // Recursive call with no base case
    }


}
