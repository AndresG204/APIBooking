package com.booking.back.bookingback.controller;

import com.booking.back.bookingback.dtos.ProductoCompletoDTO;
import com.booking.back.bookingback.dtos.ProductoReservaDTO;
import com.booking.back.bookingback.dtos.ReservaFiltroCiFeDTO;
import com.booking.back.bookingback.exceptions.ResourceNotFoundException;
import com.booking.back.bookingback.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    IProductoService productoService;

    @PostMapping("/crear")
    public ResponseEntity<Object> agregarProducto(@RequestBody ProductoCompletoDTO productoCompletoDTO) {
        productoService.agregarProducto(productoCompletoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            Collections.singletonMap("message", "Producto agregado correctamente")
        );
    }
    @PutMapping("/actualizar")
    public ResponseEntity<Object> actualizarProducto(@RequestBody ProductoCompletoDTO productoCompletoDTO)throws ResourceNotFoundException{
        productoService.actualizarProducto(productoCompletoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                Collections.singletonMap("message"," Producto actualizado correctamente")
        );
    }

    @GetMapping("/listar")
    public ResponseEntity<Collection<ProductoCompletoDTO>>  listarProductos(@RequestParam(required = false) Long ciudad_id, @RequestParam(required = false) Long categoria_id) {
        return ResponseEntity.ok(productoService.listarProductos(ciudad_id, categoria_id));
    }
    
    @PostMapping("/ciudad/fechas")
    public ResponseEntity<Collection<ProductoCompletoDTO>> listarPorFechasYOCiudad(@RequestBody ReservaFiltroCiFeDTO reservaFiltroCiFeDTO)throws ResourceNotFoundException{
        return ResponseEntity.ok(productoService.listarSegunCiudadYOIntervaloDeFechas(reservaFiltroCiFeDTO));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ProductoCompletoDTO> buscarProducto(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(productoService.buscarProducto(id));
    }

    @GetMapping("/reserva/{id}")
    public ResponseEntity<ProductoReservaDTO> buscarProductoReserva(@PathVariable Long id)throws ResourceNotFoundException{
        return ResponseEntity.ok(productoService.buscarProductoReserva(id));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Object> borrarProducto(@PathVariable Long id) throws ResourceNotFoundException {
        productoService.borrarProducto(id);
        return ResponseEntity.status(HttpStatus.OK).body(
            Collections.singletonMap("message", "Se elimino el producto correctamente")
        );
    }

}

