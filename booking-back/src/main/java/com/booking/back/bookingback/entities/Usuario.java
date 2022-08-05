package com.booking.back.bookingback.entities;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "usuarios")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id", scope = Usuario.class)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String email;
    @Column
    private String password;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "ciudad_id", referencedColumnName = "id")
    private Ciudad ciudad;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="Usuario_roles",
            joinColumns = @JoinColumn(name ="id_usuario"),
            inverseJoinColumns = @JoinColumn(name="id_rol")
    )
    private Set<Rol> roles;
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY)
    private Set<Reserva> reservas =new HashSet<>();

    public Usuario() {

    }

    public Usuario(String nombre, String apellido, String email, String password, Ciudad ciudad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.ciudad = ciudad;
    }

    public Usuario(Long id, String nombre, String apellido, String email, String password, Ciudad ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.ciudad = ciudad;
    }

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
    public Ciudad getCiudad() {
        return ciudad;
    }
    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
    public Set<Rol> getRoles() {
        return roles;
    }
    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
    public Set<Reserva> getReservas() {
        return reservas;
    }
    public void setReserva(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

}
