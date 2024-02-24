package com.farmSimulation.core.utilities.exception;

import com.farmSimulation.business.services.LogEntryService;
import com.farmSimulation.entities.concretes.LogEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final LogEntryService logEntryService;

    @Autowired
    public GlobalExceptionHandler(LogEntryService logEntryService) {
        this.logEntryService = logEntryService;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        String message = ex.getMessage();
        logger.error("Resource not found: {}", message);
        logEntryService.saveLogEntry(new LogEntry(message, LogLevel.ERROR));
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), "Not Found", message);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        String message = ex.getMessage();
        logger.error("An unexpected error occurred: {}", message);
        logEntryService.saveLogEntry(new LogEntry(message, LogLevel.ERROR));
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", message);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
