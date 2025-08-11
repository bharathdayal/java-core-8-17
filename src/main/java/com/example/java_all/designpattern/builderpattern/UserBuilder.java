package com.example.java_all.designpattern.builderpattern;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;

@Builder
public class UserBuilder {

    @JsonSerialize
    @JsonProperty("FULL NAME")
    private  String user;
    @JsonSerialize
    private  int age;
    @JsonSerialize
    private  String email;
    @JsonSerialize
    private  String phone;


}
