package com.booking.back.bookingback.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//Manejo de excepciones global.
@ControllerAdvice
public class GlobalExceptions extends ResponseEntityExceptionHandler {

    //Logger para documentar los errores/excepciones
 private static final Logger logger = Logger.getLogger(GlobalExceptions.class);

 //Manejando la excepcion de recurso no encontrado.
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> handlerNotFound(ResourceNotFoundException ex){
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler({CreationErrorException.class})
    public ResponseEntity<String>  hanlerCreationErrror(CreationErrorException ex){
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<String>  handleConflictException(SQLIntegrityConstraintViolationException ex){
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

}
