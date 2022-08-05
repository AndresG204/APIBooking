package com.booking.back.bookingback.controller;

import com.booking.back.bookingback.dtos.ReservaARetornarDTO;
import com.booking.back.bookingback.dtos.ReservaDTO;
import com.booking.back.bookingback.dtos.ReservaUsuarioProductosDTO;
import com.booking.back.bookingback.exceptions.CreationErrorException;
import com.booking.back.bookingback.exceptions.ResourceNotFoundException;
import com.booking.back.bookingback.service.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    IReservaService reservaService;

    @PostMapping("/crear")
    public ResponseEntity<Object> agregarReserva(@RequestBody ReservaDTO reservaDTO)throws CreationErrorException {
        reservaService.agregarReserva(reservaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            Collections.singletonMap("message", "Reserva agendada correctamente"));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ReservaARetornarDTO>>listarReservas(){
        return ResponseEntity.ok(reservaService.listarReservas());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ReservaARetornarDTO> buscarReserva(@PathVariable Long id)throws ResourceNotFoundException{
        return ResponseEntity.ok(reservaService.buscarReserva(id));
    }
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<ReservaUsuarioProductosDTO>> funtionMisReservas(@PathVariable Long id)throws ResourceNotFoundException{
        return ResponseEntity.ok(reservaService.misReservas(id));
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Object> eliminarReserva(@PathVariable Long id)throws ResourceNotFoundException{
        reservaService.borraReserva(id);
        return ResponseEntity.status(HttpStatus.OK).body(
            Collections.singletonMap("message","La reserva se elimino correctamente"));
    }
}
