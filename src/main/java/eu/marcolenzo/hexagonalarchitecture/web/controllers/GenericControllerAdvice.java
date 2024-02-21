package eu.marcolenzo.hexagonalarchitecture.web.controllers;

import eu.marcolenzo.hexagonalarchitecture.core.exceptions.InsufficientFundsException;
import eu.marcolenzo.hexagonalarchitecture.core.exceptions.UnknownAccountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericControllerAdvice {


  @ExceptionHandler(InsufficientFundsException.class)
  public ResponseEntity<String> handleInsufficientFundsException(InsufficientFundsException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UnknownAccountException.class)
  public ResponseEntity<String> handleUnknownAccountException(UnknownAccountException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

}
