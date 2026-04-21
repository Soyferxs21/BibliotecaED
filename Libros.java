/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Control.Book;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author soyfe
 */
public class Libros extends JPanel {
    private JTable tabla;
    private DefaultTableModel modelo;

    public Libros(LinkedList<Book> catalogo) {
        setLayout(new BorderLayout(20, 20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titulo = new JLabel("Gestión de Libros");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        // Tabla
        String[] columnas = {"ISBN", "Título", "Autor", "Año", "Categoría", "Stock"};
        modelo = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modelo);
        
        // Llenar tabla 
        actualizarTabla(catalogo);

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Botones inferiores
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotones.setBackground(Color.WHITE);
        panelBotones.add(new JButton("Agregar Libro"));
        panelBotones.add(new JButton("Editar"));
        panelBotones.add(new JButton("Eliminar"));
        
        add(panelBotones, BorderLayout.SOUTH);
    }

    public void actualizarTabla(LinkedList<Book> catalogo) {
        modelo.setRowCount(0);
        for (Book b : catalogo) {
            modelo.addRow(new Object[]{
                b.isbn, b.titulo, b.autor, b.year, b.categoria, b.disponibles
            });
        }
    }
}