package com.alura.literalura.repository;

import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long>{
    Optional<Libro> findByTituloContainsIgnoreCase(String nombreLibro);

   // listado de todos los libros, ignorando may/min
   //Optional<Libro> findByTituloContainsIgnoreCase(String nombreLibro);

   //

}
