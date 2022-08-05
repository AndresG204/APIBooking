package com.booking.back.bookingback.service.impl;

import com.booking.back.bookingback.entities.Politica;
import com.booking.back.bookingback.exceptions.ResourceNotFoundException;
import com.booking.back.bookingback.repository.PoliticaRepository;
import com.booking.back.bookingback.service.IPoliticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliticaService implements IPoliticaService {

    @Autowired
    PoliticaRepository politicaRepository;

    @Override
    public Politica agregarPolitica(Politica politica) {
        return politicaRepository.save(politica);
    }

    @Override
    public List<Politica> listarPoliticas() {
        return politicaRepository.findAll();
    }

    @Override
    public Politica buscarPolitica(Long id) throws ResourceNotFoundException {
        Optional<Politica> politicaABuscar=politicaRepository.findById(id);
        if (politicaABuscar.isPresent()){
            return politicaABuscar.get();
        }else {
            throw new ResourceNotFoundException("La politica con el id : "+id+" no existe ");
        }
    }

    @Override
    public void borrarPolitica(Long id) throws ResourceNotFoundException {
        Optional<Politica> politicaAEliminar=politicaRepository.findById(id);
        if (politicaAEliminar.isPresent()){
            politicaRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("La politica a eliminar con el id : "+id+" no existe ");
        }
    }
}
