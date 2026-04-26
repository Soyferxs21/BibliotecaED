/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Control.Book;
import Control.Prestamo;
import Control.User;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.LinkedList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author soyfe
 */
public class Sistem extends JFrame {

    private LinkedList<Book> catalogo = new LinkedList<>();
    private LinkedList<User> usuarios = new LinkedList<>();
    private LinkedList<Prestamo> prestamosActivos = new LinkedList<>();

    private JPanel contentPanel;
    private CardLayout cardLayout;

    public Sistem() {

        inicializarDatos();
        estilizarVentana();
        configurarPaneles();
    }

    private void estilizarVentana() {
        setTitle("Books - Sistema de Gestión de Biblioteca");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //menu lateral
        JPanel sideMenu = new JPanel();
        sideMenu.setBackground(new Color(255, 255, 255));
        sideMenu.setPreferredSize(new Dimension(220, 700));
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setBorder(new EmptyBorder(20, 10, 20, 10));

        JLabel logo = new JLabel("Books");
        logo.setFont(new Font("SansSerif", Font.BOLD, 22));
        logo.setForeground(new Color(41, 128, 185));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        sideMenu.add(logo);
        sideMenu.add(Box.createRigidArea(new Dimension(0, 40)));

        sideMenu.add(crearBotonMenu("Inicio", "inicio"));
        sideMenu.add(crearBotonMenu("Libros", "libros"));
        sideMenu.add(crearBotonMenu("Préstamos", "prestamos"));
        sideMenu.add(crearBotonMenu("Reportes", "reportes"));

        add(sideMenu, BorderLayout.WEST);

        //panel contenedor
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(new Color(245, 247, 250));
        add(contentPanel, BorderLayout.CENTER);
    }

    private void configurarPaneles() {
        contentPanel.add(new Inicio(catalogo, prestamosActivos), "inicio");
        contentPanel.add(new Libros(catalogo), "libros");
        contentPanel.add(new Prestamos(catalogo, usuarios, prestamosActivos), "prestamos");
        contentPanel.add(new Reportes(catalogo, prestamosActivos), "reportes");
    }

    private JButton crearBotonMenu(String texto, String comando) {
        JButton btn = new JButton(texto);
        btn.setMaximumSize(new Dimension(200, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btn.addActionListener(e -> cardLayout.show(contentPanel, comando));
        return btn;
    }

    private void inicializarDatos() {
        catalogo.add(new Book("978-0134685991", "Clean Code", "Robert Martin", 2008, "Programación", 3));
        usuarios.add(new User("U123", "Juan Perez", "juan@email.com", "555-1234"));
    }
}
