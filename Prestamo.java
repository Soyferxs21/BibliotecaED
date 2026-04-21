/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author soyfe
 */

public class Prestamo {

    User usuario;
    Book libro;
    LocalDate fechaPrestamo;
    LocalDate fechaDevolucion;

    public Prestamo(User usuario, Book libro) {
        this.usuario = usuario;
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = fechaPrestamo.plusDays(15);
    }

    public long diasPrestado() {
        return ChronoUnit.DAYS.between(fechaPrestamo, LocalDate.now());
    }

    public double calcularMulta() {
        long dias = diasPrestado();
        if (dias > 15) {
            return (dias - 15) * 0.50;
        }
        return 0;
    }
}