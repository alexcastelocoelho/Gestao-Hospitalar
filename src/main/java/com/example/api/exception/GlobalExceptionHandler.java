package com.example.api.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request, HandlerMethod method){
        return new ApiErrorResponse(HttpStatus.BAD_REQUEST,exception.getBindingResult().getFieldError().getDefaultMessage(),
                request.getRequestURI(),
                method.getMethod().getName(),
                ZonedDateTime.now());
    }

}
