package com.booking.back.bookingback.repository;

import com.booking.back.bookingback.entities.Categoria;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//interfaz encargada de almacenar los datos en nuestra base de datos.
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

    @Query("from Categoria c where c.titulo =:titulo")
    Optional<Categoria> getCategoriaByTitulo(@Param("titulo") String titulo);
}
