package com.example.api.exception;

public class BadRequest extends RuntimeException{
    public BadRequest() {
    }

    public BadRequest(String message) {
        super(message);
    }
}
