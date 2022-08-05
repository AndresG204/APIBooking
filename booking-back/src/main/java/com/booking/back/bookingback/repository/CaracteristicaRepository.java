package com.booking.back.bookingback.repository;

import com.booking.back.bookingback.entities.Caracteristica;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Long> {
    @Modifying
    @Query(value= "delete from caracteristicas where producto_id = ?", nativeQuery = true)
    void EliminarPorProdID(Long id);
}
