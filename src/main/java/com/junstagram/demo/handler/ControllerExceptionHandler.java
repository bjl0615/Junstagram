package com.junstagram.demo.handler;

import com.junstagram.demo.handler.exception.CustomApiException;
import com.junstagram.demo.handler.exception.CustomValidationApiException;
import com.junstagram.demo.handler.exception.CustomValidationException;
import com.junstagram.demo.util.Script;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<String> validationException(CustomValidationException e) {
        if (e.getErrorMap() == null) {
            return new ResponseEntity<>(Script.back(e.getMessage()), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(Script.back(e.getErrorMap().toString()), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomApiException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
