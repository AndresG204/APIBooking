package com.booking.back.bookingback.entities;

import javax.persistence.*;

@Entity
@Table(name = "imagenes")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String titulo;
    @Column
    private String url_imagen;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id", referencedColumnName = "id", nullable = false, foreignKey=@ForeignKey(name="producto_id_FK"))
    private Producto producto;

    public Imagen() {
    }

    public Imagen(String titulo, String url_imagen, Producto producto) {
        this.titulo = titulo;
        this.url_imagen = url_imagen;
        this.producto = producto;
    }

    public Imagen(Long id, String titulo, String url_imagen, Producto producto) {
        this.id = id;
        this.titulo = titulo;
        this.url_imagen = url_imagen;
        this.producto = producto;
    }

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

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
