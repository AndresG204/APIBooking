package com.booking.back.bookingback.dtos;

import java.util.Set;

public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private CiudadDTO ciudad;
    private Set<RolDTO> roles;

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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public CiudadDTO getCiudad() {
        return ciudad;
    }
    public void setCiudad(CiudadDTO ciudad) {
        this.ciudad = ciudad;
    }
    public Set<RolDTO> getRoles() {
        return roles;
    }
    public void setRoles(Set<RolDTO> roles) {
        this.roles = roles;
    }
}
