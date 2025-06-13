package com.alura.literalura.model;

public enum IdiomaEnum {
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

   IdiomaEnum(String idiomaGutendex, String idiomaEnEspanol) {
      this.idiomaGutendex = idiomaGutendex;
      this.idiomaEnEspanol = idiomaEnEspanol;
   }
   
   public static IdiomaEnum fromString(String text) {
      for (IdiomaEnum idiomaEnum : IdiomaEnum.values()) {
         if (idiomaEnum.idiomaGutendex.equalsIgnoreCase(text)) {
            return idiomaEnum;
         }
      }
      throw new IllegalArgumentException("Ningún idioma encontrado: " + text);
   }

   public static IdiomaEnum fromEspanol(String text) {
      for (IdiomaEnum idiomaEnum : IdiomaEnum.values()) {
         if (idiomaEnum.idiomaEnEspanol.equalsIgnoreCase(text)) {
            return idiomaEnum;
         }
      }
      throw new IllegalArgumentException("Ningún idioma encontrado: " + text);
   }
}