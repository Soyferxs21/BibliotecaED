/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import java.util.LinkedList;

/**
 *
 * @author soyfe
 */
public class User{
    String id;
    String nombre;
    String email;
    String telefono;

    LinkedList<Book> librosPrestados = new LinkedList<>();

    public User(String id, String nombre,
                   String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public boolean puedePrestar() {
        return librosPrestados.size() < 3;
    }

    @Override
    public String toString() {
        return nombre + " | Libros actuales: " + librosPrestados.size();
    }

    public Object getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}