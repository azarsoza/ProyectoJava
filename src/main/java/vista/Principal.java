package vista;
/**
 *
 * @author ZARSOZA-PC
 */
import modelo.Usuario;

public class Principal extends javax.swing.JFrame {
    private FrmProducto producto = null;
    private FrmCompra compra = null;
    private FrmVenta venta = null;
    private FrmUsuarios usuario = null;
    private FrmCategoria categoria = null;
    private FrmEmpleado empleado = null;
    private FrmComprasListar consultaCompra = null;
    private FrmVentasListar consultaVentas = null;
    private Usuario u;
    
    public Principal(Usuario u) {
        initComponents();
        
        this.u = u;
        cargarDatosUsuario();
    }

    private void cargarDatosUsuario() {
        lblUsuario.setText("Usuario: " + u.getEmpleado().getNombres() + " " + u.getEmpleado().getApellidos());
        lblFecha.setText("Fecha: " + java.time.LocalDate.now());
        lblFormulario.setText("Formulario: Inicio");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblFormulario = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        usuarioMenuItem1 = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        fileMenu = new javax.swing.JMenu();
        empleadosMenuItem = new javax.swing.JMenuItem();
        productosMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        compraMenuItem = new javax.swing.JMenuItem();
        consultacomprasMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        ventaMenuItem = new javax.swing.JMenuItem();
        consultaventasMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        lblUsuario.setText("jLabel1");
        jPanel1.add(lblUsuario);

        lblFecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFecha.setText("jLabel2");
        jPanel1.add(lblFecha);

        lblFormulario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFormulario.setText("jLabel3");
        jPanel1.add(lblFormulario);

        desktopPane.add(jPanel1);
        jPanel1.setBounds(0, 760, 1130, 30);

        jMenu1.setText("Archivo");

        usuarioMenuItem1.setText("Usuario");
        usuarioMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(usuarioMenuItem1);

        exitMenuItem.setText("Salir");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(exitMenuItem);

        menuBar.add(jMenu1);

        fileMenu.setText("Mantenimientos");

        empleadosMenuItem.setText("Empleados");
        empleadosMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empleadosMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(empleadosMenuItem);

        productosMenuItem.setText("Productos");
        productosMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productosMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(productosMenuItem);

        menuBar.add(fileMenu);

        editMenu.setText("Compras");

        compraMenuItem.setText("Registrar Compra");
        compraMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compraMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(compraMenuItem);

        consultacomprasMenuItem.setText("Consultar Compras");
        consultacomprasMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultacomprasMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(consultacomprasMenuItem);

        menuBar.add(editMenu);

        helpMenu.setText("Ventas");

        ventaMenuItem.setText("Registrar Venta");
        ventaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventaMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(ventaMenuItem);

        consultaventasMenuItem.setText("Consultar Ventas");
        consultaventasMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaventasMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(consultaventasMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1131, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void productosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productosMenuItemActionPerformed
        if(producto == null || producto.isClosed()){
            producto = new FrmProducto();
            this.desktopPane.add(producto);
        }       
        producto.setVisible(true);
        lblFormulario.setText("Formulario: " + producto.getTitle());
    }//GEN-LAST:event_productosMenuItemActionPerformed

    private void compraMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compraMenuItemActionPerformed
        if(compra == null || compra.isClosed()){
            compra = new FrmCompra();
            this.desktopPane.add(compra);
        }
        compra.setVisible(true);
        lblFormulario.setText("Formulario: " + compra.getTitle());
    }//GEN-LAST:event_compraMenuItemActionPerformed

    private void ventaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventaMenuItemActionPerformed
        if(venta == null || venta.isClosed()){
            venta = new FrmVenta();
            this.desktopPane.add(venta);
        }
        venta.setVisible(true);
        lblFormulario.setText("Formulario: " + venta.getTitle());
    }//GEN-LAST:event_ventaMenuItemActionPerformed

    private void usuarioMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioMenuItem1ActionPerformed
        if(usuario == null || usuario.isClosed()){
            usuario = new FrmUsuarios();
            this.desktopPane.add(usuario);
        }
        usuario.setVisible(true);
        lblFormulario.setText("Formulario: " + usuario.getTitle());
    }//GEN-LAST:event_usuarioMenuItem1ActionPerformed

    private void empleadosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empleadosMenuItemActionPerformed
        if(empleado == null || empleado.isClosed()){
            empleado = new FrmEmpleado();
            this.desktopPane.add(empleado);
        }
        empleado.setVisible(true);
        lblFormulario.setText("Formulario: " + empleado.getTitle());
    }//GEN-LAST:event_empleadosMenuItemActionPerformed

    private void consultacomprasMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultacomprasMenuItemActionPerformed
       if(consultaCompra == null || consultaCompra.isClosed()){
            consultaCompra = new FrmComprasListar();
            this.desktopPane.add(consultaCompra);
        }
        consultaCompra.setVisible(true);
        lblFormulario.setText("Formulario: " + consultaCompra.getTitle());
    }//GEN-LAST:event_consultacomprasMenuItemActionPerformed

    private void consultaventasMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaventasMenuItemActionPerformed
        if(consultaVentas == null || consultaVentas.isClosed()){
            consultaVentas = new FrmVentasListar();
            this.desktopPane.add(consultaVentas);
        }
        consultaVentas.setVisible(true);
        lblFormulario.setText("Formulario: " + consultaVentas.getTitle());
    }//GEN-LAST:event_consultaventasMenuItemActionPerformed


//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Principal().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem compraMenuItem;
    private javax.swing.JMenuItem consultacomprasMenuItem;
    private javax.swing.JMenuItem consultaventasMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem empleadosMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFormulario;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem productosMenuItem;
    private javax.swing.JMenuItem usuarioMenuItem1;
    private javax.swing.JMenuItem ventaMenuItem;
    // End of variables declaration//GEN-END:variables

}
