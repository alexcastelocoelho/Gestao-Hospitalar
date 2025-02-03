package com.example.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class EmptyList extends RuntimeException{

    public EmptyList() {
    }

    public EmptyList(String message) {
        super(message);
    }
}
