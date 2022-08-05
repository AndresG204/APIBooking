package com.booking.back.bookingback.controller;

import com.booking.back.bookingback.dtos.CiudadDTO;
import com.booking.back.bookingback.exceptions.ResourceNotFoundException;
import com.booking.back.bookingback.service.ICiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/ciudades")
public class CiudadController {
    
    @Autowired
    ICiudadService ciudadService;

    @PostMapping("/crear")
    public ResponseEntity<Object> agregarCiudad(@RequestBody CiudadDTO ciudadDTO){
        ciudadService.agregarCiudad(ciudadDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
            Collections.singletonMap("message", "Ciudad agregada correctamente"));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CiudadDTO>> ListarCiudades(){
        return ResponseEntity.ok(ciudadService.listarCiudades());
    }

    @GetMapping("buscar/{id}")
    public ResponseEntity<CiudadDTO> buscarCiudad(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(ciudadService.buscarCiudad(id));
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Object> elimunarCiudad(@PathVariable Long id) throws ResourceNotFoundException{
        ciudadService.borrarCiudad(id);
        return ResponseEntity.status(HttpStatus.OK).body(
            Collections.singletonMap("message", "La ciudad se elimino correctamente"));
    }
}
