package dao;

import conexion.Conexion;
import modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;

public class ProductoDAO {
    
    public boolean insertar(Producto producto){
    
        String sql ="INSERT INTO producto(id_categoria, nombre, precio_venta, activo) "
                + "VALUES(?,?,?,?)";
        
        try (Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            )
            {
            
            ps.setInt(1,producto.getCategoria().getIdCategoria());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getPrecioVenta());
            ps.setBoolean(4, producto.getActivo());
            
            ps.executeUpdate(); 
            
            return true;
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean actualizar(Producto producto){
        
        String sql ="UPDATE producto "
                + "SET id_categoria=?, nombre=?, precio_venta=?, activo=? "
                + "WHERE id_producto=?";
        
        try (
                Connection cn = Conexion.getConexion();
                PreparedStatement ps = cn.prepareStatement(sql)
            ){
            
            ps.setInt(1, producto.getCategoria().getIdCategoria());
            ps.setString(2,producto.getNombre());
            ps.setDouble(3, producto.getPrecioVenta());
            ps.setBoolean(4, producto.getActivo());
            ps.setInt(5, producto.getIdProducto());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }        
    }
    
    public boolean eliminar(int idProducto){
        
        String sql = "UPDATE producto "
                + "SET activo = false "
                + "WHERE id_producto=?";
        
        try (
                Connection cn = Conexion.getConexion(); 
                PreparedStatement ps = cn.prepareStatement(sql);
            ){
            ps.setInt(1, idProducto);            
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public Producto buscar(int idProducto){
        
        String sql = "SELECT p.*, c.nombre AS categoria "
                + "FROM producto p INNER JOIN categoria c "
                + "ON p.id_categoria = c.id_categoria "
                + "WHERE p.id_producto=?";
        
        try (
            Connection cn = Conexion.getConexion();  
            PreparedStatement ps = cn.prepareStatement(sql);
                ){
            
            ps.setInt(1, idProducto);
            
            try(ResultSet rs = ps.executeQuery()){
            
            if (rs.next()) {
                Producto producto = new Producto();
                
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("categoria"));
                
                producto.setCategoria(categoria);
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecioVenta(rs.getDouble("precio_venta"));
                producto.setActivo(rs.getBoolean("activo")); 
                   
                return producto;        
            }
            }
            
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
    
    public List<Producto> listar(){
        List<Producto> lista = new ArrayList<>();
        
        String sql = "SELECT p.*, c.nombre AS categoria "
                + "FROM producto p INNER JOIN categoria c "
                + "ON p.id_categoria = c.id_categoria "
                + "ORDER BY p.id_producto";
        
        try (Connection cn = Conexion.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery())
            {
            while(rs.next()){
                Producto producto = new Producto();
                
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("categoria"));
                
                producto.setCategoria(categoria);
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecioVenta(rs.getDouble("precio_venta"));
                producto.setActivo(rs.getBoolean("activo"));           
                
                lista.add(producto);
            }
            
            rs.close();
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }
}