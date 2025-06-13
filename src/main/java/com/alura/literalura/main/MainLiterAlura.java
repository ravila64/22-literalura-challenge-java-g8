package com.alura.literalura.main;

import com.alura.literalura.model.DatosLibro;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MainLiterAlura {

   private Scanner teclado = new Scanner(System.in);
   private ConsumoAPI consumoApi = new ConsumoAPI();
   // books?search=
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
               1 - Grabar libros en la BD
               2 - Buscar libros por titulo
               3 - Buscar libros registrados
               4 - Listar autores registrados
               5 - Listar autores vivos en determinado año
               6 - Listar libros x idiomaEnum
               
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
               buscarLibrosPorTitulo();
               break;
            case 3:
               //mostrarLibrosBuscadas();
               break;
            case 4:
               //buscarLibrosPorTitulo();
               break;
            case 5:
               //buscarTop5Libros();
               //cargarJson();
               break;

            case 0:
               System.out.println("Cerrando la aplicación...");
               break;
            default:
               System.out.println("Opción inválida");
         }
      }
   }

      private DatosLibro getDatosLibro () {
         System.out.println("Escribe el nombre del libro que deseas buscar");
         var nombreLibro = teclado.nextLine().toLowerCase();
         String encodedQuery = null;
         try {
            encodedQuery = URLEncoder.encode(nombreLibro, "UTF-8");
         } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
         }
         var URL = URL_BASE + encodedQuery; // nombreLibro;
         System.out.println("URL enviada " + URL);
         var json = consumoApi.obtenerDatos(URL);
         System.out.println("json generado \n" + json);
         DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
         return datos;
      }

      private void buscarLibroWeb () {
         DatosLibro datos = getDatosLibro();
         Libro libro = new Libro(datos);
         System.out.println("Libro a grabar " + libro.toString());
         repositorio.save(libro);
         System.out.println("Libro guardado en la base de datos");
         //datosLibros.add(datos);
         System.out.println(datos);
      }

      private void buscarLibrosPorTitulo () {
         System.out.print("Escriba nombre del libro a buscar ");
         var nombreLibro = teclado.nextLine();
         libroBuscado = repositorio.findByTituloContainsIgnoreCase(nombreLibro);
         if (libroBuscado.isPresent()) {
            System.out.println("Libro buscado " + libroBuscado.get());
         } else {
            System.out.println("Libro no encontrado !!!");
         }
      }

   }
