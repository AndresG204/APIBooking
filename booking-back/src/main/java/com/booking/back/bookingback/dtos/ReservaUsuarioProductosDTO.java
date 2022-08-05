package com.booking.back.bookingback.dtos;

import java.time.LocalDate;

public class ReservaUsuarioProductosDTO {
    private  Long id;
    private String horaLlegada;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private ProductoCompletoDTO productoCompletoDTO;
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

    public ProductoCompletoDTO getProductoCompletoDTO() {
        return productoCompletoDTO;
    }

    public void setProductoCompletoDTO(ProductoCompletoDTO productoCompletoDTO) {
        this.productoCompletoDTO = productoCompletoDTO;
    }

    public UsuarioDTO_login getUsuarioDTO_login() {
        return usuarioDTO_login;
    }

    public void setUsuarioDTO_login(UsuarioDTO_login usuarioDTO_login) {
        this.usuarioDTO_login = usuarioDTO_login;
    }
}
