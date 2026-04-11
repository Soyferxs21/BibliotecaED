/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobiblioteca;

/**
 *
 * @author nexxu
 */
public class GestionBiblioteca {
    private Libro cabeza;
    
    public boolean estaVacia() {
    return cabeza == null;
}
    
    public void agregar(Libro nuevo) {
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Libro temp = cabeza;
            while (temp.siguiente != null) temp = temp.siguiente;
            temp.siguiente = nuevo;
        }
    }

    public boolean eliminar(String isbn) {
        if (cabeza == null) return false;
        if (cabeza.isbn.equals(isbn)) {
            cabeza = cabeza.siguiente;
            return true;
        }
        Libro temp = cabeza;
        while (temp.siguiente != null) {
            if (temp.siguiente.isbn.equals(isbn)) {
                temp.siguiente = temp.siguiente.siguiente;
                return true;
            }
            temp = temp.siguiente;
        }
        return false;
    }

    public void mostrarTodo() {
        if (estaVacia()) {
        System.out.println("--- La biblioteca esta vacia actualmente ---");
        return;
        }
        Libro temp = cabeza;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.siguiente;
        }
    }
    
    public void buscarPorCriterio(String termino, int opcion) {
        Libro temp = cabeza;
        boolean encontrado = false;

        while (temp != null) {
            boolean coincide = false;
        
            // Evaluar según la opción seleccionada
            switch (opcion) {
                case 1 -> coincide = temp.titulo.equalsIgnoreCase(termino);
                case 2 -> coincide = temp.autor.equalsIgnoreCase(termino);
                case 3 -> coincide = temp.isbn.equalsIgnoreCase(termino);
            }

            if (coincide) {
                System.out.println("Resultado: " + temp);
                encontrado = true;
            }
            temp = temp.siguiente;
        }

        if (!encontrado) {
            System.out.println("No se encontraron libros con el término: " + termino);
        }
    }
    
    public Libro getCabeza() {
        return cabeza;
    }
    
    public void solicitarReporte() {
    GeneradorReportes.crearReporteTxt(this.cabeza);
    }
}
