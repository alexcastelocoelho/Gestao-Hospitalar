package com.example.api.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFound.class)
    public ApiErrorResponse resourceNotFound(ResourceNotFound exception, HttpServletRequest request, HandlerMethod method){
        return new ApiErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage(),
                request.getRequestURI(),
                method.getMethod().getName(),
                ZonedDateTime.now());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiErrorResponse methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception, HttpServletRequest request, HandlerMethod method){
        if (exception.getMessage().contains("UUID string too large")){
            return new ApiErrorResponse(HttpStatus.BAD_REQUEST, "Id invalido ou logo demais, verique, por favor",
                    request.getRequestURI(),
                    method.getMethod().getName(),
                    ZonedDateTime.now());
        }

        String message = "Argumento invalido para " + exception.getName().toUpperCase();
        return new ApiErrorResponse(HttpStatus.BAD_REQUEST, message,
                request.getRequestURI(),
                method.getMethod().getName(),
                ZonedDateTime.now());
    }



}
