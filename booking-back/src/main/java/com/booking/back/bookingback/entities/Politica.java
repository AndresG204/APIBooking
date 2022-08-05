package com.booking.back.bookingback.entities;

import javax.persistence.*;

@Entity
@Table(name = "politicas")
public class Politica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id", referencedColumnName = "id", nullable = false, foreignKey=@ForeignKey(name="producto_id_FK"))
    private Producto producto;

    public Politica() {
    }

    public Politica(String descripcion, Producto producto) {
        this.descripcion = descripcion;
        this.producto = producto;
    }

    public Politica(Long id, String descripcion, Producto producto) {
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


    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
