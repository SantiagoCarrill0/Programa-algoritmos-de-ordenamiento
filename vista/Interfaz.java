package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controlador.Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz {
    public static JComboBox<String> algoritmoComboBox; //Declara la variable algoritmoComboBox
    public static JComboBox<String> ordenComboBox; //Declara la variable ordenComboBox
    public static int[] numbersArray;
    public static Controlador controlador = new Controlador();
    public static JTextField direccionArchivoField;
    
    public void init() {
        //VENTANA
        JFrame ventana = new JFrame("Ordenador de números");
        ventana.setSize(500, 300);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        //ICONO DEL PROGRAMA
        ImageIcon icono = new ImageIcon(getClass().getResource("/vista/icono/icono.png"));
        ventana.setIconImage(icono.getImage());
        //PANEL
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setLayout(new GridBagLayout()); //El GridBag es para orientar las cosas en el panel
        //TEXTO DIRECCION DEL ARCHIVO
        JLabel labelURL = new JLabel("Dirección del archivo");
        labelURL.setForeground(Color.WHITE);
        GridBagConstraints gbcLabelURL = new GridBagConstraints();
        gbcLabelURL.gridx = 1;
        gbcLabelURL.gridy = 0;
        gbcLabelURL.insets = new Insets(10, 10, 0, 10);
        panel.add(labelURL, gbcLabelURL);
        ventana.add(panel);
        //BARRA PARA LA DIRECCION DEL ARCHIVO
        direccionArchivoField = new JTextField(20);
        direccionArchivoField.setPreferredSize(new Dimension(300, direccionArchivoField.getPreferredSize().height));
        GridBagConstraints gbcDireccionArchivo = new GridBagConstraints();
        gbcDireccionArchivo.gridx = 1; 
        gbcDireccionArchivo.gridy = 1;
        gbcDireccionArchivo.gridwidth = 2; // Ajustar el ancho del JTextField ocupando 2 celdas
        gbcDireccionArchivo.fill = GridBagConstraints.HORIZONTAL; //Rellenar horizontalmente
        gbcDireccionArchivo.insets = new Insets(10, 10, 10, 10);
        panel.add(direccionArchivoField, gbcDireccionArchivo);
        //BOTON PARA ABRIR EL ARCHIVO DE LA RUTA 
        JButton abrirArchivoButton = new JButton("Seleccionar archivo");
        GridBagConstraints gbcBotonSeleccionar = new GridBagConstraints();
        gbcBotonSeleccionar.gridx = 0;
        gbcBotonSeleccionar.gridy = 1;
        gbcBotonSeleccionar.insets = new Insets(10, 10, 10, 10);
        panel.add(abrirArchivoButton, gbcBotonSeleccionar);
        abrirArchivoButton.addActionListener(new Controlador());
        //Boton para ordenar
        JButton ordenarButton = new JButton("Ordenar");
        GridBagConstraints gbcOrdenarButton = new GridBagConstraints();
        gbcOrdenarButton.gridx = 1;
        gbcOrdenarButton.gridy = 4;
        gbcOrdenarButton.insets = new Insets(10, 10, 10, 10);
        panel.add(ordenarButton, gbcOrdenarButton);
        ordenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Interfaz.numbersArray != null) { // Verifica si se ha seleccionado un archivo
                    String seleccion = ordenComboBox.getSelectedItem().toString();
                    Interfaz.controlador.ordenar(seleccion);
                } else {
                    JOptionPane.showMessageDialog(null, "Primero selecciona un archivo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        //BOTON PARA GUARDAR
        JButton guardarArchivoButton = new JButton("Guardar");
        GridBagConstraints gbcBotonGuardar = new GridBagConstraints();
        gbcBotonGuardar.gridx = 0;
        gbcBotonGuardar.gridy = 3;
        gbcBotonGuardar.insets = new Insets(0, 10, 10, 10);
        panel.add(guardarArchivoButton, gbcBotonGuardar);
        Controlador actionListenerguardar = new Controlador();
        guardarArchivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionListenerguardar.guardar();
            }
        });
        //TEXTO ELECCION ALGORITMO
        JLabel labelAlgoritmo = new JLabel("Tipo de algoritmo");
        labelAlgoritmo.setForeground(Color.WHITE);
        GridBagConstraints gbcLabelAlgoritmo = new GridBagConstraints();
        gbcLabelAlgoritmo.gridx = 1;
        gbcLabelAlgoritmo.gridy = 2;
        gbcLabelAlgoritmo.insets = new Insets(10, 10, 5, 10);
        panel.add(labelAlgoritmo, gbcLabelAlgoritmo);
        //COMBO BOX ELECCION DE ALGORITMO
        String[] algoritmos = {"Merge sort", "Quick sort", "Bucket sort"};
        algoritmoComboBox = new JComboBox<>(algoritmos); 
        GridBagConstraints gbcAlgoritmoComboBox = new GridBagConstraints();
        gbcAlgoritmoComboBox.gridx = 1;
        gbcAlgoritmoComboBox.gridy = 3; 
        gbcAlgoritmoComboBox.insets = new Insets(0, 10, 10, 10);
        panel.add(algoritmoComboBox, gbcAlgoritmoComboBox);
        //TEXTO ELECCION ORDEN
        JLabel labelOrden = new JLabel("Tipo de orden");
        labelOrden.setForeground(Color.WHITE);
        GridBagConstraints gbcLabelOrden = new GridBagConstraints();
        gbcLabelOrden.gridx = 2;
        gbcLabelOrden.gridy = 2;
        gbcLabelOrden.insets = new Insets(10, 10, 5, 10);
        panel.add(labelOrden, gbcLabelOrden);
        //COMBO BOX ORDEN DE ALGORITMO
        String[] orden = {"Ascendente", "Descendente",};
        ordenComboBox = new JComboBox<>(orden);
        GridBagConstraints gbcOrdenComboBox = new GridBagConstraints();
        gbcOrdenComboBox.gridx = 2;
        gbcOrdenComboBox.gridy = 3;
        gbcOrdenComboBox.insets = new Insets(0, 10, 10, 10);
        panel.add(ordenComboBox, gbcOrdenComboBox);
        ventana.add(panel);
        ventana.setVisible(true);
    }
}