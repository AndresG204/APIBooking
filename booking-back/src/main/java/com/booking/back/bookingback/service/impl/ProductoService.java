package com.booking.back.bookingback.service.impl;

import com.booking.back.bookingback.dtos.*;
import com.booking.back.bookingback.entities.*;
import com.booking.back.bookingback.exceptions.ResourceNotFoundException;
import com.booking.back.bookingback.repository.*;
import com.booking.back.bookingback.service.IProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class ProductoService implements IProductoService {

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    ImagenRepository imagenRepository;

    @Autowired
    PoliticaRepository politicaRepository;

    @Autowired
    CaracteristicaRepository caracteristicaRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public ProductoCompletoDTO agregarProducto(ProductoCompletoDTO productoCompletoDTO) {
        Producto producto = mapper.convertValue(productoCompletoDTO, Producto.class);
        productoRepository.save(producto);
        
        // Imagen Entity
        for (ImagenDTO imgDto : productoCompletoDTO.getImagenes()) {
            Imagen img = mapper.convertValue(imgDto, Imagen.class);
            img.setProducto(producto);
            imagenRepository.save(img);
        }

        // Politicas Entity
        for (PoliticaDTO poliDto : productoCompletoDTO.getPoliticas()) {
            Politica politica = mapper.convertValue(poliDto, Politica.class);
            politica.setProducto(producto);
            politicaRepository.save(politica);
        }

        // Caracteristicas Entity
        for (CaracteristicaDTO caractDto : productoCompletoDTO.getCaracteristicas()) {
            Caracteristica caracteristica = mapper.convertValue(caractDto, Caracteristica.class);
            caracteristica.setProducto(producto);
            caracteristicaRepository.save(caracteristica);
        }
        
        return mapper.convertValue(producto, ProductoCompletoDTO.class);
    }
    public void actualizarProducto(ProductoCompletoDTO productoCompletoDTO) throws ResourceNotFoundException{
        try {
            buscarProducto(productoCompletoDTO.getId());
            imagenRepository.EliminarPorProdID(productoCompletoDTO.getId());
            politicaRepository.EliminarPorProdID(productoCompletoDTO.getId());
            caracteristicaRepository.EliminarPorProdID(productoCompletoDTO.getId());
            agregarProducto(productoCompletoDTO);
        } catch (ResourceNotFoundException e){
            throw e;
        }
    }



    @Override
    public Collection<ProductoCompletoDTO> listarProductos(Long ciudad_id, Long categoria_id) {
        List<Producto> productos = productoRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<ProductoCompletoDTO> productoCompletoDTO = new ArrayList<>();

        for (Producto producto : productos) {
            if (
                    (ciudad_id != null && ciudad_id != producto.getCiudad().getId()) ||
                    (categoria_id != null && categoria_id != producto.getCategoria().getId())
            ) {
                continue;
            }

            ProductoCompletoDTO dto = new ProductoCompletoDTO();

            dto = convertirADTO(producto);

            productoCompletoDTO.add(dto);
        }

       return productoCompletoDTO;
    }


    
    @Override
    public Collection<ProductoCompletoDTO> listarSegunCiudadYOIntervaloDeFechas(ReservaFiltroCiFeDTO reservaFiltroCiFeDTO)throws ResourceNotFoundException{
        List<Producto> productosAAnalizar=new ArrayList<>();
        List<ProductoCompletoDTO> productosARetornar=new ArrayList<>();
        int numerox=0;
        if (reservaFiltroCiFeDTO.getCiudad_id()==null){
            productosAAnalizar=productoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        }else {
            List<Producto>productosSegunCiudad=productoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
            for (Producto producto:productosSegunCiudad){
                if (reservaFiltroCiFeDTO.getCiudad_id()!=null && reservaFiltroCiFeDTO.getCiudad_id()!=producto.getCiudad().getId()){
                    continue;
                }
                productosAAnalizar.add(producto);
            }
        }
        for (Producto producto:productosAAnalizar){
            numerox=0;
            for (Reserva reserva:producto.getReservas()){
                if ((reservaFiltroCiFeDTO.getFechaInicial().compareTo(reserva.getFechaInicial())>=0 && reservaFiltroCiFeDTO.getFechaInicial().compareTo(reserva.getFechaFinal())<=0)||(reservaFiltroCiFeDTO.getFechaFinal().compareTo(reserva.getFechaInicial())>=0 && reservaFiltroCiFeDTO.getFechaFinal().compareTo(reserva.getFechaFinal())<=0)){
                    numerox=3;
                }
            }
            if (numerox>0){
                continue;
            }
            ProductoCompletoDTO dto=new ProductoCompletoDTO();
            dto=convertirADTO(producto);
            productosARetornar.add(dto);

        }
        if (productosARetornar.size()>0){
            return productosARetornar;
        }else {
            throw new ResourceNotFoundException("No hay productos con las caracteristicas seleccionadas");
        }
    }

    @Override
    public ProductoCompletoDTO buscarProducto(Long id) throws ResourceNotFoundException {
            ProductoCompletoDTO prodCompDTO = new ProductoCompletoDTO();
            Optional<Producto> query = productoRepository.findById(id);
            if(query.isPresent()) {
                Producto producto = query.get();

                prodCompDTO = convertirADTO(producto);

            return prodCompDTO;
        } else {
            throw new ResourceNotFoundException("Producto no encontrado");
        }
    }
    public ProductoReservaDTO buscarProductoReserva(Long id)throws ResourceNotFoundException{
        ProductoReservaDTO productoReservaDTO = new ProductoReservaDTO();
        Optional<Producto> productoAbuscar=productoRepository.findById(id);
        if (productoAbuscar.isPresent()){
            Producto producto=productoAbuscar.get();
            productoReservaDTO= convertirAProductoReservaDTO(producto);
            return productoReservaDTO;
        }else {
            throw new ResourceNotFoundException("Producto no encontrado");
        }
    }

    @Override
    public void borrarProducto(Long id) throws ResourceNotFoundException {
        if(productoRepository.findById(id).isPresent()) {
            imagenRepository.EliminarPorProdID(id);
            politicaRepository.EliminarPorProdID(id);
            caracteristicaRepository.EliminarPorProdID(id);
            productoRepository.EliminarPorProdID(id);
        } else {
            throw new ResourceNotFoundException("Producto no encontrado");
        }
    }

    public Collection<ProductoCompletoDTO> listarPorCiudad(Long ciudad_id) {


        return null;
    }


    private ProductoCompletoDTO convertirADTO(Producto producto) {
            
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
    private ProductoReservaDTO convertirAProductoReservaDTO (Producto producto){
        ProductoReservaDTO dto=new ProductoReservaDTO();
        dto=mapper.convertValue(producto,ProductoReservaDTO.class);
            //imangen
            ImagenDTO imagenDTO=new ImagenDTO();
            for (Imagen imagen:producto.getImagenes()){
                imagenDTO=mapper.convertValue(imagen,ImagenDTO.class);
                break;
            }
            dto.setImagenDTO(imagenDTO);
            //politicas
            Set<PoliticaDTO>politicasDTOS = new HashSet<>();
            for (Politica politica:producto.getPoliticas()){
                PoliticaDTO dtoPolitica=mapper.convertValue(politica,PoliticaDTO.class);
                politicasDTOS.add(dtoPolitica);
            }
            dto.setPoliticas(politicasDTOS);
            //Reservas
            Set<ReservaEspecificaDTO> reservasDTOS =new HashSet<>();
            for (Reserva reserva:producto.getReservas()){
                ReservaEspecificaDTO reservaDTO=mapper.convertValue(reserva,ReservaEspecificaDTO.class);
                reservasDTOS.add(reservaDTO);
            }
            dto.setReservas(reservasDTOS);

            return dto;
    }


}
