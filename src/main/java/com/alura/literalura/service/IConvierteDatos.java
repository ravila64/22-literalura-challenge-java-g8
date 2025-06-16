package com.alura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IConvierteDatos {
   <T> T obtenerDatos(String json, Class<T> clase) throws JsonProcessingException;
}
