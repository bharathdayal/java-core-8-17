package com.example.java_all.Java8;

import com.example.java_all.designpattern.SingletonLoggerClass;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SequentialStream {

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

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

    // Sort list of emp in desc

    public List<EmployeeV1> sortDescStream() {
        List<EmployeeV1> employeeV1List = Arrays.asList(
                new EmployeeV1("Emp 1", 10000.0),
                new EmployeeV1("Emp 2", 20000.0),
                new EmployeeV1("Emp 3", 30000.0)

        );


        List<EmployeeV1> sortedList = employeeV1List.stream()

                .sorted(Comparator.comparingDouble(EmployeeV1::getSalary).reversed())
                .peek(System.out::println)
                .toList();

        return sortedList;
    }

    public int streamEvenSum() {

        List<Integer> ranInt = List.of(1,2,3,4,5,6,7,8,9,10);

        int result =ranInt.stream()
                .filter(n->n%2==0)
                .mapToInt(f->f*2)
                .sum();

        return result;

    }

    public Optional<Integer> streamSecond() {
        List<Integer> streamsec = List.of(100,500,800,900);

        Optional<Integer> result = streamsec.stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();
        logger.log(result.toString());
        return result;
    }

    public List<String> streamCommon() {

        List<String> arr1 = List.of("one","two","three");
        List<String> arr2 = List.of("four","five","two","three");

        Set<String> set = new HashSet<>(arr2);
        List<String> result = arr1.stream()
                .sorted(Comparator.reverseOrder())
                .filter(set::contains)
                .toList();
        logger.log(result.toString());
        return result;

    }

    public void removeDuplicate() {

        List<String> actualStr = List.of("Apple","Apple","Samsung","Xiomi");

        List<String> result = actualStr.stream()
                .distinct()
                .toList();

        logger.log(result.toString());
    }

    public void sortReverseOrder() {
        List<Integer> actualStr = List.of(1,2,3,4,5,6,7,8,9,10);

        List<Integer> result = actualStr.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        logger.log(result.toString());
    }

    public void multiplesofInt(Integer num) {
        List<Integer> actualStr = List.of(1,2,3,4,5,6,7,8,9,10);

        List<Integer> result = actualStr.stream()
                .map(e->e*num)
                .toList();
        logger.log(result.toString());
    }

    public void filterMutilples(Integer num) {
        List<Integer> actualStr = List.of(1,2,3,4,5,6,7,8,9,10);

        List<Integer> result = actualStr.stream()
                .filter(n->n%num==0)
                .toList();
        logger.log(result.toString());
    }

    public void filterMax() {
        List<Integer> actualStr = List.of(1,2,3,4,5,6,7,8,9,10);

        Optional<Integer> result = actualStr.stream()
                .max(Comparator.naturalOrder());

        logger.log(result.toString());
    }

    public void filterMin() {
        List<Integer> actualStr = List.of(1,2,3,4,5,6,7,8,9,10);
        Optional<Integer> result = actualStr.stream()
                .min(Comparator.naturalOrder());

        logger.log(result.toString());
    }

    public void commontwoArray() {
        List<Integer> arr1 = List.of(1,2,3,4,5);
        List<Integer> arr2 = List.of(6,7,8,9,10,2,3);

        Set<Integer> set = new HashSet<>(arr1);

        List<Integer> result= arr2.stream()

                .filter(set::contains)
                .toList();
        logger.log(result.toString());
    }

    public void mergeTwoArray() {
        List<Integer> arr1 = List.of(1,2,3,4,5);
        List<Integer> arr2 = List.of(6,7,8,9,10);

        List<Integer> result = Stream.concat(arr1.stream(),arr2.stream())
                .toList();
        logger.log(result.toString());
    }
}
