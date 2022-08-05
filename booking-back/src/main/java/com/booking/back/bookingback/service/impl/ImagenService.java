package com.booking.back.bookingback.service.impl;

import com.booking.back.bookingback.entities.Imagen;
import com.booking.back.bookingback.exceptions.ResourceNotFoundException;
import com.booking.back.bookingback.repository.ImagenRepository;
import com.booking.back.bookingback.service.IImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagenService implements IImagenService {

    @Autowired
    ImagenRepository imagenRepository;

    @Override
    public Imagen agregarImagen(Imagen imagen) {
        return imagenRepository.save(imagen);
    }

    @Override
    public List<Imagen> listarImagenes() {
        return imagenRepository.findAll();
    }

    @Override
    public Imagen buscarImagen(Long id) throws ResourceNotFoundException {
        Optional<Imagen> imagenABuscar=imagenRepository.findById(id);
        if (imagenABuscar.isPresent()){
            return imagenABuscar.get();
        }else {
            throw new ResourceNotFoundException("la imagen que quiere buscar no existe");
        }
    }

    @Override
    public void borrarImagen(Long id) throws ResourceNotFoundException{
        Optional<Imagen> imagenAEliminar=imagenRepository.findById(id);
        if (imagenAEliminar.isPresent()){
            imagenRepository.delete(imagenAEliminar.get());
        }else {
            throw new ResourceNotFoundException("la imagen que quere eliminar no existe ");
        }
    }
}
