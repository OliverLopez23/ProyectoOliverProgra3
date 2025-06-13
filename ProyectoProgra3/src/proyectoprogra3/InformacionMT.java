package proyectoprogra3;

import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

public class InformacionMT {
    private String rutaCarpetaPrincipal;
    private HashMap<String, ListaDobleMultas> multasPorDepartamento;
    private HashMap<String, ListaCircularTraspasos> traspasosPorDepartamento;

    public InformacionMT(String rutaCarpetaPrincipal) {
        this.rutaCarpetaPrincipal = rutaCarpetaPrincipal;
        this.multasPorDepartamento = new HashMap<>();
        this.traspasosPorDepartamento = new HashMap<>();
    }

    public void leerVehiculoPorPlaca(String departamento, String placa, DefaultTableModel modelo) {
        String rutaArchivo = rutaCarpetaPrincipal + "\\" + departamento + "\\" + departamento + "_vehiculos.txt";
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            JOptionPane.showMessageDialog(null, "El archivo " + departamento + "_vehiculos.txt no existe en: " + rutaArchivo);
            return;
        }

        modelo.setRowCount(0);
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 8 && datos[0].trim().equals(placa)) {
                    Object[] fila = new Object[9];
                    System.arraycopy(datos, 0, fila, 0, 8);
                    fila[8] = departamento;
                    modelo.addRow(fila);
                    break;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo " + archivo.getName() + ": " + e.getMessage());
        }
    }

    public boolean existePlaca(String departamento, String placa) {
        String rutaArchivo = rutaCarpetaPrincipal + "\\" + departamento + "\\" + departamento + "_vehiculos.txt";
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 8 && datos[0].trim().equals(placa)) {
                    return true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo " + archivo.getName() + ": " + e.getMessage());
        }
        return false;
    }

    public boolean guardarVehiculo(String departamento, String placa, String dpi, String nombre, String marca, String modelo, int ano) {
        String rutaArchivo = rutaCarpetaPrincipal + "\\" + departamento + "\\" + departamento + "_vehiculos.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            writer.write(String.format("%s,%s,%s,%s,%s,%d,%d,%d%n", placa, dpi, nombre, marca, modelo, ano, 0, 0));
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el vehículo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void cargarMultas(String departamento) {
        if (multasPorDepartamento.containsKey(departamento)) {
            return;
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

    public void cargarTraspasos(String departamento) {
        if (traspasosPorDepartamento.containsKey(departamento)) {
            return;
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

    public void leerArchivoMultas(String departamento, String placa, DefaultTableModel modelo, javax.swing.JTextField totalMultasField, javax.swing.JTextField sumaMontosField) {
        cargarMultas(departamento);
        ListaDobleMultas listaMultas = multasPorDepartamento.get(departamento);
        listaMultas.obtenerMultasPorPlaca(placa, modelo, totalMultasField, sumaMontosField);
    }

    public void leerArchivoTraspasos(String departamento, String placa, DefaultTableModel modelo, javax.swing.JTextField totalTraspasosField) {
        cargarTraspasos(departamento);
        ListaCircularTraspasos listaTraspasos = traspasosPorDepartamento.get(departamento);
        listaTraspasos.obtenerTraspasosPorPlaca(placa, modelo, totalTraspasosField);
    }

    public void actualizarVehiculoTraspaso(String departamento, String placa, String dpi, String nombre, int deltaTraspasos) {
        String rutaArchivo = rutaCarpetaPrincipal + "\\" + departamento + "\\" + departamento + "_vehiculos.txt";
        File inputFile = new File(rutaArchivo);
        File tempFile = new File(rutaArchivo + ".tmp");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                if (datos.length == 8 && datos[0].trim().equals(placa)) {
                    found = true;
                    int traspasos = Integer.parseInt(datos[7].trim()) + deltaTraspasos;
                    if (traspasos < 0) traspasos = 0; // Evitar conteos negativos
                    String newDpi = (dpi != null) ? dpi : datos[1].trim();
                    String newNombre = (nombre != null) ? nombre : datos[2].trim();
                    writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%d%n",
                        datos[0].trim(), newDpi, newNombre, datos[3].trim(), datos[4].trim(),
                        datos[5].trim(), datos[6].trim(), traspasos));
                } else {
                    writer.write(line + "\n");
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(null, "Vehículo no encontrado para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el vehículo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            tempFile.delete();
            return;
        }

        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el archivo de vehículos.", "Error", JOptionPane.ERROR_MESSAGE);
            tempFile.delete();
        }
    }

    public void actualizarVehiculoMulta(String departamento, String placa, int deltaMultas) {
        String rutaArchivo = rutaCarpetaPrincipal + "\\" + departamento + "\\" + departamento + "_vehiculos.txt";
        File inputFile = new File(rutaArchivo);
        File tempFile = new File(rutaArchivo + ".tmp");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                if (datos.length == 8 && datos[0].trim().equals(placa)) {
                    found = true;
                    int multas = Integer.parseInt(datos[6].trim()) + deltaMultas;
                    if (multas < 0) multas = 0; // Evitar conteos negativos
                    writer.write(String.format("%s,%s,%s,%s,%s,%s,%d,%s%n",
                        datos[0].trim(), datos[1].trim(), datos[2].trim(), datos[3].trim(),
                        datos[4].trim(), datos[5].trim(), multas, datos[7].trim()));
                } else {
                    writer.write(line + "\n");
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(null, "Vehículo no encontrado para actualizar multas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el vehículo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            tempFile.delete();
            return;
        }

        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el archivo de vehículos.", "Error", JOptionPane.ERROR_MESSAGE);
            tempFile.delete();
        }
    }

    public boolean eliminarVehiculoYRegistros(String departamento, String placa) {
        // Eliminar vehículo del archivo _vehiculos.txt
        String rutaVehiculos = rutaCarpetaPrincipal + "\\" + departamento + "\\" + departamento + "_vehiculos.txt";
        File inputVehiculos = new File(rutaVehiculos);
        File tempVehiculos = new File(rutaVehiculos + ".tmp");
        boolean foundVehiculo = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputVehiculos));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempVehiculos))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                if (datos.length == 8 && datos[0].trim().equals(placa)) {
                    foundVehiculo = true;
                    continue; // Skip the line to delete it
                }
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el vehículo del archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            tempVehiculos.delete();
            return false;
        }

        if (!foundVehiculo) {
            JOptionPane.showMessageDialog(null, "Vehículo no encontrado en el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            tempVehiculos.delete();
            return false;
        }

        if (!inputVehiculos.delete() || !tempVehiculos.renameTo(inputVehiculos)) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el archivo de vehículos.", "Error", JOptionPane.ERROR_MESSAGE);
            tempVehiculos.delete();
            return false;
        }

        // Eliminar multas del archivo _multas.txt
        String rutaMultas = rutaCarpetaPrincipal + "\\" + departamento + "\\" + departamento + "_multas.txt";
        File inputMultas = new File(rutaMultas);
        File tempMultas = new File(rutaMultas + ".tmp");
        boolean multasExist = inputMultas.exists();

        if (multasExist) {
            try (BufferedReader reader = new BufferedReader(new FileReader(inputMultas));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(tempMultas))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] datos = line.split(",");
                    if (datos.length == 4 && datos[0].trim().equals(placa)) {
                        continue; // Skip the line to delete it
                    }
                    writer.write(line + "\n");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar las multas del archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                tempMultas.delete();
                return false;
            }

            if (!inputMultas.delete() || !tempMultas.renameTo(inputMultas)) {
                JOptionPane.showMessageDialog(null, "Error al actualizar el archivo de multas.", "Error", JOptionPane.ERROR_MESSAGE);
                tempMultas.delete();
                return false;
            }
        }

        // Eliminar traspasos del archivo _traspasos.txt
        String rutaTraspasos = rutaCarpetaPrincipal + "\\" + departamento + "\\" + departamento + "_traspasos.txt";
        File inputTraspasos = new File(rutaTraspasos);
        File tempTraspasos = new File(rutaTraspasos + ".tmp");
        boolean traspasosExist = inputTraspasos.exists();

        if (traspasosExist) {
            try (BufferedReader reader = new BufferedReader(new FileReader(inputTraspasos));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(tempTraspasos))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] datos = line.split(",");
                    if (datos.length == 6 && datos[0].trim().equals(placa)) {
                        continue; // Skip the line to delete it
                    }
                    writer.write(line + "\n");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar los traspasos del archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                tempTraspasos.delete();
                return false;
            }

            if (!inputTraspasos.delete() || !tempTraspasos.renameTo(inputTraspasos)) {
                JOptionPane.showMessageDialog(null, "Error al actualizar el archivo de traspasos.", "Error", JOptionPane.ERROR_MESSAGE);
                tempTraspasos.delete();
                return false;
            }
        }

        // Limpiar cachés en memoria
        multasPorDepartamento.remove(departamento);
        traspasosPorDepartamento.remove(departamento);

        return true;
    }

    public ListaDobleMultas getMultasPorDepartamento(String departamento) {
        cargarMultas(departamento);
        return multasPorDepartamento.get(departamento);
    }

    public ListaCircularTraspasos getTraspasosPorDepartamento(String departamento) {
        cargarTraspasos(departamento);
        return traspasosPorDepartamento.get(departamento);
    }
}