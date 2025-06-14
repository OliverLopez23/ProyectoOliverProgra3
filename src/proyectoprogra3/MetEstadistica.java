package proyectoprogra3;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.File;

public class MetEstadistica {
    private InformacionMT informacionMT;
    private Arboles arbolABB;
    private ArbolesAVL arbolAVL;
    private String rutaCarpeta;

    public MetEstadistica(InformacionMT informacionMT, Arboles arbolABB, ArbolesAVL arbolAVL) {
        this.informacionMT = informacionMT;
        this.arbolABB = arbolABB;
        this.arbolAVL = arbolAVL;
        this.rutaCarpeta = "C:\\Users\\Mayby\\Desktop\\SIRVE_Datos_Vehiculos DataSet - copia";
    }

    public void llenarTablaMultas(JTable jTable1) {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);

        Map<String, VehicleStats> vehicleFines = new HashMap<>();
        
        File carpetaPrincipal = new File(rutaCarpeta);
        File[] subCarpetas = carpetaPrincipal.listFiles(File::isDirectory);
        
        if (subCarpetas != null) {
            for (File subCarpeta : subCarpetas) {
                String departamento = subCarpeta.getName();
                informacionMT.cargarMultas(departamento);
                ListaDobleMultas multas = informacionMT.getMultasPorDepartamento(departamento);
                
                List<Multa> nodosMultas = multas.getAllMultas();
                for (Multa actual : nodosMultas) {
                    String key = actual.getPlaca() + "-" + departamento;
                    VehicleStats stats = vehicleFines.computeIfAbsent(key, k -> new VehicleStats(actual.getPlaca(), departamento));
                    stats.finesCount++;
                    stats.totalAmount += actual.getMonto();
                }
            }
        }

        List<VehicleStats> sortedFines = new ArrayList<>(vehicleFines.values());
        sortedFines.sort((a, b) -> Integer.compare(b.finesCount, a.finesCount));

