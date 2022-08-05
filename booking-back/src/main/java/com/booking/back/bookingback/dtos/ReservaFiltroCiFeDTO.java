package com.booking.back.bookingback.dtos;

import java.time.LocalDate;

public class ReservaFiltroCiFeDTO {
    public Long ciudad_id;
    public LocalDate fechaInicial;
    public LocalDate fechaFinal;

    public Long getCiudad_id() {
        return ciudad_id;
    }

    public void setCiudad_id(Long ciudad_id) {
        this.ciudad_id = ciudad_id;
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
}
