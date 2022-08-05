package com.booking.back.bookingback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.back.bookingback.entities.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol,Long> {
}
