package proyectoprogra3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class InformacionMT {
    private String rutaCarpetaPrincipal;
    private HashMap<String, ListaDobleMultas> multasPorDepartamento;
    private HashMap<String, ListaCircularTraspasos> traspasosPorDepartamento;

    public InformacionMT(String rutaCarpetaPrincipal) {
        this.rutaCarpetaPrincipal = rutaCarpetaPrincipal;
        this.multasPorDepartamento = new HashMap<>();
        this.traspasosPorDepartamento = new HashMap<>();
    }

    // Método para leer un vehículo específico por placa
    public void leerVehiculoPorPlaca(String departamento, String placa, DefaultTableModel modelo) {
        String rutaArchivo = rutaCarpetaPrincipal + "\\" + departamento + "\\" + departamento + "_vehiculos.txt";
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            JOptionPane.showMessageDialog(null, "El archivo " + departamento + "_vehiculos.txt no existe en: " + rutaArchivo);
            return;
        }

        modelo.setRowCount(0); // Limpiar la tabla
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 8 && datos[0].trim().equals(placa)) {
                    Object[] fila = new Object[9];
                    System.arraycopy(datos, 0, fila, 0, 8);
                    fila[8] = departamento;
                    modelo.addRow(fila);
                    break; // Salir tras encontrar la placa
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo " + archivo.getName() + ": " + e.getMessage());
        }
    }

    // Método para leer el archivo <departamento>_multas.txt y cargar en la lista doble
    private void cargarMultas(String departamento) {
        if (multasPorDepartamento.containsKey(departamento)) {
            return; // Ya cargado
        }

        ListaDobleMultas listaMultas = new ListaDobleMultas();
        String rutaArchivo = rutaCarpetaPrincipal + "\\" + departamento + "\\" + departamento + "_multas.txt";
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            JOptionPane.showMessageDialog(null, "El archivo " + departamento + "_multas.txt no existe en: " + rutaArchivo);
            multasPorDepartamento.put(departamento, listaMultas);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    try {
                        double monto = Double.parseDouble(datos[3].trim());
                        listaMultas.insertar(datos[0].trim(), datos[1].trim(), datos[2].trim(), monto);
                    } catch (NumberFormatException e) {
                        System.err.println("Monto inválido en " + archivo.getName() + ": " + datos[3]);
                        JOptionPane.showMessageDialog(null, "Monto inválido en " + archivo.getName() + ": " + datos[3]);
                    }
                } else {
                    System.err.println("Línea inválida en " + archivo.getName() + ": " + linea);
                    JOptionPane.showMessageDialog(null, "Línea inválida en " + archivo.getName() + ": " + linea);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo " + archivo.getName() + ": " + e.getMessage());
        }

        multasPorDepartamento.put(departamento, listaMultas);
    }

    // Método para leer el archivo <departamento>_traspasos.txt y cargar en la lista circular
    private void cargarTraspasos(String departamento) {
        if (traspasosPorDepartamento.containsKey(departamento)) {
            return; // Ya cargado
        }

        ListaCircularTraspasos listaTraspasos = new ListaCircularTraspasos();
        String rutaArchivo = rutaCarpetaPrincipal + "\\" + departamento + "\\" + departamento + "_traspasos.txt";
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            JOptionPane.showMessageDialog(null, "El archivo " + departamento + "_traspasos.txt no existe en: " + rutaArchivo);
            traspasosPorDepartamento.put(departamento, listaTraspasos);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 6) {
                    listaTraspasos.insertar(datos[0].trim(), datos[1].trim(), datos[2].trim(), 
                                            datos[3].trim(), datos[4].trim(), datos[5].trim());
                } else {
                    System.err.println("Línea inválida en " + archivo.getName() + ": " + linea);
                    JOptionPane.showMessageDialog(null, "Línea inválida en " + archivo.getName() + ": " + linea);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo " + archivo.getName() + ": " + e.getMessage());
        }

        traspasosPorDepartamento.put(departamento, listaTraspasos);
    }

    // Método para obtener multas de una placa desde la lista doble
    public void leerArchivoMultas(String departamento, String placa, DefaultTableModel modelo, javax.swing.JTextField totalMultasField, javax.swing.JTextField sumaMontosField) {
        cargarMultas(departamento);
        ListaDobleMultas listaMultas = multasPorDepartamento.get(departamento);
        listaMultas.obtenerMultasPorPlaca(placa, modelo, totalMultasField, sumaMontosField);
    }

    // Método para obtener traspasos de una placa desde la lista circular
    public void leerArchivoTraspasos(String departamento, String placa, DefaultTableModel modelo, javax.swing.JTextField totalTraspasosField) {
        cargarTraspasos(departamento);
        ListaCircularTraspasos listaTraspasos = traspasosPorDepartamento.get(departamento);
        listaTraspasos.obtenerTraspasosPorPlaca(placa, modelo, totalTraspasosField);
    }
}