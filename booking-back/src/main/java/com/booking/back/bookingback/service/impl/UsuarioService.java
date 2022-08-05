package com.booking.back.bookingback.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.booking.back.bookingback.dtos.UsuarioDTO;
import com.booking.back.bookingback.dtos.UsuarioDTO_login;
import com.booking.back.bookingback.entities.Rol;
import com.booking.back.bookingback.entities.Usuario;
import com.booking.back.bookingback.exceptions.ResourceNotFoundException;
import com.booking.back.bookingback.exceptions.SQLIntegrityConstraintViolationException;
import com.booking.back.bookingback.repository.RolRepository;
import com.booking.back.bookingback.repository.UsuarioRepository;
import com.booking.back.bookingback.service.IUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UsuarioService implements IUsuarioService{
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    RolRepository rolRepository;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void agregarUsuario(UsuarioDTO usuarioDTO) throws SQLIntegrityConstraintViolationException {
        Usuario us = objectMapper.convertValue(usuarioDTO, Usuario.class);
        us.setPassword(encoder.encode(us.getPassword()));
       
        Set<Rol> roles = new HashSet<>();
        Long role_id = 1L;
        Rol rol = rolRepository.findById(role_id).get();
        roles.add(rol);
        us.setRoles(roles);

        Optional<Usuario> esONoNull = usuarioRepository.getUserByEmail(us.getEmail());

        if(esONoNull.isPresent() == false) {
            usuarioRepository.save(us);
        } else {
            throw new SQLIntegrityConstraintViolationException("Email no disponible");
        }
     

    }

    @Override
    public UsuarioDTO buscarUsuario(Long id) throws ResourceNotFoundException {
        Optional<Usuario> us = usuarioRepository.findById(id);
        if(us.isPresent()) {
           return objectMapper.convertValue(us.get(), UsuarioDTO.class);
        } else {
            throw new ResourceNotFoundException("Usuario no encontrado");
        }
    }

    @Override
    public UsuarioDTO_login buscarPorEmail(String email) throws ResourceNotFoundException {
        Optional<Usuario> user = usuarioRepository.getUserByEmail(email);
        
        if(user.isPresent()) {
            return objectMapper.convertValue(user.get(), UsuarioDTO_login.class);
         } else {
             throw new ResourceNotFoundException("Usuario no encontrado");
         }
        
    }

    @Override
    public void borrarUsuario(Long id) throws ResourceNotFoundException {
        Optional<Usuario> us = usuarioRepository.findById(id);
            if(us.isPresent()) {
                usuarioRepository.delete(us.get());
            } else {
                throw new ResourceNotFoundException("Usuario no encontrado");
            }
    }
    
}

