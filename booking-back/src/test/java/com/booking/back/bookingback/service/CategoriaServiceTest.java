// package com.booking.back.bookingback.service;

// import com.booking.back.bookingback.entities.Categoria;
// import com.booking.back.bookingback.exceptions.ResourceNotFoundException;
// import com.booking.back.bookingback.repository.CategoriaRepository;
// import com.booking.back.bookingback.service.impl.CategoriaService;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import java.util.List;
// import java.util.Optional;

// @SpringBootTest
// class CategoriaServiceTest {
//     @Autowired
//     CategoriaService categoriaService;
//     CategoriaRepository categoriaRepository;


//     @Test
//     public void guardarCategoria() throws ResourceNotFoundException {
//         Categoria categoria1=categoriaService.agregarCategoria(new Categoria("hotel colombia","la mejores vistas del pais","/colombiaHotel"));
//         Assertions.assertTrue(categoria1.getId()!=null);
//         categoriaService.borrarCategoria(categoria1.getId());
//     }
//     @Test
//     public void listaDeCategorias(){

//         List<Categoria> listaDeCategorias = categoriaService.listarCategorias();
//         Assertions.assertTrue(listaDeCategorias.size()>=1);
//     }
//     @Test
//     public void buscandoCategoria() throws ResourceNotFoundException {
//         Categoria categoria2=categoriaService.agregarCategoria(new Categoria("hotel cucuta","la mejores vistas de cucuta","/cucutaHotel"));
//         Optional<Categoria> categoriaBuscada=categoriaRepository.findById(categoria2.getId());
//         Assertions.assertTrue(categoriaBuscada.isPresent());
//         categoriaService.borrarCategoria(categoria2.getId());
//     }
//     @Test
//     public void eliminarCategoria() throws ResourceNotFoundException {
//         Categoria categoriaAEliminar=categoriaService.agregarCategoria(new Categoria("hotel cartagena","vista al mar","/hotelCartagena"));
//         categoriaService.borrarCategoria(categoriaAEliminar.getId());
//         Optional<Categoria> buscarCategoriaEliminada=categoriaRepository.findById(categoriaAEliminar.getId());
//         Assertions.assertFalse(buscarCategoriaEliminada.isPresent());
//     }
//     @Test
//     public void actualizarCategoria() throws ResourceNotFoundException {
//         Categoria categoria3=categoriaService.agregarCategoria(new Categoria("hotel medellin","la mejores vistas de medellin","/medellinHotel"));
//         categoriaService.ActualizarCategoria(new Categoria(categoria3.getId(), "hotel bogota","las mejores vistas de bogota","/bogotaHotel"));
//         Assertions.assertFalse(categoriaService.buscarCategoria(categoria3.getId()).equals(categoria3));
//         categoriaService.borrarCategoria(categoria3.getId());
//     }

// }