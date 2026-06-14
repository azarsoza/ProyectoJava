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
    
    public boolean insertar(Producto producto){
    
        String sql ="INSERT INTO producto(nombre, precio_venta, stock)"
                + "VALUES(?,?,?)";
        
        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            
            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecioVenta());
            ps.setInt(3, producto.getStock());
            
            ps.executeUpdate(); 
            ps.close();
            cn.close();
            
            return true;
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean actualizar(Producto producto){
        
        String sql ="UPDATE producto "
                + "SET nombre=?, precio_venta=?, stock=? "
                + "WHERE id_producto=?";
        
        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            
            ps.setString(1,producto.getNombre());
            ps.setDouble(2, producto.getPrecioVenta());
            ps.setInt(3, producto.getStock());
            ps.setInt(4, producto.getIdProducto());
            
            ps.executeUpdate();
            ps.close();
            cn.close();
            
            return true;
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }        
    }
    
    public boolean eliminar(int idProducto){
        
        String sql = "DELETE FROM producto "
                + "WHERE id_producto=?";
        
        try {
            Connection cn = Conexion.getConexion(); 
            PreparedStatement ps = cn.prepareStatement(sql);
            
            ps.setInt(1, idProducto);            
            ps.executeUpdate();
            
            ps.close();
            cn.close();
            ps.close();
            return true;    
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public Producto buscar(int idProducto){
        
        String sql = "SELECT * FROM producto "
                + "WHERE id_producto=?";
        
        try {
            Connection cn = Conexion.getConexion();  
            PreparedStatement ps = cn.prepareStatement(sql);
            
            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Producto producto = new Producto();
                
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecioVenta(rs.getDouble("precio_venta"));
                producto.setStock(rs.getInt("stock"));
                
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
    
    public List<Producto> listar(){
        List<Producto> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM producto "
                + "ORDER BY id_producto";
        
        try {
            Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Producto producto = new Producto();
                
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecioVenta(rs.getDouble("precio_venta"));
                producto.setStock(rs.getInt("stock"));           
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