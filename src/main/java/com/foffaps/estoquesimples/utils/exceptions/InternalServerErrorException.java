package com.foffaps.estoquesimples.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends Exception {
    public InternalServerErrorException(String message) {

        super(message);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public Integer returnStatusCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}
