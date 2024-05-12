package com.foffaps.estoquesimples.utils.exceptions;


import org.springframework.http.HttpStatus;

public class Exception extends java.lang.Exception {
    public Exception(String message) {
        super(message, null, false, false);
    }

    public Exception(String message, Throwable cause) {
        super(message, cause, false, false);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + getMessage();
    }

    public Integer returnStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }
}

