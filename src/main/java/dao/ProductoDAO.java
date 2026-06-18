package dao;

import conexion.Conexion;
import modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // Insertar nuevo producto (activo = 1 por defecto)
    public boolean insertar(Producto producto) {
        String sql = "INSERT INTO producto(id_categoria, nombre, precio_venta, stock, activo) "
                   + "VALUES(?,?,?,?,1)";
        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, producto.getIdCategoria());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getPrecioVenta());
            ps.setInt(4, producto.getStock());
            ps.executeUpdate();
            ps.close();
            cn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    // Actualizar producto existente
    public boolean actualizar(Producto producto) {
        String sql = "UPDATE producto "
                   + "SET id_categoria=?, nombre=?, precio_venta=?, stock=? "
                   + "WHERE id_producto=?";
        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, producto.getIdCategoria());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getPrecioVenta());
            ps.setInt(4, producto.getStock());
            ps.setInt(5, producto.getIdProducto());
            ps.executeUpdate();
            ps.close();
            cn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    // Borrado LOGICO: pone activo = 0 en lugar de DELETE
    public boolean eliminar(int idProducto) {
        String sql = "UPDATE producto SET activo = 0 WHERE id_producto = ?";
        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idProducto);
            ps.executeUpdate();
            ps.close();
            cn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    // Buscar por ID (solo productos activos)
    public Producto buscar(int idProducto) {
        String sql = "SELECT * FROM producto WHERE id_producto = ? AND activo = 1";
        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setIdCategoria(rs.getInt("id_categoria"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecioVenta(rs.getDouble("precio_venta"));
                producto.setStock(rs.getInt("stock"));
                producto.setActivo(rs.getInt("activo"));
                rs.close();
                ps.close();
                cn.close();
                return producto;
            }
            rs.close();
            ps.close();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    // Listar solo productos activos
    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE activo = 1 ORDER BY id_producto";
        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setIdCategoria(rs.getInt("id_categoria"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecioVenta(rs.getDouble("precio_venta"));
                producto.setStock(rs.getInt("stock"));
                producto.setActivo(rs.getInt("activo"));
                lista.add(producto);
            }
            rs.close();
            ps.close();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }
}
