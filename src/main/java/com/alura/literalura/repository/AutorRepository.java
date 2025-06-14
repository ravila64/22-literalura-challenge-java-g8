package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
   Optional<Autor> findByNombreAutorAndYearBornAndYearDead(String nombreAutor, Integer yearBorn, Integer yearDead);

   List<Autor> findByNombreAutorContainsIgnoreCase(String nombreAutor);

   // JPQL
   @Query(value = "SELECT * FROM Autor a WHERE a.yearBorn >= :year AND a.yearDead <= :year")
   List<Autor> autoresVivosSegunFechas(int year);

   //query native
   @Query(value = "SELECT FROM autores a ORDER BY (a.yearDead-a.yearBorn) DESC LIMIT 1", nativeQuery = true)
   Optional<Autor> autoresMasLongevos();
}
