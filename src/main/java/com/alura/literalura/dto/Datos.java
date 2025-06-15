package com.alura.literalura.dto;

import com.alura.literalura.model.DatosLibro;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos(
      @JsonAlias("results") List<DatosLibro> listaDeLibros
) {
}
