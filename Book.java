/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author soyfe
 */


public class Book {

   public String isbn;
   public String titulo;
    public String autor;
   public int year;
   public String categoria;
   public int disponibles;
   public int vecesPrestado = 0;

    public Book(String isbn, String titulo, String autor,
                 int anio, String categoria, int disponibles) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.year = year;
        this.categoria = categoria;
        this.disponibles = disponibles;
    }

    @Override
    public String toString() {
        return titulo + " - " + autor + 
               " | Disponibles: " + disponibles;
    }
}