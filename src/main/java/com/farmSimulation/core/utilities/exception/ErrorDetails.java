package com.farmSimulation.core.utilities.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetails {
    private int status;
    private String error;
    private String message;

}
