package com.wheel.wheelhouse.customException;

public class CarDeletionException extends RuntimeException{
    public CarDeletionException(String message) {
        super(message);
    }
}
