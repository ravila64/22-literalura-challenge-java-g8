package com.alura.literalura.service;

import com.alura.literalura.model.Libro;

import java.util.List;

public class RespuestaAPI {
   private List<Libro> results;

   public List<Libro> getResults() {
      return results;
   }

   public void setResults(List<Libro> results) {
      this.results = results;
   }
}
