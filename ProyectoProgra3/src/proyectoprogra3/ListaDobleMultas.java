package proyectoprogra3;

import javax.swing.table.DefaultTableModel;

public class ListaDobleMultas {
    private NodoMulta cabeza;
    private NodoMulta cola;

    // Nodo interno para la lista doblemente enlazada
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
        cabeza = null;
        cola = null;
    }

    // Insertar una multa al final de la lista
    public void insertar(String placa, String fecha, String descripcion, double monto) {
        NodoMulta nuevo = new NodoMulta(placa, fecha, descripcion, monto);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            nuevo.anterior = cola;
            cola.siguiente = nuevo;
            cola = nuevo;
        }
    }

    // Obtener multas para una placa específica y actualizar la tabla y campos
    public void obtenerMultasPorPlaca(String placa, DefaultTableModel modelo, javax.swing.JTextField totalMultasField, javax.swing.JTextField sumaMontosField) {
        modelo.setRowCount(0); // Limpiar la tabla
        int totalMultas = 0;
        double sumaMontos = 0.0;
        NodoMulta actual = cabeza;

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