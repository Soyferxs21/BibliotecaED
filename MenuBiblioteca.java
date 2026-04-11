/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobiblioteca;

/**
 *
 * @author nexxu
 */
import java.util.Scanner;

public class MenuBiblioteca {
    private GestionBiblioteca gestion = new GestionBiblioteca();
    private Scanner leer = new Scanner(System.in);

    public void Menu() {
        int opcion;
        do {
            System.out.println("\n--- SISTEMA DE BIBLIOTECA ---");
            System.out.println("1. Agregar Libro ");
            System.out.println("2. Eliminar Libro");
            System.out.println("3. Buscar por Titulo , Autor o ISBN");
            System.out.println("4. Ver Todos");
            System.out.println("5. Generar Reporte");
            System.out.println("6. Salir");
            System.out.print("Elejir Opcion: ");
            opcion = leer.nextInt();
            leer.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> crearLibro();
                case 2 -> borrarLibro();
                case 3 -> menuBusqueda();
                case 4 -> gestion.mostrarTodo();
                case 5 -> generarReporte();
                default -> System.out.println("Cerrando Programa...");
            }
        } while (opcion != 6);
    }

    private void crearLibro() {
        System.out.print("Título: "); String t = leer.nextLine();
        System.out.print("Autor: "); String a = leer.nextLine();
        System.out.print("ISBN: "); String i = leer.nextLine();
        int an = 0;
        boolean anioValido = false;
        
        while (!anioValido) {
            System.out.print("Año: ");
            try {
                an = Integer.parseInt(leer.nextLine());
            
                if (an >= 0 && an <= 2026) {
                    anioValido = true;
                } else {
                    System.out.println("Error: Debe ser un año");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un número válido para el año.");
            }
        }
        gestion.agregar(new Libro(t, a, i, an));
    }

    private void borrarLibro() {
        System.out.print("ISBN a eliminar: ");
        String i = leer.nextLine();
        if (gestion.eliminar(i)) System.out.println("Eliminado con éxito.");
        else System.out.println("No se encontró el libro.");
    }
    
    private void menuBusqueda() {
    System.out.println("\n--- BUSCAR LIBRO ---");
    System.out.println("1. Por Título\n2. Por Autor\n3. Por ISBN");
    System.out.print("Elija una opcion: ");
    int criterio = leer.nextInt();
    leer.nextLine(); // Limpiar buffer
        
        String nombreCriterio = switch (criterio) {
        case 1 -> "Título";
        case 2 -> "Autor";
        case 3 -> "ISBN";
        default -> "término";
        };
        
        if (criterio >= 1 && criterio <= 3) {
            System.out.print("Ingrese el "+nombreCriterio + " a buscar: ");
            String texto = leer.nextLine();
            gestion.buscarPorCriterio(texto, criterio);
        } else {
            System.out.println("Opción de búsqueda no válida.");
        }
    }
    
    private void generarReporte() {
        if (gestion.estaVacia()) {
        System.out.println("Error: No hay libros registrados para generar un reporte.");
        } else {
            System.out.println("Generando reporte en texto...");
            GeneradorReportes.crearReporteTxt(gestion.getCabeza());
        }
    }
}
