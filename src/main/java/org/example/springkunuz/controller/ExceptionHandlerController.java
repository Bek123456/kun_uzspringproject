package org.example.springkunuz.controller;

import io.jsonwebtoken.JwtException;
import org.example.springkunuz.exp.AppBadException;
import org.example.springkunuz.exp.ForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(AppBadException.class)
    private ResponseEntity<?>handler(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    private ResponseEntity<?>handle(ForbiddenException e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler(JwtException.class)
    private ResponseEntity<?> handle(JwtException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?>handle(RuntimeException e){
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
