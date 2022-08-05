package com.booking.back.bookingback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.booking.back.bookingback.entities.Rol;
import com.booking.back.bookingback.entities.Usuario;
import com.booking.back.bookingback.repository.UsuarioRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> user = usuarioRepository.getUserByEmail(email);
        
        Set<GrantedAuthority> autorizaciones = new HashSet<>();
        GrantedAuthority autorizacion = null;
        for (Rol rol : user.get().getRoles()) {
            autorizacion = new SimpleGrantedAuthority(rol.getNombre());
            autorizaciones.add(autorizacion);
        }
        org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(),true, true, true,true,autorizaciones);
        // System.out.println(userDetail);
        return userDetail;
    }

    
}

