package proyectoprogra3;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class ListaCircularTraspasos {
    private NodoTraspaso ultimo;

    private class NodoTraspaso {
        String placa;
        String dpiAnt;
        String nombreAnt;
        String fecha;
        String dpiNew;
        String nombreNew;
        NodoTraspaso siguiente;

        NodoTraspaso(String placa, String dpiAnt, String nombreAnt, String fecha, String dpiNew, String nombreNew) {
            this.placa = placa;
            this.dpiAnt = dpiAnt;
            this.nombreAnt = nombreAnt;
            this.fecha = fecha;
            this.dpiNew = dpiNew;
            this.nombreNew = nombreNew;
            this.siguiente = null;
        }
    }

    public ListaCircularTraspasos() {
        ultimo = null;
    }

    public void insertar(String placa, String dpiAnt, String nombreAnt, String fecha, String dpiNew, String nombreNew) {
        NodoTraspaso nuevo = new NodoTraspaso(placa, dpiAnt, nombreAnt, fecha, dpiNew, nombreNew);
        if (ultimo == null) {
            nuevo.siguiente = nuevo;
            ultimo = nuevo;
        } else {
            nuevo.siguiente = ultimo.siguiente;
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }
    }

    public void obtenerTraspasosPorPlaca(String placa, DefaultTableModel modelo, javax.swing.JTextField totalTraspasosField) {
        modelo.setRowCount(0);
        int totalTraspasos = 0;
        if (ultimo == null) {
            totalTraspasosField.setText("0");
            return;
        }

        NodoTraspaso actual = ultimo.siguiente;
        do {
            if (actual.placa.trim().equals(placa)) {
                modelo.addRow(new Object[]{actual.placa, actual.dpiAnt, actual.nombreAnt, actual.fecha, actual.dpiNew, actual.nombreNew});
                totalTraspasos++;
            }
            actual = actual.siguiente;
        } while (actual != ultimo.siguiente);

        totalTraspasosField.setText(String.valueOf(totalTraspasos));
    }

    public boolean guardarTraspaso(String placa, String dpiAnt, String nombreAnt, String fecha, 
                                   String dpiNew, String nombreNew, String departamento, InformacionMT informacionMT) {
        if (dpiAnt.isEmpty() || nombreAnt.isEmpty() || fecha.isEmpty() || dpiNew.isEmpty() || nombreNew.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String rutaArchivo = "C:\\Users\\Mayby\\Desktop\\SIRVE_Datos_Vehiculos DataSet - copia\\" + 
                            departamento + "\\" + departamento + "_traspasos.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            writer.write(String.format("%s,%s,%s,%s,%s,%s%n", placa, dpiAnt, nombreAnt, fecha, dpiNew, nombreNew));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el traspaso: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        insertar(placa, dpiAnt, nombreAnt, fecha, dpiNew, nombreNew);
        informacionMT.actualizarVehiculoTraspaso(departamento, placa, dpiNew, nombreNew, 1);
        return true;
    }

    public boolean eliminarTraspaso(String placa, String dpiAnt, String nombreAnt, String fecha, 
                                    String dpiNew, String nombreNew, String departamento, InformacionMT informacionMT) {
        // Remove from in-memory list
        if (ultimo == null) {
            JOptionPane.showMessageDialog(null, "No hay traspasos en la lista.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        NodoTraspaso actual = ultimo.siguiente;
        NodoTraspaso anterior = ultimo;
        boolean foundInMemory = false;
        do {
            if (actual.placa.equals(placa) &&
                actual.dpiAnt.equals(dpiAnt) &&
                actual.nombreAnt.equals(nombreAnt) &&
                actual.fecha.equals(fecha) &&
                actual.dpiNew.equals(dpiNew) &&
                actual.nombreNew.equals(nombreNew)) {
                foundInMemory = true;
                if (actual == ultimo && actual.siguiente == ultimo) {
                    ultimo = null; // List becomes empty
                } else {
                    anterior.siguiente = actual.siguiente;
                    if (actual == ultimo) {
                        ultimo = anterior;
                    }
                }
                break;
            }
            anterior = actual;
            actual = actual.siguiente;
        } while (actual != ultimo.siguiente);

        // Remove from file
        String rutaArchivo = "C:\\Users\\Mayby\\Desktop\\SIRVE_Datos_Vehiculos DataSet - copia\\" + 
                            departamento + "\\" + departamento + "_traspasos.txt";
        File inputFile = new File(rutaArchivo);
        File tempFile = new File(rutaArchivo + ".tmp");

        if (!inputFile.exists()) {
            JOptionPane.showMessageDialog(null, "El archivo de traspasos no existe: " + rutaArchivo, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        boolean foundInFile = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                if (datos.length == 6 && 
                    datos[0].trim().equals(placa) &&
                    datos[1].trim().equals(dpiAnt) &&
                    datos[2].trim().equals(nombreAnt) &&
                    datos[3].trim().equals(fecha) &&
                    datos[4].trim().equals(dpiNew) &&
                    datos[5].trim().equals(nombreNew)) {
                    foundInFile = true;
                    continue; // Skip the line to delete it
                }
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el traspaso del archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            tempFile.delete();
            return false;
        }

        if (!foundInFile) {
            JOptionPane.showMessageDialog(null, "Traspaso no encontrado en el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            tempFile.delete();
            return false;
        }

        if (!inputFile.delete()) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el archivo original.", "Error", JOptionPane.ERROR_MESSAGE);
            tempFile.delete();
            return false;
        }
        if (!tempFile.renameTo(inputFile)) {
            JOptionPane.showMessageDialog(null, "No se pudo renombrar el archivo temporal.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Update vehicle file to decrement traspasos and revert owner
        informacionMT.actualizarVehiculoTraspaso(departamento, placa, dpiAnt, nombreAnt, -1);
        return true;
    }
}