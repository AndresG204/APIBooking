package com.booking.back.bookingback.service;

import com.booking.back.bookingback.entities.Caracteristica;
import com.booking.back.bookingback.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ICaracteristicaService {

    Caracteristica agregarCaracteristica(Caracteristica caracteristica);
    List<Caracteristica> listarCaracteristicas();
    Caracteristica buscarCaracteristica(Long id) throws ResourceNotFoundException;
    void borrarCaracteristica(Long id) throws ResourceNotFoundException;
}
