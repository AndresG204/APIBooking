package com.booking.back.bookingback.repository;

import com.booking.back.bookingback.entities.Producto;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ProductoRepository extends JpaRepository<Producto,Long> {
    @Modifying
    @Query(value= "delete from productos where id = ?", nativeQuery = true)
    void EliminarPorProdID(Long id);
}
