package com.booking.back.bookingback.service.impl;

import com.booking.back.bookingback.dtos.CategoriaDTO;
import com.booking.back.bookingback.entities.Categoria;
import com.booking.back.bookingback.exceptions.ResourceNotFoundException;
import com.booking.back.bookingback.exceptions.SQLIntegrityConstraintViolationException;
import com.booking.back.bookingback.repository.CategoriaRepository;
import com.booking.back.bookingback.service.ICategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements ICategoriaService {

    //Utilizamos nuestro repository de categorias para realizar cada metodo en nuestra base de datos.
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    ObjectMapper objectMapper;

    public CategoriaDTO agregarCategoria(CategoriaDTO categoriaDTO) throws SQLIntegrityConstraintViolationException{
        Categoria categoria = objectMapper.convertValue(categoriaDTO, Categoria.class);
        Optional<Categoria> existeONo = categoriaRepository.getCategoriaByTitulo(categoria.getTitulo());
        if(existeONo.isPresent() == false) {
            categoriaRepository.save(categoria);
            return categoriaDTO;
        } else {
            throw new SQLIntegrityConstraintViolationException("Titulo no disponible. Categoria ya existente.");
        }
    }

    public List<CategoriaDTO> listarCategorias(){
        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaDTO> categoriaDTOList = new ArrayList<>();
        
        for (Categoria categoria : categorias) {
            CategoriaDTO catDTO = objectMapper.convertValue(categoria, CategoriaDTO.class);
            categoriaDTOList.add(catDTO);
        }
        return categoriaDTOList;
    }
    //metodo buscar para utilizarlo para buscar el id de la  categoria a actualizar

    @Override
    public Categoria buscarCategoria(Long id) throws ResourceNotFoundException{
        Optional<Categoria> categoriaABuscar = categoriaRepository.findById(id);
        if (categoriaABuscar.isPresent()){
            return categoriaABuscar.get();
        }else {
            throw new ResourceNotFoundException("La categoria no existe");
        }
    }

    public void ActualizarCategoria (CategoriaDTO categoriaDTO) throws ResourceNotFoundException{
        try {
            buscarCategoria(categoriaDTO.getId());
            Categoria cat = objectMapper.convertValue(categoriaDTO, Categoria.class);
            categoriaRepository.save(cat);
        } catch (ResourceNotFoundException e) {
            throw e;
        }
    }
    
    //Borramos una categoria si el id existe, sino arrojamos un mensaje de excepcion.
    public void borrarCategoria(Long id) throws ResourceNotFoundException {
        try {
            buscarCategoria(id);
            categoriaRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("El id: " + id + " ingresado no corresponde con ninguna categoria.");
        }
    }

}
