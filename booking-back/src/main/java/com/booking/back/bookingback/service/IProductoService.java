package com.booking.back.bookingback.service;

import com.booking.back.bookingback.dtos.ProductoCompletoDTO;
import com.booking.back.bookingback.dtos.ProductoReservaDTO;
import com.booking.back.bookingback.dtos.ReservaFiltroCiFeDTO;
import com.booking.back.bookingback.exceptions.ResourceNotFoundException;

import java.util.Collection;

public interface IProductoService {

    ProductoCompletoDTO agregarProducto(ProductoCompletoDTO productoCompletoDTO);
    void actualizarProducto(ProductoCompletoDTO productoCompletoDTO) throws ResourceNotFoundException;
    Collection<ProductoCompletoDTO> listarProductos(Long ciudad_id, Long categoria_id);
    ProductoCompletoDTO buscarProducto(Long id) throws ResourceNotFoundException;
    ProductoReservaDTO buscarProductoReserva(Long id)throws ResourceNotFoundException;
    void borrarProducto(Long id) throws ResourceNotFoundException;
    Collection<ProductoCompletoDTO> listarSegunCiudadYOIntervaloDeFechas(ReservaFiltroCiFeDTO reservaFiltroCiFeDTO)throws ResourceNotFoundException;

}
