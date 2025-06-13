package proyectoprogra3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

public class Arboles {
    private NodoVehiculo raiz;

    public Arboles() {
        raiz = null;
    }

    public void insertarNodo(String placa, String dpi, String nombre, String marca, String modelo, 
                            int ano, int multas, int traspasos, String departamento) {
        NodoVehiculo nuevo = new NodoVehiculo(placa, dpi, nombre, marca, modelo, ano, multas, traspasos, departamento);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            NodoVehiculo auxiliar = raiz;
            NodoVehiculo padre;
            while (true) {
                padre = auxiliar;
                if (placa.compareTo(auxiliar.placa) < 0) {
                    auxiliar = auxiliar.hijoIzquierdo;
                    if (auxiliar == null) {
                        padre.hijoIzquierdo = nuevo;
                        return;
                    }
                } else {
                    auxiliar = auxiliar.hijoDerecho;
                    if (auxiliar == null) {
                        padre.hijoDerecho = nuevo;
                        return;
                    }
                }
            }
        }
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public void preOrden(NodoVehiculo r, DefaultTableModel modelo) {
        if (r != null) {
            modelo.addRow(new Object[]{r.placa, r.dpi, r.nombre, r.marca, r.modelo, 
                                      r.ano, r.multas, r.traspasos, r.departamento});
            preOrden(r.hijoIzquierdo, modelo);
            preOrden(r.hijoDerecho, modelo);
        }
    }

    public void inOrden(NodoVehiculo r, DefaultTableModel modelo) {
        if (r != null) {
            inOrden(r.hijoIzquierdo, modelo);
            modelo.addRow(new Object[]{r.placa, r.dpi, r.nombre, r.marca, r.modelo, 
                                      r.ano, r.multas, r.traspasos, r.departamento});
            inOrden(r.hijoDerecho, modelo);
        }
    }

    public void postOrden(NodoVehiculo r, DefaultTableModel modelo) {
        if (r != null) {
            postOrden(r.hijoIzquierdo, modelo);
            postOrden(r.hijoDerecho, modelo);
            modelo.addRow(new Object[]{r.placa, r.dpi, r.nombre, r.marca, r.modelo, 
                                      r.ano, r.multas, r.traspasos, r.departamento});
        }
    }

    public void leerArchivos(String rutaCarpeta) {
        File carpetaPrincipal = new File(rutaCarpeta);
        if (!carpetaPrincipal.exists() || !carpetaPrincipal.isDirectory()) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "La carpeta principal no existe o no es un directorio: " + rutaCarpeta);
            return;
        }

        File[] subCarpetas = carpetaPrincipal.listFiles(File::isDirectory);
        if (subCarpetas == null || subCarpetas.length == 0) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "No se encontraron subcarpetas en: " + rutaCarpeta);
            return;
        }

        for (File subCarpeta : subCarpetas) {
            String nombreDepartamento = subCarpeta.getName();
            String rutaArchivo = subCarpeta.getPath() + "\\" + nombreDepartamento + "_vehiculos.txt";
            File archivo = new File(rutaArchivo);
            if (!archivo.exists()) {
                javax.swing.JOptionPane.showMessageDialog(null, 
                    "El archivo " + nombreDepartamento + "_vehiculos.txt no existe en: " + subCarpeta.getPath());
                continue;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length == 8) {
                        try {
                            String placa = datos[0].trim();
                            String dpi = datos[1].trim();
                            String nombre = datos[2].trim();
                            String marca = datos[3].trim();
                            String modelo = datos[4].trim();
                            int ano = Integer.parseInt(datos[5].trim());
                            int multas = Integer.parseInt(datos[6].trim());
                            int traspasos = Integer.parseInt(datos[7].trim());
                            String departamento = nombreDepartamento;
                            insertarNodo(placa, dpi, nombre, marca, modelo, ano, multas, traspasos, departamento);
                        } catch (NumberFormatException e) {
                            javax.swing.JOptionPane.showMessageDialog(null, 
                                "Error de formato numérico en la línea: " + linea + " en " + archivo.getName());
                        }
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(null, 
                            "Línea inválida en " + archivo.getName() + ": " + linea);
                    }
                }
            } catch (IOException e) {
                javax.swing.JOptionPane.showMessageDialog(null, 
                    "Error al leer el archivo " + archivo.getName() + ": " + e.getMessage());
            }
        }
    }

    public NodoVehiculo getRaiz() {
        return raiz;
    }

    // Método para buscar un vehículo por placa
    public NodoVehiculo buscarPorPlaca(String placa) {
        NodoVehiculo actual = raiz;
        while (actual != null) {
            int comparacion = placa.compareTo(actual.placa);
            if (comparacion == 0) {
                return actual;
            } else if (comparacion < 0) {
                actual = actual.hijoIzquierdo;
            } else {
                actual = actual.hijoDerecho;
            }
        }
        return null;
    }
}