package com.example.demo.exceptions;

import com.example.demo.run.RunRepository;


public class RunTitleNotFoundException extends RuntimeException {
    public RunTitleNotFoundException() {
        super("Run title not found, you idiot");
    }
}
