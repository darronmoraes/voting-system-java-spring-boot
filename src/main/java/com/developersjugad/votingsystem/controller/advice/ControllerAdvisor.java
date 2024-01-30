package com.developersjugad.votingsystem.controller.advice;

import com.developersjugad.votingsystem.exception.*;
import com.developersjugad.votingsystem.model.Election;
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

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErrorResponse> handleAuthorizationException(AuthorizationException e) {
        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                e.getMessage(),
                "Unauthorized Request",
                LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ElectionNotFoundException.class)
    public ResponseEntity<ErrorResponse> ElectionNotFoundException(ElectionNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                e.getMessage(),
                "Election Not Found",
                LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}
