package com.example.java_all;

import com.example.java_all.codinginterview.DuplicateArrayList;
import com.example.java_all.interfaces.IArrayList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class JavaAllApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaAllApplication.class, args);

		DuplicateArrayList duplicateArrayList=new DuplicateArrayList();
		duplicateArrayList.duplicateArray();
		duplicateArrayList.countFrequentElement();
		duplicateArrayList.reverseArray();

	}

}
