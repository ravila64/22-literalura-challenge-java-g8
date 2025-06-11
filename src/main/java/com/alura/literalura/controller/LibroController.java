package com.alura.literalura.controller;
import com.alura.literalura.dto.LibroDTO;
import com.alura.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class LibroController {
   @Autowired
   private LibroService service;

   @GetMapping("")
   public List<LibroDTO> obtenerTodosLibros() {
      return service.obtenerTodosLibros();
   }

}
