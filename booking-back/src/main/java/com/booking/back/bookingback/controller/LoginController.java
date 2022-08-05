package com.booking.back.bookingback.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.back.bookingback.dtos.UsuarioDTO_login;
import com.booking.back.bookingback.entities.AuthenticationRequest;
import com.booking.back.bookingback.entities.AuthenticationResponse;
import com.booking.back.bookingback.jwt.JwtUtil;
import com.booking.back.bookingback.service.impl.UsuarioService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService loginService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        }catch (BadCredentialsException e) {
            throw new Exception("Incorrect", e);
        }
        final UserDetails userDetails = loginService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        UsuarioDTO_login user = usuarioService.buscarPorEmail(authenticationRequest.getEmail());

        HashMap<String, Object> resp = new HashMap<String, Object>();
        resp.put("Token", new AuthenticationResponse((jwt)));
        resp.put("Usuario", user);

        return ResponseEntity.ok(resp);
    }
}
