package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

@Entity
@Table(name="autores")
public class Autor {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String nombre;

   @Column(name = "yearBorn")
   @JsonAlias({"birth_year"})
   private Integer yearBorn;

   @Column(name = "yearDead")
   @JsonAlias({"death_year"})
   private Integer yearDead;

   @ManyToOne
   private Libro libro;

   public Autor() {
   }

   public Autor(DatosAutor da) {
      this.nombre = da.nombre();
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

   public Libro getLibro() {
      return libro;
   }

   public String getNombre() {
      return nombre;
   }

   public Integer getYearBorn() {
      return yearBorn;
   }

   public Integer getYearDead() {
      return yearDead;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public void setLibro(Libro libro) {
      this.libro = libro;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public void setYearBorn(Integer yearBorn) {
      this.yearBorn = yearBorn;
   }

   public void setYearDead(Integer yearDead) {
      this.yearDead = yearDead;
   }

   @Override
   public String toString() {
      return "Autor{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", yearBorn=" + yearBorn +
            ", yearDead=" + yearDead +
            ", libro=" + libro +
            '}';
   }
}
