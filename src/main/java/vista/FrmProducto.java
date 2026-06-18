package vista;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;
import servicio.ProductoService;

public class FrmProducto extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(FrmProducto.class.getName());
    private ProductoService servicio;

    public FrmProducto() {
        initComponents();
        servicio = new ProductoService();
        txtIdProducto.setEditable(false);
        cargarTabla();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        lblCodigo      = new javax.swing.JLabel();
        lblCategoria   = new javax.swing.JLabel();
        lblNombre      = new javax.swing.JLabel();
        lblPrecio      = new javax.swing.JLabel();
        lblStock       = new javax.swing.JLabel();

        txtIdProducto  = new javax.swing.JTextField();
        txtIdCategoria = new javax.swing.JTextField();
        txtNombre      = new javax.swing.JTextField();
        txtPrecio      = new javax.swing.JTextField();
        txtStock       = new javax.swing.JTextField();

        btnNuevo       = new javax.swing.JButton();
        btnGuardar     = new javax.swing.JButton();
        btnBuscar      = new javax.swing.JButton();
        btnEditar      = new javax.swing.JButton();
        btnEliminar    = new javax.swing.JButton();

        jScrollPane1   = new javax.swing.JScrollPane();
        tblProducto    = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mi Bodeguita - Gestión de Productos");

        lblCodigo.setText("Código:");
        lblCategoria.setText("ID Categoría:");
        lblNombre.setText("Nombre del Producto:");
        lblPrecio.setText("Precio Venta:");
        lblStock.setText("Stock:");

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(evt -> btnNuevoActionPerformed(evt));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(evt -> btnGuardarActionPerformed(evt));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(evt -> btnBuscarActionPerformed(evt));

        btnEditar.setText("Editar");
        btnEditar.addActionListener(evt -> btnEditarActionPerformed(evt));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(evt -> btnEliminarActionPerformed(evt));

        tblProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[] { "Código", "Categoría", "Nombre", "Precio", "Stock" }
        ));
        jScrollPane1.setViewportView(tblProducto);

        // Layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCodigo)
                        .addGap(30).addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCategoria)
                        .addGap(10).addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNombre)
                        .addGap(10).addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPrecio)
                        .addGap(20).addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblStock)
                        .addGap(50).addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                )
                .addGap(30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                )
                .addGap(20))
            .addGroup(layout.createSequentialGroup()
                .addGap(10)
                .addComponent(jScrollPane1)
                .addGap(10))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo))
                .addGap(10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategoria)
                    .addComponent(txtIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar))
                .addGap(10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecio)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar))
                .addGap(10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStock)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar))
                .addGap(15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10)
        );

        pack();
    }

    // ── NUEVO: limpiar campos ──────────────────────────────────────────────────
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        limpiar();
    }

    // ── GUARDAR: insertar producto ────────────────────────────────────────────
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Producto producto = new Producto();
            producto.setIdCategoria(Integer.parseInt(txtIdCategoria.getText()));
            producto.setNombre(txtNombre.getText());
            producto.setPrecioVenta(Double.parseDouble(txtPrecio.getText()));
            producto.setStock(Integer.parseInt(txtStock.getText()));
            producto.setActivo(1);

            if (servicio.insertar(producto)) {
                JOptionPane.showMessageDialog(this, "Producto registrado correctamente.");
                cargarTabla();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar el producto.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Verifica que Categoría, Precio y Stock sean números válidos.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // ── BUSCAR por ID ─────────────────────────────────────────────────────────
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int id = Integer.parseInt(txtIdProducto.getText());
            Producto producto = servicio.buscar(id);
            if (producto != null) {
                txtIdCategoria.setText(String.valueOf(producto.getIdCategoria()));
                txtNombre.setText(producto.getNombre());
                txtPrecio.setText(String.valueOf(producto.getPrecioVenta()));
                txtStock.setText(String.valueOf(producto.getStock()));
            } else {
                JOptionPane.showMessageDialog(this, "Producto no encontrado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingresa un código válido para buscar.");
        }
    }

    // ── EDITAR: actualizar producto ───────────────────────────────────────────
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Producto producto = new Producto();
            producto.setIdProducto(Integer.parseInt(txtIdProducto.getText()));
            producto.setIdCategoria(Integer.parseInt(txtIdCategoria.getText()));
            producto.setNombre(txtNombre.getText());
            producto.setPrecioVenta(Double.parseDouble(txtPrecio.getText()));
            producto.setStock(Integer.parseInt(txtStock.getText()));

            if (servicio.actualizar(producto)) {
                JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.");
                cargarTabla();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el producto.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Verifica que los campos numéricos sean válidos.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // ── ELIMINAR: borrado lógico (activo = 0) ─────────────────────────────────
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int id = Integer.parseInt(txtIdProducto.getText());
            int confirmar = JOptionPane.showConfirmDialog(this,
                "¿Deseas desactivar el producto con código " + id + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                if (servicio.eliminar(id)) {
                    JOptionPane.showMessageDialog(this, "Producto desactivado correctamente.");
                    cargarTabla();
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al desactivar el producto.");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingresa un código válido para eliminar.");
        }
    }

    // ── Limpiar campos ────────────────────────────────────────────────────────
    private void limpiar() {
        txtIdProducto.setText("");
        txtIdCategoria.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        txtNombre.requestFocus();
    }

    // ── Cargar tabla ──────────────────────────────────────────────────────────
    private void cargarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Categoría");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");

        List<Producto> lista = servicio.listar();
        for (Producto producto : lista) {
            Object[] fila = new Object[5];
            fila[0] = producto.getIdProducto();
            fila[1] = producto.getIdCategoria();
            fila[2] = producto.getNombre();
            fila[3] = producto.getPrecioVenta();
            fila[4] = producto.getStock();
            modelo.addRow(fila);
        }
        tblProducto.setModel(modelo);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FrmProducto().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblStock;
    private javax.swing.JTextField txtIdProducto;
    private javax.swing.JTextField txtIdCategoria;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtStock;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProducto;
}
