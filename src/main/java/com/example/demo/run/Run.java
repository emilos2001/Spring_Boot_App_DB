package com.example.demo.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;


public record Run(
    @Positive
    Integer id,
    @NotEmpty
    String title,
    LocalDate startedOn,
    LocalDate completedOn,
    @Positive
    Integer distance,
    Location  location
){
    public Run {
        if(id < 0){
            throw new IllegalArgumentException("Run id must be a positive integer");
        }
        if (!completedOn.isAfter(startedOn)){
            throw new IllegalArgumentException("Completed on must be after started on");
        }
        if (title.isEmpty()){
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (distance < 0){
            throw new IllegalArgumentException("Distance cannot be negative");
        }
    }
}
