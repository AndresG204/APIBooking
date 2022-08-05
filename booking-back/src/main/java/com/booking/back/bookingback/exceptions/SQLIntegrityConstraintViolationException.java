package com.booking.back.bookingback.exceptions;

public class SQLIntegrityConstraintViolationException extends Exception{
    public SQLIntegrityConstraintViolationException(String msj) {
        super(msj);
    }    
}
