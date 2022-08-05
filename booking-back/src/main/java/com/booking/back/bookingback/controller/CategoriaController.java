package com.booking.back.bookingback.controller;
import com.booking.back.bookingback.dtos.CategoriaDTO;
import com.booking.back.bookingback.exceptions.ResourceNotFoundException;
import com.booking.back.bookingback.exceptions.SQLIntegrityConstraintViolationException;
import com.booking.back.bookingback.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    ICategoriaService categoriaService;

    //Metodo para listar todas las categorias.
    @GetMapping("/listar")
    public List<CategoriaDTO> listar(){
        return categoriaService.listarCategorias();
    }

    //Metodo para agregar una nueva categoria.
    @PostMapping("/crear")
    public ResponseEntity<Object> agregar(@RequestBody CategoriaDTO categoriaDTO) throws SQLIntegrityConstraintViolationException{
        categoriaService.agregarCategoria(categoriaDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
            Collections.singletonMap("message", "Categoria agregada correctamente")
        );
    }

    //Metodo para actualizar una categoria.
    @PutMapping("/actualizar")
    public ResponseEntity<Object> Actualizar(@RequestBody CategoriaDTO categoriaDTO) throws ResourceNotFoundException{
        categoriaService.ActualizarCategoria(categoriaDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
            Collections.singletonMap("message", "Categoria actualizada correctamente")
        );
    }

    //Metodo para borrar una categoria por id.
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Object> borrar (@PathVariable Long id) throws ResourceNotFoundException {
        categoriaService.borrarCategoria(id);
        return ResponseEntity.status(HttpStatus.OK).body(
            Collections.singletonMap("message",  "Se elimino la categoria correctamente.")
        );
    }

}
