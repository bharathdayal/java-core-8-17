package com.example.java_all.java21;

import com.example.java_all.designpattern.SingletonLoggerClass;

public class StringBuilderRepeat {

    SingletonLoggerClass logger = SingletonLoggerClass.getInstance();

    public void stringRepeat() {

        StringBuilder sb = new StringBuilder();
        String str = "Repeated string";
        sb.append(str);
        sb.repeat(30,10);

        logger.log(String.valueOf(sb));
    }
}
