package com.booking.back.bookingback.service;

import com.booking.back.bookingback.dtos.ReservaARetornarDTO;
import com.booking.back.bookingback.dtos.ReservaDTO;
import com.booking.back.bookingback.dtos.ReservaUsuarioProductosDTO;
import com.booking.back.bookingback.exceptions.CreationErrorException;
import com.booking.back.bookingback.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IReservaService {

    ReservaARetornarDTO agregarReserva(ReservaDTO reservaDTO)throws CreationErrorException;
    List<ReservaARetornarDTO> listarReservas();
    ReservaARetornarDTO buscarReserva(Long id) throws ResourceNotFoundException;
    void borraReserva(Long id) throws ResourceNotFoundException;
    List<ReservaUsuarioProductosDTO> misReservas(Long usuario_id)throws ResourceNotFoundException;
}
