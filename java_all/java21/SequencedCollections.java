package com.example.java_all.java21;

import com.example.java_all.designpattern.SingletonLoggerClass;


import java.util.LinkedHashSet;
import java.util.LinkedList;


public class SequencedCollections {

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    public void sequenceSet() {


        LinkedHashSet<String> sequencedCollection =  new LinkedHashSet<>();

        sequencedCollection.add("Set1");
        sequencedCollection.add("Set2");
        sequencedCollection.add("Set3");


        sequencedCollection.addFirst("Set4");
        sequencedCollection.addLast("Set5");
        sequencedCollection.reversed().forEach(System.out::println);


        logger.log("Sequence Set fetch first " + sequencedCollection.getFirst());
        logger.log("Sequence Set fetch last " + sequencedCollection.getLast());
        logger.log("Sequence Set " + sequencedCollection);

    }
}
