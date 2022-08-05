package com.booking.back.bookingback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.booking.back.bookingback.entities.Usuario;

import java.util.Optional;

@Transactional
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("from Usuario u where u.email =:email")
    Optional<Usuario> getUserByEmail(@Param("email") String email);
}
