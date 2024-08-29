package com.example.demo.exceptions;

import com.example.demo.run.RunRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RunIdNotFoundException extends RuntimeException {
    RunRepository runRepository;

    public RunIdNotFoundException() {
        super("Run not found with id like that, you idiot");
    }

}
