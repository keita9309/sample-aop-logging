package com.example.logging.service;

import org.springframework.stereotype.Service;

@Service
public class Greeting {

    public Greeting() {

    }

    public String getGreeting(String firstName,String lastName) {
        return privateBuildGreeting(firstName,lastName);
    }

    private String privateBuildGreeting(String firstName, String lastName) {
        return "Hello " + firstName + ":" + lastName;
    }
}