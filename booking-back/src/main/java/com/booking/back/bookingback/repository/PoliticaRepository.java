package com.booking.back.bookingback.repository;

import com.booking.back.bookingback.entities.Politica;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface PoliticaRepository extends JpaRepository<Politica, Long> {
    @Modifying
    @Query(value= "delete from politicas where producto_id = ?", nativeQuery = true)
    void EliminarPorProdID(Long id);
}
