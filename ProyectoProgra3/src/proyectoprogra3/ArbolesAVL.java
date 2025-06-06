package proyectoprogra3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;

public class ArbolesAVL {
    private NodoVehiculo raiz;

    public ArbolesAVL() {
        raiz = null;
    }

    // Método para obtener la altura de un nodo
    private int getAltura(NodoVehiculo nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.altura;
    }

    // Método para actualizar la altura de un nodo
    private void actualizarAltura(NodoVehiculo nodo) {
        nodo.altura = Math.max(getAltura(nodo.hijoIzquierdo), getAltura(nodo.hijoDerecho)) + 1;
    }

    // Método para obtener el factor de balance
    private int getFactorBalance(NodoVehiculo nodo) {
        if (nodo == null) {
            return 0;
        }
        return getAltura(nodo.hijoIzquierdo) - getAltura(nodo.hijoDerecho);
    }

    // Rotación a la derecha
    private NodoVehiculo rotacionDerecha(NodoVehiculo y) {
        NodoVehiculo x = y.hijoIzquierdo;
        NodoVehiculo T2 = x.hijoDerecho;

        x.hijoDerecho = y;
        y.hijoIzquierdo = T2;

        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }

    // Rotación a la izquierda
    private NodoVehiculo rotacionIzquierda(NodoVehiculo x) {
        NodoVehiculo y = x.hijoDerecho;
        NodoVehiculo T2 = y.hijoIzquierdo;

        y.hijoIzquierdo = x;
        x.hijoDerecho = T2;

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }

    // Método para insertar un nodo recursivamente
    private NodoVehiculo insertarNodoRecursivo(NodoVehiculo nodo, String placa, String dpi, String nombre, 
                                              String marca, String modelo, int ano, int multas, 
                                              int traspasos, String departamento) {
        if (nodo == null) {
            return new NodoVehiculo(placa, dpi, nombre, marca, modelo, ano, multas, traspasos, departamento);
        }

        if (placa.compareTo(nodo.placa) < 0) {
            nodo.hijoIzquierdo = insertarNodoRecursivo(nodo.hijoIzquierdo, placa, dpi, nombre, marca, 
                                                       modelo, ano, multas, traspasos, departamento);
        } else if (placa.compareTo(nodo.placa) > 0) {
            nodo.hijoDerecho = insertarNodoRecursivo(nodo.hijoDerecho, placa, dpi, nombre, marca, 
                                                     modelo, ano, multas, traspasos, departamento);
        } else {
            // Handle duplicate placa (skip for now)
            return nodo;
        }

        // Actualizar altura
        actualizarAltura(nodo);

        // Calcular factor de balance
        int balance = getFactorBalance(nodo);

        // Left-Left Case
        if (balance > 1 && placa.compareTo(nodo.hijoIzquierdo.placa) < 0) {
            return rotacionDerecha(nodo);
        }
        // Right-Right Case
        if (balance < -1 && placa.compareTo(nodo.hijoDerecho.placa) > 0) {
            return rotacionIzquierda(nodo);
        }
        // Left-Right Case
        if (balance > 1 && placa.compareTo(nodo.hijoIzquierdo.placa) > 0) {
            nodo.hijoIzquierdo = rotacionIzquierda(nodo.hijoIzquierdo);
            return rotacionDerecha(nodo);
        }
        // Right-Left Case
        if (balance < -1 && placa.compareTo(nodo.hijoDerecho.placa) < 0) {
            nodo.hijoDerecho = rotacionDerecha(nodo.hijoDerecho);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    // Método público para insertar un nodo
    public void insertarNodo(String placa, String dpi, String nombre, String marca, String modelo, 
                             int ano, int multas, int traspasos, String departamento) {
        raiz = insertarNodoRecursivo(raiz, placa, dpi, nombre, marca, modelo, ano, multas, traspasos, departamento);
    }

    // Método para verificar si el árbol está vacío
    public boolean estaVacio() {
        return raiz == null;
    }

    // Recorrido preorden
    public void preOrden(NodoVehiculo r, DefaultTableModel modelo) {
        if (r != null) {
            modelo.addRow(new Object[]{r.placa, r.dpi, r.nombre, r.marca, r.modelo, 
                                      r.ano, r.multas, r.traspasos, r.departamento});
            preOrden(r.hijoIzquierdo, modelo);
            preOrden(r.hijoDerecho, modelo);
        }
    }

    // Recorrido inorden
    public void inOrden(NodoVehiculo r, DefaultTableModel modelo) {
        if (r != null) {
            inOrden(r.hijoIzquierdo, modelo);
            modelo.addRow(new Object[]{r.placa, r.dpi, r.nombre, r.marca, r.modelo, 
                                      r.ano, r.multas, r.traspasos, r.departamento});
            inOrden(r.hijoDerecho, modelo);
        }
    }

    // Recorrido postorden
    public void postOrden(NodoVehiculo r, DefaultTableModel modelo) {
        if (r != null) {
            postOrden(r.hijoIzquierdo, modelo);
            postOrden(r.hijoDerecho, modelo);
            modelo.addRow(new Object[]{r.placa, r.dpi, r.nombre, r.marca, r.modelo, 
                                      r.ano, r.multas, r.traspasos, r.departamento});
        }
    }

    // Método para leer archivos <departamento>_vehiculos.txt desde la carpeta principal y subcarpetas
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

    // Método para obtener la raíz
    public NodoVehiculo getRaiz() {
        return raiz;
    }
}