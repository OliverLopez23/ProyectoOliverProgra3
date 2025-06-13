package proyectoprogra3;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class Informacion extends javax.swing.JFrame {
    private static final String RUTA_CARPETA_PRINCIPAL = "C:\\Users\\Mayby\\Desktop\\SIRVE_Datos_Vehiculos DataSet - copia";
    private InformacionMT informacionMT;
    private String currentPlaca;
    private String currentDepartamento;

    public Informacion() {
        initComponents();
        informacionMT = new InformacionMT(RUTA_CARPETA_PRINCIPAL);
        setLocationRelativeTo(null);
    }

    public void cargarDatos(String placa, String departamento) {
        this.currentPlaca = placa;
        this.currentDepartamento = departamento;
        jLabel2.setText("Vehículo: " + placa + " - " + departamento);
        refreshTables();
        
    }

    private void refreshTables() {
        DefaultTableModel modeloVehiculo = (DefaultTableModel) TablaABB.getModel();
        DefaultTableModel modeloMultas = (DefaultTableModel) jTable2.getModel();
        DefaultTableModel modeloTraspasos = (DefaultTableModel) jTable3.getModel();
        
        modeloVehiculo.setRowCount(0);
        modeloMultas.setRowCount(0);
        modeloTraspasos.setRowCount(0);
        
        informacionMT.leerVehiculoPorPlaca(currentDepartamento, currentPlaca, modeloVehiculo);
        informacionMT.leerArchivoMultas(currentDepartamento, currentPlaca, modeloMultas, jTextField1, jTextField2);
        informacionMT.leerArchivoTraspasos(currentDepartamento, currentPlaca, modeloTraspasos, jTextField3);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Regresar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaABB = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Multas ");

        jLabel2.setText("Traspasos");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Placa", "Fecha", "Descripcion", "Monto"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Placa", "DPI_Ant", "Nombre_Ant", "Fecha", "DPI_New", "Nombre_New"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setText("jTextField2");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setText("jTextField3");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel3.setText("Cantidad de Multas");

        jLabel4.setText("Cantidad total de monto ");

        jLabel5.setText("Cantidad de Traspasos");

        Regresar.setText("Regresar");
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });

        TablaABB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Placa", "DPI", "Nombre", "Marca", "Modelo", "Año", "Multas", "Traspasos", "Departamento"
            }
        ));
        jScrollPane1.setViewportView(TablaABB);

        jLabel6.setText("Vehiculo");

        jButton1.setText("Agregar Multas");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Agregar Traspasos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Eliminar Multas");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Eliminar Traspasos");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Regresar)
                            .addComponent(jLabel4)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jLabel3)
                            .addComponent(jButton3)
                            .addComponent(jButton4))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(5, 5, 5)
                        .addComponent(jButton3)
                        .addGap(28, 28, 28)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Regresar)))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (currentPlaca == null || currentDepartamento == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un vehículo primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        informacionMT.cargarMultas(currentDepartamento);
        ListaDobleMultas listaMultas = informacionMT.getMultasPorDepartamento(currentDepartamento);
        Multas newMultas = new Multas(currentPlaca, currentDepartamento, listaMultas);
        newMultas.setVisible(true);
        newMultas.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                refreshTables();
            }
        });
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (currentPlaca == null || currentDepartamento == null) {
        JOptionPane.showMessageDialog(this, "Seleccione un vehículo primero.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Obtener DPI y Nombre de la tabla TablaABB
    DefaultTableModel modeloVehiculo = (DefaultTableModel) TablaABB.getModel();
    String dpiAnt = "";
    String nombreAnt = "";
    if (modeloVehiculo.getRowCount() > 0) {
        dpiAnt = modeloVehiculo.getValueAt(0, 1).toString().trim(); // DPI está en la columna 1
        nombreAnt = modeloVehiculo.getValueAt(0, 2).toString().trim(); // Nombre está en la columna 2
    } else {
        JOptionPane.showMessageDialog(this, "No se encontraron datos del vehículo.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    informacionMT.cargarTraspasos(currentDepartamento);
    ListaCircularTraspasos listaTraspasos = informacionMT.getTraspasosPorDepartamento(currentDepartamento);
    Traspasos newTraspasos = new Traspasos(currentPlaca, currentDepartamento, listaTraspasos, informacionMT, dpiAnt, nombreAnt);
    newTraspasos.setVisible(true);
    newTraspasos.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
            refreshTables();
        }
    });
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (currentPlaca == null || currentDepartamento == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un vehículo primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una multa para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String fecha = jTable2.getValueAt(selectedRow, 1).toString().trim();
        String descripcion = jTable2.getValueAt(selectedRow, 2).toString().trim();
        String monto = jTable2.getValueAt(selectedRow, 3).toString().trim();

        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de eliminar la multa con fecha " + fecha + " y descripción " + descripcion + "?",
            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        ListaDobleMultas listaMultas = informacionMT.getMultasPorDepartamento(currentDepartamento);
        if (listaMultas.eliminarMulta(currentPlaca, fecha, descripcion, monto, currentDepartamento, informacionMT)) {
            JOptionPane.showMessageDialog(this, "Multa eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            refreshTables();
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
        this.dispose();

    }//GEN-LAST:event_RegresarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
          if (currentPlaca == null || currentDepartamento == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un vehículo primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int selectedRow = jTable3.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un traspaso para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String dpiAnt = jTable3.getValueAt(selectedRow, 1).toString().trim();
        String nombreAnt = jTable3.getValueAt(selectedRow, 2).toString().trim();
        String fecha = jTable3.getValueAt(selectedRow, 3).toString().trim();
        String dpiNew = jTable3.getValueAt(selectedRow, 4).toString().trim();
        String nombreNew = jTable3.getValueAt(selectedRow, 5).toString().trim();

        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de eliminar el traspaso con fecha " + fecha + " y nuevo propietario " + nombreNew + "?",
            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        ListaCircularTraspasos listaTraspasos = informacionMT.getTraspasosPorDepartamento(currentDepartamento);
        if (listaTraspasos.eliminarTraspaso(currentPlaca, dpiAnt, nombreAnt, fecha, dpiNew, nombreNew, currentDepartamento, informacionMT)) {
            JOptionPane.showMessageDialog(this, "Traspaso eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            refreshTables();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    
     public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Informacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Informacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Regresar;
    private javax.swing.JTable TablaABB;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
