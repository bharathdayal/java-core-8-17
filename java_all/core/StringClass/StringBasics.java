package com.example.java_all.core.StringClass;

import com.example.java_all.designpattern.SingletonLoggerClass;

public class StringBasics {

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    public void  strEquals() {

        String s1 = "Hello1";
        String s2 = "Hello";


        System.out.println(s1==s2); // TRUE

        String s3 = new String("Hello1");
        String s4 = new String("Hello1");

        System.out.println(s1==s3); // FALSE

        System.out.println(s1.equals(s2));  // TRUE

        System.out.println(s1.equals(s3)); // TRUE

        System.out.println(s3.equals(s4)); // TRUE

    }

    public void equalsCheck() {

        String s1 = "Hello1";
        String s2 = "Hello";

        System.out.println(s1==s2);
    }
}
