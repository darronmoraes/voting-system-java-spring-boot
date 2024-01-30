package com.developersjugad.votingsystem.controller.advice;

import com.developersjugad.votingsystem.exception.UserNotApprovedException;
import com.developersjugad.votingsystem.exception.UserNotFoundException;
import com.developersjugad.votingsystem.exception.UserRegisterException;
import com.developersjugad.votingsystem.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(UserRegisterException.class)
    public ResponseEntity<ErrorResponse> handleUserRegisterException(UserRegisterException e) {
        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                e.getMessage(),
                "User Register Exception",
                LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotApprovedException.class)
    public ResponseEntity<ErrorResponse> handleUserNotApprovedException(UserNotApprovedException e) {
        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                e.getMessage(),
                "Registered User Approval Pending",
                LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                e.getMessage(),
                "User Not Found",
                LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}
