package proyectoprogra3;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RegistroVehiculosGT extends javax.swing.JFrame {
    private Arboles arbolABB;
    private ArbolesAVL arbolAVL;
    private InformacionMT informacionMT;

    public RegistroVehiculosGT() {
        initComponents();
        String rutaCarpeta = "C:\\Users\\Mayby\\Desktop\\SIRVE_Datos_Vehiculos DataSet - copia";
        informacionMT = new InformacionMT(rutaCarpeta);
        arbolABB = new Arboles();
        arbolAVL = new ArbolesAVL();
        arbolABB.leerArchivos(rutaCarpeta);
        arbolAVL.leerArchivos(rutaCarpeta);
        actualizarTabla("inorden", "ABB");
        actualizarTabla("inorden", "AVL");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaABB = new javax.swing.JTable();
        PreOrdenABB = new javax.swing.JButton();
        InOrdenABB = new javax.swing.JButton();
        PostOrdenABB = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaAVL = new javax.swing.JTable();
        PreOrdenAVL = new javax.swing.JButton();
        InOrdenAVL = new javax.swing.JButton();
        PostOrdenAVL = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        VerInfo = new javax.swing.JButton();
        EliminarVehiculo = new javax.swing.JButton();
        AgregarVehiculo = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        ModificarVehiculo = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ABB");

        TablaABB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Placa", "DPI", "Nombre", "Marca", "Modelo", "Año", "Multas", "Traspasos", "Departamento"
            }
        ));
        jScrollPane1.setViewportView(TablaABB);

        PreOrdenABB.setText("PreOrden");
        PreOrdenABB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreOrdenABBActionPerformed(evt);
            }
        });

        InOrdenABB.setText("InOren");
        InOrdenABB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InOrdenABBActionPerformed(evt);
            }
        });

        PostOrdenABB.setText("PostOrden");
        PostOrdenABB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PostOrdenABBActionPerformed(evt);
            }
        });

        jLabel2.setText("AVL");

        TablaAVL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Placa", "DPI", "Nombre", "Marca", "Modelo", "Año", "Multas", "Traspasos", "Departamento"
            }
        ));
        jScrollPane2.setViewportView(TablaAVL);

        PreOrdenAVL.setText("PreOrden");
        PreOrdenAVL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreOrdenAVLActionPerformed(evt);
            }
        });

        InOrdenAVL.setText("InOren");
        InOrdenAVL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InOrdenAVLActionPerformed(evt);
            }
        });

        PostOrdenAVL.setText("PostOrden");
        PostOrdenAVL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PostOrdenAVLActionPerformed(evt);
            }
        });

        jLabel3.setText("Ver info de MT");

        VerInfo.setText("Ver info");
        VerInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerInfoActionPerformed(evt);
            }
        });

        EliminarVehiculo.setText("Eliminar Vehiculo");
        EliminarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarVehiculoActionPerformed(evt);
            }
        });

        AgregarVehiculo.setText("Agregar Vehiculo");
        AgregarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarVehiculoActionPerformed(evt);
            }
        });

        jLabel4.setText("Buscar por placa ");

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Tiempo ABB");

        jLabel6.setText("Tiempo AVL");

        ModificarVehiculo.setText("Modificar Vehiculo");
        ModificarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarVehiculoActionPerformed(evt);
            }
        });

        jButton2.setText("Ver Estadistica");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PreOrdenABB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(InOrdenABB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PostOrdenABB))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PreOrdenAVL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InOrdenAVL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PostOrdenAVL))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(VerInfo)
                                    .addComponent(jLabel5)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(EliminarVehiculo)
                                    .addComponent(AgregarVehiculo)
                                    .addComponent(ModificarVehiculo)
                                    .addComponent(jButton2)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(43, 43, 43)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(VerInfo)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(AgregarVehiculo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EliminarVehiculo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PreOrdenABB)
                            .addComponent(InOrdenABB)
                            .addComponent(PostOrdenABB))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PreOrdenAVL)
                            .addComponent(InOrdenAVL)
                            .addComponent(PostOrdenAVL)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ModificarVehiculo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PreOrdenABBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreOrdenABBActionPerformed
        actualizarTabla("preorden", "ABB");
    }//GEN-LAST:event_PreOrdenABBActionPerformed

    private void PostOrdenABBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PostOrdenABBActionPerformed
        actualizarTabla("postorden", "ABB");
    }//GEN-LAST:event_PostOrdenABBActionPerformed

    private void InOrdenABBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InOrdenABBActionPerformed
        actualizarTabla("inorden", "ABB");
    }//GEN-LAST:event_InOrdenABBActionPerformed

    private void PreOrdenAVLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreOrdenAVLActionPerformed
        actualizarTabla("preorden", "AVL");
    }//GEN-LAST:event_PreOrdenAVLActionPerformed

    private void InOrdenAVLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InOrdenAVLActionPerformed
        actualizarTabla("inorden", "AVL");
    }//GEN-LAST:event_InOrdenAVLActionPerformed

    private void PostOrdenAVLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PostOrdenAVLActionPerformed
        actualizarTabla("postorden", "AVL");
    }//GEN-LAST:event_PostOrdenAVLActionPerformed

    private void VerInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerInfoActionPerformed
        // Verificar si hay una fila seleccionada en alguna de las tablas
        int selectedRowABB = TablaABB.getSelectedRow();
        int selectedRowAVL = TablaAVL.getSelectedRow();

        if (selectedRowABB == -1 && selectedRowAVL == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un vehículo en la tabla ABB o AVL.");
            return;
        }

        // Obtener placa y departamento de la tabla seleccionada
        String placa;
        String departamento;
        if (selectedRowABB != -1) {
            placa = (String) TablaABB.getValueAt(selectedRowABB, 0); // Columna 0: Placa
            departamento = (String) TablaABB.getValueAt(selectedRowABB, 8); // Columna 8: Departamento
        } else {
            placa = (String) TablaAVL.getValueAt(selectedRowAVL, 0); // Columna 0: Placa
            departamento = (String) TablaAVL.getValueAt(selectedRowAVL, 8); // Columna 8: Departamento
        }

        // Abrir la ventana Informacion y cargar los datos
        Informacion informacion = new Informacion();
        informacion.cargarDatos(placa, departamento);
        informacion.setVisible(true);
        // Agregar WindowListener para recargar árboles al cerrar Informacion
        informacion.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                recargarArboles();
            }
        });    
    }//GEN-LAST:event_VerInfoActionPerformed

    private void EliminarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarVehiculoActionPerformed
        // Verificar si hay una fila seleccionada en alguna de las tablas
        int selectedRowABB = TablaABB.getSelectedRow();
        int selectedRowAVL = TablaAVL.getSelectedRow();

        if (selectedRowABB == -1 && selectedRowAVL == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un vehículo en la tabla ABB o AVL.");
            return;
        }

        // Obtener placa y departamento de la tabla seleccionada
        String placa;
        String departamento;
        if (selectedRowABB != -1) {
            placa = (String) TablaABB.getValueAt(selectedRowABB, 0); // Columna 0: Placa
            departamento = (String) TablaABB.getValueAt(selectedRowABB, 8); // Columna 8: Departamento
        } else {
            placa = (String) TablaAVL.getValueAt(selectedRowAVL, 0); // Columna 0: Placa
            departamento = (String) TablaAVL.getValueAt(selectedRowAVL, 8); // Columna 8: Departamento
        }

        // Confirmar eliminación
        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de eliminar el vehículo con placa " + placa + " y todos sus multas y traspasos?",
            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // Eliminar vehículo, multas y traspasos
        if (informacionMT.eliminarVehiculoYRegistros(departamento, placa)) {
            JOptionPane.showMessageDialog(this, "Vehículo eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            recargarArboles();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar el vehículo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    
    }//GEN-LAST:event_EliminarVehiculoActionPerformed

    private void AgregarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarVehiculoActionPerformed
        Automovil automovil = new Automovil(this);
    automovil.setVisible(true);
    }//GEN-LAST:event_AgregarVehiculoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String placa = jTextField1.getText().trim();

        if (placa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una placa.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!placa.matches("[A-Z]{3,4}-[0-9]{3,4}")) {
            JOptionPane.showMessageDialog(this, "Placa inválida. Debe ser 3 o 4 letras mayúsculas, un guion, y 3 o 4 dígitos (ej. ZZAB-102, KYT-9063).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel modeloABB = (DefaultTableModel) TablaABB.getModel();
        DefaultTableModel modeloAVL = (DefaultTableModel) TablaAVL.getModel();
        modeloABB.setRowCount(0);
        modeloAVL.setRowCount(0);

        long startTimeABB = System.nanoTime();
        NodoVehiculo nodoABB = arbolABB.buscarPorPlaca(placa);
        long endTimeABB = System.nanoTime();
        long tiempoABB = endTimeABB - startTimeABB;

        long startTimeAVL = System.nanoTime();
        NodoVehiculo nodoAVL = arbolAVL.buscarPorPlaca(placa);
        long endTimeAVL = System.nanoTime();
        long tiempoAVL = endTimeAVL - startTimeAVL;

        jTextField2.setText((tiempoABB / 1000) + " µs");
        jTextField3.setText((tiempoAVL / 1000) + " µs");

        boolean encontrado = false;
        if (nodoABB != null) {
            modeloABB.addRow(new Object[]{
                nodoABB.placa,
                nodoABB.dpi,
                nodoABB.nombre,
                nodoABB.marca,
                nodoABB.modelo,
                nodoABB.ano,
                nodoABB.multas,
                nodoABB.traspasos,
                nodoABB.departamento
            });
            TablaABB.setRowSelectionInterval(0, 0);
            encontrado = true;
        }

        if (nodoAVL != null) {
            modeloAVL.addRow(new Object[]{
                nodoAVL.placa,
                nodoAVL.dpi,
                nodoAVL.nombre,
                nodoAVL.marca,
                nodoAVL.modelo,
                nodoAVL.ano,
                nodoAVL.multas,
                nodoAVL.traspasos,
                nodoAVL.departamento
            });
            TablaAVL.setRowSelectionInterval(0, 0);
            encontrado = true;
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(this, "No se encontró un vehículo con la placa " + placa, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ModificarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarVehiculoActionPerformed
        int selectedRowABB = TablaABB.getSelectedRow();
        int selectedRowAVL = TablaAVL.getSelectedRow();

        if (selectedRowABB == -1 && selectedRowAVL == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un vehículo en la tabla ABB o AVL.");
            return;
        }

        String placa, dpi, nombre, marca, modelo, departamento;
        int ano;
        if (selectedRowABB != -1) {
            placa = (String) TablaABB.getValueAt(selectedRowABB, 0);
            dpi = (String) TablaABB.getValueAt(selectedRowABB, 1);
            nombre = (String) TablaABB.getValueAt(selectedRowABB, 2);
            marca = (String) TablaABB.getValueAt(selectedRowABB, 3);
            modelo = (String) TablaABB.getValueAt(selectedRowABB, 4);
            ano = (Integer) TablaABB.getValueAt(selectedRowABB, 5);
            departamento = (String) TablaABB.getValueAt(selectedRowABB, 8);
        } else {
            placa = (String) TablaAVL.getValueAt(selectedRowAVL, 0);
            dpi = (String) TablaAVL.getValueAt(selectedRowAVL, 1);
            nombre = (String) TablaAVL.getValueAt(selectedRowAVL, 2);
            marca = (String) TablaAVL.getValueAt(selectedRowAVL, 3);
            modelo = (String) TablaAVL.getValueAt(selectedRowAVL, 4);
            ano = (Integer) TablaAVL.getValueAt(selectedRowAVL, 5);
            departamento = (String) TablaAVL.getValueAt(selectedRowAVL, 8);
        }

        Automovil automovil = new Automovil(this, true, placa, dpi, nombre, marca, modelo, ano, departamento);
        automovil.setVisible(true);
    }//GEN-LAST:event_ModificarVehiculoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new Estadistica().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

private void actualizarTabla(String tipoRecorrido, String tipoArbol) {
        DefaultTableModel modelo;
        if (tipoArbol.equals("ABB")) {
            modelo = (DefaultTableModel) TablaABB.getModel();
            modelo.setRowCount(0); // Limpiar la tabla ABB
            if (!arbolABB.estaVacio()) {
                switch (tipoRecorrido) {
                    case "preorden":
                        arbolABB.preOrden(arbolABB.getRaiz(), modelo);
                        break;
                    case "inorden":
                        arbolABB.inOrden(arbolABB.getRaiz(), modelo);
                        break;
                    case "postorden":
                        arbolABB.postOrden(arbolABB.getRaiz(), modelo);
                        break;
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "No se encontraron datos en los archivos (ABB).");
            }
        } else if (tipoArbol.equals("AVL")) {
            modelo = (DefaultTableModel) TablaAVL.getModel();
            modelo.setRowCount(0); // Limpiar la tabla AVL
            if (!arbolAVL.estaVacio()) {
                switch (tipoRecorrido) {
                    case "preorden":
                        arbolAVL.preOrden(arbolAVL.getRaiz(), modelo);
                        break;
                    case "inorden":
                        arbolAVL.inOrden(arbolAVL.getRaiz(), modelo);
                        break;
                    case "postorden":
                        arbolAVL.postOrden(arbolAVL.getRaiz(), modelo);
                        break;
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "No se encontraron datos en los archivos (AVL).");
            }
        }
    }

    // Método para recargar los árboles y actualizar las tablas
    public void recargarArboles() {
        // Reinicializar los árboles
        arbolABB = new Arboles();
        arbolAVL = new ArbolesAVL();
        String rutaCarpeta = "C:\\Users\\Mayby\\Desktop\\SIRVE_Datos_Vehiculos DataSet - copia";
        arbolABB.leerArchivos(rutaCarpeta);
        arbolAVL.leerArchivos(rutaCarpeta);
        // Actualizar tablas con recorrido inorden
        actualizarTabla("inorden", "ABB");
        actualizarTabla("inorden", "AVL");
    }
 
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroVehiculosGT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new RegistroVehiculosGT().setVisible(true));
    }  
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AgregarVehiculo;
    private javax.swing.JButton EliminarVehiculo;
    private javax.swing.JButton InOrdenABB;
    private javax.swing.JButton InOrdenAVL;
    private javax.swing.JButton ModificarVehiculo;
    private javax.swing.JButton PostOrdenABB;
    private javax.swing.JButton PostOrdenAVL;
    private javax.swing.JButton PreOrdenABB;
    private javax.swing.JButton PreOrdenAVL;
    private javax.swing.JTable TablaABB;
    private javax.swing.JTable TablaAVL;
    private javax.swing.JButton VerInfo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
