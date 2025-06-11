package com.alura.literalura.main;

import com.alura.literalura.repository.LibroRepository;

public class MainLiterAlura {

   private LibroRepository repositorio;

   public MainLiterAlura(LibroRepository repository) {
      this.repositorio = repository;
   }

   public void mostrarMenu() {
      System.out.println("MENU PRINCIPAL");
   }

}
