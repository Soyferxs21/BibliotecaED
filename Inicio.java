/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Control.Book;
import Control.Prestamo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author soyfe
 */
public class Inicio extends JPanel {

    public Inicio(LinkedList<Book> catalogo, LinkedList<Prestamo> prestamos) {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 247, 250)); // Gris claro de fondo
        setBorder(new EmptyBorder(30, 30, 30, 30));

        // Encabezado
        JLabel bienvenido = new JLabel("¡Bienvenido, Admin!");
        bienvenido.setFont(new Font("SansSerif", Font.BOLD, 28));
        add(bienvenido, BorderLayout.NORTH);

        // Panel tarjetas
        JPanel panelCards = new JPanel(new GridLayout(1, 2, 20, 0));
        panelCards.setBackground(new Color(245, 247, 250));

        // Calculos totales
        int totalDisponibles = catalogo.stream().mapToInt(b -> b.disponibles).sum();
        int totalPrestados = prestamos.size();

        // libros disponibles
        panelCards.add(crearTarjeta("Libros Disponibles", 
                                    String.valueOf(totalDisponibles), 
                                    new Color(71, 145, 219)));

        // Tarjeta Naranja - Libros Prestados
        panelCards.add(crearTarjeta("Libros Prestados", 
                                    String.valueOf(totalPrestados), 
                                    new Color(230, 177, 122)));

        add(panelCards, BorderLayout.CENTER);
        
        // Seccion inferior
        JPanel inferior = new JPanel(new BorderLayout());
        inferior.setPreferredSize(new Dimension(0, 300));
        inferior.setBackground(Color.WHITE);
        inferior.setBorder(BorderFactory.createTitledBorder("Actividad Reciente"));
        add(inferior, BorderLayout.SOUTH);
    }

    // tarjetas de colores
    private JPanel crearTarjeta(String titulo, String valor, Color colorFondo) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(colorFondo);
        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 18));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblValor = new JLabel(valor);
        lblValor.setForeground(Color.WHITE);
        lblValor.setFont(new Font("SansSerif", Font.BOLD, 48));
        lblValor.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(lblTitulo);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(lblValor);

        return card;
    }
}