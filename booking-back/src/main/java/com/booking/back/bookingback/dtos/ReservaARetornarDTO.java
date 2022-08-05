package com.booking.back.bookingback.dtos;

import com.booking.back.bookingback.entities.Producto;

import java.time.LocalDate;

public class ReservaARetornarDTO {
    private  Long id;
    private String horaLlegada;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private Producto producto;
    private UsuarioDTO_login usuarioDTO_login;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public UsuarioDTO_login getUsuarioDTO_login() {
        return usuarioDTO_login;
    }

    public void setUsuarioDTO_login(UsuarioDTO_login usuarioDTO_login) {
        this.usuarioDTO_login = usuarioDTO_login;
    }
}
