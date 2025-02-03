package com.example.api.exception;

public class CpfConflictException extends RuntimeException{

    public CpfConflictException() {
    }

    public CpfConflictException(String message) {
        super(message);
    }
}
