package com.booking.back.bookingback.service.impl;

import com.booking.back.bookingback.dtos.*;
import com.booking.back.bookingback.entities.*;
import com.booking.back.bookingback.exceptions.CreationErrorException;
import com.booking.back.bookingback.exceptions.ResourceNotFoundException;
import com.booking.back.bookingback.repository.ProductoRepository;
import com.booking.back.bookingback.repository.ReservaRepository;
import com.booking.back.bookingback.service.IReservaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservaService implements IReservaService {
    @Autowired
    ReservaRepository reservaRepository;
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    ObjectMapper mapper;

    @Override
    public ReservaARetornarDTO agregarReserva(ReservaDTO reservaDTO) throws CreationErrorException {
        Reserva reserva =mapper.convertValue(reservaDTO,Reserva.class);
        Optional<Producto> producto = productoRepository.findById(reserva.getProducto().getId());
        int numerox=0;
        if (producto.isPresent()){
            Producto productoAEvaluar=producto.get();
            for (Reserva reserva1:productoAEvaluar.getReservas()){
                if ((reserva.getFechaInicial().compareTo(reserva1.getFechaInicial())>=0 &&reserva.getFechaInicial().compareTo(reserva1.getFechaFinal())<=0)||(reserva.getFechaFinal().compareTo(reserva1.getFechaInicial())>=0 && reserva.getFechaFinal().compareTo(reserva1.getFechaFinal())<=0)){
                    numerox=3;
                }
            }
        }
        if (numerox==0){
            reservaRepository.save(reserva);
            return mapper.convertValue(reserva, ReservaARetornarDTO.class);
        }else {
            throw new CreationErrorException("Rango de fechas invalido");
        }

    }

    @Override
    public List<ReservaARetornarDTO> listarReservas() {
        List<Reserva> reservas=reservaRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        List<ReservaARetornarDTO>reservaDTOS=new ArrayList<>();
        for (Reserva reserva:reservas){
            ReservaARetornarDTO reservaDTO=new ReservaARetornarDTO();
            reservaDTO=convertirDTO(reserva);
            reservaDTO.setUsuarioDTO_login(mapper.convertValue(reserva.getUsuario(), UsuarioDTO_login.class));
            reservaDTOS.add(reservaDTO);
        }
        return reservaDTOS;
    }

    @Override
    public ReservaARetornarDTO buscarReserva(Long id) throws ResourceNotFoundException {
        ReservaARetornarDTO reservaDTORetornar=new ReservaARetornarDTO();
        Optional<Reserva> reservaABuscar=reservaRepository.findById(id);
        if (reservaABuscar.isPresent()){
            reservaDTORetornar=convertirDTO(reservaABuscar.get());
            reservaDTORetornar.setUsuarioDTO_login(mapper.convertValue(reservaABuscar.get().getUsuario(),UsuarioDTO_login.class));
            return reservaDTORetornar;
        }else {
            throw new ResourceNotFoundException("no existe nunguna reserva con el id : "+id);
        }
    }

    @Override
    public void borraReserva(Long id) throws ResourceNotFoundException{
        Optional<Reserva> reservaAEliminar=reservaRepository.findById(id);
        if (reservaAEliminar.isPresent()){
            reservaRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("El id : "+id+" no pertenece a ninugna reserva");
        }

    }
    public List<ReservaUsuarioProductosDTO> misReservas(Long usuario_id)throws ResourceNotFoundException{
        List<Reserva>reservas=reservaRepository.findAll();
        List<ReservaUsuarioProductosDTO> reservaUsuarioProductosDTOList=new ArrayList<>();
        for (Reserva reserva:reservas){
            if (reserva.getUsuario().getId()==usuario_id){
                ReservaUsuarioProductosDTO dto=new ReservaUsuarioProductosDTO();
                dto=mapper.convertValue(reserva,ReservaUsuarioProductosDTO.class);
                dto.setProductoCompletoDTO(convertirProductoCompleotDTO(reserva.getProducto()));
                dto.setUsuarioDTO_login(mapper.convertValue(reserva.getUsuario(),UsuarioDTO_login.class));
                reservaUsuarioProductosDTOList.add(dto);
            }
        }
        if (reservaUsuarioProductosDTOList.size()>0){
            return reservaUsuarioProductosDTOList;
        }else {
            throw new ResourceNotFoundException("El usuario con ID : "+usuario_id+" no tiene reservas");
        }
    }
    public ReservaARetornarDTO convertirDTO (Reserva reserva){
        ReservaARetornarDTO dto =new ReservaARetornarDTO();
        dto=  mapper.convertValue(reserva,ReservaARetornarDTO.class);
        return dto;
    }
    private ProductoCompletoDTO convertirProductoCompleotDTO(Producto producto) {

        ProductoCompletoDTO dto = new ProductoCompletoDTO();
        dto = mapper.convertValue(producto, ProductoCompletoDTO.class);

        // Imagen DTO
        Set<ImagenDTO> imagenesDTO = new HashSet<>();
        for (Imagen imagen : producto.getImagenes()) {
            ImagenDTO dtoImg = mapper.convertValue(imagen, ImagenDTO.class);
            imagenesDTO.add(dtoImg);
        }
        dto.setImagenes(imagenesDTO);

        // Caracteristicas DTO
        Set<CaracteristicaDTO> caraDTO = new HashSet<>();
        for (Caracteristica caract : producto.getCaracteristicas()) {
            CaracteristicaDTO dtoCaract = mapper.convertValue(caract, CaracteristicaDTO.class);
            caraDTO.add(dtoCaract);
        }
        dto.setCaracteristicas(caraDTO);

        // Politicas DTO
        Set<PoliticaDTO> politicasDTO = new HashSet<>();
        for (Politica politica : producto.getPoliticas()) {
            PoliticaDTO dtoPolitica = mapper.convertValue(politica, PoliticaDTO.class);
            politicasDTO.add(dtoPolitica);
        }
        dto.setPoliticas(politicasDTO);

        return dto;
    }
}
