package com.booking.back.bookingback.service;

import com.booking.back.bookingback.entities.Politica;
import com.booking.back.bookingback.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPoliticaService {

    Politica agregarPolitica(Politica politica);
    List<Politica> listarPoliticas();
    Politica buscarPolitica(Long id) throws ResourceNotFoundException;
    void borrarPolitica(Long id) throws ResourceNotFoundException;
}
