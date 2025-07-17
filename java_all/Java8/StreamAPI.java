package com.example.java_all.Java8;

import com.example.java_all.designpattern.SingletonLoggerClass;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAPI {

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();


    List<EmpStreamClass> emp = List.of(
            new EmpStreamClass(1, "Alice", "HR", 50000),
            new EmpStreamClass(2, "Bob", "IT", 70000),
            new EmpStreamClass(3, "Charlie", "Finance", 60000),
            new EmpStreamClass(4, "David", "IT", 80000),
            new EmpStreamClass(5, "Eva", "HR", 55000),
            new EmpStreamClass(6, "Frank", "Finance", 65000),
            new EmpStreamClass(7, "Grace", "IT", 75000),
            new EmpStreamClass(8, "Heidi", "HR", 52000));

    // logger.log(emp.toString());

    DepartmentStreamClass hrDept = new DepartmentStreamClass("HR", List.of(
            emp.get(0), // Alice
            emp.get(4), // Eva
            emp.get(7)  // Heidi
    ));

    DepartmentStreamClass itDept = new DepartmentStreamClass("IT", List.of(
            emp.get(1), // Bob
            emp.get(3), // David
            emp.get(6)  // Grace
    ));

    DepartmentStreamClass financeDept = new DepartmentStreamClass("Finance", List.of(
            emp.get(2), // Charlie
            emp.get(5)  // Frank
    ));

    //Given a sentence find the word that has highest length
    public void highestLength() {

        String s1 = "This is to find the highest length in sentence";

        String result = Arrays.stream(s1.split(" "))
                .max(Comparator.comparing(String::length)).orElse("Not found");

        logger.log(result);
    }

    // Remove duplicates from string and return in same order

    public void duplicateSameorder() {

        String s1 = "dabcadefg";

        Arrays.stream(s1.split(""))
                .distinct()
                .forEach(System.out::print);

    }

    public void SecondHighestStrLength() {
        String s1 = "This is to find the highest length in sentence";

        Optional<String> result = Arrays.stream(s1.split(" "))
                .sorted(Comparator.comparing(String::length).reversed())
                .skip(1)
                .findFirst();

        logger.log(result.toString());

    }

    public void SecondHighestIntegerLength() {

        int[] num = {100,200,300,400,500};

        OptionalInt result = IntStream.of(num)
                .sorted()
                .skip(1)
                .findFirst();

        logger.log(result.toString());
    }

    public void highestSalaryEmpDept() {


        List<DepartmentStreamClass> dept = List.of(hrDept,itDept,financeDept);


        Map<String,Optional<EmpStreamClass>> highestByDept = emp.stream()
                .collect(Collectors.groupingBy(EmpStreamClass::department,
                        Collectors.maxBy(Comparator.comparing(EmpStreamClass::salary))));


        logger.log(highestByDept.toString());

    }

    public void departCount() {

        Map<String,Long> depcount =  emp.stream()
                .collect(Collectors.groupingBy(EmpStreamClass::department,Collectors.counting()));
        logger.log(depcount.toString());
    }

    public void ListEmpByDept() {

        Map<String,List<String>> empcount = emp.stream()
                .collect(Collectors.groupingBy(EmpStreamClass::department,
                        Collectors.mapping(EmpStreamClass::name,Collectors.toList())
                        ));

        logger.log(empcount.toString());
    }


    //Write a program to filter out all the even numbers from a list using Java Stream API
    public void filterEven() {

        List<Integer> evennum = List.of(1,2,3,4,5,6,7,8,9,10);

        List<Integer> result = evennum.stream()
                .filter(n->n%2==0)
                .toList();

        logger.log(result.toString());

    }

    //Given a list of strings, write a program to count the number of strings containing a
    // specific character ‘a’ using Java Stream API.

    public void findCharStream() {

        List<String> cntString = List.of("apple", "banana", "cherry", "date", "fig", "grape");

        Long result = cntString.stream()
                .filter(n->n.contains("a"))
                .count();
        logger.log(result.toString());

    }

    // Write a program to find the longest string in a list of strings using Java Stream API.

    public void findLongString() {
        List<String> longString = List.of("apple", "banana", "cherry", "date", "fig", "grape");

        String result = longString.stream()
                .max(Comparator.comparing(String::length)).orElse("No String found");

        logger.log(result.toString());

    }

    //Write a program to find the frequency of each element in a list of integers using Java Stream API.
    //(1, 2, 2, 3, 1, 4, 2, 3, 4, 4, 4)

    public void findFrequencyInt() {

        List<Integer> frqnum = List.of(1, 2, 2, 3, 1, 4, 2, 3, 4, 4, 4);

        Map<Integer,Long> result = frqnum.stream()
                .collect(Collectors.groupingBy(n->n,Collectors.counting()));

        logger.log(result.toString());

    }






}
