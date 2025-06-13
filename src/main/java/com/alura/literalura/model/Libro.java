package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(unique = true)
   private String titulo;

   //@Transient
   @OneToMany(mappedBy = "libro", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
   private List<Autor> autores;

   private List<String> idiomas;

   public Libro() {
   }

   public Libro(DatosLibro datosLibro) {
      this.titulo = datosLibro.titulo();
      this.autores = datosLibro.autores();
      this.idiomas = datosLibro.idiomas();
   }

   @Override
   public String toString() {
      return "Libro{" +
            "autores=" + autores +
            ", id=" + id +
            ", titulo='" + titulo + '\'' +
            ", idiomas=" + idiomas +
            '}';
   }

   // getters and setters

   public List<Autor> getAutores() {
      return autores;
   }

   public void setAutores(List<Autor> autores) {
      autores.forEach(a->a.setLibro(this));
      this.autores = autores;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public List<String> getIdiomas() {
      return idiomas;
   }

   public void setIdiomas(List<String> idiomas) {
      this.idiomas = idiomas;
   }

   public String getTitulo() {
      return titulo;
   }

   public void setTitulo(String titulo) {
      this.titulo = titulo;
   }
}
