/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Control.*;
import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author soyfe
 */
public class Reportes extends JPanel {

    private LinkedList<Book> catalogo;
    private LinkedList<Prestamo> prestamos;

    public Reportes(LinkedList<Book> cat, LinkedList<Prestamo> pre) {
        this.catalogo = cat;
        this.prestamos = pre;

        setLayout(new GridLayout(1, 2, 20, 0)); // Dos columnas: Multas y Gráfica
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(30, 30, 30, 30));

        //columna de atrasados
        add(crearPanelMultas());

        //columna de populares
        add(crearPanelGrafica());
    }

    private JPanel crearPanelMultas() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Libros con Atraso (Multas)"));

        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        double totalMultas = 0;

        for (Prestamo p : prestamos) {
            if (p.diasPrestado() > 15) {
                double multa = p.calcularMulta();
                totalMultas += multa;
                modeloLista.addElement(p.libro.titulo + " - Multa: $" + multa);
            }
        }

        JList<String> lista = new JList<>(modeloLista);
        panel.add(new JScrollPane(lista), BorderLayout.CENTER);

        JLabel lblTotal = new JLabel("Total Recaudado: $" + totalMultas);
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTotal.setBorder(new EmptyBorder(10, 0, 0, 0));
        panel.add(lblTotal, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelGrafica() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder("Top 5 Libros Más Populares"));

//ordena por que tantas veces es prestado
        catalogo.sort((a, b) -> b.vecesPrestado - a.vecesPrestado);

        // Componente de dibujo personalizado para las barras
        JPanel grafico = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int y = 50;
                int maxPrestamos = catalogo.isEmpty() ? 1 : Math.max(1, catalogo.get(0).vecesPrestado);

                // Dibujar hasta los primeros 5 libros
                for (int i = 0; i < Math.min(5, catalogo.size()); i++) {
                    Book b = catalogo.get(i);

               //barra proporcional
int anchoBarra = (b.vecesPrestado * (getWidth() - 150)) / maxPrestamos;

                    // Dibujar Barra Azul (Inspirada en tu imagen)
                    g2.setColor(new Color(71, 145, 219));
                    g2.fillRoundRect(100, y, anchoBarra, 30, 10, 10);

                    // Etiquetas
                    g2.setColor(Color.BLACK);
                    g2.setFont(new Font("SansSerif", Font.PLAIN, 11));
                    g2.drawString(b.titulo, 10, y + 20);
                    g2.drawString(String.valueOf(b.vecesPrestado), 100 + anchoBarra + 5, y + 20);

                    y += 50;
                }
            }
        };
        grafico.setBackground(Color.WHITE);
        panel.add(grafico, BorderLayout.CENTER);

        return panel;
    }
}
