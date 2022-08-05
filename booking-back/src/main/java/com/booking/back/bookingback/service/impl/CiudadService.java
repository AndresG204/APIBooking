package com.booking.back.bookingback.service.impl;

import com.booking.back.bookingback.dtos.CiudadDTO;
import com.booking.back.bookingback.entities.Ciudad;
import com.booking.back.bookingback.exceptions.ResourceNotFoundException;
import com.booking.back.bookingback.repository.CiudadRepository;
import com.booking.back.bookingback.service.ICiudadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CiudadService implements ICiudadService {

    @Autowired
    CiudadRepository ciudadRepository;
    @Autowired
    ObjectMapper mapper;


    @Override
    public CiudadDTO agregarCiudad(CiudadDTO ciudadDTO) {
        Ciudad ciudad = mapper.convertValue(ciudadDTO,Ciudad.class);
         ciudadRepository.save(ciudad);
        return mapper.convertValue(ciudad,CiudadDTO.class);
    }

    @Override
    public List<CiudadDTO> listarCiudades() {
        List<Ciudad> ciudades=ciudadRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        List<CiudadDTO>ciudadesDTOS=new ArrayList<>();
        for (Ciudad ciudad:ciudades){
            CiudadDTO ciudadDTO= new CiudadDTO();
            ciudadDTO=convetirDTO(ciudad);
            ciudadesDTOS.add(ciudadDTO);
        }
        return ciudadesDTOS;
    }

    @Override
    public CiudadDTO buscarCiudad(Long id) throws ResourceNotFoundException {
        CiudadDTO ciudadDTOARetornar=new CiudadDTO();
        Optional<Ciudad> ciudadABuscar=ciudadRepository.findById(id);
        if (ciudadABuscar.isPresent()){
            ciudadDTOARetornar= convetirDTO(ciudadABuscar.get());
            return ciudadDTOARetornar;
        }else {
            throw new ResourceNotFoundException("no existe ninguna ciudad con el id : "+id);
        }
    }

    @Override
    public void borrarCiudad(Long id) throws ResourceNotFoundException{
         Optional<Ciudad> ciudadAEliminar=ciudadRepository.findById(id);
         if (ciudadAEliminar.isPresent()){
             ciudadRepository.deleteById(id);
         }else {
             throw new ResourceNotFoundException("El id : "+id+" no pertenece a ninguna ciudad");
         }
    }

    public CiudadDTO convetirDTO (Ciudad ciudad){
        CiudadDTO dto =new CiudadDTO();
        dto=mapper.convertValue(ciudad,CiudadDTO.class);
        return dto;
    }
    
}
