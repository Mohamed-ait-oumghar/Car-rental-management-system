package com.wheel.wheelhouse.customException;

public class UserDeletionException extends RuntimeException{

    //Client deletion exception
    public UserDeletionException(String message) {
        super(message);
    }
}
