package com.booking.back.bookingback.dtos;

import com.booking.back.bookingback.entities.*;

import java.util.Set;

public class ProductoCompletoDTO {
    
    private Long id;
    private String titulo;
    private String nombre;
    private String descripcion;
    private String direccion;
    private float latitud;
    private float longitud;
    private Categoria categoria;
    private Ciudad ciudad;

    private Set<ImagenDTO> imagenes;
    private Set<PoliticaDTO> politicas;
    private Set<CaracteristicaDTO> caracteristicas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Set<ImagenDTO> getImagenes() {
        return imagenes;
    }

    public void setImagenes(Set<ImagenDTO> imagenes) {
        this.imagenes = imagenes;
    }

    public Set<PoliticaDTO> getPoliticas() {
        return politicas;
    }

    public void setPoliticas(Set<PoliticaDTO> politicas) {
        this.politicas = politicas;
    }

    public Set<CaracteristicaDTO> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Set<CaracteristicaDTO> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }
}
