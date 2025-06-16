package com.alura.literalura.main;

import com.alura.literalura.dto.Datos;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.DatosLibro;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
// falta dto.Datos

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainLiterAlura {

   private final String URL_BASE = "https://www.gutendex.com/Libros/";
   private final String URL_PAGE = "?page=";
   private final String URL_FIND = "?search=";
   private Scanner teclado = new Scanner(System.in);
   private ConsumoAPI consumoApi = new ConsumoAPI();
   //private ConsumoRespuesta consumoRespuesta = new ConsumoRespuesta();
   private ConvierteDatos conversor = new ConvierteDatos();
   //   private List<DatosLibro> datosLibros = new ArrayList<>();
   private LibroRepository repositorioLib;
   private AutorRepository repositorioAut;
   //private List<Libro> results;
   //private LibroService libroService = new LibroService();   // instanciar

   //private Optional<Libro> apiLibro;

   public MainLiterAlura(LibroRepository repositoryL, AutorRepository repositoryA)
   {
      this.repositorioLib = repositoryL;
      this.repositorioAut = repositoryA;
   }
   
   private int opcion = -1;
   
   public void mostrarMenu() {
      System.out.println("MENU PRINCIPAL");
      while (opcion != 0) {
         var menu = """
               1 - Buscar Libro y grabar libros en la BD
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
               buscarYGrabarLibroXTitulo();
               break;
            case 2:
               //buscarLibrosPorTitulo();
               break;
            case 3:
               //mostrarLibrosBuscadas();
               break;
            case 4:
               //buscarLibrosPorTitulo();
               break;
            case 5:
               autoresVivosSegunFechas();
               break;

            case 0:
               System.out.println("Cerrando la aplicación...");
               break;
            default:
               System.out.println("Opción inválida");
         } // end sw
      } // end wh
   }

   // se agrego un throws, al utilizar obtner datos
   public Optional<DatosLibro> getDatosLibro(String tituloLibro) {
      String json = consumoApi.obtenerDatos(URL_BASE + URL_FIND + tituloLibro.toLowerCase().replace(" ", "+"));
      List<DatosLibro> libros = conversor.obtenerDatos(json, Datos.class).listaDeLibros();
      Optional<DatosLibro> libro;
            libro = libros.stream()
            .filter(l -> l.titulo().toLowerCase().contains(tituloLibro.toLowerCase()))
            .findFirst();
      return libro;
   }

//      private DatosLibro getDatosLibro () {
//         System.out.println("Escribe el nombre del libro que deseas buscar");
//         var nombreLibro = teclado.nextLine().toLowerCase();
//         String encodedQuery = null;
//         try {
//            encodedQuery = URLEncoder.encode(nombreLibro, "UTF-8");
//         } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//         }
//         var URL2 = URL_BASE + encodedQuery; // nombreLibro;
//         System.out.println("URL enviada " + URL_BASE);
//         var json = consumoApi.obtenerDatos(URL_BASE);
//         // var json = consumoRespuesta.obtenerDatos(URL_BASE);  // OPCION 2
//         System.out.println("json generado \n" + json);
//         DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
//         return datos;
//      }

      private void buscarYGrabarLibroXTitulo() {
         System.out.println("Escribe el nombre del libro que deseas buscar");
         var nombreLibro = teclado.nextLine().toLowerCase();
         Optional<DatosLibro> datos = getDatosLibro(nombreLibro);
         // si no existe, grabar en BD
         if(datos.isPresent()){   // existe en la api
            Libro libro = new Libro(datos.get());
            repositorioLib.save(libro);
            System.out.println("Libro grabado en BD " + libro);
         }
         
         //buscar libro en la BD
         Optional<Libro> libroEnBaseDatos = repositorioLib.findByTituloContainsIgnoreCase(nombreLibro);
         if (libroEnBaseDatos.isPresent()) {
            System.out.println("Libro ya existe en la Base de Datos");
            System.out.println(libroEnBaseDatos.get());
         } else if (datos.isPresent()) {
            // busqueda y/o crear nuevo autor
            // Recorrerer lista de autores y grabarla

            List<Autor> AutorList = datos.get().autores().stream()
                    .map(a -> AutorRepository.findByNameContainsIgnoreCase(a.name())
                            .orElseGet(() -> AutorRepository.save(new Autor(a))))
                    .collect(Collectors.toList());
            // nueva instancia...
            Book newlibroEnBaseDatos = new Book(apiLibro.get(),AutorList);
            bookRepository.save(newlibroEnBaseDatos);
            System.out.println(newlibroEnBaseDatos);
         } else {
            System.out.println("Libro no encontrado :(");
         }
            // si encontramos el libro en la api...
//         DatosLibro datos = getDatosLibro(nombreLibro);
//         Libro libro = new Libro(datos);
//         System.out.println("Libro a grabar " + libro.toString());
//         repositorioLib.save(libro);
//         System.out.println("Libro guardado en la base de datos");
//         //datosLibros.add(datos);
//         System.out.println(datos);
      }

//      private void buscarLibrosPorTitulo () {
//         System.out.print("Escriba nombre del libro a buscar ");
//         var nombreLibro = teclado.nextLine();
//         apiLibro = repositorioLib.findByTituloContainsIgnoreCase(nombreLibro);
//         if (apiLibro.isPresent()) {
//            System.out.println("Libro buscado " + apiLibro.get());
//         } else {
//            System.out.println("Libro no encontrado !!!");
//         }
//      }

      private void autoresVivosSegunFechas(){
         System.out.print("Ingrese numero de año para saber autores vivos en esa fecha ");
         var buscar = teclado.nextLine();
         try{
            Integer fecha = Integer.parseInt(buscar);
            List<Autor> autoresVivosSegunFecha = repositorioAut.autoresVivosSegunFechas(fecha);
            if(autoresVivosSegunFecha.isEmpty()){
               System.out.println("No se registran autores vivos en esa fecha");
               return;
            }
            autoresVivosSegunFecha.stream().sorted(Comparator.comparing(Autor::getNombreAutor))
                  .forEach(System.out::println);
         } catch (NumberFormatException e) {
            System.out.println("El valor ingresado no es un numero entero !!!");
         }
      }
   }
