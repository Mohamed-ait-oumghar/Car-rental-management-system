package com.wheel.wheelhouse.advice;

import com.wheel.wheelhouse.customException.CarDeletionException;
import com.wheel.wheelhouse.customException.ClientDeletionException;
import com.wheel.wheelhouse.customException.UserDeletionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    // Handle validation errors (@Valid @RequestBody)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    // Handle other exceptions
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {

        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    //Handle custom exception for Client
    @ExceptionHandler(ClientDeletionException.class)
    public ResponseEntity<Map<String, String>> handleClientDeletion(ClientDeletionException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }
    //Handle custom exception for Car
    @ExceptionHandler(CarDeletionException.class)
    public ResponseEntity<Map<String, String>> handleCarDeletion(CarDeletionException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }
    //Handle custom exception for User
    @ExceptionHandler(UserDeletionException.class)
    public ResponseEntity<Map<String, String>> handleUserDeletion(UserDeletionException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }




}
