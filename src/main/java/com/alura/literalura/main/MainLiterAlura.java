package com.alura.literalura.main;

import com.alura.literalura.model.DatosLibro;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MainLiterAlura {

   private Scanner teclado = new Scanner(System.in);
   private ConsumoAPI consumoApi = new ConsumoAPI();
   private final String URL_BASE = "https://www.gutendex.com/books?search=";
   //   private final String API_KEY = "&apikey=" + System.getenv("API_KEY_MOVIES");
   private ConvierteDatos conversor = new ConvierteDatos();
   //   private List<DatosLibro> datosLibros = new ArrayList<>();
   private LibroRepository repositorio;
   private List<Libro> libros;
   private Optional<Libro> libroBuscado;

   public MainLiterAlura(LibroRepository repository) {
      this.repositorio = repository;
   }

   public void mostrarMenu() {
      System.out.println("MENU PRINCIPAL");
      var opcion = -1;
      while (opcion != 0) {
         var menu = """
               1 - Buscar libros por titulo
               2 - Buscar libros registrados
               3 - Listar autores registrados
               4 - Listar autores vivos en determinado año
               5 - Listar libros x idioma
               
               0 - Salir
               """;
         System.out.println(menu);
         System.out.print("Digite opción entre [1..5] o 0=[Salir] : ");
         opcion = teclado.nextInt();
         teclado.nextLine();

         switch (opcion) {
            case 1:
               buscarLibroWeb();
               break;
            case 2:
               //buscarEpisodioPorLibro();
               break;
            case 3:
               //mostrarLibrosBuscadas();
               break;
            case 4:
               //buscarLibrosPorTitulo();
               break;
            case 5:
               //buscarTop5Libros();
               break;

            case 0:
               System.out.println("Cerrando la aplicación...");
               break;
            default:
               System.out.println("Opción inválida");
         }
      }
   }


   private DatosLibro getDatosLibro() {
      System.out.println("Escribe el nombre del libro que deseas buscar");
      var nombreLibro = teclado.nextLine();
      var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
      System.out.println(json);
      DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
      return datos;
   }

   private void buscarLibroWeb() {
      DatosLibro datos = getDatosLibro();
      Libro libro = new Libro(datos);
      repositorio.save(libro);
      //datosLibros.add(datos);
      System.out.println(datos);
   }

}
