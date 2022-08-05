package com.booking.back.bookingback.entities;

import javax.persistence.*;

@Entity
@Table(name = "caracteristicas")
public class Caracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String descripcion;
    @Column
    private String icono;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id", referencedColumnName = "id", nullable = false, foreignKey=@ForeignKey(name="producto_id_FK"))
    private Producto producto;

    public Caracteristica() {
    }

    public Caracteristica(String descripcion, Producto producto) {
        this.descripcion = descripcion;
        this.producto = producto;
    }

    public Caracteristica(Long id, String descripcion, Producto producto) {
        this.id = id;
        this.descripcion = descripcion;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
