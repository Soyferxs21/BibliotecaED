/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Control.User;
import Control.Book;
import Control.Prestamo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author soyfe
 */
public class Prestamos extends JPanel {

    private JTextField txtIdUsuario, txtIsbn;
    private JTable tablaPrestamos;
    private DefaultTableModel modelo;

    private LinkedList<Book> catalogo;
    private LinkedList<User> usuarios;
    private LinkedList<Prestamo> prestamosActivos;

    public Prestamos(LinkedList<Book> cat, LinkedList<User> usu, LinkedList<Prestamo> pre) {
        this.catalogo = cat;
        this.usuarios = usu;
        this.prestamosActivos = pre;

        setLayout(new BorderLayout(20, 20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //Formulario de entrada
        JPanel panelForm = new JPanel(new GridLayout(2, 3, 10, 10));
        panelForm.setBackground(Color.WHITE);
        panelForm.setBorder(BorderFactory.createTitledBorder("Nuevo Préstamo"));

        txtIdUsuario = new JTextField();
        txtIsbn = new JTextField();
        JButton btnPrestar = new JButton("Prestar Libro");
        btnPrestar.setBackground(new Color(71, 145, 219));
        btnPrestar.setForeground(Color.WHITE);
        btnPrestar.setFocusPainted(false);

        panelForm.add(new JLabel("ID Usuario:"));
        panelForm.add(new JLabel("ISBN Libro:"));
        panelForm.add(new JLabel("")); // Espacio vacío
        panelForm.add(txtIdUsuario);
        panelForm.add(txtIsbn);
        panelForm.add(btnPrestar);

        add(panelForm, BorderLayout.NORTH);

        //prestamos actuales
        String[] columnas = {"Usuario", "Libro", "Fecha Salida", "Devolución esperada"};
        modelo = new DefaultTableModel(columnas, 0);
        tablaPrestamos = new JTable(modelo);
        add(new JScrollPane(tablaPrestamos), BorderLayout.CENTER);
        btnPrestar.addActionListener(e -> ejecutarPrestamo());
    }

    private void ejecutarPrestamo() {
        String id = txtIdUsuario.getText();
        String isbn = txtIsbn.getText();

        //busqueda
        User usuario = buscarUsuario(id);
        Book libro = buscarLibro(isbn);

        if (usuario == null || libro == null) {
            JOptionPane.showMessageDialog(this, "Usuario o Libro no encontrado");
            return;
        }

        // validaciones
        if (!usuario.puedePrestar()) {
            JOptionPane.showMessageDialog(this, "El usuario ya tiene 3 libros prestados.");
            return;
        }

        if (libro.disponibles <= 0) {
            JOptionPane.showMessageDialog(this, "No hay ejemplares disponibles.");
            return;
        }

        //prestamo
        Prestamo p = new Prestamo(usuario, libro);
        prestamosActivos.add(p);
        usuario.librosPrestados.add(libro);
        libro.disponibles--;
        libro.vecesPrestado++;

        actualizarTabla();
        limpiarCampos();
        JOptionPane.showMessageDialog(this, "¡Préstamo realizado con éxito!");
    }

    private void actualizarTabla() {
        modelo.setRowCount(0);
        for (Prestamo p : prestamosActivos) {
            modelo.addRow(new Object[]{
                p.usuario.nombre,
                p.libro.titulo,
                p.fechaPrestamo,
                p.fechaDevolucion
            });
        }
    }

    private void limpiarCampos() {
        txtIdUsuario.setText("");
        txtIsbn.setText("");
    }

    //aux
    private User buscarUsuario(String id) {
        for (User u : usuarios) {
            if (u.id.equals(id)) {
                return u;
            }
        }
        return null;
    }

    private Book buscarLibro(String isbn) {
        for (Book b : catalogo) {
            if (b.isbn.equals(isbn)) {
                return b;
            }
        }
        return null;
    }
}
