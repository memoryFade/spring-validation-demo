package com.glancebar.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YISHEN CAI
 */
@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }


    @ExceptionHandler(value = ParamException.class)
    public ResponseEntity<?> handleParamException(ParamException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(value = BindException.class)
    public ResponseEntity<?> handleParamNotValidException(BindException e) {
        List<String> messages = new ArrayList<>();
        e.getAllErrors().forEach(objectError ->
                messages.add("Field: " + objectError.getObjectName() + " " + objectError.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.join(",", messages));
    }
}