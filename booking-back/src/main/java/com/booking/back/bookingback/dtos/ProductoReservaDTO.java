package com.booking.back.bookingback.dtos;

import com.booking.back.bookingback.entities.Categoria;
import com.booking.back.bookingback.entities.Ciudad;

import java.util.Set;

public class ProductoReservaDTO {
    private Long id;
    private String nombre;
    private Categoria categoria;
    private Ciudad ciudad;
    private ImagenDTO imagenDTO;
    private Set<PoliticaDTO> politicas;
    private Set<ReservaEspecificaDTO> reservas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public ImagenDTO getImagenDTO() {
        return imagenDTO;
    }

    public void setImagenDTO(ImagenDTO imagenDTO) {
        this.imagenDTO = imagenDTO;
    }

    public Set<PoliticaDTO> getPoliticas() {
        return politicas;
    }

    public void setPoliticas(Set<PoliticaDTO> politicas) {
        this.politicas = politicas;
    }

    public Set<ReservaEspecificaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(Set<ReservaEspecificaDTO> reservas) {
        this.reservas = reservas;
    }
}