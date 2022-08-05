package com.booking.back.bookingback.service;

import com.booking.back.bookingback.dtos.UsuarioDTO;
import com.booking.back.bookingback.dtos.UsuarioDTO_login;
import com.booking.back.bookingback.exceptions.ResourceNotFoundException;
import com.booking.back.bookingback.exceptions.SQLIntegrityConstraintViolationException;

public interface IUsuarioService {
    
    void agregarUsuario(UsuarioDTO usuarioDTO) throws SQLIntegrityConstraintViolationException;
    UsuarioDTO buscarUsuario(Long id) throws ResourceNotFoundException;
    void borrarUsuario(Long id) throws ResourceNotFoundException;
    UsuarioDTO_login buscarPorEmail(String email) throws ResourceNotFoundException;
}