        for (int i = 0; i < Math.min(10, sortedFines.size()); i++) {
            VehicleStats stats = sortedFines.get(i);
            modelo.addRow(new Object[]{
                i + 1,
                stats.placa,
                stats.finesCount,
                String.format("%.2f", stats.totalAmount),
                stats.departamento
            });
        }
    }

    public void llenarTablaTraspasos(JTable jTable2) {
        DefaultTableModel modelo = (DefaultTableModel) jTable2.getModel();
        modelo.setRowCount(0);

        Map<String, VehicleStats> vehicleTransfers = new HashMap<>();
        
        File carpetaPrincipal = new File(rutaCarpeta);
        File[] subCarpetas = carpetaPrincipal.listFiles(File::isDirectory);
        
        if (subCarpetas != null) {
            for (File subCarpeta : subCarpetas) {
                String departamento = subCarpeta.getName();
                informacionMT.cargarTraspasos(departamento);
                ListaCircularTraspasos traspasos = informacionMT.getTraspasosPorDepartamento(departamento);
                
                List<Traspaso> nodosTraspasos = traspasos.getAllTraspasos();
                for (Traspaso actual : nodosTraspasos) {
                    String key = actual.getPlaca() + "-" + departamento;
                    VehicleStats stats = vehicleTransfers.computeIfAbsent(key, k -> new VehicleStats(actual.getPlaca(), departamento));
                    stats.transfersCount++;
                }
            }
        }

        List<VehicleStats> sortedTransfers = new ArrayList<>(vehicleTransfers.values());
        sortedTransfers.sort((a, b) -> Integer.compare(b.transfersCount, a.transfersCount));

        for (int i = 0; i < Math.min(10, sortedTransfers.size()); i++) {
            VehicleStats stats = sortedTransfers.get(i);
            modelo.addRow(new Object[]{
                i + 1,
                stats.placa,
                stats.transfersCount,
                stats.departamento
            });
        }
    }

    public void encontrarMesConMasMovimientos(JTextField jTextField6, JTextField jTextField7) {
        Map<String, Integer> movimientosPorMes = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        File carpetaPrincipal = new File(rutaCarpeta);
        File[] subCarpetas = carpetaPrincipal.listFiles(File::isDirectory);
        
        if (subCarpetas != null) {
            for (File subCarpeta : subCarpetas) {
                String departamento = subCarpeta.getName();
                
                informacionMT.cargarMultas(departamento);
                ListaDobleMultas multas = informacionMT.getMultasPorDepartamento(departamento);
                for (Multa actualMulta : multas.getAllMultas()) {
                    try {
                        LocalDate fecha = LocalDate.parse(actualMulta.getFecha(), formatter);
                        String mes = fecha.getYear() + "-" + String.format("%02d", fecha.getMonthValue());
                        movimientosPorMes.merge(mes, 1, Integer::sum);
                    } catch (Exception e) {
                        // Skip invalid dates
                    }
                }

                informacionMT.cargarTraspasos(departamento);
                ListaCircularTraspasos traspasos = informacionMT.getTraspasosPorDepartamento(departamento);
                for (Traspaso actualTraspaso : traspasos.getAllTraspasos()) {
                    try {
                        LocalDate fecha = LocalDate.parse(actualTraspaso.getFecha(), formatter);
                        String mes = fecha.getYear() + "-" + String.format("%02d", fecha.getMonthValue());
                        movimientosPorMes.merge(mes, 1, Integer::sum);
                    } catch (Exception e) {
                        // Skip invalid dates
                    }
                }
            }
        }

        String mesMax = null;
        int maxMovimientos = 0;
        for (Map.Entry<String, Integer> entry : movimientosPorMes.entrySet()) {
            if (entry.getValue() > maxMovimientos) {
                mesMax = entry.getKey();
                maxMovimientos = entry.getValue();
            }
        }

        if (mesMax != null) {
            jTextField6.setText(mesMax);
            jTextField7.setText(String.valueOf(maxMovimientos));
        } else {
            jTextField6.setText("No hay datos");
            jTextField7.setText("0");
        }
    }

    public void calcularTiemposPromedio(JTextField jTextField8, JTextField jTextField9, JTextField jTextField10, JTextField jTextField11) {
        List<Long> tiemposABB = new ArrayList<>();
        List<Long> tiemposAVL = new ArrayList<>();
        Random random = new Random();
        
        List<String> placas = new ArrayList<>();
        // Definir columnas para el DefaultTableModel
        String[] columnas = {"Placa", "DPI", "Nombre", "Marca", "Modelo", "Año", "Multas", "Traspasos", "Departamento"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        arbolABB.inOrden(arbolABB.getRaiz(), modelo);
        
        // Verificar si el modelo tiene filas antes de acceder
        if (modelo.getRowCount() > 0) {
            for (int i = 0; i < modelo.getRowCount(); i++) {
                placas.add((String) modelo.getValueAt(i, 0));
            }
        }

        // Si no hay placas, establecer valores N/A y salir
        if (placas.isEmpty()) {
            jTextField8.setText("N/A");
            jTextField9.setText("N/A");
            jTextField10.setText("N/A");
            jTextField11.setText("N/A");
            return;
        }

        int numBusquedas = 100;
        for (int i = 0; i < numBusquedas; i++) {
            String placa = placas.get(random.nextInt(placas.size()));
            
            long startTimeABB = System.nanoTime();
            arbolABB.buscarPorPlaca(placa);
            long endTimeABB = System.nanoTime();
            tiemposABB.add(endTimeABB - startTimeABB);
            
            long startTimeAVL = System.nanoTime();
            arbolAVL.buscarPorPlaca(placa);
            long endTimeAVL = System.nanoTime();
            tiemposAVL.add(endTimeAVL - startTimeAVL);
        }

        double maxABB = tiemposABB.stream().mapToLong(Long::longValue).max().orElse(0) / 1000.0; // µs
        double minABB = tiemposABB.stream().mapToLong(Long::longValue).min().orElse(0) / 1000.0; // µs
        double avgABB = tiemposABB.stream().mapToLong(Long::longValue).average().orElse(0) / 1000.0; // µs
        double avgAVL = tiemposAVL.stream().mapToLong(Long::longValue).average().orElse(0) / 1000.0; // µs
        
        jTextField8.setText(String.format("%.2f µs", maxABB));
        jTextField9.setText(String.format("%.2f µs", minABB));
        jTextField10.setText(String.format("%.2f µs", avgABB));
        jTextField11.setText(String.format("%.2f µs", avgAVL));
    }

    private static class VehicleStats {
        String placa;
        String departamento;
        int finesCount;
        double totalAmount;
        int transfersCount;

        VehicleStats(String placa, String departamento) {
            this.placa = placa;
            this.departamento = departamento;
            this.finesCount = 0;
            this.totalAmount = 0.0;
            this.transfersCount = 0;
        }
    }
}