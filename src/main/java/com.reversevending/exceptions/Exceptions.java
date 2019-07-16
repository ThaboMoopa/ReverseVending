package com.reversevending.exceptions;

public class Exceptions extends RuntimeException {

    private String message;

    public Exceptions(String message)
    {
        super(message);
    }

//    @Override
//    public String getMessage() {
//        return "The object cannot be null";
//    }
}
