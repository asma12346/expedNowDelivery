package com.example.demo.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.LivreurDejaAssignerVehiculeException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.VehiculeAlreadyExistException;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(VehiculeAlreadyExistException.class)
    public  ResponseEntity<List<String>> handleVehiculeAlreadyExistException(VehiculeAlreadyExistException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getFields());
    }


    @ExceptionHandler(LivreurDejaAssignerVehiculeException.class)
    public ResponseEntity<String> handleLivreurDejaAssigmer(LivreurDejaAssignerVehiculeException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

}
