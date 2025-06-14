package proyectoprogra3;

import javax.swing.table.DefaultTableModel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class UtilidadesExportacion {
    private static final String RUTA_EXPORTACIONES = "C:\\Users\\Mayby\\Desktop\\Exportaciones";
    private static final int DESPLAZAMIENTO_CIFRADO = 3; // Desplazamiento para cifrado César
    private boolean datosEncriptados = false;

    // Método para exportar la tabla AVL a un archivo de texto
    public void exportarTablaAVL(DefaultTableModel modelo, boolean encriptado) {
        // Crear la carpeta de exportaciones si no existe
        File carpetaExportaciones = new File(RUTA_EXPORTACIONES);
        if (!carpetaExportaciones.exists()) {
            if (!carpetaExportaciones.mkdirs()) {
                JOptionPane.showMessageDialog(null, "Error al crear la carpeta de exportaciones: " + RUTA_EXPORTACIONES, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Generar nombre de archivo con marca de tiempo
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String nombreArchivo = "Exportacion_AVL_" + ahora.format(formatter) + ".txt";
        String rutaArchivo = RUTA_EXPORTACIONES + "\\" + nombreArchivo;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            // Escribir encabezados
            String[] encabezados = {"Placa", "DPI", "Nombre", "Marca", "Modelo", "Año", "Multas", "Traspasos", "Departamento"};
            for (int i = 0; i < encabezados.length; i++) {
                String valor = encriptado ? encriptar(encabezados[i]) : encabezados[i];
                writer.write(valor);
                if (i < encabezados.length - 1) {
                    writer.write(",");
                }
            }
            writer.newLine();

            // Escribir datos de la tabla
            for (int i = 0; i < modelo.getRowCount(); i++) {
                for (int j = 0; j < modelo.getColumnCount(); j++) {
                    Object valor = modelo.getValueAt(i, j);
                    String valorStr = valor != null ? valor.toString() : "";
                    // Encriptar solo columnas de texto (Placa, DPI, Nombre, Marca, Modelo, Departamento)
                    if (j == 0 || j == 1 || j == 2 || j == 3 || j == 4 || j == 8) {
                        valorStr = encriptado ? encriptar(valorStr) : valorStr;
                    }
                    writer.write(valorStr);
                    if (j < modelo.getColumnCount() - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "Tabla AVL exportada exitosamente a: " + rutaArchivo, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al exportar la tabla AVL: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para encriptar/desencriptar las tablas ABB y AVL
    public void toggleEncriptacionTablas(DefaultTableModel modeloABB, DefaultTableModel modeloAVL) {
        if (!datosEncriptados) {
            // Encriptar datos
            encriptarTabla(modeloABB);
            encriptarTabla(modeloAVL);
            datosEncriptados = true;
            JOptionPane.showMessageDialog(null, "Datos de las tablas ABB y AVL encriptados.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Desencriptar datos
            desencriptarTabla(modeloABB);
            desencriptarTabla(modeloAVL);
            datosEncriptados = false;
            JOptionPane.showMessageDialog(null, "Datos de las tablas ABB y AVL desencriptados.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Método para saber si los datos están encriptados
    public boolean estanDatosEncriptados() {
        return datosEncriptados;
    }

    // Método privado para encriptar una tabla
    private void encriptarTabla(DefaultTableModel modelo) {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            for (int j = 0; j < modelo.getColumnCount(); j++) {
                Object valor = modelo.getValueAt(i, j);
                if (valor != null && (j == 0 || j == 1 || j == 2 || j == 3 || j == 4 || j == 8)) {
                    modelo.setValueAt(encriptar(valor.toString()), i, j);
                }
            }
        }
    }

    // Método privado para desencriptar una tabla
    private void desencriptarTabla(DefaultTableModel modelo) {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            for (int j = 0; j < modelo.getColumnCount(); j++) {
                Object valor = modelo.getValueAt(i, j);
                if (valor != null && (j == 0 || j == 1 || j == 2 || j == 3 || j == 4 || j == 8)) {
                    modelo.setValueAt(desencriptar(valor.toString()), i, j);
                }
            }
        }
    }

    // Método para encriptar un texto usando cifrado César
    private String encriptar(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        StringBuilder resultado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                resultado.append((char) ((c - base + DESPLAZAMIENTO_CIFRADO) % 26 + base));
            } else if (Character.isDigit(c)) {
                // Mantener dígitos sin cambios
                resultado.append(c);
            } else {
                // Mantener otros caracteres (como guiones, espacios) sin cambios
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

    // Método para desencriptar un texto usando cifrado César
    private String desencriptar(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        StringBuilder resultado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                resultado.append((char) ((c - base - DESPLAZAMIENTO_CIFRADO + 26) % 26 + base));
            } else {
                // Mantener otros caracteres sin cambios
                resultado.append(c);
            }
        }
        return resultado.toString();
    }
}