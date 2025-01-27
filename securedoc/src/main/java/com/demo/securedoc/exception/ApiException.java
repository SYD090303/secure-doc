package com.demo.securedoc.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }

    public ApiException() { super("an error occured"); }
}
