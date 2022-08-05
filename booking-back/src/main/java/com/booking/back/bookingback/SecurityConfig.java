package com.booking.back.bookingback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.booking.back.bookingback.jwt.JwtRequestFilter;
import com.booking.back.bookingback.service.impl.LoginService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService).passwordEncoder(bCryptPasswordEncoder);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
        .antMatchers("/categorias/crear", "/categorias/actualizar", "/categorias/eliminar/**").hasRole("ADMIN")
        .antMatchers("/ciudades/eliminar/**").hasRole("ADMIN")
        .antMatchers("/productos/crear", "/productos/eliminar/**").hasRole("ADMIN")
        .antMatchers( "/productos/reserva/**").hasAnyRole("USER", "ADMIN")
        .antMatchers("/registroUsuario/buscar/**", "/registroUsuario/eliminar/**").hasAnyRole("USER", "ADMIN")
        .antMatchers("/reservas/listar", "/reservas/buscar/**").hasRole("ADMIN")
        .antMatchers("/reservas/crear", "/reservas/usuario/**", "/reservas/eliminar/**").authenticated()
        .antMatchers("/v2/api-docs",
        "/swagger-resources", "/swagger-resources/**", "/configuration/ui", "/configuration/security", "/swagger-ui.html", "/webjars/**", "/v3/api-docs/**", "/swagger-ui/**",
        "/login", 
        "/registroUsuario/crear",
        "/categorias/listar",
        "/productos/listar", "/productos/ciudad/fechas", "/productos/buscar/**",
        "/ciudades/crear","/ciudades/listar","/ciudades/buscar/**").permitAll()
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
   public DaoAuthenticationProvider daoAuthenticationProvider() {
       DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
       provider.setPasswordEncoder(bCryptPasswordEncoder);
       provider.setUserDetailsService(loginService);
       return provider;
   }

}
