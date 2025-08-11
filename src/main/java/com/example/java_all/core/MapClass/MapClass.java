package com.example.java_all.core.MapClass;

import com.example.java_all.designpattern.SingletonLoggerClass;

import java.util.HashMap;
import java.util.Map;

public class MapClass {

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    public void HashMapBucketIndexInteger() {

        Map<Integer,String> hashmap = new HashMap<>();
        hashmap.put(1,"one");
        hashmap.put(2,"two");
        hashmap.put(3,null);
        hashmap.put(null,"three");
        hashmap.put(4,"four");
        hashmap.put(17,"seventeen");
        hashmap.put(33,"thirtythree");

        int capacity = 16;

        for(Map.Entry<Integer,String> entry:hashmap.entrySet()) {
            Integer key =entry.getKey();
            if(key !=null) {
                int hashcode = key.hashCode();
                int bucketindex = (capacity - 1) & hashcode;
                System.out.println("Key :" + key + ",Value :" + entry.getValue() + ",Hashcode :" + hashcode + ",BucketIndex :" + bucketindex);

            } else {
                System.out.println("Key: null, HashCode: null, Bucket Index: 0 (special bucket)");
            }
        }
        System.out.println("-------------------------------------------------------------------------------------------------------");

    }

    public void HashMapBucketIndexStr() {

        Map<String,Integer> hashmap = new HashMap<>();
        hashmap.put("one",1);
        hashmap.put("two",2);
        hashmap.put(null,3);
        hashmap.put("three",null);
        hashmap.put("four",4);
        hashmap.put("seventeen",17);
        hashmap.put("thirtythree",33);

        int capacity = 16;

        for(Map.Entry<String,Integer> entry:hashmap.entrySet()) {
            String key = entry.getKey();
            if(key !=null) {
                int hashcode = key.hashCode();
                int bucketindex = (capacity-1) & hashcode;
                System.out.println("Key :" + key + ",Value :" + entry.getValue() + ",Hashcode :" + hashcode + ",BucketIndex :" + bucketindex);
            } else {
                System.out.println("Key: null, HashCode: null, Bucket Index: 0 (special bucket)");
            }


        }
        System.out.println("AB".hashCode());
    }
}
