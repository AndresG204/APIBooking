package com.booking.back.bookingback.dtos;

import java.util.Set;

import com.booking.back.bookingback.entities.Rol;

public class UsuarioDTO_login {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private CiudadDTO ciudad;
    private Set<Rol> roles;

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
    public String getApellido() {
    return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public CiudadDTO getCiudad() {
        return ciudad;
    }
    public void setCiudad(CiudadDTO ciudad) {
        this.ciudad = ciudad;
    }

    public Set<Rol> getRoles() {
        return roles;
    }
    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    
}
