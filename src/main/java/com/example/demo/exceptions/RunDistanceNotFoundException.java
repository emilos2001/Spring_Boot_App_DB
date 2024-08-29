package com.example.demo.exceptions;

public class RunDistanceNotFoundException extends RuntimeException{
    public RunDistanceNotFoundException() {
        super("Run Distance Not Found");
    }
}
