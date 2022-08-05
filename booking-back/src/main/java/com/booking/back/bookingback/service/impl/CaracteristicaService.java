package com.booking.back.bookingback.service.impl;

import com.booking.back.bookingback.entities.Caracteristica;
import com.booking.back.bookingback.exceptions.ResourceNotFoundException;
import com.booking.back.bookingback.repository.CaracteristicaRepository;
import com.booking.back.bookingback.service.ICaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaracteristicaService implements ICaracteristicaService {

    @Autowired
    CaracteristicaRepository caracteristicaRepository;

    @Override
    public Caracteristica agregarCaracteristica(Caracteristica caracteristica) {
        return caracteristicaRepository.save(caracteristica);
    }

    @Override
    public List<Caracteristica> listarCaracteristicas() {
        return caracteristicaRepository.findAll();
    }

    @Override
    public Caracteristica buscarCaracteristica(Long id) throws ResourceNotFoundException {
        Optional<Caracteristica> caracteristicaABuscar=caracteristicaRepository.findById(id);
        if(caracteristicaABuscar.isPresent()){
            return caracteristicaABuscar.get();
        }else {
            throw new ResourceNotFoundException("Carasteristica no encontrada");
        }
    }

    @Override
    public void borrarCaracteristica(Long id)throws ResourceNotFoundException {
        Optional<Caracteristica> caracteristicaAEliminar=caracteristicaRepository.findById(id);
        if (caracteristicaAEliminar.isPresent()){
            caracteristicaRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("no Existe ninguna caractaristica con el id"+id);
        }
    }

}
