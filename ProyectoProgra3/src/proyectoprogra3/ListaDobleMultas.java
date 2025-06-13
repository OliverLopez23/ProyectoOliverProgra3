package proyectoprogra3;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ListaDobleMultas {
    private NodoMulta primero;
    private NodoMulta ultimo;

    private class NodoMulta {
        String placa;
        String fecha;
        String descripcion;
        double monto;
        NodoMulta siguiente;
        NodoMulta anterior;

        NodoMulta(String placa, String fecha, String descripcion, double monto) {
            this.placa = placa;
            this.fecha = fecha;
            this.descripcion = descripcion;
            this.monto = monto;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    public ListaDobleMultas() {
        primero = null;
        ultimo = null;
    }

    public void insertar(String placa, String fecha, String descripcion, double monto) {
        NodoMulta nuevo = new NodoMulta(placa, fecha, descripcion, monto);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            nuevo.siguiente = primero;
            primero.anterior = nuevo;
            primero = nuevo;
        }
    }

    public boolean guardarMulta(String placa, String fecha, String descripcion, String montoStr, String departamento, InformacionMT informacionMT) {
        try {
            double monto = Double.parseDouble(montoStr.trim());
            if (fecha.isEmpty() || descripcion.isEmpty() || montoStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            String rutaArchivo = "C:\\Users\\Mayby\\Desktop\\SIRVE_Datos_Vehiculos DataSet - copia\\" + 
                                departamento + "\\" + departamento + "_multas.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
                writer.write(String.format("%s,%s,%s,%.2f%n", placa, fecha, descripcion, monto));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al guardar la multa: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            insertar(placa, fecha, descripcion, monto);
            informacionMT.actualizarVehiculoMulta(departamento, placa, 1); // Incrementar multas
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Monto inválido: " + montoStr, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean eliminarMulta(String placa, String fecha, String descripcion, String montoStr, String departamento, InformacionMT informacionMT) {
        try {
            double monto = Double.parseDouble(montoStr.trim());

            // Remove from in-memory list
            NodoMulta actual = primero;
            boolean foundInMemory = false;
            while (actual != null) {
                if (actual.placa.equals(placa) && 
                    actual.fecha.equals(fecha) && 
                    actual.descripcion.equals(descripcion) && 
                    Math.abs(actual.monto - monto) < 0.01) {
                    foundInMemory = true;
                    if (actual.anterior != null) {
                        actual.anterior.siguiente = actual.siguiente;
                    } else {
                        primero = actual.siguiente;
                    }
                    if (actual.siguiente != null) {
                        actual.siguiente.anterior = actual.anterior;
                    } else {
                        ultimo = actual.anterior;
                    }
                    break;
                }
                actual = actual.siguiente;
            }

            // Remove from file
            String rutaArchivo = "C:\\Users\\Mayby\\Desktop\\SIRVE_Datos_Vehiculos DataSet - copia\\" + 
                                departamento + "\\" + departamento + "_multas.txt";
            File inputFile = new File(rutaArchivo);
            File tempFile = new File(rutaArchivo + ".tmp");

            if (!inputFile.exists()) {
                JOptionPane.showMessageDialog(null, "El archivo de multas no existe: " + rutaArchivo, "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            boolean foundInFile = false;
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] datos = line.split(",");
                    if (datos.length == 4 && 
                        datos[0].trim().equals(placa) &&
                        datos[1].trim().equals(fecha) &&
                        datos[2].trim().equals(descripcion) &&
                        Math.abs(Double.parseDouble(datos[3].trim()) - monto) < 0.01) {
                        foundInFile = true;
                        continue; // Skip the line to delete it
                    }
                    writer.write(line + "\n");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar la multa del archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                tempFile.delete();
                return false;
            }

            if (!foundInFile) {
                JOptionPane.showMessageDialog(null, "Multa no encontrada en el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
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

            informacionMT.actualizarVehiculoMulta(departamento, placa, -1); // Decrementar multas
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Monto inválido: " + montoStr, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void obtenerMultasPorPlaca(String placa, DefaultTableModel modelo, javax.swing.JTextField totalMultasField, javax.swing.JTextField sumaMontosField) {
        modelo.setRowCount(0);
        int totalMultas = 0;
        double sumaMontos = 0.0;
        NodoMulta actual = primero;
        while (actual != null) {
            if (actual.placa.trim().equals(placa)) {
                modelo.addRow(new Object[]{actual.placa, actual.fecha, actual.descripcion, actual.monto});
                totalMultas++;
                sumaMontos += actual.monto;
            }
            actual = actual.siguiente;
        }
        totalMultasField.setText(String.valueOf(totalMultas));
        sumaMontosField.setText(String.format("%.2f", sumaMontos));
    }
}