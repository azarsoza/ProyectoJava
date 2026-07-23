package dao;
import conexion.Conexiondb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
import modelo.Venta;
import modelo.DetalleVenta;
import modelo.Producto;

public class VentaDAO {
    // Método principal transaccional: Registra la Venta y todos sus detalles juntos
    public boolean insertar(Venta venta, List<DetalleVenta> detalles) {
        String sqlVenta = "INSERT INTO venta (id_usuario, fecha, valor_impuesto, impuesto, total) VALUES (?, ?, ?, ?, ?)";
        String sqlDetalle = "INSERT INTO detalle_venta (id_venta, id_producto, cantidad, precio_venta, subtotal) VALUES (?, ?, ?, ?, ?)";
        
        Connection cn = Conexiondb.getInstance().conectar();
        PreparedStatement psVenta = null;
        PreparedStatement psDetalle = null;
        ResultSet rs = null;
        
        try {
            // Iniciamos la transacción: no se guarda nada de forma automática
            cn.setAutoCommit(false);
            
            // 1. Insertar la cabecera de la Venta y recuperar el ID autonumérico generado
            psVenta = cn.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS);
            psVenta.setInt(1, venta.getUsuario().getIdUsuario());
            psVenta.setDate(2, venta.getFecha());
            psVenta.setDouble(3, venta.getValorImpuesto());
            psVenta.setDouble(4, venta.getImpuesto());
            psVenta.setDouble(5, venta.getTotal());
            
            int filasAfectadas = psVenta.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("Error: No se pudo registrar la cabecera de la venta.");
            }
            
            // Obtenemos el id_venta generado por la base de datos
            rs = psVenta.getGeneratedKeys();
            int idVentaGenerado = 0;
            if (rs.next()) {
                idVentaGenerado = rs.getInt(1);
            } else {
                throw new SQLException("Error: No se pudo obtener el ID generado para la venta.");
            }
            
            // 2. Insertar los detalles de la venta usando Batch (procesamiento por lotes)
            psDetalle = cn.prepareStatement(sqlDetalle);
            for (DetalleVenta dv : detalles) {
                psDetalle.setInt(1, idVentaGenerado);
                psDetalle.setInt(2, dv.getProducto().getIdProducto());
                psDetalle.setDouble(3, dv.getCantidad());
                psDetalle.setDouble(4, dv.getPrecioVenta());
                psDetalle.setDouble(5, dv.getSubtotal());
                psDetalle.addBatch(); // Lo añadimos al lote
            }
            
            // Ejecutamos todos los detalles juntos
            psDetalle.executeBatch();
            
            // Si todo salió bien en ambos inserts, confirmamos la transacción
            cn.commit();
            return true;
            
        } catch (SQLException e) {
            System.out.println("Error en transacción de Venta: " + e.getMessage());
            if (cn != null) {
                try {
                    System.out.println("Ejecutando Rollback de emergencia...");
                    cn.rollback(); // Deshace todo si hubo algún error en el proceso
                } catch (SQLException ex) {
                    System.out.println("Error en Rollback: " + ex.getMessage());
                }
            }
            return false;
        } finally {
            // Cerramos de forma segura todos los recursos abiertos
            try {
                if (rs != null) rs.close();
                if (psVenta != null) psVenta.close();
                if (psDetalle != null) psDetalle.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
    
    // Método para cumplir con el requerimiento "Ver historial de ventas"
    public List<Venta> listar() {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT v.*, u.nombre AS nombre_usuario FROM venta v "
                   + "INNER JOIN usuario u ON v.id_usuario = u.id_usuario "
                   + "ORDER BY v.id_venta DESC";
        
        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setFecha(rs.getDate("fecha"));
                venta.setValorImpuesto(rs.getDouble("valor_impuesto"));
                venta.setImpuesto(rs.getDouble("impuesto"));
                venta.setTotal(rs.getDouble("total"));
                
                // Mapeamos el usuario que realizó la venta de manera segura
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                
                venta.setUsuario(usuario);
                
                lista.add(venta);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar historial de ventas: " + e.getMessage());
        }
        return lista;
    }
    
    public List<Venta> listarVentas() {
        List<Venta> lista = new ArrayList<>();
        
        String sql = " SELECT v.id_venta, v.fecha, v.valor_impuesto, "
                + " v.impuesto, v.total, u.id_usuario, u.usuario "
                + " FROM Venta v INNER JOIN Usuario u "
                + " ON v.id_usuario = u.id_usuario "
                + " ORDER BY v.id_venta DESC ";

        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setFecha(rs.getDate("fecha"));
                venta.setValorImpuesto(rs.getDouble("valor_impuesto"));
                venta.setImpuesto(rs.getDouble("impuesto"));
                venta.setTotal(rs.getDouble("total"));
                
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsuario(rs.getString("usuario")); // cambia si tu campo tiene otro nombre
                
                venta.setUsuario(usuario);
                
                lista.add(venta);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
    public boolean eliminar(int idVenta) {
        String sqlDetalle = "DELETE FROM detalle_venta WHERE id_venta = ?";
        String sqlVenta = "DELETE FROM venta WHERE id_venta = ?";
        
        try (Connection cn = Conexiondb.getInstance().conectar()) {
            cn.setAutoCommit(false);
            
            try (PreparedStatement psDetalle = cn.prepareStatement(sqlDetalle);
                    PreparedStatement psVenta = cn.prepareStatement(sqlVenta)) {
        
                psDetalle.setInt(1, idVenta);
                psDetalle.executeUpdate();
                
                psVenta.setInt(1, idVenta);
                
                int filas = psVenta.executeUpdate();
                cn.commit();
                return filas > 0;
            } catch (SQLException e) {
                cn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
    
    public Venta buscarVenta(int idVenta) {
        Venta venta = null;
        
        String sql = " SELECT v.id_venta, v.fecha, v.valor_impuesto, "
                    + " v.impuesto, v.total, u.id_usuario, u.usuario "
                    + " FROM Venta v INNER JOIN Usuario u "
                    + " ON v.id_usuario = u.id_usuario "
                    + " WHERE v.id_venta = ? ";
        
        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    venta = new Venta();
                    venta.setIdVenta(rs.getInt("id_venta"));
                    venta.setFecha(rs.getDate("fecha"));
                    venta.setValorImpuesto(rs.getDouble("valor_impuesto"));
                    venta.setImpuesto(rs.getDouble("impuesto"));
                    venta.setTotal(rs.getDouble("total"));
                    
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setUsuario(rs.getString("usuario"));
                    
                    venta.setUsuario(usuario);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return venta;
    }
    
    public List<DetalleVenta> listarDetalleVenta(int idVenta){
        List<DetalleVenta> lista = new ArrayList<>();
        
        String sql = " SELECT dv.*, p.nombre " 
                    + " FROM detalle_venta dv INNER JOIN Producto p "
                    + " ON dv.id_producto = p.id_producto "
                    + " WHERE dv.id_venta = ? ";
        
        try(Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setInt(1,idVenta);
            
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Producto producto = new Producto();
                    producto.setIdProducto(rs.getInt("id_producto"));
                    producto.setNombre(rs.getString("nombre"));
                    
                    DetalleVenta detalle = new DetalleVenta();
                    detalle.setProducto(producto);
                    detalle.setCantidad(rs.getDouble("cantidad"));
                    detalle.setPrecioVenta(rs.getDouble("precio_venta"));
                    
                    lista.add(detalle);
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
}