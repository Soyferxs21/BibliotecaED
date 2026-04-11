/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobiblioteca;

/**
 *
 * @author nexxu
 */
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeneradorReportes {

    public static void crearReporteTxt(Libro cabeza) {
        String nombreArchivo = "reporte_biblioteca.txt";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            writer.println("==================================================");
            writer.println("          REPORTE DE ESTADO DE LIBROS            ");
            writer.println("          Generado: " + dtf.format(LocalDateTime.now()));
            writer.println("==================================================");
            writer.println(String.format("%-15s %-25s %-10s", "ISBN", "TÍTULO", "ESTADO"));
            writer.println("--------------------------------------------------");

            Libro temp = cabeza;
            int contDisponibles = 0;
            int contPrestados = 0;

            while (temp != null) {
                String estado = temp.disponible ? "Disponible" : "Prestado";
                writer.println(String.format("%-15s %-25s %-10s", temp.isbn, temp.titulo, estado));
                
                if (temp.disponible) contDisponibles++;
                else contPrestados++;
                
                temp = temp.siguiente;
            }

            writer.println("--------------------------------------------------");
            writer.println("RESUMEN:");
            writer.println("> Libros Disponibles: " + contDisponibles);
            writer.println("> Libros Prestados: " + contPrestados);
            writer.println("==================================================");

            System.out.println("Archivo '" + nombreArchivo + "' generado con éxito.");

        } catch (IOException e) {
            System.err.println("Error al escribir el reporte: " + e.getMessage());
        }
    }
}
