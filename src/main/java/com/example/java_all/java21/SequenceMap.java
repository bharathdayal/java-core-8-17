package com.example.java_all.java21;

import ch.qos.logback.core.util.InvocationGate;
import com.example.java_all.designpattern.SingletonLoggerClass;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SequencedCollection;
import java.util.SequencedMap;

public class SequenceMap {

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    public void sequenceMap() {
        SequencedMap<String, Integer> sequencedMap =   new LinkedHashMap<>();
        sequencedMap.put("Map4",400);
        sequencedMap.put("Map3",300);
        sequencedMap.put("Map2",200);

        sequencedMap.putFirst("Map1",100);
        sequencedMap.putLast("Map5",500);
        sequencedMap.reversed().forEach((k,v)->System.out.println(k + ":" +v));

        logger.log("sequence map fetch first "+sequencedMap.firstEntry());
        logger.log("sequence map  fetch last "+sequencedMap.lastEntry());
        logger.log("Sequence Map "+sequencedMap);


    }
}
