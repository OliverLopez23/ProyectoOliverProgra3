package proyectoprogra3;

import javax.swing.table.DefaultTableModel;

public class ListaCircularTraspasos {
    private NodoTraspaso ultimo;

    // Nodo interno para la lista circular
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

    // Insertar un traspaso en la lista circular
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

    // Obtener traspasos para una placa específica y actualizar la tabla y campo
    public void obtenerTraspasosPorPlaca(String placa, DefaultTableModel modelo, javax.swing.JTextField totalTraspasosField) {
        modelo.setRowCount(0); // Limpiar la tabla
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
}