package com.booking.back.bookingback.exceptions;

/*Creamos la clase para nuestra excepcion de recurso no encontrado, que luego
utilizaremos en nuestro GlobalExceptions para el manejo de errores.*/

public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String msj) {
        super(msj);
    }
}
