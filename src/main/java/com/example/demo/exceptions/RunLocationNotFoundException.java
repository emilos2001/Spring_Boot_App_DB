package com.example.demo.exceptions;

public class RunLocationNotFoundException extends RuntimeException {
    public RunLocationNotFoundException() {
        super("Run location not found");
    }
}
