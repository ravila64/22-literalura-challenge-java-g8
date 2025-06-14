package com.alura.literalura.service;

import org.springframework.web.client.RestTemplate;

public class ConsumoRespuesta {

   public String obtenerDatos(String url) {
      RestTemplate restTemplate = new RestTemplate();
      RespuestaAPI resp = restTemplate.getForObject(url, RespuestaAPI.class);
      return resp.toString();
   }

}
