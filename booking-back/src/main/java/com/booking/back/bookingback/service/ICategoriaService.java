package com.booking.back.bookingback.service;
import com.booking.back.bookingback.dtos.CategoriaDTO;
import com.booking.back.bookingback.entities.Categoria;
import com.booking.back.bookingback.exceptions.ResourceNotFoundException;
import com.booking.back.bookingback.exceptions.SQLIntegrityConstraintViolationException;

import java.util.List;

public interface ICategoriaService {

    CategoriaDTO agregarCategoria(CategoriaDTO categoriaDTO) throws SQLIntegrityConstraintViolationException;
    List<CategoriaDTO> listarCategorias();
    Categoria buscarCategoria(Long id) throws ResourceNotFoundException;
    void ActualizarCategoria (CategoriaDTO categoriaDTO) throws ResourceNotFoundException;
    void borrarCategoria(Long id) throws ResourceNotFoundException;
}
