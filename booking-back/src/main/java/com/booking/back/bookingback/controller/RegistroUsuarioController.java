package com.booking.back.bookingback.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.back.bookingback.dtos.UsuarioDTO;
import com.booking.back.bookingback.exceptions.ResourceNotFoundException;
import com.booking.back.bookingback.exceptions.SQLIntegrityConstraintViolationException;
import com.booking.back.bookingback.service.IUsuarioService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/registroUsuario")
public class RegistroUsuarioController {

    @Autowired
    IUsuarioService usuarioService;

    @PostMapping("/crear")
    public ResponseEntity<Object> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) throws SQLIntegrityConstraintViolationException {
        usuarioService.agregarUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
            Collections.singletonMap("message", "Usuario creado correctamente")
        );
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuario(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(usuarioService.buscarUsuario(id));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Object> borrarUsuario(@PathVariable Long id) throws ResourceNotFoundException {
        usuarioService.borrarUsuario(id);
        return ResponseEntity.status(HttpStatus.OK).body(
            Collections.singletonMap("message", "Se elimino el usuario correctamente")
        );
    }
}
