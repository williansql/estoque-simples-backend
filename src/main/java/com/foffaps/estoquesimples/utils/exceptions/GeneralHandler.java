package com.foffaps.estoquesimples.utils.exceptions;

import com.foffaps.estoquesimples.utils.models.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException e) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.of(e.returnStatusCode(), e.getMessage());
        return ResponseEntity.status(e.returnStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.of(e.returnStatusCode(), e.getMessage());
        return ResponseEntity.status(e.returnStatusCode()).body(apiResponse);
    }
}