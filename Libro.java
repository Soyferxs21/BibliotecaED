/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobiblioteca;

/**
 *
 * @author nexxu
 */
public class Libro {
    String titulo, autor, isbn;
    int anio;
    boolean disponible;
    Libro siguiente;

    public Libro(String titulo, String autor, String isbn, int anio) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.anio = anio;
        this.disponible = true;
        this.siguiente = null;
    }

    @Override
    public String toString() {
        return String.format("[%s] %-20s | %-15s | %d | %s", 
            isbn, titulo, autor, anio, (disponible ? "Disponible" : "Prestado"));
    }
}
