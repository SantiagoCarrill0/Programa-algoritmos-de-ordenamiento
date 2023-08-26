package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import modelo.Algoritmos;
import vista.Interfaz;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Controlador implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            ArrayList<Integer> numbers = new ArrayList<>();
            try {
                Scanner scanner = new Scanner(archivo);
                while (scanner.hasNextLine()) {
                    int number = Integer.parseInt(scanner.nextLine());
                    numbers.add(number);    
                }
                scanner.close();

                Interfaz.numbersArray = new int[numbers.size()];
                for (int i = 0; i < numbers.size(); i++) {
                    Interfaz.numbersArray[i] = numbers.get(i);
                }
                String filePath = archivo.getAbsolutePath();
                Interfaz.direccionArchivoField.setText(filePath);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void ordenar(String seleccion) {
        int[] numeros = Interfaz.numbersArray;
    
        if (Interfaz.algoritmoComboBox.getSelectedItem().equals("Merge sort")) {
            if (seleccion.equals("Ascendente")) {
                Algoritmos.mergeSort(numeros, 0, numeros.length - 1);
            } else if (seleccion.equals("Descendente")) {
                Algoritmos.mergeSortDesc(numeros, 0, numeros.length - 1);
            }
        } else if (Interfaz.algoritmoComboBox.getSelectedItem().equals("Quick sort")) {
            if (seleccion.equals("Ascendente")) {
                Algoritmos.quickSort(numeros, 0, numeros.length - 1);
            } else if (seleccion.equals("Descendente")) {
                Algoritmos.quickSortDesc(numeros, 0, numeros.length - 1);
            }
        } else if (Interfaz.algoritmoComboBox.getSelectedItem().equals("Heap sort")) {
            if (seleccion.equals("Ascendente")) {
                Algoritmos.heapSort(numeros);
            } else if (seleccion.equals("Descendente")) {
                Algoritmos.heapSortDesc(numeros);
            }
        }
        Interfaz.numbersArray = numeros;
    }
    public void guardar() {
    if (Interfaz.numbersArray != null) {
        JFileChooser saveFileChooser = new JFileChooser();
        int saveResult = saveFileChooser.showSaveDialog(null);
        if (saveResult == JFileChooser.APPROVE_OPTION) {
            File saveFile = saveFileChooser.getSelectedFile();
            try (FileOutputStream fos = new FileOutputStream(saveFile);
                 OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                 BufferedWriter writer = new BufferedWriter(osw)) {

                for (int number : Interfaz.numbersArray) {
                    String numeroComoCadena = String.valueOf(number);
                    writer.write(numeroComoCadena);
                    writer.newLine();
                }
                JOptionPane.showMessageDialog(null, "Números ordenados guardados exitosamente en el archivo seleccionado.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "No hay ningun archivo seleccionado ni ordenado para poder guardar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
}