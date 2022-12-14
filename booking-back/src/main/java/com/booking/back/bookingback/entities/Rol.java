package com.booking.back.bookingback.entities;

import javax.persistence.*;

@Entity
@Table(name = "Roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;

    public Rol() {

    }

    public Rol(String nombre) {
        this.nombre = nombre;
    }

    public Rol(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
