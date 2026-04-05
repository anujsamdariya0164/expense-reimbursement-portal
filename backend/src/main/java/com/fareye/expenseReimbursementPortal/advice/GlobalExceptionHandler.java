package com.fareye.expenseReimbursementPortal.advice;

import com.fareye.expenseReimbursementPortal.model.dto.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ExceptionResponse> handleAuthException(InternalAuthenticationServiceException exception, HttpServletRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleIncorrectPassword(
            BadCredentialsException exception, HttpServletRequest request
    ) {
        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN.value())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGeneralException(Exception exception, HttpServletRequest request) {
        if (exception.getMessage().contains("InternalAuthenticationServiceException")) {
            return handleAuthException(new InternalAuthenticationServiceException("User with the given email does not exists!"), request);
        } else if (exception.getMessage().contains("BadCredentialsException")) {
            return handleIncorrectPassword(new BadCredentialsException("Incorrect password!"), request);
        }

        ExceptionResponse response = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
