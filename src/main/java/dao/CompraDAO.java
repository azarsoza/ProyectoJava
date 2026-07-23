package dao;

import conexion.Conexiondb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Compra;
import modelo.DetalleCompra;
import modelo.Producto;
import modelo.Usuario;

public class CompraDAO {   
    
    // Método principal transaccional: Registra la Compra y todos sus detalles juntos
    public boolean insertar(Compra compra, List<DetalleCompra> detalles) {
        String sqlCompra = "INSERT INTO compra (id_usuario, fecha, valor_impuesto, impuesto, total) VALUES (?, ?, ?, ?, ?)";
        String sqlDetalle = "INSERT INTO detalle_compra (id_compra, id_producto, cantidad, precio_compra, subtotal) VALUES (?, ?, ?, ?, ?)";
        
        Connection cn = Conexiondb.getInstance().conectar();
        PreparedStatement psCompra = null;
        PreparedStatement psDetalle = null;
        ResultSet rs = null;
        
        try {           
            // Iniciamos la transacción
            cn.setAutoCommit(false);
            
            // 1. Insertar la cabecera de la Compra y recuperar el ID generado
            psCompra = cn.prepareStatement(sqlCompra, Statement.RETURN_GENERATED_KEYS);
            psCompra.setInt(1, compra.getUsuario().getIdUsuario());
            psCompra.setDate(2, compra.getFecha());
            psCompra.setDouble(3, compra.getValorImpuesto());
            psCompra.setDouble(4, compra.getImpuesto());
            psCompra.setDouble(5, compra.getTotal());
            
            int filasAfectadas = psCompra.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("Error: No se pudo registrar la cabecera de la compra.");
            }
            
            rs = psCompra.getGeneratedKeys();
            int idCompraGenerado = 0;
            if (rs.next()) {
                idCompraGenerado = rs.getInt(1);
            } else {
                throw new SQLException("Error: No se pudo obtener el ID generado para la compra.");
            }
            
            // 2. Insertar los detalles de la compra usando Batch
            psDetalle = cn.prepareStatement(sqlDetalle);
            for (DetalleCompra dc : detalles) {
                psDetalle.setInt(1, idCompraGenerado);
                psDetalle.setInt(2, dc.getProducto().getIdProducto());
                psDetalle.setDouble(3, dc.getCantidad());
                psDetalle.setDouble(4, dc.getPrecioCompra());
                psDetalle.setDouble(5, dc.getSubtotal());
                psDetalle.addBatch();
            }
            
            psDetalle.executeBatch();
            
            // Si todo es correcto, guardamos permanentemente
            cn.commit();
            return true;
            
        } catch (SQLException e) {
            System.out.println("Error en transacción de Compra: " + e.getMessage());
            if (cn != null) {
                try {
                    System.out.println("Ejecutando Rollback de emergencia...");
                    cn.rollback(); // Revierte los cambios ante fallas
                } catch (SQLException ex) {
                    System.out.println("Error en Rollback: " + ex.getMessage());
                }
            }
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (psCompra != null) psCompra.close();
                if (psDetalle != null) psDetalle.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
    
    // Método para cumplir con el requerimiento "Ver historial de compras"
    public List<Compra> listar() {
        List<Compra> lista = new ArrayList<>();
        String sql = "SELECT c.*, u.nombre AS nombre_usuario FROM compra c "
                   + "INNER JOIN usuario u ON c.id_usuario = u.id_usuario "
                   + "ORDER BY c.id_compra DESC";
        
        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Compra compra = new Compra();
                compra.setIdCompra(rs.getInt("id_compra"));
                compra.setFecha(rs.getDate("fecha"));
                compra.setValorImpuesto(rs.getDouble("valor_impuesto"));
                compra.setImpuesto(rs.getDouble("impuesto"));
                compra.setTotal(rs.getDouble("total"));
                
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                
                compra.setUsuario(usuario);
                
                lista.add(compra);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar historial de compras: " + e.getMessage());
        }
        return lista;
    }
    
    public List<Compra> listarCompras() {
        List<Compra> lista = new ArrayList<>();
        
        String sql = " SELECT c.id_compra, c.fecha, c.valor_impuesto, "
                + " c.impuesto, c.total, u.id_usuario, u.usuario "
                + " FROM Compra c INNER JOIN Usuario u "
                + " ON c.id_usuario = u.id_usuario "
                + " ORDER BY c.id_compra DESC ";

        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Compra compra = new Compra();
                compra.setIdCompra(rs.getInt("id_compra"));
                compra.setFecha(rs.getDate("fecha"));
                compra.setValorImpuesto(rs.getDouble("valor_impuesto"));
                compra.setImpuesto(rs.getDouble("impuesto"));
                compra.setTotal(rs.getDouble("total"));
                
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsuario(rs.getString("usuario")); // cambia si tu campo tiene otro nombre
                
                compra.setUsuario(usuario);
                
                lista.add(compra);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
    public boolean eliminar(int idCompra) {
        String sqlDetalle = "DELETE FROM detalle_compra WHERE id_compra = ?";
        String sqlCompra = "DELETE FROM compra WHERE id_compra = ?";
        
        try (Connection cn = Conexiondb.getInstance().conectar()) {
            cn.setAutoCommit(false);
            
            try (PreparedStatement psDetalle = cn.prepareStatement(sqlDetalle);
                    PreparedStatement psCompra = cn.prepareStatement(sqlCompra)) {
        
                psDetalle.setInt(1, idCompra);
                psDetalle.executeUpdate();
                
                psCompra.setInt(1, idCompra);
                
                int filas = psCompra.executeUpdate();
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
    
    public Compra buscarCompra(int idCompra) {
        Compra compra = null;
        
        String sql = " SELECT c.id_compra, c.fecha, c.valor_impuesto, "
                    + " c.impuesto, c.total, u.id_usuario, u.usuario "
                    + " FROM Compra c INNER JOIN Usuario u "
                    + " ON c.id_usuario = u.id_usuario "
                    + " WHERE c.id_compra = ? ";
        
        try (Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idCompra);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    compra = new Compra();
                    compra.setIdCompra(rs.getInt("id_compra"));
                    compra.setFecha(rs.getDate("fecha"));
                    compra.setValorImpuesto(rs.getDouble("valor_impuesto"));
                    compra.setImpuesto(rs.getDouble("impuesto"));
                    compra.setTotal(rs.getDouble("total"));
                    
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setUsuario(rs.getString("usuario"));
                    
                    compra.setUsuario(usuario);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return compra;
    }
    
    public List<DetalleCompra> listarDetalleCompra(int idCompra){
        List<DetalleCompra> lista = new ArrayList<>();
        
        String sql = " SELECT dc.*, p.nombre " 
                    + " FROM detalle_compra dc INNER JOIN Producto p "
                    + " ON dc.id_producto = p.id_producto "
                    + " WHERE dc.id_compra = ? ";
        
        try(Connection cn = Conexiondb.getInstance().conectar();
                PreparedStatement ps = cn.prepareStatement(sql)){
            ps.setInt(1,idCompra);
            
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Producto producto = new Producto();
                    producto.setIdProducto(rs.getInt("id_producto"));
                    producto.setNombre(rs.getString("nombre"));
                    
                    DetalleCompra detalle = new DetalleCompra();
                    detalle.setProducto(producto);
                    detalle.setCantidad(rs.getDouble("cantidad"));
                    detalle.setPrecioCompra(rs.getDouble("precio_compra"));
                    
                    lista.add(detalle);
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
}