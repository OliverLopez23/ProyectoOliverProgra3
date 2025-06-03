
package proyectoprogra3;

import javax.swing.table.DefaultTableModel;


public class RegistroVehiculosGT extends javax.swing.JFrame {
private Arboles arbolABB; // Árbol binario de búsqueda (ABB)
private ArbolesAVL arbolAVL; // Árbol AVL

    public RegistroVehiculosGT() {
        initComponents();
        // Inicializar los árboles y cargar datos
        arbolABB = new Arboles();
        arbolAVL = new ArbolesAVL();
        String rutaCarpeta = "C:\\Users\\Mayby\\Desktop\\SIRVE_Datos_Vehiculos DataSet - copia";
        arbolABB.leerArchivos(rutaCarpeta);
        arbolAVL.leerArchivos(rutaCarpeta);
        // Mostrar datos iniciales con recorrido inorden para ambos árboles
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PreOrdenAVL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InOrdenAVL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PostOrdenAVL))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PreOrdenABB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(InOrdenABB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PostOrdenABB))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PreOrdenABB)
                    .addComponent(InOrdenABB)
                    .addComponent(PostOrdenABB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PreOrdenAVL)
                    .addComponent(InOrdenAVL)
                    .addComponent(PostOrdenAVL))
                .addContainerGap(18, Short.MAX_VALUE))
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
    private javax.swing.JButton InOrdenABB;
    private javax.swing.JButton InOrdenAVL;
    private javax.swing.JButton PostOrdenABB;
    private javax.swing.JButton PostOrdenAVL;
    private javax.swing.JButton PreOrdenABB;
    private javax.swing.JButton PreOrdenAVL;
    private javax.swing.JTable TablaABB;
    private javax.swing.JTable TablaAVL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
