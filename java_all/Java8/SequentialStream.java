package com.example.java_all.Java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SequentialStream {

    List<String> list = List.of("apple","apple", "banana", "cherry", "banana", "date","ant","aircraft");

    public List<String> streamFilter() {


        return list.stream()
                .filter(s->s.startsWith("a"))
                .peek(System.out::println)
                .collect(Collectors.toList());

    }

    public List<String> streamMap() {


        List<String> result = list.stream()
                .map(String::toUpperCase)
                .peek(System.out::println)
                .toList();

        return result;
    }

    public List<List<String>> streamFlatmap() {
        List<List<String>> nested = List.of(List.of("a","b"),List.of("c","d"));

        return Collections.singletonList(nested.stream()
                .flatMap(List::stream)
                .peek(System.out::println)
                .collect(Collectors.toList()));

    }

    public List<String> streamDistinct() {
        List<String> result = list.stream()
                 .distinct()
                 .sorted()
                 .skip(1)
                 .limit(4)
                 .peek(System.out::println)
                 .toList();
        return result;
    }

    public List<Integer> streamReduce() {
        List<Integer> list =List.of(1,2,3,4,5);
        List<Integer> result = Collections.singletonList(list.stream()
                .map(Integer::reverse)
                .reduce(0, Integer::sum));

        return result;

    }

    public List<Integer> streamReverse() {
        List<Integer> list =List.of(1,2,3,4,5);
        List<Integer>result = list.stream().
                             sorted(Comparator.reverseOrder())
                .peek(System.out::println)
                .toList();
        return result;
    }

    public List<Employee> streamEmp() {
        List<Employee> employees = Arrays.asList(
                     new Employee("Emp3", 3000),
                     new Employee("Emp1", 1000),
                     new Employee("Emp2", 2000)
        );

        List<Employee> result = employees.stream()
                         .sorted(
                                 Comparator
                                         .comparingInt(Employee::salary)
                                         .thenComparing(Employee::empname)

                         )
                        .peek(System.out::println)
                        .toList();

        return result;
    }
}
