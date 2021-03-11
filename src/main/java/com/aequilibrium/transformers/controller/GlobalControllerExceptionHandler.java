package com.aequilibrium.transformers.controller;

import com.aequilibrium.transformers.model.dto.ErrorModelDTO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aequilibrium.transformers.exception.InvalidTransformerTypeException;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(InvalidTransformerTypeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorModelDTO> handleTeamNotFound(RuntimeException ex) {
	return new ResponseEntity<>(new ErrorModelDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage()),
		HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorModelDTO> handleEmptyResultDataAccess(RuntimeException ex) {
	return new ResponseEntity<>(new ErrorModelDTO(HttpStatus.NOT_FOUND.value(), "Transformers not found"),
		HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorModelDTO> handleValidation(MethodArgumentNotValidException ex) {
	return new ResponseEntity<>(new ErrorModelDTO(HttpStatus.BAD_REQUEST.value(),
		ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ErrorModelDTO> handleValidation(HttpRequestMethodNotSupportedException ex) {
	return new ResponseEntity<>(new ErrorModelDTO(HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getMessage()),
		HttpStatus.METHOD_NOT_ALLOWED);
    }

}