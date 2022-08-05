package com.booking.back.bookingback.service;

import com.booking.back.bookingback.dtos.CiudadDTO;
import com.booking.back.bookingback.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ICiudadService {

    CiudadDTO agregarCiudad(CiudadDTO ciudadDTO);
    List<CiudadDTO> listarCiudades();
    CiudadDTO buscarCiudad(Long id) throws ResourceNotFoundException;
    void borrarCiudad(Long id) throws ResourceNotFoundException;

}
