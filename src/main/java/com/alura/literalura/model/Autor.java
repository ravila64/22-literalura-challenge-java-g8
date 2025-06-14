package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

@Entity
@Table(name="autores")
public class Autor {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String nombreAutor;

   //@Column(name = "yearBorn")
   @JsonAlias({"birth_year"})
   private Integer yearBorn;

   //@Column(name = "yearDead")
   @JsonAlias({"death_year"})
   private Integer yearDead;

   @ManyToOne
   private Libro libro;

   public Autor() {
   }

   public Autor(DatosAutor da) {
      this.nombreAutor = da.nombreAutor();
      this.yearBorn = da.yearBorn();
      try {
         this.yearDead = Integer.valueOf(da.yearDead());
      } catch (NumberFormatException e) {
         this.yearDead = 0;
      }
      this.yearDead = da.yearDead();
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Libro getLibro() {
      return libro;
   }

   public void setLibro(Libro libro) {
      this.libro = libro;
   }

   public String getNombreAutor() {
      return nombreAutor;
   }

   public void setNombreAutor(String nombreAutor) {
      this.nombreAutor = nombreAutor;
   }

   public Integer getYearBorn() {
      return yearBorn;
   }

   public void setYearBorn(Integer yearBorn) {
      this.yearBorn = yearBorn;
   }

   public Integer getYearDead() {
      return yearDead;
   }

   public void setYearDead(Integer yearDead) {
      this.yearDead = yearDead;
   }

   @Override
   public String toString() {
      return "Autor{" +
            "id=" + this.getId() +
            ", nombreAutor='" + nombreAutor + '\'' +
            ", yearBorn=" + yearBorn +
            ", yearDead=" + yearDead +
            ", libro=" + libro +
            '}';
   }
}
