package com.jedco.jedcoengineeringspring.config;

import com.jedco.jedcoengineeringspring.exceptions.AuthenticationException;
import com.jedco.jedcoengineeringspring.exceptions.ResponseException;
import com.jedco.jedcoengineeringspring.rest.response.JwtAuthenticationResponse;
import com.jedco.jedcoengineeringspring.rest.response.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<JwtAuthenticationResponse> handleAuthenticationFailureException(AuthenticationException ex) {
        JwtAuthenticationResponse response = new JwtAuthenticationResponse("","", new HashSet<>(), false, ex.getMessage());
        return ResponseEntity.ok(response);
    }
    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<ResponseDto> handleResponseException(ResponseException ex){
        ResponseDto responseDTO= new ResponseDto(false, ex.getMessage());
        return ResponseEntity.ok(responseDTO);
    }
}
