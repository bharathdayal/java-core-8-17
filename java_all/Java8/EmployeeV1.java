package com.example.java_all.Java8;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeV1 {

    @Getter
    private String empname;

    private Double salary;

    public EmployeeV1(String empname,Double salary) {
        this.empname=empname;
        this.salary =salary;
    }

    @Override
    public String toString() {
        return empname + '-' + salary;
    }


}
