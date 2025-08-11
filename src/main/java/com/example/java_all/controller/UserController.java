package com.example.java_all.controller;

import com.example.java_all.core.serializable.UserSerialize;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @PostMapping
    public String createUser(@RequestBody UserSerialize user) {
        return "Received user: " + user.getUser() + ", age: " + user.getAge();
    }

    @GetMapping
    public UserSerialize getUser() {
        return new UserSerialize("Bharath", 40);
    }

    @GetMapping("/date")
    public String getDate(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return "Parsed: " + date;
    }
}
