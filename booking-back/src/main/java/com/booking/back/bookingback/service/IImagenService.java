package com.booking.back.bookingback.service;

import com.booking.back.bookingback.entities.Imagen;
import com.booking.back.bookingback.exceptions.ResourceNotFoundException;

import java.util.Collection;

public interface IImagenService {

    Imagen agregarImagen(Imagen imagen);
    Collection<Imagen> listarImagenes();
    Imagen buscarImagen(Long id) throws ResourceNotFoundException;
    void borrarImagen(Long id) throws ResourceNotFoundException;

}
