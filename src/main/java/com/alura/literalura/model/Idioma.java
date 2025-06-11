package com.alura.literalura.model;

public enum Idioma {
   INGLES("en", "Inglés"),
   ESPAÑOL("es", "Español"),
   FRANCES("fr", "Francés"),
   ALEMAN("de", "Alemán"),
   ITALIANO("it", "Italiano"),
   JAPONES("ja", "Japonés"),
   PORTUGUES("pt", "Portugués"),
   CHINO("zh", "Chino"),
   RUSO("ru", "Ruso"),
   ARABE("ar", "Árabe"),
   INDU("hi", "Hindi"),
   FILANDES("fi", "Finlandés");

   private String idiomaGutendex;
   private String idiomaEnEspanol;

   Idioma(String idiomaGutendex, String idiomaEnEspanol) {
      this.idiomaGutendex = idiomaGutendex;
      this.idiomaEnEspanol = idiomaEnEspanol;
   }
   
   public static Idioma fromString(String text) {
      for (Idioma idioma : Idioma.values()) {
         if (idioma.idiomaGutendex.equalsIgnoreCase(text)) {
            return idioma;
         }
      }
      throw new IllegalArgumentException("Ningún idioma encontrado: " + text);
   }

   public static Idioma fromEspanol(String text) {
      for (Idioma idioma : Idioma.values()) {
         if (idioma.idiomaEnEspanol.equalsIgnoreCase(text)) {
            return idioma;
         }
      }
      throw new IllegalArgumentException("Ningún idioma encontrado: " + text);
   }
}