package com.urlshortner.UrlShortner.ExceptionHandler;

import com.urlshortner.UrlShortner.ErrorResponse.ErrorResponse;
import com.urlshortner.UrlShortner.Exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ErrorResponse> exception(CustomException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),ex.getHttpStatus(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponse,ex.getHttpStatus());
    }

}
